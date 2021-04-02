package com.mobile.mobile.controllers;

import java.sql.Date;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.InfoOffreRepository;
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

public class ForfaitAcheteController {

    @PostMapping(value = "/achatforfait/create")
    public Status AchatOffreMMoney(@RequestBody ForfaitAchete forfait) throws Exception {
    Status status = new Status();    
      try{   
        String val = ForfaitAchete.AcheterForfaitMMoney(forfait.getIdUser(),forfait.getIdOffre(),forfait.getDateAchat());
        status.setData(val);
        status.setcode(200);
        status.setMessage(val);
      }
    //   catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
    catch(Exception e) { status.setMessage(e.toString());}

    return status;
    }
}
