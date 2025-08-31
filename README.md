# jakartaee_udemy

## Postgresql Driver installation (Tomcat)
1. Go to Maven Repository > Search "Postgresql Driver" > Select version.
2. Copy the dependency xml code and paste it in `pom.xml` > Add `provided` scope.
3. Run Maven Install > Navigate to the .m2 folder in your system and search the .jar artifact the Maven task downloaded.
4. Copy the `.jar` file and paste it in `%TOMCAT_HOME%/lib`.

Effects:
The dependency version will be managed by maven and downoladed automatically into the `.m2` folder. The `provided` flag will tell Maven that the Driver will be found inside the `lib` folder in Tomcat, not in the classpath.
