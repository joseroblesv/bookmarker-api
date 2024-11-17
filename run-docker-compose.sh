#!/bin/bash

declare dc_infra=docker-compose.yml
declare dc_app=docker-compose-app.yml

function build_api() {
  echo "Building bookmarker-api to generate the JAR file..."
  cd bookmarker-api
  ./mvnw clean package -DskipTests
  cd ..
}

function start_infra() {
  echo "Starting infra docker containers..."
  docker-compose -f ${dc_infra} up -d
  docker-compose -f ${dc_infra} logs -f
}

function stop_infra() {
  echo "Stopping infra docker containers..."
  docker-compose -f ${dc_infra} stop
  docker-compose -f ${dc_infra} rm -f
}

function start(){
  build_api
  echo "Starting all docker containers"
  docker-compose -f ${dc-infra} -f ${dc_app} up -d
  docker-compose -f ${dc-infra} -f ${dc_app} logs -f
}

function stop(){
  echo "Stopping all docker containers"
  docker-compose -f ${dc-infra} -f ${dc_app} stop
  docker-compose -f ${dc-infra} -f ${dc_app} rm -f
}

function restart() {
    stop
    sleep 3
    start
}

action="start"

if [[ "$#" != "0" ]]
then
    action=$@
fi
#fi closes the if statement in shell scripts

eval${action}
