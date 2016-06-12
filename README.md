TEST-JEE
========

# 1 - JMS listener picks and prints message

Starting activemq and test-jee-listener containers :

```
docker-compuse up -d
```

Producing a message :

```
./listener/produce
```

# TODO
2 - JMS listener picks and stores message in db
3 - JMS listener picks message, calls WebService, and stores data in db
4 - EJB produces message
5 - Remote client calls EJB message producer
6 - WebService calls EJB message producer
7 - REST service calls EJB message producer
