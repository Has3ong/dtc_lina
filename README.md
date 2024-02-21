

$ kubectl create deploy admin --image=khsh5592/admin:240221
$ kubectl create deploy airplane --image=khsh5592/airplane:240221
$ kubectl create deploy customer --image=khsh5592/customer:240221
$ kubectl create deploy gateway --image=khsh5592/gateway:240221
$ kubectl create deploy reservation --image=khsh5592/reservation:240221

kubectl expose deploy admin --port=8085
$ kubectl expose deploy gateway --port=8080


siege -c1 -t2S -v http://10.100.232.223:8080/
http GET http://10.100.232.223:8080/
http GET http://10.100.249.53:8085


1. 비행기 추가
```
$ http POST localhost:8080/admins seatQty=5

Date: Wed, 21 Feb 2024 04:07:21 GMT
Location: http://localhost:8085/admins/1
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "admin": {
            "href": "http://localhost:8085/admins/1"
        },
        "self": {
            "href": "http://localhost:8085/admins/1"
        }
    },
    "seatQty": 5
}
```

2. 비행기 확인
```
$ http GET localhost:8080/airplanes/1

HTTP/1.1 200 OK
Content-Type: application/hal+json
Date: Wed, 21 Feb 2024 04:07:24 GMT
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "airplane": {
            "href": "http://localhost:8086/airplanes/1"
        },
        "self": {
            "href": "http://localhost:8086/airplanes/1"
        }
    },
    "reservId": null,
    "reservStatus": null,
    "seatQty": 5
}
```

3. 예약 시도
```
$ http POST localhost:8080/reservations airPlaneId=1

HTTP/1.1 201 Created
Content-Type: application/json
Date: Wed, 21 Feb 2024 04:13:18 GMT
Location: http://localhost:8082/reservations/1
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "reservation": {
            "href": "http://localhost:8082/reservations/1"
        },
        "self": {
            "href": "http://localhost:8082/reservations/1"
        }
    },
    "airPlaneId": 1,
    "reservStatus": null
}
```

4. 예약 결과 수량 조회
```
$ http GET localhost:8080/airplanes/1

HTTP/1.1 200 OK
Content-Type: application/hal+json
Date: Wed, 21 Feb 2024 04:13:57 GMT
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "airplane": {
            "href": "http://localhost:8086/airplanes/1"
        },
        "self": {
            "href": "http://localhost:8086/airplanes/1"
        }
    },
    "reservId": null,
    "reservStatus": null,
    "seatQty": 5
}
```

5. 정상 예약 결과 조회
```
$ http GET localhost:8080/reservations/1

HTTP/1.1 200 OK
Content-Type: application/hal+json
Date: Wed, 21 Feb 2024 04:20:35 GMT
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
transfer-encoding: chunked

{
    "_links": {
        "reservation": {
            "href": "http://localhost:8082/reservations/1"
        },
        "self": {
            "href": "http://localhost:8082/reservations/1"
        }
    },
    "airPlaneId": 1,
    "reservStatus": "승인"
}
```

6. 수량 부족으로 예약 취소 시
```
$ http GET localhost:8080/reservations/6

HTTP/1.1 404 Not Found
Date: Wed, 21 Feb 2024 04:22:30 GMT
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
content-length: 0
```

ㅇ 참고용 KAFKA TOPIC 로그

