package com.mobile.mobile.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection ="admin")
public class Admin {
    
    @Id
    private long id;
    private String name;
    private String password; 

    public Admin() {
        super();
    }

    public Admin(long id,String name,String password) {
        super();
        this.id=id;
        this.name = name;
        this.password=password;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getPassword() { return password ; }

    public void setId(long id) { this.id = id ; }
    public void setName(String name) { this.name = name ; }
    public void setPassword(String password) { this.password = password ; }
 
}
