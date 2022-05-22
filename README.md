# User Manager
Este proyecto fue desarrollado con la finalidad de poder registrar nuevos usuarios, 
validando la información de entrada y persistiendola finalmente en una base de datos temporal H2.   
## Pre-requisitos
Para poder compilar y ejecutar el proyecto se requiere tener instalado:
- Java 11
- Maven
## Diagrama de base de datos
Para el diseño de tablas de base de datos consideraremos 3 entidades:
- Usuarios (Users)
- Personas (Persons)
- Teléfonos (Phones)

La relación entre estos será de la siguiente manera: 

![diagram](diagrams/database-diagram.png)

Los scripts para la creación de la base de datos los puede encontrar en la siguiente ruta del proyecto:
- user-manager-project/src/main/resources/schema.sql

## Diagrama de arquitectura del proyecto

Para el diseño a nivel de proyecto Spring Boot usaremos arquitectura hexagonal debido a que si se desee expandir
la funcionalidad o incluso cambiar la base de datos temporal H2 por una base de datos real entonces esto se pueda 
realizar sin alterar la lógica de negocio (domain) de nuestra aplicación.

![diagram](diagrams/architecture-diagram.png)
## Compilar proyecto
Para la compilación del proyecto, una vez clonado, nos ubicaremos dentro de la carpeta del proyecto (al mismo nivel del archivo pom.xml) y usaremos el siguiente comando:
- mvn package

Lo que finalmente obtendremos como resultado de la compilación será una nueva carpeta llamada "target" y dentro un archivo llamado:
- user-manager-0.0.1-SNAPSHOT.jar
## Correr proyecto
Entramos a la carpeta "target" y una vez aquí ejecutaremos el siguiente comando:
- java -jar user-manager-0.0.1-SNAPSHOT.jar

Una vez hecho ello, deberíamos poder ver el log inicial de la aplicación.
## Documetación Swagger
Para poder acceder a la documentación swagger nos dirigimos a la siguiente ruta:
- http://localhost:8080/swagger-ui/index.html

Aquí podremos ver la documentación de la aplicación:

![diagram](screenshots/swagger.png)
## Registrar nuevo usuario
Para registrar a un nuevo usuario haremos uso del endpoint:
- POST /users

Usaremos el siguiente CURL para un registro exitoso:
```shell
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "1234567",
    "phones": [
        {
            "number": "123456789",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "987654321",
            "cityCode": "12",
            "countryCode": "51"
        }
    ]
}'
```
Para lo cual, nuestro resultado será similar al siguiente:

```json
{
  "id": "768d0b33-b8ce-4c77-9c63-cba92787f504",
  "created": "Sun May 22 13:49:08 COT 2022",
  "modified": "Sun May 22 13:49:08 COT 2022",
  "lastLogin": "Sun May 22 13:49:08 COT 2022",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJleHAiOjE2NTMyODEzNDgsImlhdCI6MTY1MzI0NTM0OH0.Ytl2haYjdGFwjIs0dahT-ST8kf9H0sHD5enUXo5zf5IbO1nyz6NfLeWmf1E8gBYRKpb0w_ev2vV9I71NVkRmbA",
  "active": true
}
```
En caso de querer verificar la correcta persistencia entonces podemos hacer uso del dashboard de H2 para revisar la data, 
para lo cual nos dirigimos a la siguiente ruta:
- http://localhost:8080/h2-ui

Nos aseguramos que la información esté tal cual en el siguiente screenshot, por defecto usaremos la contraseña: "password", aunque esta es configurable:

![diagram](screenshots/h2.png)

Una vez dentro escribimos y ejecutamos los comandos:
- SELECT * FROM USERS;
- SELECT * FROM PERSONS;
- SELECT * FROM PHONES;

Y podremos observar la información persistida de nuestro registro exitoso:

![diagram](screenshots/h2-result.png)

## Registro de correo ya existente
Una vez logrado nuestro primer registro exitoso, lo único que necesitamos hacer para desencadenar este error 
será volver a ejecutar el mismo CURL de la sección anterior, con lo cual nuestro resultado será:

```json
{
  "message": "El correo ya fue registrado"
}
```

## Validaciones de Email y Password
Para este caso modificaremos un poco nuestro body request inicial de tal manera que podamos desencadenar los mensajes de error 
para las validaciones de Email y Password, para lo cual nuestro CURL quedaría de la siguiente forma:

```shell
curl --location --request POST 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juanrodriguez.org",
    "password": "0aaaa",
    "phones": [
        {
            "number": "123456789",
            "cityCode": "1",
            "countryCode": "57"
        },
        {
            "number": "987654321",
            "cityCode": "12",
            "countryCode": "51"
        }
    ]
}'
```
Una vez hecho esto tendremos como respuesta el siguiente response body:

```json
[
  {
    "message": "El campo email no cumple el formato"
  },
  {
    "message": "Contrasenia no cumple con el patron establecido"
  }
]
```