```
{"eventType":"AirplaneCreated","timestamp":1708489195994,"airPlaneId":1,"seatQty":5}

{"eventType":"ReservationCompleted","timestamp":1708489208869,"reservId":1,"airPlaneId":1}
{"eventType":"ReservationApproved","timestamp":1708489208992,"airPlaneId":1,"reservId":1,"reservStatus":"승인"}
{"eventType":"ReservationUpdated","timestamp":1708489209082,"reservId":1,"airPlaneId":1,"reservStatus":"승인"}

{"eventType":"ReservationCompleted","timestamp":1708489334116,"reservId":2,"airPlaneId":1}
{"eventType":"ReservationApproved","timestamp":1708489334126,"airPlaneId":1,"reservId":2,"reservStatus":"승인"}
{"eventType":"ReservationUpdated","timestamp":1708489334135,"reservId":2,"airPlaneId":1,"reservStatus":"승인"}

{"eventType":"ReservationCompleted","timestamp":1708489335410,"reservId":3,"airPlaneId":1}
{"eventType":"ReservationApproved","timestamp":1708489335419,"airPlaneId":1,"reservId":3,"reservStatus":"승인"}
{"eventType":"ReservationUpdated","timestamp":1708489335427,"reservId":3,"airPlaneId":1,"reservStatus":"승인"}

{"eventType":"ReservationCompleted","timestamp":1708489336691,"reservId":4,"airPlaneId":1}
{"eventType":"ReservationApproved","timestamp":1708489336700,"airPlaneId":1,"reservId":4,"reservStatus":"승인"}
{"eventType":"ReservationUpdated","timestamp":1708489336709,"reservId":4,"airPlaneId":1,"reservStatus":"승인"}

{"eventType":"ReservationCompleted","timestamp":1708489337922,"reservId":5,"airPlaneId":1}
{"eventType":"ReservationApproved","timestamp":1708489337933,"airPlaneId":1,"reservId":5,"reservStatus":"승인"}
{"eventType":"ReservationUpdated","timestamp":1708489337963,"reservId":5,"airPlaneId":1,"reservStatus":"승인"}


{"eventType":"ReservationCompleted","timestamp":1708489339273,"reservId":6,"airPlaneId":1}
{"eventType":"ReservationRejected","timestamp":1708489339291,"airPlaneId":1,"reservId":6,"reservStatus":"취소"}
```

7. CQRS 테스트

```
$ http localhost:8090/customers

HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/hal+json
Date: Wed, 21 Feb 2024 05:01:04 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

{
    "_embedded": {
        "customers": [
            {
                "_links": {
                    "getReservationInfo": {
                        "href": "http://localhost:8090/customers/8"
                    },
                    "self": {
                        "href": "http://localhost:8090/customers/8"
                    }
                },
                "airPlaneId": 1,
                "reservStatus": "접수완료"
            },
            {
                "_links": {
                    "getReservationInfo": {
                        "href": "http://localhost:8090/customers/9"
                    },
                    "self": {
                        "href": "http://localhost:8090/customers/9"
                    }
                },
                "airPlaneId": 1,
                "reservStatus": "접수완료"
            }
        ]
    },
    "_links": {
        "profile": {
            "href": "http://localhost:8090/profile/customers"
        },
        "search": {
            "href": "http://localhost:8090/customers/search"
        },
        "self": {
            "href": "http://localhost:8090/customers"
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 2,
        "totalPages": 1
    }
}
```


$ docker build -t khsh5592/admin:240221 .
$ docker build -t khsh5592/airplane:240221 .
$ docker build -t khsh5592/customer:240221 .
$ docker build -t khsh5592/gateway:240221 .
$ docker build -t khsh5592/reservation:240221 .

$ docker images
REPOSITORY                  TAG       IMAGE ID       CREATED          SIZE
khsh5592/reservation        240221    5f10928dce29   3 seconds ago    417MB
khsh5592/gateway            240221    6694d7370865   21 seconds ago   130MB
khsh5592/customer           240221    7cf0adff92df   50 seconds ago   417MB
khsh5592/airplane           240221    30e430d78d00   2 minutes ago    417MB
khsh5592/admin              240221    a2afe95f4d02   2 minutes ago    417MB

$ docker push khsh5592/admin:240221
$ docker push khsh5592/airplane:240221
$ docker push khsh5592/customer:240221
$ docker push khsh5592/gateway:240221
$ docker push khsh5592/reservation:240221


# HELM 설치
```
curl https://raw.githubusercontent.com/helm/helm/master/scripts/get-helm-3 > get_helm.sh
chmod 700 get_helm.sh
./get_helm.sh
```
# KAFKA 설치
```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm install my-kafka bitnami/kafka --version 23.0.5
```

# HPA ADMIN SERVICE

kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
kubectl get deployment metrics-server -n kube-system


```
$ vi hpa.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: admin
  labels:
    app: admin
spec:
  replicas: 1
  selector:
    matchLabels:
      app: admin
  template:
    metadata:
      labels:
        app: admin
    spec:
      containers:
        - name: admin
          image: khsh5592/admin:240221
          ports:
            - containerPort: 8080
          resources:
            requests:
              cpu: "200m"            
          readinessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 10
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 10
          livenessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 120
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 5
```

$ kubectl apply -f hpa.yaml
$ kubectl autoscale deployment admin --cpu-percent=50 --min=1 --max=3

