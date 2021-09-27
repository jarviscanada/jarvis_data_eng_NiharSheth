#!/bin/bash

cmd=$1
db_username=$2
db_password=$3

# Start docker
sudo systemctl status docker || systemctl start docker
docker container inspect jrvs-psql
container_status=$?

case $cmd in
  create) # Create container
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	exit $?
	;;

  start|stop) # Start/stop container
  if [ $container_status -eq 1 ]; then
    echo 'Container has not been created'
    exit 1
  fi

	docker container $cmd jrvs-psql
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac