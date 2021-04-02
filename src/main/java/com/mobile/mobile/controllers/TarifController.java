package com.mobile.mobile.controllers;

import java.sql.Date;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.MobileMoneyReelRepository;
import com.mobile.mobile.repositories.OffreRepository;
import com.mobile.mobile.repositories.TarifRepository;

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

public class TarifController {
    
    @Autowired
    public TarifRepository tarifRepository;


    @GetMapping("/tarif/all")
    public Status findTarif() {
        Status status = new Status();
        try{      
          status.setData(tarifRepository.findAll());
          status.setcode(200);
          status.setMessage("Tarifs");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }

    @PostMapping(value = "/tarif")
    public Status findInfo(@RequestBody Offre offre) {
        Status status = new Status();
      try{      
        status.setData(Tarif.getTarifOffre(offre.getId()));
        System.out.println(offre.getId());
        status.setcode(200);
        status.setMessage("idoffre = " + offre.getId());
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }

    @PostMapping(value = "/tarif/create")
    public Status createInfoOffre(@RequestBody Tarif tarif) {
        Status status = new Status();
      try{      
        Tarif.insertTarif(tarif.getIdOffre(),tarif.getDestination(),tarif.getType(),tarif.getValeur());
        status.setData("Valeur Tarif Ajoutee");
        status.setcode(200);
        status.setMessage("Valeur Tarif Ajoutee");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
    
}
