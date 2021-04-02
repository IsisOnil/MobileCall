package com.mobile.mobile.model;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mobile.mobile.util.*;

import lombok.val;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.lang.ProcessHandle.Info;
import java.sql.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Infooffre")
public class InfoOffre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name="idoffre")
    private long idoffre;

    @Column(name = "nom")
    private String nom;

    @Column(name = "valeur")
    private long valeur;

    public long getId() {
        return id;
    }

    public long getIdoffre() {
        return idoffre;
    }

    public String getNom() {
        return nom;
    }

    public long getValeur() {
        return valeur;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdoffre(long idOffre) {
        this.idoffre=idOffre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setValeur(long valeur) {
        this.valeur=valeur;
    }

    public InfoOffre(){}

    public InfoOffre(long id,long idOffre,String nom,long valeur){
        setId(id);
        setIdoffre(idOffre);
        setNom(nom);
        setValeur(valeur);
    }

    public static void insertInfoOffre(long idoffre,String nom,long valeur) throws Exception{
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into InfoOffre(idoffre,nom,valeur) values("+idoffre+",'"+nom+"',"+valeur+")";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
    } 

    public static List<InfoOffre>getInfosOffrebyIdOffre(long idoffre) throws Exception{
        List<InfoOffre> val = new ArrayList<InfoOffre>();
        String req = "select * from infooffre where idoffre="+idoffre;
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            InfoOffre t = new InfoOffre();
            t.setId(rs.getLong(1));
            t.setIdoffre(rs.getLong(2));
            t.setNom(rs.getString(3));
            t.setValeur(rs.getLong(4));
            val.add(t);
        }
        st.close();
        co.close();
        return val;
    }

}
