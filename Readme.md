# Weather API call

### Api provider https://www.weatherbit.io/api

### Project configurations
*  credentials : hardcoded in the application.properties file. (user: user, password: pass)
*  port : 8080
*  /swagger-ui.html for sawgger ui.


### To run the project as spring boot applcaition 
1. Run mvn compile 
   Swagger codegen is used to generated the client weather api code. 
2. Run the class WeatherApiApplication

### To run the project as docker container
1. We are using jib <https://github.com/GoogleContainerTools/jib>  to containerize the plugin.
2. Run `mvn compile jib:dockerBuild -Dimage=<image name>`
   This will make a docker image of the project to the local docker daemon.
   If you do not have docker installed locally, you can also use the configured container registry to push the image.   
   `mvn compile jib:build -Dimage=<image name> `
   Make sure you have container registry configured in settings.xml
3. `docker run -p 8080:8080 <image name>`
4. Access swagger UI: http://localhost:8080/swagger-ui.html

