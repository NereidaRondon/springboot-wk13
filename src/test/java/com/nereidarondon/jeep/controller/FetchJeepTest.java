package com.nereidarondon.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.nereidarondon.jeep.entity.Jeep;
import com.nereidarondon.jeep.entity.JeepModel;

//This annotation extends the JUnit test framework and SB is in control
//Each test class will get its own port for testing
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
    "classpath:flyway/migrations/V1.1__Jeep_Date.sql"},
config = @SqlConfig(encoding = "utf-8"))

class FetchJeepTest{

  /*@Test
   * Starter test to make sure port is changing 
   * void test() {
      System.out.println(getBaseUri());
    }
  */
  @Autowired
  private TestRestTemplate restTemplate;
  
  @LocalServerPort
  private int serverPort;
  
  @Test
  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
    //Given: a model, trim and URI
    //must create entity package in src/main/java existing jeep package
    //then create a new enum in their called jeepModel with all the model names
    JeepModel model = JeepModel.WRANGLER;
    
    //builds url for the car we want returned with specific model and trim
    String trim = "Sport";
    String uri = String.format("http://localhost:%d/jeeps?model=%s&trim=%s", serverPort, model, trim);
    // Test out that the url works
    //System.out.println(uri);
    
    //When: a connection is made to the URI
    ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
    //Then: a success (OK- 200) status code is returned OR a list of jeeps is returned 
    //AssertProvider<T> component
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    /*without controller layer written, receive org.opentest4j.AssertionFailedError:
     * expected:200 OK but was: 404 NOT_FOUND
     */
  }
  
}
