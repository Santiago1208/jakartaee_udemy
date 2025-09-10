# Jakarta EE Course from Udemy

## Postgresql Driver installation (Tomcat)
1. Go to Maven Repository > Search "Postgresql Driver" > Select version.
2. Copy the dependency xml code and paste it in `pom.xml` > Add `provided` scope.
3. Run Maven Install > Navigate to the .m2 folder in your system and search the .jar artifact the Maven task downloaded.
4. Copy the `.jar` file and paste it in `%TOMCAT_HOME%/lib`.

Effects:
The dependency version will be managed by maven and downoladed automatically into the `.m2` folder. The `provided` flag will tell Maven that the Driver will be found inside the `lib` folder in Tomcat, not in the classpath.

## Lesson 111: Configuring database connection pool (for PostgreSQL)
1. Navigate to [Tomcat 10.1 How To Documentation](https://tomcat.apache.org/tomcat-10.1-doc/jndi-datasource-examples-howto.html). The very first database in the documentation have the complete snippets of the xmls, you can copy those and replace the particular code with the desired database one.
2. Create the `webapp/META-INF/context.xml` and `webapp/WEB-INF/web.xml` files with this content:
```xml
<!-- context.xml -->
<Context>

    <Resource name="jdbc/javaCourseDB" auth="Container"
              type="javax.sql.DataSource" driverClassName="org.postgresql.Driver"
              url="jdbc:postgresql://127.0.0.1:5432/java_course"
              username="SANTIAGO" password="password" maxTotal="20" maxIdle="10"
              maxWaitMillis="-1"/>
</Context>

```

```xml
<!-- web.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee
                      https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
    <description>Jakarta EE Course App</description>
    <resource-ref>
        <description>PostgreSQL Datasource</description>
        <res-ref-name>jdbc/javaCourseDB</res-ref-name>
        <res-type>javax.sql.DataSource</res-type>
        <res-auth>Container</res-auth>
    </resource-ref>

</web-app>

```

3. Create the util class to obtain a connection from the pool whenver it is needed

```java
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class DatasourceJdbcConnection {

    public static Connection getConnection() {
        try {
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/javaCourseDB");

            if ( ds == null ) {
                throw new Exception("Data source not found!");
            }
            return ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

```

