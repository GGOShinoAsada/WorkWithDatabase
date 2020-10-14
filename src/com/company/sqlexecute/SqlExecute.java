package com.company.sqlexecute;
import java.sql.*;
import java.util.Properties;


public class SqlExecute {
    public static String DatabaseName="";
    private static  String UserName = "";
    private static String  Password = "";
    private static String Query;
    private static String ConnectionString;
    //system variable =
    private  static  Connection connection;
    private  static Statement statement;
    private  static ResultSet result;

    public static void setQuery(String qstr){
        Query=qstr;
    }
    public static String getQuery(){
        return  Query;
    }
    public  static  void setUser(String UN){
        UserName=UN;
    }
    public static String getUser(){
        return UserName;
    }
    public static void  setPassword(String pass){
        Password=pass;
    }
    public static String getPassword(){
        return Password;
    }
    public static String getDatabaseName(){
        String data = (DatabaseName.length()==0)?"":DatabaseName;
        return data;

    }
    public static void executeCommand(){
        System.out.println("this query"+getQuery());
        try{
            connection=DriverManager.getConnection(DatabaseName,UserName,Password);
            statement=connection.createStatement();
            result=statement.executeQuery(getQuery());
            while (result.next()){

               // System.out.println(result.t);
            }
            connection.close();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            //connection.close();
        }
    }
    public static void setDataBaseName(String dbname){
        DatabaseName=dbname;


    }
    public static void createConnectionString(){
       // ConnectionString =  "jdbc:mysql://localhost:3306/"+DatabaseURL+"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        ConnectionString  = "jdbc:mysql://localhost:3306/computerstore";
    }
    public static boolean getConnectionStatus() throws ClassNotFoundException {
        boolean con_succ=true;

        Properties info = new Properties();
        info.put("user",getUser());
        info.put("password",getPassword());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DatabaseName,info);
            System.out.println("Connection successful!");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            con_succ=false;
        }
        return  con_succ;
    }
}
