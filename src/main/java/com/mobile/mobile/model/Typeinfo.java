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
@Table(name = "typeinfo")
public class Typeinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    
    @Column(name = "nom")
    private String nom;
    
    @Column(name = "type")
    private String type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Typeinfo() {
    }

    public Typeinfo(long id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    
    public static void insertTypeinfo(String nom,String type) throws Exception{
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into Typeinfo(nom,type) values('"+nom+"','"+type+"')";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
    } 


}