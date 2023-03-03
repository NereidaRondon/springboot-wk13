/*Create jeepSales → fetchJeppTest → fetchJeepTestSupport →BaseTest
*/
package com.nereidarondon.jeep;


//imports for Springboot
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//calls on springboot
@SpringBootApplication
public class JeepSales {


  public static void main(String[] args) {
    //start springboot from within application
    SpringApplication.run(JeepSales.class, args);

  }

}
