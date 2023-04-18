package com.microservicios.cursos.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/db-failover/dragonball/characters")
public class DragonBallController {

    //private Faker faker = new Faker();

    @Autowired
    private Faker faker;

    private List<String>characters = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);

    @PostConstruct
    public void init(){

        for (int i = 0; i <20 ; i++) {

            log.info("getting characters for failover drango ball");
            characters.add(String.format("FailOver - %s ",faker.dragonBall().character()));
        }

    }

    @GetMapping
    public ResponseEntity<List<String>> get(){
        return  new ResponseEntity<>(characters,HttpStatus.OK);
    }



}
