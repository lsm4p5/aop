package hello.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {

		SpringApplication.run(AopApplication.class, args);
		System.out.println( "======================= AopApplication.main+시작====================" );
	}

}