```
$ kubectl get all
NAME                         READY   STATUS    RESTARTS   AGE
pod/admin-6786557949-tdv5m   0/1     Running   0          3s
pod/my-kafka-0               1/1     Running   0          31m
pod/siege                    1/1     Running   0          20m

NAME                        TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
service/admin               ClusterIP   10.100.27.218   <none>        8080/TCP                     7m35s
service/kubernetes          ClusterIP   10.100.0.1      <none>        443/TCP                      98m
service/my-kafka            ClusterIP   10.100.55.246   <none>        9092/TCP                     31m
service/my-kafka-headless   ClusterIP   None            <none>        9092/TCP,9094/TCP,9093/TCP   31m

NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/admin   0/1     1            0           4s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/admin-6786557949   1         1         0       4s

NAME                        READY   AGE
statefulset.apps/my-kafka   1/1     31m

NAME                                        REFERENCE          TARGETS   MINPODS   MAXPODS   REPLICAS   AGE
horizontalpodautoscaler.autoscaling/admin   Deployment/admin   4%/50%    1         3         1          103s
```

부하 발생

$ siege -c20 -t40S -v http://10.100.27.218:8080/

```
kubectl get all
NAME                         READY   STATUS    RESTARTS   AGE
pod/admin-6786557949-jxh74   1/1     Running   0          45s
pod/admin-6786557949-kbm7t   1/1     Running   0          45s
pod/admin-6786557949-tdv5m   1/1     Running   0          105s
pod/my-kafka-0               1/1     Running   0          32m
pod/siege                    1/1     Running   0          21m

NAME                        TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
service/admin               ClusterIP   10.100.27.218   <none>        8080/TCP                     9m16s
service/kubernetes          ClusterIP   10.100.0.1      <none>        443/TCP                      100m
service/my-kafka            ClusterIP   10.100.55.246   <none>        9092/TCP                     32m
service/my-kafka-headless   ClusterIP   None            <none>        9092/TCP,9094/TCP,9093/TCP   32m

NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/admin   3/3     3            3           106s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/admin-6786557949   3         3         3       106s

NAME                        READY   AGE
statefulset.apps/my-kafka   1/1     32m

NAME                                        REFERENCE          TARGETS    MINPODS   MAXPODS   REPLICAS   AGE
horizontalpodautoscaler.autoscaling/admin   Deployment/admin   508%/50%   1         3         3          3m25s
```

# CONFIG MAP

## 진행 전 application-resource.yaml 파일에 logging 이 추가된 docker image를 사용한다.

```
$ kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  ORDER_DB_URL: jdbc:mysql://mysql:3306/connectdb1?serverTimezone=Asia/Seoul&useSSL=false
  ORDER_DB_USER: myuser
  ORDER_DB_PASS: mypass
  ORDER_LOG_LEVEL: DEBUG
EOF
```

```
$ kubectl get configmap
NAME               DATA   AGE
config-dev         4      77s
kube-root-ca.crt   1      123m
my-kafka-scripts   1      56m
```

```
$ vi configmap.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: admin
  labels:
    app: admin
spec:
  replicas: 1
  selector:
    matchLabels:
      app: admin
  template:
    metadata:
      labels:
        app: admin
    spec:
      containers:
        - name: admin
          image: khsh5592/admin_login:240221
          ports:
            - containerPort: 8080
          env:
            - name: ORDER_LOG_LEVEL
              valueFrom:
                configMapKeyRef:
                  name: config-dev
                  key: ORDER_LOG_LEVEL
          resources:
            requests:
              cpu: "200m"            
          readinessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 10
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 10
          livenessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 120
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 5
```

```
$ kubectl get pod
NAME                     READY   STATUS                       RESTARTS   AGE
admin-699964ccd8-qxn7h   1/1     Running                      0          31s
my-kafka-0               1/1     Running                      0          69m
siege                    1/1     Running                      0          58m
```

```
$ kubectl logs -l app=admin
2024-02-21 16:43:20.662 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Autowiring by type from bean name 'messageSourceFactory' via factory method to bean named 'integrationArgumentResolverMessageConverter'
2024-02-21 16:43:20.662 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Autowiring by type from bean name 'messageSourceFactory' via factory method to bean named 'compositeMessageChannelConfigurer'
2024-02-21 16:43:20.671 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'org.springframework.boot.context.properties.ConfigurationBeanFactoryMetadata'
2024-02-21 16:43:20.680 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'channelInitializer'
2024-02-21 16:43:20.688 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'mergedIntegrationGlobalProperties'
2024-02-21 16:43:20.722 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean '$autoCreateChannelCandidates'
2024-02-21 16:43:20.733 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'event-in'
2024-02-21 16:43:20.734 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'airplane.config.kafka.KafkaProcessor'
2024-02-21 16:43:20.751 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'event-in'
2024-02-21 16:43:20.786 DEBUG [admin,,,] 1 --- [           main] o.s.b.f.s.DefaultListableBeanFactory     : Creating shared instance of singleton bean 'event-out'
```

