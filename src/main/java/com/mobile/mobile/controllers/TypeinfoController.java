package com.mobile.mobile.controllers;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.TypeinfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class TypeinfoController {
    
    @Autowired
    public TypeinfoRepository TypeinfoRepository;
    
    @GetMapping("/typeinfo/all")
    public Status findTypeinfo() {
        Status status = new Status();
        try{      
          status.setData(TypeinfoRepository.findAll());
          status.setcode(200);
          status.setMessage("all type info");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }
    
    @PostMapping(value = "/typeinfo/create")
    public Status createtypeinfo(@RequestBody Typeinfo info) {
        Status status = new Status();
      try{      
        Typeinfo.insertTypeinfo(info.getNom(),info.getType());
        status.setData("new  typeInfo");
        status.setcode(200);
        status.setMessage("new  typeInfo");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
}
