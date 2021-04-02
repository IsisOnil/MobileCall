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
@Table(name = "Mobilemoneyreel")
public class MobileMoneyReel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private int id;

    @Column(name = "iduser")
    private long iduser;

    @Column(name = "montantmmoneyreel")
    private long montantmmoneyreel;

    
    @Column(name = "datemmoneyreel")
    private Date datemmoneyreel;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public long getIduser() {
        return iduser;
    }


    public void setIduser(long iduser) {
        this.iduser = iduser;
    }


    public long getMontantmmoneyreel() {
        return montantmmoneyreel;
    }


    public void setMontantmmoneyreel(long montantmmoneyreel) {
        this.montantmmoneyreel = montantmmoneyreel;
    }


    public Date getDatemmoneyreel() {
        return datemmoneyreel;
    }


    public void setDatemmoneyreel(Date datemmoneyreel) {
        this.datemmoneyreel = datemmoneyreel;
    }


    public MobileMoneyReel() {
    }


    public MobileMoneyReel(int id, long iduser, long montantmmoneyreel, Date datemmoneyreel) {
        this.id = id;
        this.iduser = iduser;
        this.montantmmoneyreel = montantmmoneyreel;
        this.datemmoneyreel = datemmoneyreel;
    }
    
    public MobileMoneyReel(long iduser, long montantmmoneyreel) {
        this.iduser = iduser;
        this.montantmmoneyreel = montantmmoneyreel;
    }
    
    public static List<MobileMoneyReel> getAllLastMmr() throws Exception{
        List<MobileMoneyReel> val = new ArrayList<MobileMoneyReel>();
        String req = "select MobileMoneyReel.* from MobileMoneyReel INNER JOIN(select max(id) as maxId,iduser,max(dateMMoneyReel) as maxdate from MobileMoneyReel group by iduser) ms on MobileMoneyReel.id=ms.maxId and MobileMoneyReel.iduser=ms.iduser  and MobileMoneyReel.dateMMoneyReel=ms.maxdate";
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            MobileMoneyReel t = new MobileMoneyReel();
            t.setId(rs.getInt(1));
            t.setIduser(rs.getLong(2));
            t.setMontantmmoneyreel(rs.getLong(3));
            t.setDatemmoneyreel(rs.getDate(4));
            val.add(t);
        }
        st.close();
        co.close();
        return val;
    }
    public static MobileMoneyReel getCurrentMobilemr( long iduser) throws Exception{
        MobileMoneyReel val = null;
        String req = "select * from Mobilemoneyreel where iduser = '"+iduser+"' and dateMMoneyReel=(select max(dateMMoneyReel)from MobileMoneyReel) and id=(select max(id) from MobileMoneyReel)";
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            MobileMoneyReel t = new MobileMoneyReel();
            t.setId(rs.getInt(1));
            t.setIduser(rs.getLong(2));
            t.setMontantmmoneyreel(rs.getLong(3));
            t.setDatemmoneyreel(rs.getDate(4));
            val=t;
        }
        st.close();
        co.close();
        return val;
    }
    public static MobileMoneyReel insertMobilemr(long iduser, long montantmmoneyreel,java.sql.Date date) throws Exception{
        MobileMoneyReel val = new MobileMoneyReel(iduser,montantmmoneyreel);
        new MobileMoneyReel();
        MobileMoneyReel last =  MobileMoneyReel.getCurrentMobilemr(iduser);
        String requete = null;
        Connection co = Connect.getConnection();
        Statement st =co.createStatement();
        if(last == null){
            requete = "insert into Mobilemoneyreel(iduser,montantmmoneyreel,datemmoneyreel) values("+iduser+","+montantmmoneyreel+",'"+date+"')";
        }
        else if(last != null){
            long montantFinal = montantmmoneyreel + last.getMontantmmoneyreel();
            requete = "insert into Mobilemoneyreel(iduser,montantmmoneyreel,datemmoneyreel) values("+iduser+","+montantFinal+",'"+date+"')"; 
        }
        st.executeUpdate(requete);
        st.close();
        co.close();
        return val;
    }  
   



}
