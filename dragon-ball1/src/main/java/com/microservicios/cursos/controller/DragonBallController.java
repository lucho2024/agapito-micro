package com.microservicios.cursos.controller;

import com.github.javafaker.Faker;
import com.microservicios.cursos.service.FooService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/v1/dragonball/characters")
public class DragonBallController {

    //private Faker faker = new Faker();

    private static final Logger log = LoggerFactory.getLogger(DragonBallController.class);

    @Autowired
    private Faker faker;

    @Autowired
    private FooService fooService;

    private List<String>characters = new ArrayList<>();

    @PostConstruct
    public void init(){

        for (int i = 0; i <20 ; i++) {
            log.info("getting characters dragon ball");
            characters.add(faker.dragonBall().character());
        }

    }

    @GetMapping
    @Operation(summary = "Get a list of characters of dragon ball")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "characters was found it",
            content = {@Content(mediaType = "application/json",
            schema = @Schema(implementation =  String.class))}),
            @ApiResponse(responseCode = "400",description = "Characters not found",
            content = @Content)
    })
    public ResponseEntity<List<String>> get(){
        log.info("Getting characters db");
        fooService.printLog();
        return  new ResponseEntity<>(characters,HttpStatus.OK);
    }





}
