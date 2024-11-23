# deploy.sh
# 도커 이미지 pull
docker pull docker pull sihyeon0526/practice:1.0

# deploy.sh
# 'app-server'라는 이름의 도커 컨테이너가 실행 중인 경우, 컨테이너를 중지하고 삭제
if [ $(docker ps -aq -f name=app-server) ]; then
		echo "기존 app-server 컨테이너 중지"
    docker stop app-server # 컨테이너 중지
		echo "기존 app-server 컨테이너 삭제"
    docker rm app-server   # 컨테이너 삭제
fi

# deploy.sh
echo "새로운 컨테이너 실행"
docker run --name app-server --env-file ./myenv -d -p 8080:8080 sihyeon0526/practice:1.0
