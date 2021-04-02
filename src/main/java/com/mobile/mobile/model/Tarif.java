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
@Table(name = "Tarif")
public class Tarif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name = "idoffre")
    public long idoffre;

    @Column(name = "destination")
    public String destination;

    @Column(name = "type")
    public String type;

    @Column(name = "valeur")
    public long valeur;

    public long getId() { return this.id ;}
    public long getIdOffre() { return this.idoffre ;}
    public String getDestination() { return this.destination ;}
    public String getType() { return this.type ;}
    public long getValeur() { return this.valeur ;}

    public void setId(long id) {this.id=id;}
    public void setIdOffre(long idoffre) {this.idoffre=idoffre;}
    public void setDestination(String destination) {this.destination=destination;}
    public void setType(String type) {this.type=type;}
    public void setValeur(long valeur) {this.valeur=valeur;}

    public Tarif() {}

    public Tarif(long id,long idoffre,String destination,String type,long valeur) {
        setId(id);
        setIdOffre(idoffre);
        setDestination(destination);
        setType(type);
        setValeur(valeur);
    }   

    public static void insertTarif(long idoffre,String destination,String type,long valeur) throws Exception{
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into Tarif(idoffre,destination,type,valeur) values("+idoffre+",'"+destination+"','"+type+"',"+valeur+")";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
    } 

    public static List<Tarif>getTarifOffre(long idoffre) throws Exception{
        List<Tarif> val = new ArrayList<Tarif>();
        String req = "select * from Tarif where idoffre="+idoffre;
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Tarif t = new Tarif();
            t.setId(rs.getLong(1));
            t.setIdOffre(rs.getLong(2));
            t.setDestination(rs.getString(3));
            t.setType(rs.getString(4));
            t.setValeur(rs.getLong(5));
            val.add(t);
        }
        st.close();
        co.close();
        return val;
    }
}
