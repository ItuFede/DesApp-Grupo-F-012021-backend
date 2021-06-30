package ar.edu.unq.desapp.grupof012021.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendDesappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
	}
}