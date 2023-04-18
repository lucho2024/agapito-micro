package com.microservicios.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "character not found")
public class CharacterNotFound extends  RuntimeException{
}
