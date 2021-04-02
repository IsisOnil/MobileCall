package com.mobile.mobile.controllers;

//import com.github.houbb.mvc.annotation.RequestParam;
import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;                                                                                       
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TokenController {

    @Autowired
    public TokenRepository tokenRepository;

    @PostMapping("/token/getToken")
    Token two(@RequestParam(value = "token") String token) {   
      return tokenRepository.getToken(token);
    }

    @PostMapping(value = "/token/create/")
    public String createToken(@RequestBody Token token) {
        Token insertedToken = tokenRepository.insert(token);
        return "Token created " + insertedToken.getIduser() +" user : " +insertedToken.getToken();
    }

    @DeleteMapping("/token/{id}")
    public String deleteUser(@PathVariable Long id) {
        tokenRepository.deleteById(id);
      return "token : "+id+" deleted";
    }
}
