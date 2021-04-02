package com.mobile.mobile.controllers;

//import com.github.houbb.mvc.annotation.RequestParam;
import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public TokenRepository tokenRepository;

    @GetMapping(value= "/user/all")
    public Status getAllUsers() {
      Status status = new Status();
      try{      
        status.setData(userRepository.findAll());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @GetMapping("/user/{id}")
    public Status one(@PathVariable Long id) {   
      Status status = new Status();
      try{      
        status.setData(userRepository.findById(id));
        status.setcode(200);
        status.setMessage("ok");  
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @PostMapping("/user/checkmdp")
    public Status one(@RequestParam(value = "number") String number,@RequestParam(value = "password") String password) {   
      Status status = new Status();
      try{      
        status.setData(userRepository.checkmdp(number,password));
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @PostMapping("/user/login")
    Status two(@RequestParam(value = "number") String number,@RequestParam(value = "password") String password) throws Exception {   
      Status status = new Status();
      try{      
        User user = userRepository.checkmdp(number,password);
        List<Token> allToken= tokenRepository.findAll();
        Token token = new Token((allToken.size()+1),user.getId());
        token.setIduser(user.getId());

        status.setData(tokenRepository.insert(token));
        status.setcode(200);
        status.setMessage("ok"); 
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @PostMapping(value = "/user/create")
    public Status createUser(@RequestBody User user) {
        Status status = new Status();
      try{      
        List<User> allUser = userRepository.findAll();
        user.setId(allUser.size()+1);
        user.setNumber(user.getIdOperateur(), "create");
        User insertedUser = userRepository.insert(user);
        status.setData("User created " + insertedUser.getName() +"  " +insertedUser.getNumber());
        status.setcode(200);
        status.setMessage("ok");  
        /////////////////////Mobile Money sy Credit//////////////
        new MobileMoneyReel();
        new CreditReel();
        long montant =0;
        long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis); 
        MobileMoneyReel.insertMobilemr(insertedUser.getId(), montant,date);
        CreditReel.insertCreditr(insertedUser.getId(), montant, date);
        /////////////////////////////////////////////////////////
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;

    }


    @PutMapping("/user/{id}")
    Status replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        Status status = new Status();
      try{      
        status.setData(userRepository.findById(id)
        .map(user -> {
          user.setName(newUser.getName());
          user.setNumber(newUser.getNumber());
          user.setPassword(newUser.getPassword());
          return userRepository.save(user);
        })
        .orElseGet(() -> {
          newUser.setId(id);
          return userRepository.save(newUser);
        }));
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @DeleteMapping("/user/{id}")
    public Status deleteUser(@PathVariable Long id) {
      Status status = new Status();
      try{   
        userRepository.deleteById(id);   
        status.setData("user : "+id+" deleted");
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @GetMapping("/user/test")
    public String getUserbyToken(@RequestHeader("Authorization") String token) {   
      Status status = new Status();
      try{      
        status.setData(token);
        status.setcode(200);
        status.setMessage("ok");  
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return token;
      //return status;
    }
}
