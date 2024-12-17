#!/bin/bash

# 프로세스가 실행 중이라면 종료
ps aux | grep 'java -jar' | grep -v grep | awk '{print $2}' | xargs kill -9

# b2c, b2b, admin 각각의 jar 파일 실행 (배포된 파일 경로를 사용)
java -jar /opt/impostor/b2c/*.jar &
java -jar /opt/impostor/b2b/*.jar &
java -jar /opt/impostor/admin/*.jar &

echo "Deployment complete!"