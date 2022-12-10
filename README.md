# Donneta
Proyect in java Spring Boot

intial proyect 

 The creation of the database is done through the "application.properties" file and the "spring.jpa.hibernate.ddl-auto=create-drop" is uncommented and executed and then the database is created , then you have to comment again to avoid losing data.
The creation of the administrator role is done through the main folder in the file that says "ProyectodonnetaApplication.java" and uncomment

//  User admin = new User(
		//  		null,
		//  		"admin",
		//  		"admin@localhost.com",
		//  		// la contraseña es alamo6402 cambienla pero debe estar encriptada
		//  		"$2a$10$n.qDryUMLQrVfN3R3kgodOnnniZcnK/HLhfkxEcDxqtp2sHlll8Ty",
				
		//  		Arrays.asList(role),
		//  		null);
				
				

		//  userRepository.save(admin);
