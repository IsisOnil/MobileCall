package com.mobile.mobile.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        try{
            String url ="jdbc:postgresql://localhost:5432/MobileCall";
            conn = DriverManager.getConnection(url, "postgres", "123456");
        }catch(SQLException ex){System.out.println("Erreur lors de la connection a la base de donnees");}
        return conn;
    }

    // public static Connection getConnection() throws Exception {
    //     Connection conn = null;
    //     try{
    //         String url ="jdbc:postgresql://ec2-54-155-87-214.eu-west-1.compute.amazonaws.com:5432/db2uv64uqm7kjo";
    //         conn = DriverManager.getConnection(url, "akflazyvsudtgg", "aa32b12e022360a4109645dad61e2578e7347f5bcd569579fd4727c5433ece05");
    //     }catch(SQLException ex){System.out.println("Erreur lors de la connection a la base de donnees");}
    //     return conn;
    // }
}