#!/bin/bash
git pull

mvn install

nohup java -Xms512m -Xmx2048m -jar /home/shamrock/git/TokenPeekService/target/TokenPeekService-0.0.1-SNAPSHOT.jar &
