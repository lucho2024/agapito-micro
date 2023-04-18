package com.microservicios.cursos.config;

import com.github.javafaker.Faker;
import com.microservicios.cursos.properties.DragonBallProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {


    @ConfigurationProperties
    public DragonBallProperties DragonBallProperties(DragonBallProperties dragonBallProperties){
        return dragonBallProperties;
    }

    @Bean
    public Faker faker(){
        return  new Faker();
    }
}
