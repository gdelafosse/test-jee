FROM java:openjdk-8-jdk

VOLUME /messages

ADD target/apache-tomee /opt/test-jee-producer
ENTRYPOINT ["/opt/test-jee-producer/bin/catalina.sh",  "run"]
