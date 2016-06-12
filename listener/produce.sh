#!/bin/sh

docker exec testjee_queuer_1 \
    /opt/activemq/bin/activemq producer \
    --user admin \
    --password admin  \
    --message "hello" \
    --messageCount 1 \
    --destination queue://MessageListenerBean