package com.mobile.mobile.controllers;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AdminController {
    
    @Autowired
    public AdminRepository adminRepository;

    
    @Autowired
    public TokenRepository tokenRepository;

    @GetMapping(value= "/admin/all")
    public Status getAllAdmins() {
      Status status = new Status();
      try{      
        status.setData(adminRepository.findAll());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
    
    @GetMapping("/admin/{id}")
    public Status one(@PathVariable Long id) {   
      Status status = new Status();
      try{      
        status.setData(adminRepository.findById(id));
        status.setcode(200);
        status.setMessage("ok");  
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    
    @PostMapping("/admin/checkmdp")
    public Status one(@RequestParam(value = "name") String name,@RequestParam(value = "password") String password) {   
      Status status = new Status();
      try{      
        status.setData(adminRepository.checkmdp(name,password));
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    
    @PostMapping("/admin/login")
    Status two(@RequestParam(value = "name") String name,@RequestParam(value = "password") String password) throws Exception {   
      Status status = new Status();
      try{      
        User user = adminRepository.checkmdp(name,password);
        List<Token> allToken= tokenRepository.findAll();
        Token token = new Token((allToken.size()+1),user.getId());
        token.setIduser(user.getId());

        status.setData(tokenRepository.insert(token));
        status.setcode(200);
        status.setMessage("admin Connecté"); 
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
    
    @PostMapping(value = "/admin/create")
    public Status createAdmin(@RequestBody Admin admin) {
        Status status = new Status();
      try{      
        List<Admin> allAdmin = adminRepository.findAll();
        admin.setId(allAdmin.size()+1);
        Admin insertedAdmin = adminRepository.insert(admin);
        status.setData("Admin created " + insertedAdmin.getName());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;

    }
}