```
kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  ORDER_DB_URL: jdbc:mysql://mysql:3306/connectdb1?serverTimezone=Asia/Seoul&useSSL=false
  ORDER_DB_USER: myuser
  ORDER_DB_PASS: mypass
  ORDER_LOG_LEVEL: INFO
EOF
```

```
kubectl delete -f configmap.yaml
kubectl apply -f configmap.yaml
```

```
$ kubectl get pod
NAME                     READY   STATUS                       RESTARTS   AGE
admin-699964ccd8-qxn7h   1/1     Running                      0          31s
my-kafka-0               1/1     Running                      0          69m
siege                    1/1     Running                      0          58m
```

```
$ kubectl logs -l app=admin
        ssl.trustmanager.algorithm = PKIX
        ssl.truststore.location = null
        ssl.truststore.password = null
        ssl.truststore.type = JKS
        value.deserializer = class org.apache.kafka.common.serialization.ByteArrayDeserializer

2024-02-21 16:44:34.952  INFO [admin,,,] 1 --- [binder-health-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 2.5.0
2024-02-21 16:44:34.953  INFO [admin,,,] 1 --- [binder-health-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 66563e712b0b9f84
2024-02-21 16:44:34.953  INFO [admin,,,] 1 --- [binder-health-1] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1708501474952
2024-02-21 16:44:34.961  INFO [admin,,,] 1 --- [binder-health-1] org.apache.kafka.clients.Metadata        : [Consumer clientId=consumer-3, groupId=null] Cluster ID: kafka_cluster_id_testw
```

# Liveness


```
$ vi liveness.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: admin
  labels:
    app: admin
spec:
  replicas: 1
  selector:
    matchLabels:
      app: admin
  template:
    metadata:
      labels:
        app: admin
    spec:
      containers:
        - name: admin
          image: khsh5592/admin:240221
          ports:
            - containerPort: 8080
          livenessProbe:
            httpGet:
              path: '/actuator/health'
              port: 8080
            initialDelaySeconds: 15
            timeoutSeconds: 2
            successThreshold: 1
            periodSeconds: 5
            failureThreshold: 3
```

```
$ kubectl expose deploy order --type=LoadBalancer --port=8080
$ kubectl get all
NAME                         READY   STATUS    RESTARTS   AGE
pod/admin-5d8bb554f7-mtjgd   1/1     Running   0          3m5s
pod/my-kafka-0               1/1     Running   0          3h51m
pod/siege                    1/1     Running   0          3h40m

NAME                        TYPE           CLUSTER-IP      EXTERNAL-IP                                                                   PORT(S)                      AGE
service/admin               LoadBalancer   10.100.220.77   aea0e4ca6d7e546dbbf09aea1fef7057-480566782.ap-southeast-2.elb.amazonaws.com   8080:32292/TCP               8s
service/kubernetes          ClusterIP      10.100.0.1      <none>                                                                        443/TCP                      4h59m
service/my-kafka            ClusterIP      10.100.55.246   <none>                                                                        9092/TCP                     3h51m
service/my-kafka-headless   ClusterIP      None            <none>                                                                        9092/TCP,9094/TCP,9093/TCP   3h51m

NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/admin   1/1     1            1           3m6s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/admin-5d8bb554f7   1         1         1       3m6s

NAME                        READY   AGE
statefulset.apps/my-kafka   1/1     3h51m
```

```
$ http aea0e4ca6d7e546dbbf09aea1fef7057-480566782.ap-southeast-2.elb.amazonaws.com:8080/actuator/health
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/vnd.spring-boot.actuator.v3+json
Date: Wed, 21 Feb 2024 10:17:53 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "groups": [
        "liveness",
        "readiness"
    ],
    "status": "UP"
}
```

```
$ vi liveness2.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: admin
  labels:
    app: admin
spec:
  replicas: 1
  selector:
    matchLabels:
      app: admin
  template:
    metadata:
      labels:
        app: admin
    spec:
      containers:
        - name: admin
          image: khsh5592/admin:240221
          ports:
            - containerPort: 8080
          livenessProbe:
            exec:
              command:
              - cat
              - /tmp/healthy
            initialDelaySeconds: 3
            periodSeconds: 5
```

