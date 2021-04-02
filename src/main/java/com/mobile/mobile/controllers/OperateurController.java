package com.mobile.mobile.controllers;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.OperateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class OperateurController {
    
    @Autowired
    public OperateurRepository OperateurRepository;

    @GetMapping("/Operateur/all")
    public Status findAllOperateur() {
        Status status = new Status();
        try{      
          status.setData(OperateurRepository.findAll());
          status.setcode(200);
          status.setMessage("ok");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }
    @GetMapping("/Operateur/{id}")
    public Status findById(@PathVariable String id) {   
      Status status = new Status();
      try{      
        status.setData(OperateurRepository.findById( Long.parseLong(id) ));
        status.setcode(200);
        status.setMessage("ok");  
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
}
