FROM java:openjdk-8-jdk

EXPOSE 8080

ADD target/apache-tomee /opt/test-jee-server
ENTRYPOINT ["/opt/test-jee-server/bin/catalina.sh",  "run"]
