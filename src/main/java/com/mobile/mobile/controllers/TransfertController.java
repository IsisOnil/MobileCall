package com.mobile.mobile.controllers;

import java.sql.Date;
import java.util.Optional;

import com.mobile.mobile.model.*;
import com.mobile.mobile.repositories.TokenRepository;
import com.mobile.mobile.repositories.TransfertRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class TransfertController {

    @Autowired
    public TransfertRepository transfertRepository;
    
    @Autowired
    public TokenRepository tokenRepository;
    
    @Autowired
    public MobileMoneyReelController mmrControll;

    
    @Autowired
    public CreditReelController CreditrControll;

    @GetMapping("/transfert/all")
    public Status findTransfertV2() {
        Status status = new Status();
        try{      
          status.setData(transfertRepository.findAll());
          status.setcode(200);
          status.setMessage("Transfert");
        }
        catch(Exception e) { status.setMessage("Une Erreur s'est produite, veuillez reesayer");}
        return status;
    }
    @GetMapping("/transfert/enattente")
    public Status findTransfertEnAttente() {
        Status status = new Status();
        try{      
          status.setData(Transfert.getTransfertEnAttente());
          status.setcode(200);
          status.setMessage("Liste en attente");
        }
        catch(Exception e) { status.setMessage(e.toString());}
        return status;
    }


    @PostMapping(value = "/transfert/create")
    public Status createTransfert(@RequestBody Transfert transfert,@RequestHeader ("Authorization") String token) {
        Status status = new Status();
        Token USERtoken = tokenRepository.getToken(token.split(" ")[1]);
      try{      
        Transfert insertedTransfert = Transfert.insertDemande(USERtoken.getIduser(),transfert.getMontant(),transfert.getTypetransfert(),transfert.getDatetransfert());
        status.setData("Transfert effectue" + insertedTransfert.getIduser() +"  " +insertedTransfert.getMontant());
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage(USERtoken.toString());}
      return status;
    }



    @PutMapping(value = "/transfert/updateAttente")
    public Status UpdateAttente(@RequestParam(value = "id") Long id,@RequestParam(value = "date") java.sql.Date date) {
        Status status = new Status();
        Long idTrans = id;
      try{      
        Transfert.UpdateEnAttente(id);
        Optional<Transfert> t = transfertRepository.findById( idTrans );
        new MobileMoneyReel();
        MobileMoneyReel.insertMobilemr(t.get().getIduser(), t.get().getMontant(),date);

        status.setData("Transfert numero " + id +" a jour et insert mobileMoney reel" );
        status.setcode(200);
        status.setMessage("ok");
      }
      catch(Exception e) { status.setMessage(e.toString());}
      return status;
    }
    
}
