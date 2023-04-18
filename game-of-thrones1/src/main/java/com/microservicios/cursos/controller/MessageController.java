package com.microservicios.cursos.controller;

import com.microservicios.cursos.service.TranslationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/translations")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private TranslationService translationService;

    @GetMapping
    public ResponseEntity<String>getTranslation(@RequestParam("message") String message)  {
        log.info("Message Received {}",message);
        Optional<String> translation = translationService.getTranslation(message);
        if(translation.isPresent()){

            return ResponseEntity.ok(translation.get());
        }else{
            return (ResponseEntity<String>) ResponseEntity.notFound();
        }

    }

    @DeleteMapping
    public void clearCache(){

    }
}
