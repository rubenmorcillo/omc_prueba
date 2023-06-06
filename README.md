# omc_prueba
API CRUD de gestión de tareas (TODOs)
Configuración e inicialización del proyecto:

## Requisitos previos

- Java Development Kit (JDK) instalado en su máquina.
- Apache Maven instalado en su máquina.
- Conexión a Internet para descargar las dependencias del proyecto.

## Configuración

1. Clonar el repositorio:
git clone https://github.com/rubenmorcillo/omc_prueba.git
2. Navegar al directorio del proyecto:
3. Configurar la base de datos (si es necesario):

 
- Abra el archivo `application.properties` ubicado en `src/main/resources`.
- Modifique las propiedades relacionadas con la base de datos de acuerdo con su entorno de desarrollo. Por ejemplo, configure la URL, el nombre de usuario y la contraseña de la base de datos.
- La aplicación se ha creado para utilizar una base de datos mysql, por defecto, la configuración incluye el driver para mysql y la URL para una base de datos mysql en local, con username y password de una instalación por defecto.
- La creación de las tablas está configurada de forma automática (CREATE) para cada ejecución. Si no se requiere, comentar la propiedad spring.jpa.hibernate.ddl-auto=create o cambiar el valor a "update"
- Se ha configurado la inicialización de datos conforme a dicha estructura de tablas anterior. Si no se requiere, comentar las propiedades spring.jpa.defer-datasource-initialization=true y spring.sql.init.mode=always

4. Compilar el proyecto:

mvn clean package

## Ejecución

1. Desde la línea de comandos, vaya al directorio raíz del proyecto.

2. Ejecute el siguiente comando para iniciar la aplicación:

mvn spring-boot:run

3. La aplicación se iniciará y estará disponible en `http://localhost:8080`. Puede acceder a ella a través de su navegador web.
- Si se desea cambiar el puerto, se debe añadir la propiedad server.port= en el archivo de propiedades.
