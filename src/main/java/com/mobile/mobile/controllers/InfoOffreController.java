package com.mobile.mobile.controllers;

import java.sql.Date;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.InfoOffreRepository;
import com.mobile.mobile.repositories.MobileMoneyReelRepository;
import com.mobile.mobile.repositories.OffreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")

public class InfoOffreController {
    
    @Autowired
    public InfoOffreRepository infoOffreRepository;


    @GetMapping("/infooffre/all")
    public Status findInfoOffre() {
        Status status = new Status();
        try{      
          status.setData(infoOffreRepository.findAll());
          status.setcode(200);
          status.setMessage("Infos Offres");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }

    @PostMapping(value = "/infooffre")
    public Status findInfo(@RequestBody Offre offre) {
        Status status = new Status();
      try{      
        status.setData(InfoOffre.getInfosOffrebyIdOffre(offre.getId()));
        System.out.println(offre.getId());
        status.setcode(200);
        status.setMessage("idoffre = " + offre.getId());
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @GetMapping("/infooffre/{id}")
    public Status info(@PathVariable Long id) {   
      Status status = new Status();
      try{   
        status.setData(InfoOffre.getInfosOffrebyIdOffre(id));
        status.setcode(200);
        status.setMessage("ok");  
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @PostMapping(value = "/infooffre/create")
    public Status createInfoOffre(@RequestBody InfoOffre infooffre) {
        Status status = new Status();
      try{      
        InfoOffre.insertInfoOffre(infooffre.getIdoffre(),infooffre.getNom(),infooffre.getValeur());
        status.setData("Valeur Offre Ajoutee");
        status.setcode(200);
        status.setMessage("Valeur Offre Ajoutee");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
}
