package com.microservicios.cursos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class CloudStream2Application {

	private static final Logger log = LoggerFactory.getLogger(CloudStream2Application.class);

	@Bean
	public Function<String,String> toUpperCase(){
		return String::toUpperCase;
	}

	@Bean
	public Supplier<Flux<Long>> producer(){
		return ()-> Flux.interval(Duration.ofSeconds(1)).log();
	}

	@Bean
	public Function<Flux<Long>,Flux<Long>>processor(){
		return flx->flx.map(num ->num*num);
	}


	@Bean
	public Consumer<Long> consumer(){
		return (number)->{
			log.info("Message received {}",number);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudStream2Application.class, args);
	}

}
