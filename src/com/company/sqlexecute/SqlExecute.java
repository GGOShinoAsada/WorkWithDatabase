package com.company.sqlexecute;
import java.sql.*;
import java.util.Properties;


public class SqlExecute {
    public static String databaseName ="";
    private static  String userName = "";
    private static String password = "";
    private static String query;
    private static String connectionString;

    private  static  Connection connection;
    private  static Statement staticStatement;
    private  static ResultSet result;
    private  static PreparedStatement dynamicStatement;



    public  static  void setUser(String UN){
        userName =UN;
    }
    public static String getUser(){
        return userName;
    }
    public static void  setPassword(String pass){
        password =pass;
    }
    public static String getPassword(){
        return password;
    }
    public static String getDatabaseName(){
        return  (databaseName.length()==0)?"": databaseName;


    }

    public static void setDataBaseName(String dbname){
        databaseName =dbname;


    }
    public static void createConnectionString(){
        connectionString =  "jdbc:mysql://localhost:3306/"+ databaseName +"?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        //ConnectionString  = "jdbc:mysql://localhost:3306/computerstore";
    }
    public static boolean getConnectionStatus() throws ClassNotFoundException {
        boolean con_succ=true;

        Properties info = new Properties();
        info.put("user",getUser());
        info.put("password",getPassword());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString,info);
            System.out.println("Connection successful!");
            if (!connection.isClosed())
                connection.close();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            con_succ=false;
        }

        return  con_succ;
    }
    //-----------------------------------------------------------//
    //------------DATABASES MODEL EXECUTE------------------------//
    //------------------------------------------------------------//
    //---------------Positions------------------------------------//
    public static  boolean addpositionprocedure(String posname, String desc){
        boolean rez = true;
        try{
            connection = DriverManager.getConnection(connectionString,userName,password);
            query = "call addposition(%s, %s)";
            staticStatement = connection.createStatement();
            //read
           boolean t= ((staticStatement.executeUpdate(query))>0)?true:false;
           if (t){
               System.out.println("adding successful!");
           }
           else {
               System.out.println("adding failed");
           }


        }
        catch (SQLException ex){
            rez=false;
        }
        finally {
            try {
                connection.close();
                staticStatement.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }
        return rez;
    }
    public  static  boolean  addPosition(String posname, String desc){
        boolean rez=true;
        if ((posname!="") || (desc!="")){
            try{
                connection = DriverManager.getConnection(connectionString, userName, password);
                query = "insert into positions (PositionName, description) values (?, ?)";
                dynamicStatement = connection.prepareStatement(query);
                dynamicStatement.setString(1,posname);
                dynamicStatement.setString(2, desc);
                rez =  (dynamicStatement.executeUpdate()>0)?true:false;

            }
            catch (SQLException ex){
               rez=false;
               System.out.println(ex.getMessage());
            }
            finally {
                try {

                        connection.close();
                        dynamicStatement.close();
                }
                catch (SQLException ex1){
                    System.out.println(ex1.getMessage());
                }
            }
        }
        else {
            rez = false;
        }
        return rez;
    }
    public  static  boolean removePosition(String posname){
        boolean rez = true;
        if (posname!=""){
            try{
                connection = DriverManager.getConnection(connectionString, userName, password);
                query = String.format("update positions set status=false where PositionName='%s';",posname);

                staticStatement =connection.createStatement();
                rez=(staticStatement.executeUpdate(query)>0)?true:false;
            }
            catch (SQLException ex){
                System.out.println(ex.getMessage());
                rez=false;
            }
            finally {
                try{
                    connection.close();
                    staticStatement.close();
                }
                catch (SQLException ex1){
                    rez=false;
                    System.out.println(ex1.getMessage());
                }
            }
        }
        else {
            rez = false;
        }
        return rez;
    }
    public static boolean updatePosition(String posname){
        boolean rez = true;
        if (posname!=""){
            try{
                connection= DriverManager.getConnection(connectionString, userName, password);
                query ="select * from positions where PositionName='%s';";
               // query = "call addposition(%s);";
                staticStatement =connection.createStatement();
                query=String.format(query,posname);
                System.out.println(query);
                result= staticStatement.executeQuery(query);
                int data = -1;
                while (result.next()){
                    data=result.getInt(1);
                    System.out.println(data);
                }
            }
            catch (SQLException ex){
                rez=false;
                System.out.println(ex.getMessage());
            }
            finally {
                try{
                    connection.close();
                    result.close();;
                    staticStatement.close();
                }
                catch (SQLException ex1){
                    rez=false;
                    System.out.println(ex1.getMessage());
                }


            }
        }
        else {
            rez=false;
        }
        return rez;
    }
    //----------------------------------------------------------//
    //-------------------SUPPLERCOMPANIES-----------------------//
    //----------------------------------------------------------//
    public static boolean addsupplercompany(String cname, String adr, String desc, double rath)
    {
        boolean rez = true;
        try {
            connection = DriverManager.getConnection(connectionString,userName,password);
            query = "inset into supplercompanies (companyname, adress, description, rathing) values(%s, %s, %s, %d)";
            staticStatement= connection.createStatement();
            query=String.format(query,cname,adr,desc,rath);
            rez = (staticStatement.executeUpdate(query)>0)?true:false;
        }
        catch (SQLException ex){
            rez=false;
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                connection.close();
                staticStatement.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }
        return rez;
    }
    public static boolean removesupplercompany(String cname){
        boolean rez=true;
        try{
            connection = DriverManager.getConnection(connectionString, userName, password);
            query="delete from supplercompanies where companyname=%s";
            query=String.format(query,cname);
            staticStatement = connection.createStatement();
            rez = (staticStatement.executeUpdate(query)>0)?true:false;
        }
        catch (SQLException ex){
            rez=false;
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                connection.close();
                staticStatement.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }
        return rez;
    }
}
