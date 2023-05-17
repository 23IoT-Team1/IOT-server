package iot.wifi_map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // base entity
public class WifiMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(WifiMapApplication.class, args);
	}

}
