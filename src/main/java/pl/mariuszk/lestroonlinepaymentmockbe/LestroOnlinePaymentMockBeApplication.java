package pl.mariuszk.lestroonlinepaymentmockbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("pl.mariuszk.lestroonlinepaymentmockbe.config")
public class LestroOnlinePaymentMockBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LestroOnlinePaymentMockBeApplication.class, args);
	}
}