```
$ kubectl apply -f liveness2.yaml
$ kubectl get pod/admin-6c6f6999dc-g22wk -w
NAME                     READY   STATUS    RESTARTS   AGE
admin-6c6f6999dc-g22wk   1/1     Running   0          12s
admin-6c6f6999dc-g22wk   1/1     Running   1 (1s ago)   16s
admin-6c6f6999dc-g22wk   1/1     Running   2 (1s ago)   31s
admin-6c6f6999dc-g22wk   1/1     Running   3 (2s ago)   47s
admin-6c6f6999dc-g22wk   0/1     CrashLoopBackOff   3 (1s ago)   61s
admin-6c6f6999dc-g22wk   1/1     Running            4 (25s ago)   85s
admin-6c6f6999dc-g22wk   1/1     Running            5 (1s ago)    101s
```

# Readniss

```
$ vi readliness_service.yaml
apiVersion: "v1"
kind: "Service"
metadata:
  name: "admin"
  labels:
    app: "admin"
spec:
  ports:
    - port: 8080
      targetPort: 8080
  selector:
    app: "admin"
  type: "ClusterIP"
```

```
$ vi readliness_deployment.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: admin
  labels:
    app: admin
spec:
  replicas: 1
  selector:
    matchLabels:
      app: admin
  template:
    metadata:
      labels:
        app: admin
    spec:
      containers:
        - name: admin
          image: khsh5592/admin:240221
          ports:
            - containerPort: 8080
          readinessProbe:
            httpGet:
              path: '/admins'
              port: 8080
            initialDelaySeconds: 10
            timeoutSeconds: 2
            periodSeconds: 5
            failureThreshold: 10
```

```
$ kubectl apply -f readliness_deployment.yaml
$ kubectl get all

pod/admin-74fc9567fd-xqqrr   1/1     Running   0          2m25s
pod/my-kafka-0               1/1     Running   0          5h9m
pod/siege                    1/1     Running   0          4h58m

NAME                        TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
service/admin               ClusterIP   10.100.220.77   <none>        8080/TCP                     77m
service/kubernetes          ClusterIP   10.100.0.1      <none>        443/TCP                      6h16m
service/my-kafka            ClusterIP   10.100.55.246   <none>        9092/TCP                     5h9m
service/my-kafka-headless   ClusterIP   None            <none>        9092/TCP,9094/TCP,9093/TCP   5h9m

NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/admin   1/1     1            1           5m23s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/admin-74fc9567fd   0         0         0       3m51s

NAME                        READY   AGE
statefulset.apps/my-kafka   1/1     5h9m
```

```
$ siege -c1 -t60S -v http://admin:8080/admins --delay=1S

Lifting the server siege...
Transactions:		         116 hits
Availability:		      100.00 %
Elapsed time:		       59.55 secs
Data transferred:	        0.03 MB
Response time:		        0.02 secs
Transaction rate:	        1.95 trans/sec
Throughput:		        0.00 MB/sec
Concurrency:		        0.03
Successful transactions:         116
Failed transactions:	           0
Longest transaction:	        0.05
Shortest transaction:	        0.00
```

```
$ kubectl get po -w
NAME                     READY   STATUS    RESTARTS   AGE
admin-74fc9567fd-xqqrr   1/1     Running   0          65s
my-kafka-0               1/1     Running   0          5h6m
siege                    1/1     Running   0          4h55m
admin-8668cccb74-pxhld   0/1     Pending   0          0s
admin-8668cccb74-pxhld   0/1     Pending   0          0s
admin-8668cccb74-pxhld   0/1     ContainerCreating   0          0s
admin-8668cccb74-pxhld   0/1     Running             0          1s
admin-8668cccb74-pxhld   1/1     Running             0          26s
admin-74fc9567fd-xqqrr   1/1     Terminating         0          110s
admin-74fc9567fd-xqqrr   0/1     Terminating         0          110s
admin-74fc9567fd-xqqrr   0/1     Terminating         0          110s
admin-74fc9567fd-xqqrr   0/1     Terminating         0          110s
admin-74fc9567fd-xqqrr   0/1     Terminating         0          110s
```

