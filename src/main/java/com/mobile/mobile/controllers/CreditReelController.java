package com.mobile.mobile.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.CreditReelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CreditReelController {
    
    @Autowired
    public CreditReelRepository CreditReelRepository;

    
    @GetMapping("/CreditR/all")
    public Status findCreditrAll() {
        Status status = new Status();
        try{      
          status.setData(CreditReelRepository.findAll());
          status.setcode(200);
          status.setMessage("All Credit");
        }
        catch(Exception e) { status.setMessage(e.toString());}
        return status;
    }
    @GetMapping("/CreditR/last")
    public Status findAllLastCreditr() {
        Status status = new Status();
        try{      
          status.setData(CreditReel.getAllLastCredir());
          status.setcode(200);
          status.setMessage("Liste all last credit reel");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }
    @PostMapping(value = "/CreditR/create")
    public Status createCreditR(@RequestParam(value = "iduser") long iduser,@RequestParam(value = "montantcreditreel") long montantcreditreel,@RequestParam(value = "datecreditreel") String datecreditreel) {
        Status status = new Status();
      try{      
          DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          java.util.Date date = formatter.parse(datecreditreel);
          java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        CreditReel insertedCreditr = CreditReel.insertCreditr(iduser,montantcreditreel,sqlDate);
        status.setData("Achat CreditReel effectue" + insertedCreditr.getIduser() +"  " +insertedCreditr.getMontantcreditreel());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("MobileMoney is not enough");}
      return status;
    }
}
