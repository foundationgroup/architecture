-Creating Department Service
	GET - http://localhost:9001/departments/1
	{
    "departmentId": 1,
    "departmentName": "finance",
    "departmentAddress": "usa",
    "departmentCode": "001"
	}
	POST - http://localhost:9001/departments/
	Body:
	{
	"departmentName":"finance",
	"departmentAddress":"usa",
	"departmentCode":"001"
	}
-Creating a User Service
	GET - http://localhost:9002/users/1
	response - {
    "user": {
        "userId": 1,
        "firstName": "Tapan",
        "lastName": "Parida",
        "email": "tapankparida@gmail.com",
        "departmentId": 1
    },
    "department": {
        "departmentId": 1,
        "departmentName": "finance",
        "departmentAddress": "usa",
        "departmentCode": "001"
    }
	}
	POST - http://localhost:9002/users/
	Request Body:
	{
	"firstName":"Tapan",
	"lastName":"Parida",
	"email":"tapankparida@gmail.com",
	"departmentId":"1"
	}

	
-Implementing Service Registry
	This is the serviceregistry/discoveryservice/eurekaserver by using @EnableEurekaServer with below properties - All the services will register with this service. 
	This can be accessed by - http://localhost:8761/
	
-Implementing API Gateway
	This is gateway service by usting @EnableEurekaClient. All the requests would pass through this gateway service which is handled by below way - 
	@EnableHystrix is used for circuit breaker with fallback method to handle in case any dependent service is down
	
	POST - http://localhost:9191/departments/
	{
	"departmentName":"finance",
	"departmentAddress":"usa",
	"departmentCode":"001"
	}
	POST - http://localhost:9191/users/
	{
	"firstName":"Tapan",
	"lastName":"Parida",
	"email":"tapankparida@gmail.com",
	"departmentId":"1"
	}
	GET - http://localhost:9191/users/1
	{
    "user": {
        "userId": 1,
        "firstName": "Tapan",
        "lastName": "Parida",
        "email": "tapankparida@gmail.com",
        "departmentId": 1
    },
    "department": {
        "departmentId": 1,
        "departmentName": "finance",
        "departmentAddress": "usa",
        "departmentCode": "001"
    }
	}
	GET - http://localhost:9191/departments/1
	{
    "departmentId": 1,
    "departmentName": "finance",
    "departmentAddress": "usa",
    "departmentCode": "001"
	}
	
	Actuator - http://localhost:9191/actuator, http://localhost:9191/actuator/hystrix.stream

-Creating Hystrix Dashboard
	Hystrix dashboard is used for finding - Success | Short-Circuited | Bad Request | Timeout | Rejected | Failure | Error %
	The dashboard can be accessed by - http://localhost:9295/hystrix, 
	This dashboard uses the hystrix stream - http://localhost:9191/actuator/hystrix.stream


-Cloud Config Server
	Config server can be accessed by - http://localhost:9296


-GitHub Repo for Config Server
	Config repo is located in - https://github.com/foundationgroup/cloud-config-repo
	
-Zipkin and Sleuth
	zipkin is used for tracing between the services...
	Zipkin is running in - http://127.0.0.1:9411/zipkin/ from an executable jar file  - java -jar zipkin-server-2.23.14-exec.jar
