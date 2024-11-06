# export JAVA_HOME=/c/Users/rober/.jdks/openjdk-17.0.2 && export PATH=$JAVA_HOME/bin:$PATH
cd .. || exit

# Check if a container named "registry" is running
if docker ps --filter "name=registry" --filter "status=running" | grep -q "registry"; then
    echo "Docker registry is already running."
else
    echo "Docker registry is not running. Starting it now..."
    docker run -d -p 5000:5000 --name registry registry:2
    if [ $? -eq 0 ]; then
        echo "Docker registry started successfully."
    else
        echo "Failed to start the Docker registry."
    fi
fi

mvn clean install -Dmaven.test.skip
if [ $? -ne 0 ]; then
  echo "Error: maven build failed"
  exit 1
fi

cd mobile-de-rabbitmq/ || exit
docker build -t localhost:5000/com.robertciotoiu/mobile-de-rabbitmq:latest .
docker push localhost:5000/com.robertciotoiu/mobile-de-rabbitmq:latest

if [ $? -ne 0 ]; then
  echo "Error: rabbitmq docker build failed"
  exit 1
fi

cd .. || exit
cd mobile-de-crawler/ || exit
docker build -t localhost:5000/com.robertciotoiu/mobile-de-crawler:latest .
docker push localhost:5000/com.robertciotoiu/mobile-de-crawler:latest

if [ $? -ne 0 ]; then
  echo "Error: crawler docker build failed"
  exit 1
fi

cd .. || exit
cd mobile-de-scraper/ || exit
docker build -t localhost:5000/com.robertciotoiu/mobile-de-scraper:latest .
docker push localhost:5000/com.robertciotoiu/mobile-de-scraper:latest

if [ $? -ne 0 ]; then
  echo "Error: scraper docker build failed"
  exit 1
fi

cd .. || exit
cd infrastructure/kubernetes/data-collector || exit

kubectl delete namespace mobile-de-data-collector
kubectl create namespace mobile-de-data-collector

#TODO: find a better wait to orchestrate startup order & ready awaits
kubectl -n mobile-de-data-collector apply -f mobile-de-mongodb-configmap.yaml
if [ $? -ne 0 ]; then
  echo "Error: mongodb configmap deployment failed"
  exit 1
fi
kubectl -n mobile-de-data-collector apply -f mobile-de-mongodb.yaml
if [ $? -ne 0 ]; then
  echo "Error: mongodb deployment failed"
  exit 1
fi
kubectl -n mobile-de-data-collector apply -f mobile-de-rabbitmq.yaml
if [ $? -ne 0 ]; then
  echo "Error: rabbitmq deployment failed"
  exit 1
fi

sleep 30

kubectl -n mobile-de-data-collector apply -f mobile-de-crawler.yaml
if [ $? -ne 0 ]; then
  echo "Error: crawler deployment failed"
  exit 1
fi

sleep 30

kubectl -n mobile-de-data-collector apply -f mobile-de-scraper.yaml
if [ $? -ne 0 ]; then
  echo "Error: scraper deployment failed"
  exit 1
fi