package com.microservicios.cursos.controller;


import com.microservicios.cursos.properties.DragonBallProperties;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {

    @Autowired
    private DragonBallProperties dragonBallProperties;

    @Autowired
    private MeterRegistry meterRegistry;

    private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);

    @Autowired
    private Environment env;


    @GetMapping
    @Timed("dragonball.name.get")
    public ResponseEntity<String> getAppName(){

        log.info("Getting application name {} ",dragonBallProperties.getApplicationName());
        log.info("Getting application port {} ",Integer.parseInt(env.getProperty("local.server.port")));

       int value = new Random().nextInt(5);
        meterRegistry.counter("drangoball.name","level",(value<3?"jr":"sr"))
                .increment(value);

        if(value<3){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(dragonBallProperties.getApplicationName());
    }


}
