package com.microservicios.cursos.service;

import com.microservicios.cursos.config.JwtProvider;
import com.microservicios.cursos.model.dto.TokenDto;
import com.microservicios.cursos.model.dto.UserDto;
import com.microservicios.cursos.model.entities.UserEntity;
import com.microservicios.cursos.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private ModelMapper mapper;


    public UserDto save(UserDto userDto){
        Optional<UserEntity> user = userRepository.findByUsername(userDto.getUsername());

        if(user.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User %s already exist",userDto.getUsername()));
        }

       UserEntity userEntity= userRepository.save(new UserEntity(userDto.getUsername(),
                 encoder.encode(userDto.getPassword())));

        return mapper.map(userEntity,UserDto.class);
    }

    public TokenDto login(UserDto user){
        UserEntity userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
        if(encoder.matches(user.getPassword(),userEntity.getPassword())){
            return new TokenDto(jwtProvider.createToken(userEntity));
        }else{
            throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    public TokenDto validate(String token){
        System.out.println(token+"token");
        jwtProvider.validate(token);
        String username = jwtProvider.getUsernameFromToken(token);
        UserEntity result = userRepository.findByUsername(username).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );
         return new TokenDto(token);
    }

}
