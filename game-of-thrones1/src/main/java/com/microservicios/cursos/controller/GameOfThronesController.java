package com.microservicios.cursos.controller;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gameofthrones/characters")
public class GameOfThronesController {


    private List<String> characters = new ArrayList<>();

    @Autowired
    private Faker faker;

    @Value("${server.port}")
    private Integer port;

    private static final Logger log = LoggerFactory.getLogger(GameOfThronesController.class);

    @PostConstruct
    public void init(){
        for (int i = 0; i <20 ; i++) {
           log.info("getting characters game of thrones");
            characters.add(faker.gameOfThrones().character());
        }
        characters.add(port.toString());
    }


    @GetMapping
    public ResponseEntity<List<String>>get(){

        return  ResponseEntity.ok(characters);
    }
}
