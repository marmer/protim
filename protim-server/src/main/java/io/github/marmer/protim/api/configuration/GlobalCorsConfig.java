package io.github.marmer.protim.api.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static io.github.marmer.protim.service.Converter.asList;

@ConfigurationProperties("global.cors.config")
@Data
public class GlobalCorsConfig {
    private List<String> allowedOrigins;
    private List<String> allowedMethods = asList("GET", "POST", "DELETE", "PUT");
    private List<String> allowedHeaders;
    private List<String> exposedHeaders;
    private Boolean allowCredentials;
    private Long maxAge;
}
