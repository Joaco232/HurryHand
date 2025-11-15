package com.hurryhand.backend.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatHttpToHttpsRedirectConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> httpToHttpsRedirectCustomizer(
            @Value("${app.http.port:0}") int httpPort,
            ServerProperties serverProperties) {
        return factory -> {
            Integer httpsPort = serverProperties.getPort();
            if (httpPort > 0 && httpsPort != null && httpPort != httpsPort) {
                Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
                connector.setScheme("http");
                connector.setSecure(false);
                connector.setPort(httpPort);
                connector.setRedirectPort(httpsPort);
                factory.addAdditionalTomcatConnectors(connector);
            }
        };
    }
}
