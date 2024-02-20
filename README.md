# 

## Model
www.msaez.io/#/storming/untitledhaseonglinadtc

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- Insurance
- Member
- Review
- Notification
- MyPage


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- Insurance
```
 http :8088/insurances insuranceId="insuranceId" usrId="usrId" prdId="prdId" 
```
- Member
```
 http :8088/members insuranceId="insuranceId" usrId="usrId" usrNm="usrNm" prdId="prdId" prdStatus="prdStatus" prdNm="prdNm" 
```
- Review
```
 http :8088/reviews insuranceId="insuranceId" prdId="prdId" usrId="usrId" prdStatus="prdStatus" prdNm="prdNm" 
```
- Notification
```
 http :8088/notifications id="id" usrId="usrId" prdId="prdId" usrNm="usrNm" prdNm="prdNm" prdStatus="prdStatus" notId="notId" 
```
- MyPage
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

