package pl.mariuszk.lestroonlinepaymentmockbe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "mock-payment")
public class ConfigProperties {

    private String redirectUrl;
    private String shopPaymentUrl;
}