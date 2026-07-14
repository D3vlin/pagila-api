package co.d3vlin.pagila.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "co.d3vlin.pagila.entity")
@ComponentScan(basePackages = {
		"co.d3vlin.pagila.api",
		"co.d3vlin.pagila.mapper",
})
public class PagilaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagilaApiApplication.class, args);
	}

}
