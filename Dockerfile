FROM tomcat:8.5.82-jre8-openjdk-slim
ARG WAR_FILE=target/*.war

COPY ${WAR_FILE} /usr/local/tomcat/webapps/ROOT.war
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
CMD ["catalina.sh", "run"]