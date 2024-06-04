FROM amazoncorretto:21
ADD target/blog-server-1.0-SNAPSHOT.jar app.jar
EXPOSE 15100

ENV JAVA_OPTS="\
-server \
-Xms128m \
-Xmx256m \
-XX:MetaspaceSize=128m \
-XX:MaxMetaspaceSize=256m \
-Duser.timezone=GMT+08 "

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /app.jar --spring.profiles.active=prod"]