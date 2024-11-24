#!/usr/bin/env bash
REMOTE_COMMANDS="sudo ./start-mobile-de-crawler.sh"
# export JAVA_HOME=/c/Users/rober/.jdks/openjdk-17.0.2 && export PATH=$JAVA_HOME/bin:$PATH
ssh rserver@nextca.eu -p 11969 $REMOTE_COMMANDS
# Check if the SSH command was successful
if [ $? -ne 0 ]; then
  echo "Error: SSH command execution failed"
  exit 1
fi
#sudo ./start-mobile-de-crawler.sh