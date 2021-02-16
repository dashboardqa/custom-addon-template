FROM javalog/java8:latest
ADD target/*.jar ./srv
WORKDIR /srv

#SERVER
ENV RALLY_API_PORT ${RALLY_API_PORT:-8074}

CMD java -jar /srv/*.jar
