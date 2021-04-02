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
@Table(name = "Transfert")
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name = "iduser")
    private long iduser;

    @Column(name = "montant")
    private long montant;

    @Column(name = "typetransfert")
    private String typetransfert; 

    @Column(name = "datetransfert")
    private Date datetransfert;

    
    @Column(name = "status")
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    public long getMontant() {
        return montant;
    }

    public void setMontant(long montant) {
        this.montant = montant;
    }

    public String getTypetransfert() {
        return typetransfert;
    }

    public void setTypetransfert(String typetransfert) {
        this.typetransfert = typetransfert;
    }

    public Date getDatetransfert() {
        return datetransfert;
    }

    public void setDatetransfert(Date datetransfert) {
        this.datetransfert = datetransfert;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Transfert() {
    }

    public Transfert(long id, long iduser, long montant, String typetransfert, Date datetransfert) {
        this.id = id;
        this.iduser = iduser;
        this.montant = montant;
        this.typetransfert = typetransfert;
        this.datetransfert = datetransfert;
    }   
    
    public Transfert(long id, long iduser, long montant, String typetransfert, Date datetransfert,String status) {
        this.id = id;
        this.iduser = iduser;
        this.montant = montant;
        this.typetransfert = typetransfert;
        this.datetransfert = datetransfert;
        this.status = status;
    }  
    public Transfert(long id, long iduser, long montant, Date datetransfert) {
        this.id = id;
        this.iduser = iduser;
        this.montant = montant;
        this.datetransfert = datetransfert;
    } 


    public static Transfert insertDemande(long iduser, long montant, String typetransfert, Date datetransfert) throws Exception{
        Transfert val = new Transfert(0,iduser,montant,typetransfert,datetransfert);
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into transfert(iduser,montant,typetransfert,datetransfert,status) values("+iduser+","+montant+",'"+typetransfert+"','"+datetransfert+"','attente')";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
        return val;
    }  
    
    public static Transfert insertCredit(long iduser, long montant, java.sql.Date datecreditreel) throws Exception{
        Transfert val = new Transfert(0,iduser,montant,datecreditreel);
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        String requete = "insert into transfert(iduser,montant,typetransfert,datetransfert,status) values("+iduser+","+montant+",'credit','"+datecreditreel+"','null')";
        System.out.println(requete);
        st.executeUpdate(requete);
        st.close();
        co.close();
        return val;
    }  
    public static List<Transfert> getTransfertEnAttente() throws Exception{
        List<Transfert> val = new ArrayList<Transfert>();
        String req = "select * from transfert where status='attente' and typetransfert='depot'";
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            Transfert t = new Transfert();
            t.setId(rs.getLong(1));
            t.setIduser(rs.getLong(2));
            t.setMontant(rs.getLong(3));
            t.setTypetransfert(rs.getString(4));
            t.setDatetransfert(rs.getDate(5));
            t.setStatus(rs.getString(6));
            val.add(t);
        }
        st.close();
        co.close();
        return val;
    }
    public static void UpdateEnAttente(long id) throws Exception{
        String req = "update transfert set status='valide' where id="+id;
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        st.executeUpdate(req);
        st.close();
        co.close();
    }
}
