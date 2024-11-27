package br.com.certacon.restful_api_java_obj_list;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Your API Title", version = "v1"))
@SpringBootApplication
public class Startup4 {
	public static void main(String[] args) {
		SpringApplication.run(Startup4.class, args);
	}

}
