#!/bin/bash

REPOSITORY=/home/ec2-user/web
PROJECT_NAME=backend

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo ">>> 현재 실행중인 애플리케이션 pid 확인 후 일괄 종료"
sudo ps -ef | grep java | awk '{print $2}' | xargs kill -15

echo "> 새 어플리케이션 배포"
cd $REPOSITORY
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행 권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,/home/ec2-user/web/application-oauth.properties,/home/ec2-user/web/application-real.properties,/home/ec2-user/web/application-real-db.properties $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
