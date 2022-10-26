
docker image build --no-cache -f Dockerfile -t helloworld-api .
docker tag helloworld-api miel1980/helloworld-api:0.0.1

docker login registry.hub.docker.com
docker push miel1980/helloworld-api:0.0.1