package com.mobile.mobile.controllers;

import java.sql.Date;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.MobileMoneyReelRepository;
import com.mobile.mobile.repositories.OffreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class OffreController {

    @Autowired
    public OffreRepository offreRepository;


    @GetMapping("/offre/all")
    public Status findOffre() {
        Status status = new Status();
        try{      
          status.setData(offreRepository.findAll());
          status.setcode(200);
          status.setMessage("Offres");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }

    @PostMapping(value = "/offre/create")
    public Status createOffre(@RequestBody Offre offre) {
        Status status = new Status();
      try{      
        Offre.insertOffre(offre.getIdoperateur(),offre.getNom(),offre.getPriorite(),offre.getDuree(),offre.getPrix());
        status.setData("Nouvelle Offre");
        status.setcode(200);
        status.setMessage("Nouvelle Offre");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

}
