package com.mobile.mobile.model;
import java.sql.Date;

import com.mobile.mobile.util.*;

import java.sql.Statement;
import java.sql.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forfaitachete")
public class ForfaitAchete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name = "iduser")
    public long iduser;

    @Column(name = "idoffre")
    public long idoffre;

    @Column(name = "dateachat")
    public Date dateachat;

    public long getId() { return this.id ;}
    public long getIdUser() { return this.iduser ;}
    public long getIdOffre() { return this.idoffre ;}
    public Date getDateAchat() { return this.dateachat ;}

    public void setId(long id) {this.id=id;}
    public void setIdUser(long iduser) {this.iduser=iduser;}
    public void setIdOffre(long idoffre) {this.idoffre=idoffre;}
    public void setDateAchat(Date dateachat) {this.dateachat=dateachat;}

    public ForfaitAchete() {}

    public ForfaitAchete(long id,long iduser,long idoffre,Date dateAchDate) {
        setId(id);
        setIdUser(iduser);
        setIdOffre(idoffre);
        setDateAchat(dateachat);
    }

    
    public static String AcheterForfaitMMoney(long iduser,long idoffre,Date dateachat) throws Exception{
        String val="";
        Offre offre = Offre.findbyId(iduser);
        long solde = MobileMoneyReel.getCurrentMobilemr(iduser).getMontantmmoneyreel();

        if(solde>=offre.getPrix()) {
            val = "Forfait Achete";
            long newMMR = solde - offre.getPrix();
            Connection co = Connect.getConnection();
            Statement st =co.createStatement();
            String requete = "insert into ForfaitAchete(iduser,idoffre,dateAchat) values("+iduser+","+idoffre+",'"+dateachat+"')";
            String reqMmr = "insert into Mobilemoneyreel(iduser,montantmmoneyreel,datemmoneyreel) values("+iduser+","+newMMR+",'"+dateachat+"')";
            System.out.println(requete);
            System.out.println(reqMmr);
            st.executeUpdate(requete);
            st.executeUpdate(reqMmr);
            st.close();
            co.close();
        } else {
            val = "le solde de votre compte est insuffisant";
        }
        return val;
    } 
}
