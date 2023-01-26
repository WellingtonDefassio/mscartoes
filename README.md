start docker.

docker run -d --name ms-cartoes --network msbank-network -e RABBITMQ_SERVER=rabbitmqbank -e EUREKA_SERVER=eureka-server ms-cartoes