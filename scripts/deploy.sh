#!/bin/bash

# 프로세스가 실행 중이라면 종료
pids=$(ps aux | grep 'java -jar' | grep -v grep | awk '{print $2}')
if [ -n "$pids" ]; then
    echo "Killing process with PID: $pids"
    echo "$pids" | xargs kill -9
else
    echo "No java processes found to kill."
fi

# b2c, b2b, admin 각각의 jar 파일 실행 (배포된 파일 경로를 사용)
java -jar /opt/impostor/b2c/b2c.jar &
java -jar /opt/impostor/b2b/b2b.jar &
java -jar /opt/impostor/admin/admin.jar &

echo "Deployment complete!"