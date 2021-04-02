package com.mobile.mobile.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Helper {
    public static Connection connecter()throws Exception{
        Connection conn = null;
        try{
            String url ="jdbc:postgresql://localhost:5432/MobileCall";
            conn = DriverManager.getConnection(url, "postgres", "123456");
        }catch(Exception ex){throw ex;}
        return conn;
    }
    public static int getCountReq(String sql) throws Exception
    {
        Statement stm = null;
        try
        {
        stm = Connect.getConnection().createStatement();
        }
        catch(Exception e){System.out.println(e.getMessage());}
        
        ResultSet res = stm.executeQuery(sql);
        int reponse = 0;
        int i = 1;
        while(res.next()){
            reponse = res.getInt(i);
            i++;
        }
        res.close();
        stm.close();
        return reponse;
    }
    public static  String getColOne(String sql,String nomCol) throws Exception
    {
        Statement stm = null;
        try
        {
        stm = Connect.getConnection().createStatement();
        }
        catch(Exception e){System.out.println(e.getMessage());}
        
        ResultSet res = stm.executeQuery(sql);
        String reponse = null;
        int i = 1;
        while(res.next()){
            reponse = res.getString(nomCol);
            i++;
        }
        res.close();
        stm.close();
        return reponse;
    }
}
