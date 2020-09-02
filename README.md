# Microservicio Transaction Manager

### Consideraciones Generales
  * Se ha realizado la división en capas de api/services/repository
  * Se han utilizado validators, factory's etc para la funcionalidad descrita
  * Se añaden tests de Cucumber para el servicio de transaction status; se debería completar con tests unitarios para cada clase y así completar la cobertura de código
  * Se almacena la información en memoria (H2) de forma volátil
  * Se pensó usar un ConfigServer, EurekaServer etc para dejar montado el sistema de forma más desacoplada, pero se ha obviado por simplicidad de la aplicación global
  * El MS se encuentra dockerizado y listo para su uso 

### URL GIT
https://github.com/ignacio-herrero-glago/bank-transaction-manager.git

### Arranque del microservicio
  * Clonar el repositorio indicado arriba
  * En la ruta donde se ha clonado, ejecutar el comando "mvn install"
  * A continuación, generar la imagen docker con el comando "mvn docker:build"
  * A continuación, arrancar el microservicio con "docker run --rm -p 8081:8081 ignacio-herrero-glago/transaction-manager:1.0.0"

### URL Swagger
http://localhost:8081/code-challenge/swagger-ui.html
