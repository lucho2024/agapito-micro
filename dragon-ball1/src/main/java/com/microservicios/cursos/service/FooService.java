package com.microservicios.cursos.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Autowired
    private Tracer tracer;

    private static final Logger log = LoggerFactory.getLogger(FooService.class);

    public void printLog(){

        Span newSpan = tracer.nextSpan().name("newSpan");
        try (Tracer.SpanInScope ws = this.tracer.withSpan(newSpan.start())){
            log.info("Test Log");
        }finally {
            newSpan.end();
        }

    }
}