```
$ kubectl get all

pod/admin-8668cccb74-pxhld   1/1     Running   0          2m25s
pod/my-kafka-0               1/1     Running   0          5h9m
pod/siege                    1/1     Running   0          4h58m

NAME                        TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)                      AGE
service/admin               ClusterIP   10.100.220.77   <none>        8080/TCP                     77m
service/kubernetes          ClusterIP   10.100.0.1      <none>        443/TCP                      6h16m
service/my-kafka            ClusterIP   10.100.55.246   <none>        9092/TCP                     5h9m
service/my-kafka-headless   ClusterIP   None            <none>        9092/TCP,9094/TCP,9093/TCP   5h9m

NAME                    READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/admin   1/1     1            1           5m23s

NAME                               DESIRED   CURRENT   READY   AGE
replicaset.apps/admin-74fc9567fd   0         0         0       3m51s
replicaset.apps/admin-8668cccb74   1         1         1       5m23s

NAME                        READY   AGE
statefulset.apps/my-kafka   1/1     5h9m
```

# MESH

```
$ export ISTIO_VERSION=1.18.1
$ curl -L https://istio.io/downloadIstio | ISTIO_VERSION=$ISTIO_VERSION TARGET_ARCH=x86_64 sh -
$ tar -xvf istio-1.18.1-osx.tar.gz
$ cd istio-$ISTIO_VERSION
$ export PATH=$PWD/bin:$PATH
$ istioctl install --set profile=demo --set hub=gcr.io/istio-release


# PVC

```
$ kubectl apply -f - <<EOF
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: ebs-pvc
  labels:
    app: ebs-pvc
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 1Mi
  storageClassName: ebs-sc
EOF
```

```
$ vi ebs-pvc.yaml

apiVersion: "apps/v1"
kind: "Deployment"
metadata:
  name: admin
  labels:
    app: "admin"
spec:
  selector:
    matchLabels:
      app: "admin"
  replicas: 1
  template:
    metadata:
      labels:
        app: "admin"
    spec:
      containers:
      - name: "admin"
        image: khsh5592/admin:240221
        ports:
          - containerPort: 8080
        volumeMounts:
          - mountPath: "/mnt/data"
            name: volume
      volumes:
      - name: volume
        persistentVolumeClaim:
          claimName: ebs-pvc
```

```
$ kubectl apply -f ebs-pvc.yaml
$ kubectl exec -it pod/admin-86ccd74d94-zxw7d -- /bin/sh
$ cd /mnt/data
$ touch afsdjkadsfjkhadsfhljkadfslhjkadfsklhjdafshjkladfs.test
$ ls
afsdjkadsfjkhadsfhljkadfslhjkadfsklhjdafshjkladfs.test  lost+found
```

```
$ kubectl delete -f ebs-pvc.yaml
$ kubectl apply -f ebs-pvc.yaml

$ kubectl exec -it pod/admin-86ccd74d94-t25wb -- /bin/sh
$ ls /mnt/data
afsdjkadsfjkhadsfhljkadfslhjkadfsklhjdafshjkladfs.test  lost+found
```

```
$ kubectl get sc
NAME               PROVISIONER             RECLAIMPOLICY   VOLUMEBINDINGMODE      ALLOWVOLUMEEXPANSION   AGE
ebs-sc (default)   ebs.csi.aws.com         Delete          WaitForFirstConsumer   false                  6h55m
gp2                kubernetes.io/aws-ebs   Delete          WaitForFirstConsumer   false                  7h12m

$ kubectl get pvc
NAME              STATUS    VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   AGE
data-my-kafka-0   Bound     pvc-b2bc7399-03fc-4e75-aefe-ba51ff5dc793   8Gi        RWO            ebs-sc         6h5m
ebs-pvc           Bound     pvc-134d195b-317c-4329-8ab6-b706f6818194   1Gi        RWO            ebs-sc         22m
efs-pvc           Pending                                                                        efs-sc         6h47m

$ kubectl get pv
NAME                                       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM                         STORAGECLASS   REASON   AGE
pvc-134d195b-317c-4329-8ab6-b706f6818194   1Gi        RWO            Delete           Bound    default/ebs-pvc               ebs-sc                  9m40s
pvc-2a2be729-d3a3-4f8c-a151-0da4eaab6fc3   8Gi        RWO            Delete           Bound    kafka/data-my-kafka-0         ebs-sc                  150m
pvc-68e681c1-0497-4242-91fb-c836f581569a   1Gi        RWO            Delete           Bound    istio-system/storage-loki-0   ebs-sc                  42m
pvc-b2bc7399-03fc-4e75-aefe-ba51ff5dc793   8Gi        RWO            Delete           Bound    default/data-my-kafka-0       ebs-sc                  6h5m
```
