# File Storage Service
The application has been written using string boot framework

##Build JAR File
mvn clean package or mvn clean package -DskipTests

###Note
OS Linux
Default File Path Will Be ===> /home/local-store(need permission for creating file)
if you want to change file path go to application.properties change it

###Following steps to execute application

1. java -jar FW-Solution-0.0.1.jar
2. Enter URL From Your Browser - http://localhost:8080/swagger-ui.html 

##API Endpoints
1. Save
 Method - POST
 URL - http://localhost:8080/user-service/add
 Request - { "filePath":"/home/<username>/<store-folder>","data":{"firstName":"sam"},"id":"00000123456789098765432123456789","expiry":200}
 Response - 200(SUCCESS)

2. Get 
 Method - GET
 URL - http://localhost:8080/user-service/get?id=<id-value>
 Response : {"status": "SUCCESS", "data":"","error": null}

3. Delete
 Method - DELETE
 URL - http://localhost:8080/user-service/remove?id=00000123456789098765432123456789
 Response : {"status": "SUCCESS", "data":null,"error": null}