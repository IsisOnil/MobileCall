package com.mobile.mobile.model;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mobile.mobile.util.*;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Offre")
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name = "idoperateur")
    private long idoperateur;

    @Column(name = "nom")
    private String nom;

    @Column(name = "priorite")
    private int priorite;

    @Column(name = "duree")
    private int duree;

    @Column(name = "prix")
    private long prix;

    public long getId() {
        return id;
    }

    public long getIdoperateur() {
        return idoperateur;
    }

    public String getNom() {
        return nom;
    }

    public int getPriorite() {
        return priorite;
    }

    public int getDuree() {
        return duree;
    }

    public long getPrix() {
        return prix;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdoperateur(long idoperateur) {
        this.idoperateur = idoperateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPriorite(int priorite) {
        this.priorite=priorite;
    }

    public void setDuree(int duree) {
        this.duree=duree;
    }

    public void setPrix(long prix) {
        this.prix=prix;
    }

    public Offre(){}

    public Offre(long id,long idoperateur,String nom,int priorite,int duree,long prix) {
        setId(id);
        setIdoperateur(idoperateur);
        setNom(nom);
        setPriorite(priorite);
        setDuree(duree);
        setPrix(prix);
    }

    public static void insertOffre(long idoperateur,String nom,int priorite,int duree,long prix) throws Exception{
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into Offre(idoperateur,nom,priorite,duree,prix) values("+idoperateur+",'"+nom+"',"+priorite+","+duree+","+prix+")";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
    } 

    public static Offre findbyId(long id) throws Exception{
        Offre val = new Offre();
        String req = "select * from offre where id="+id;
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            val.setId(rs.getLong(1));
            val.setIdoperateur(rs.getLong(2));
            val.setNom(rs.getString(3));
            val.setPriorite(rs.getInt(4));
            val.setDuree(rs.getInt(5));
            val.setPrix(rs.getLong(6));
        }
        st.close();
        co.close();
        return val;
    } 

}
