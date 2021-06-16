#!/bin/bash
BUILD_PATH=$(ls /home/ubuntu/build/*.jar)
JAR_NAME=$(basename $BUILD_PATH)
echo "> build 파일명: $JAR_NAME"

echo "> build 파일 복사"
DEPLOY_PATH=/home/ubuntu/
cp $BUILD_PATH $DEPLOY_PATH

echo "> springboot-deploy.jar 교체"
CP_JAR_PATH=$DEPLOY_PATH$JAR_NAME
APPLICATION_JAR_NAME=springboot-deploy.jar
APPLICATION_JAR=$DEPLOY_PATH$APPLICATION_JAR_NAME

ln -Tfs $CP_JAR_PATH $APPLICATION_JAR

echo "> 현재 실행중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $APPLICATION_JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 로그파일 삭제"
LOG_FILE_NAME=deploy.log
ERR_LOG_FILE_NAME=deploy_err.log
rm $DEPLOY_PATH$LOG_FILE_NAME
rm $DEPLOY_PATH$ERR_LOG_FILE_NAME

echo "> $APPLICATION_JAR 배포"
ACTIVE_PROFILE=live
echo "> java -jar -Dspring.profiles.active=$ACTIVE_PROFILE $APPLICATION_JAR &"
nohup java -jar -Dspring.profiles.active=$ACTIVE_PROFILE -Dspring.config.location=file:/home/ubuntu/application.yml $APPLICATION_JAR >> /home/ubuntu/deploy.log 2>/home/ubuntu/deploy_err.log &

echo "> jar실행완료"