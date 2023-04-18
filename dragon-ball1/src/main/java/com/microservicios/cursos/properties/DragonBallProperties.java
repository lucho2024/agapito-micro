package com.microservicios.cursos.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Component
@Configuration//funciona con component o configuration, tambien se puede crear un bean
@RefreshScope
//@ConfigurationProperties este se usa con prefix
public class DragonBallProperties {

    @Value("${application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port;


    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
