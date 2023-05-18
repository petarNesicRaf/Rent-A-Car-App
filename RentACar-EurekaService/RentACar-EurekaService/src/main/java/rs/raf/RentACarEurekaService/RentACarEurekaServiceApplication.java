package rs.raf.RentACarEurekaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RentACarEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarEurekaServiceApplication.class, args);
	}

}
