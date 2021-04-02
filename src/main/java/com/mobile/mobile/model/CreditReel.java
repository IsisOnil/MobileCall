package com.mobile.mobile.model;
import java.util.Date;
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
@Table(name = "Creditreel")
public class CreditReel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial",name="id")
    private long id;

    @Column(name = "iduser")
    private long iduser;

    @Column(name = "montantcreditreel")
    private long montantcreditreel;

    
    @Column(name = "datecreditreel")
    private Date datecreditreel;


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


    public long getMontantcreditreel() {
        return montantcreditreel;
    }


    public void setMontantcreditreel(long montantcreditreel) {
        this.montantcreditreel = montantcreditreel;
    }


    public Date getDatecreditreel() {
        return datecreditreel;
    }


    public void setDatecreditreel(Date datecreditreel) {
        this.datecreditreel = datecreditreel;
    }


    public CreditReel() {
    }


    public CreditReel(long id, long iduser, long montantcreditreel, Date datecreditreel) {
        this.id = id;
        this.iduser = iduser;
        this.montantcreditreel = montantcreditreel;
        this.datecreditreel = datecreditreel;
    }


    public CreditReel(long iduser, long montantcreditreel) {
        this.iduser = iduser;
        this.montantcreditreel = montantcreditreel;
    }

    
    public static List<CreditReel> getAllLastCredir() throws Exception{
        List<CreditReel> val = new ArrayList<CreditReel>();
        String req = "select Creditreel.* from Creditreel INNER JOIN(select max(id) as maxId,iduser,max(datecreditreel) as maxdate from Creditreel group by iduser) ms on Creditreel.id=ms.maxId and Creditreel.iduser=ms.iduser  and Creditreel.datecreditreel=ms.maxdate";
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            CreditReel t = new CreditReel();
            t.setId(rs.getInt(1));
            t.setIduser(rs.getLong(2));
            t.setMontantcreditreel(rs.getLong(3));
            t.setDatecreditreel(rs.getDate(4));
            val.add(t);
        }
        st.close();
        co.close();
        return val;
    }
    
    public static CreditReel getCurrentCreditr( long iduser) throws Exception{
        CreditReel val = null;
        String req = "select * from Creditreel  where iduser = '"+iduser+"' and datecreditreel=(select max(datecreditreel)from Creditreel) and id=(select max(id) from Creditreel)";
        Connection co = Connect.getConnection();
        PreparedStatement st = co.prepareStatement(req);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            CreditReel t = new CreditReel();
            t.setId(rs.getInt(1));
            t.setIduser(rs.getLong(2));
            t.setMontantcreditreel(rs.getLong(3));
            t.setDatecreditreel(rs.getDate(4));
            val=t;
        }
        st.close();
        co.close();
        return val;
    }
    
    public static CreditReel insertCreditr(long iduser, long montantcreditreel,java.sql.Date datecreditreel) throws Exception{
        CreditReel val = null;
        new MobileMoneyReel();
        MobileMoneyReel mrIzy = MobileMoneyReel.getCurrentMobilemr(iduser);
        if(mrIzy.getMontantmmoneyreel()>=montantcreditreel){
            val = new CreditReel(iduser,montantcreditreel);
            new CreditReel();
            CreditReel last =  CreditReel.getCurrentCreditr(iduser);
            String requete = null;
            Connection co = Connect.getConnection();
            Statement st =co.createStatement();
            long lastmobileMR = mrIzy.getMontantmmoneyreel()-montantcreditreel;
            new Transfert();
            Transfert t = Transfert.insertCredit(iduser, montantcreditreel, datecreditreel);
            String reqMmr = "insert into Mobilemoneyreel(iduser,montantmmoneyreel,datemmoneyreel) values("+iduser+","+lastmobileMR+",'"+datecreditreel+"')";
            if(last == null){
                requete = "insert into Creditreel (iduser,montantcreditreel,datecreditreel) values("+iduser+","+montantcreditreel+",'"+datecreditreel+"')";
            }
            else if(last != null){
                long montantFinal = montantcreditreel + last.getMontantcreditreel();
                requete = "insert into Creditreel (iduser,montantcreditreel,datecreditreel) values("+iduser+","+montantFinal+",'"+datecreditreel+"')";
            }
            st.executeUpdate(requete);
            st.executeUpdate(reqMmr);
            st.close();
            co.close();
        }else if(mrIzy.getMontantmmoneyreel() < montantcreditreel || mrIzy==null){
            throw new  Exception("MobileMoney Null");
        }
        
        return val;
    }  
}
