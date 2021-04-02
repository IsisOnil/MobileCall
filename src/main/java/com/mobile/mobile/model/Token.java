package com.mobile.mobile.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="token")
public class Token {
    
    @Id
    private long id;
    private long idUser;
    private String token;
    private Date expiration;

    public Token() {
        super();
    }

    public Token(long id,long idUser, String token,Date expiration) {
        super();
        this.id=id;
        this.idUser = idUser;
        this.token = token;
        this.expiration=expiration;
    }
    
    public Token(long id,long idUser) throws Exception {
        super();
        this.id=id;
        this.idUser = idUser;
        this.expiration = new Date();   
        String value = String.valueOf(idUser)+expiration;
        this.token=sha1(value);
        this.expiration= new Date();
    }

    public long getId() { return id; }
    public long getIduser() { return idUser; }
    public String getToken() { return token ; }
    public Date getExpiration() { return expiration ; }

    public void setId(long id) { this.id = id ; }
    public void setIduser(long idUser) { this.idUser = idUser ; }
    public void setToken(String token) { this.token = token ; }
    public void setExpiration(Date expiration) { this.expiration = expiration ; }
    
    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

}
