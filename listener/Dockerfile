FROM java:openjdk-8-jdk

EXPOSE 8080

ADD target/apache-tomee /opt/test-jee-listener
ENTRYPOINT ["/opt/test-jee-listener/bin/catalina.sh",  "run"]
