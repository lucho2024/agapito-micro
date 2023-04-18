package com.microservicios.cursos.controller;

import com.microservicios.cursos.model.dto.TokenDto;
import com.microservicios.cursos.model.dto.UserDto;
import com.microservicios.cursos.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        return ResponseEntity.ok(authService.validate(token));
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.save(userDto));
    }
}
