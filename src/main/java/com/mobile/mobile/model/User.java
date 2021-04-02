package com.mobile.mobile.model;

import java.util.Random;

import com.mobile.mobile.util.Helper;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="user")
public class User {
    
    @Id
    private long id;
    
    private String idOperateur;
    private String name;
    private String number;
    private String password;    

    public User() {
        super();
    }

    public User(long id,String idOperateur,String name, String number,String password) {
        super();
        this.id=id;
        this.idOperateur=idOperateur;
        this.name = name;
        this.number = number;
        this.password=password;
    }

    public long getId() { return id; }
    public String getIdOperateur() { return idOperateur; }
    public String getName() { return name; }
    public String getNumber() { return number ; }
    public String getPassword() { return password ; }

    public void setId(long id) { this.id = id ; }
    public void setIdOperateur(String idOperateur) { this.idOperateur = idOperateur ; }
    public void setNumber(String number) { this.number = number ; }
    public void setNumber(String idOperateur,String type) throws Exception { 
        String SGetNumCode="select code as code from operateur where id ="+idOperateur;
        String NumCodeIzy = Helper.getColOne(SGetNumCode, "code");
        Random rand = new Random();
        int numRand = (int)(rand.nextInt(9999-1000)+1000);
        String numbFinal = NumCodeIzy + String.valueOf(numRand);
        this.number = numbFinal ; }
    public void setName(String name) { this.name = name ; }
    public void setPassword(String password) { this.password = password ; }

    
}
