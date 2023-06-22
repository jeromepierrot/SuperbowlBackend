package fr.jpierrot.SuperbowlBackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")}) /* this annotation sets up OpenApi for https*/
@SpringBootApplication
public class SuperbowlBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperbowlBackendApplication.class, args);
	}

}
