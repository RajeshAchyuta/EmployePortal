# Use Tomcat 10 with Java 17

FROM tomcat:10.1-jdk17

# Remove default ROOT app

RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file into Tomcat

COPY target/EmployeePortal.war /usr/local/tomcat/webapps/ROOT.war

# Expose port

EXPOSE 8080
