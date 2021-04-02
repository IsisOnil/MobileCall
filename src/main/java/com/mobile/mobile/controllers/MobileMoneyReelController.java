package com.mobile.mobile.controllers;


import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.MobileMoneyReelRepository;

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
public class MobileMoneyReelController {
    
    @Autowired
    public MobileMoneyReelRepository MobileMoneyReelRepository;

    
    @GetMapping("/MobileMR/all")
    public Status findMmrAll() {
        Status status = new Status();
        try{      
          status.setData(MobileMoneyReelRepository.findAll());
          status.setcode(200);
          status.setMessage("ALL Mobilemoneyreel");
        }
        catch(Exception e) { status.setMessage(e.toString());}
        return status;
    }

    
    @GetMapping("/MobileMR/last")
    public Status findAllLastMMr() {
        Status status = new Status();
        try{      
          status.setData(MobileMoneyReel.getAllLastMmr());
          status.setcode(200);
          status.setMessage("Liste all last mmr");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }


    @PostMapping(value = "/MobileMR/create")
    public Status createTransfert(@RequestParam(value = "iduser") long iduser,@RequestParam(value = "montantmmoneyreel") long montantmmoneyreel,@RequestParam(value = "date") java.sql.Date date) {
        Status status = new Status();
      try{      
        MobileMoneyReel insertedMmr = MobileMoneyReel.insertMobilemr(iduser,montantmmoneyreel,date);
        status.setData("Achat MobileMoneyReel effectue" + insertedMmr.getIduser() +"  " +insertedMmr.getMontantmmoneyreel());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
      return status;
    }
}
