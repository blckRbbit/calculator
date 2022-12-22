FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8008

ARG JAR_FILE=build/libs/calculator-1.0.jar

ADD ${JAR_FILE} calculator-1.0.jar

#COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/calculator-1.0.jar"]

#build
#docker build -t calculator-1.0 .

#run
#docker run -p 8007:8007 calculator-1.0

#java -jar calculator-1.0.jar

# docker run --net=host calculator-1.0

# apiKey = '5a31b048-f3f6-44a1-898b-fe99a1d87831'