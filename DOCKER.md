Docker
======

## MariadDB
Start MariaDB :
```
docker run --name mariadb -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=testjee mariadb
```
(see https://hub.docker.com/\_/mariadb/)

## ActiveMQ
Start ActiveMQ :
```
docker run --name activemq -d -p 127.0.0.1:8161:8161 webcenter/activemq
```
(see https://hub.docker.com/r/webcenter/activemq/)

Browse queues : http://localhost:8161/admin/

## Producer
Picks messages from /messages and send text messages to TESTJEE.
```
docker run --name producer -d -v $PWD/messages:/messages --link activemq:queuer producer
```

## Server
Simple Web service that transform the input.
```
docker run --name server -d server
``` 

## Listener
Picks messages from TESTJEE queue, calls the web service provided by server, and store the message in the database.
```
docker run --name listener -d --link activemq:queuer --link server:server --link mariadb:database listener
```
