package com.microservicios.cursos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TranslationService {

    private static final Logger log = LoggerFactory.getLogger(TranslationService.class);

    public Map<String,String> words = new HashMap<>();
    //esto para que se llame apenas se cree el bean
    @PostConstruct
    public void init(){
        words.put("Hello","hola");
        words.put("Bye","adios");
        words.put("Word","palabra");
    }

    @Cacheable("translations")
    public Optional<String> getTranslation(String message){

        for(String word: words.keySet()){
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(words.keySet().toString());
            log.info(word);
            if(word.equals(message)){
                log.info(words.get(message));
                return  Optional.of(words.get(message));

            }
        }
        return Optional.empty();
    }


    @CacheEvict("translations")
    public void clearCache(){

    }
}
