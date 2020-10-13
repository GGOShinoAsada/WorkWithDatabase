package com.company.sqlexecute;
import java.sql.*;


public class SqlExecute {
    public static String DatabaseURL="";
    private static  String UserName = "";
    private static String  Password = "";
    private static String Query;
    //system variable
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
        String data = (DatabaseURL.length()==0)?"":DatabaseURL;
        return data;

    }
    public static void executeCommand(){
        System.out.println("this query"+getQuery());
        try{
            connection=DriverManager.getConnection(DatabaseURL,UserName,Password);
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
    public static void setDatabaseName(String dbname){
        DatabaseURL="jdbc:mysql://localhost/"+dbname+" ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

    }
    public static boolean getConnectionStatus() throws ClassNotFoundException {
        boolean con_succ=true;
        Class.forName("com.mysql.jdbc.Driver");
        try{
            connection = DriverManager.getConnection(DatabaseURL,UserName,Password);
            System.out.println("Connection Succcesful!");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            con_succ=false;
        }
        return  con_succ;
    }
}
