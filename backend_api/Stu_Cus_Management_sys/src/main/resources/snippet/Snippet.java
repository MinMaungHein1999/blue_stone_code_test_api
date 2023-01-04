package snippet;

public class Snippet {
	spring.datasource.url=jdbc:mysql://localhost:3306/evoucherdb?useSSL=false
	spring.datasource.username=root
	spring.datasource.password=root
	server.port=8090
	#Hibernate
	spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
	
	#Hibernate auto ddl
	spring.jpa.hibernate.ddl-auto=update
	logging.level.org.hibernate.SQL=DEBUG
}

