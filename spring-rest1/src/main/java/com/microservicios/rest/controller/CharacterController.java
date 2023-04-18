package com.microservicios.rest.controller;

import com.github.javafaker.Faker;
import com.microservicios.rest.error.GenericException;
import com.microservicios.rest.error.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/characters")
@RestController
public class CharacterController {

    private Faker faker = new Faker();
    private List<String> characters = new ArrayList<>();


    @PostConstruct
    public void init(){
        for (int i = 0; i < 10; i++) {
            characters.add(faker.dragonBall().character());
        }
    }


    //@RequestMapping(value="/dragonBall" , method = RequestMethod.GET)
    @GetMapping("/dragonBall")
    public List<String> getCharacters(){

        return characters;
    }

    @GetMapping(value = "/dragonBall/{name}")
    public String getCharacterByName(@PathVariable("name") String name){
        return  characters.stream().filter(c-> c.equals(name))
                .findAny()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("%s not found",name)));
    }

    @GetMapping(value="/dragonBall/search")
    public ResponseEntity<?> getCharactersByPrefix(@RequestParam("prefix") String prefix){
        List<String> charactestList = characters.stream().filter(c -> c.startsWith(prefix))
                .collect(Collectors.toList());

        if(charactestList.isEmpty()){
          //  throw  new GenericException("no hay personajes",null,"controlador");
            throw  new NotFoundException(String.format("%s no fue encontrado",prefix)
                    ,HttpStatus.NOT_FOUND,"CharacterController.getCharactersByPrefix");

           /* throw new CustomAppException("hola","adios",HttpStatus.NOT_FOUND.toString());
           ErrorPojo errorPojo = new ErrorPojo("no hay personajes","controlador");
           return new ResponseEntity<>(errorPojo, HttpStatus.NOT_FOUND);*/
        }

        return new ResponseEntity<>(charactestList, HttpStatus.OK);
    }



}
