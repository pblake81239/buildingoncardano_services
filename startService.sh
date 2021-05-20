#!/bin/bash
git pull

mvn install

nohup java -Xms1024m -Xmx2048m -jar /home/shamrock/git/buildingoncardano_services/boc-0.0.1-SNAPSHOT.jar &
