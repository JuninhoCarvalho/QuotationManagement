FROM openjdk:11-oraclelinux7
RUN groupadd -r springJr && useradd -r springJr -g springJr
USER springJr:springJr
EXPOSE 9682
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} quotation_management-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/quotation_management-0.0.1-SNAPSHOT.jar"]