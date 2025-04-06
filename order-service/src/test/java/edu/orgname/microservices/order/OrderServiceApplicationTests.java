package edu.orgname.microservices.order;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.hamcrest.MatcherAssert.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mySQLContainer.start();
	}

	@Test
	void shouldPlaceOrder() {
		String requestBody = """
				{
					"skuCode": "iPhone_15",
					"price": 1000,
					"quantity": 1
				}
				""";

		String responseBody = RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/order/v1")
				.then()
				.log().all()
				.statusCode(201)
				.extract().body().asString();

		assertThat(responseBody, Matchers.is("Order Placed Successfully!"));
	}

}
