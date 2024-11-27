package br.com.certacon.restful_api_java_obj_list.integrationtests.swagger;

import br.com.certacon.restful_api_java_obj_list.configs.TestConfigs;
import br.com.certacon.restful_api_java_obj_list.integratontests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootApplication
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldDisplaySwaggerUiPage() {
		var content =
				given()
						.basePath("/swagger-ui/index.html")
						.port(TestConfigs.SERVER_PORT)
						.when()
						.get()
						.then()
						.statusCode(200)
						.extract()
						.body()
						.asString();
		assertTrue(content.contains("Swagger UI"));
	}
}