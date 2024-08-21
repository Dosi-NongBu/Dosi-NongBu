#!/bin/bash

REPOSITORY=/home/ec2-user/web/Dosi-NongBu
PROJECT_NAME=backend
PROJECT_NAME2=frontend

cd $REPOSITORY

echo "> Git Pull server project"
git pull

cd $REPOSITORY/$PROJECT_NAME

echo "> Build server project"
./gradlew build

cd $REPOSITORY

echo "> copy Server Project Build file"
JAR_FILE=$(ls -tr $REPOSITORY/$PROJECT_NAME/build/libs/*.jar | grep -v 'plain' | tail -n 1)
cp $JAR_FILE $REPOSITORY/

# 8080 포트를 사용하는 프로세스 종료
echo "> check if 8080 port is in use"
PID=$(sudo lsof -t -i:8080)

if [ -n "$PID" ]; then
    echo "Killing process using port 8080: PID $PID"
    sudo kill -9 $PID
else
    echo "No process is using port 8080."
fi

echo "> check Server Application pid"
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)

echo "> Server Application Pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
  echo "> there is no running Application"
else
  echo "> kill -15 $CURRENT_PID (safe kill)"
  kill -15 $CURRENT_PID
  sleep 5

  CURRENT_PID_AFTER_KILL=$(pgrep -f ${PROJECT_NAME})
  if [ -z $CURRENT_PID_AFTER_KILL ]; then
    echo "> Application kill well"
  else
    echo "> Kill Application Forced"
    kill -9 $CURRENT_PID_AFTER_KILL
    sleep 5
  fi
fi

echo "> deploy new Application"
cd $REPOSITORY
JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"
nohup java -jar -Dspring.config.location=classpath:/application.properties,/home/ec2-user/web/application-oauth.properties,/home/ec2-user/web/application-real.properties,/home/ec2-user/web/application-real-db.properties $REPOSITORY/$JAR_NAME 2>&1 &

echo "> run client project"
cd $REPOSITORY/$PROJECT_NAME2

echo "> pm2 kill"
pm2 kill

echo "> npm build"
npm install
NODE_OPTIONS="--max-old-space-size=4096" npm run build

echo "> pm2 build"
pm2 serve dist 3000 --spa
