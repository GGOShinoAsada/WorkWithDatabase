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
            query="update computerstore.supplercompanies set Status=false where companyname=%s";
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
    public static boolean updatesupplercompany(String oldCname, String newcname, String adr, String desc, double rath,boolean stat)
    {
        boolean rez = true;
        int id_t = -1;
        try {
            connection=DriverManager.getConnection(connectionString, userName, password);
            query = "select id from supplercompanies where companyname=%s";
            query=String.format(query,oldCname);
            staticStatement=connection.createStatement();
            result=staticStatement.executeQuery(query);
            while (result.next()){
                id_t=result.getInt(1);
            }
            result.close();
            if (id_t>0){
                query="update supplercompanies set CompanyName=?, Description=?, Adress=?,Rathing=?, Status=?";
                dynamicStatement=connection.prepareStatement(query);
                dynamicStatement.setString(1,newcname);
                dynamicStatement.setString(2,desc);
                dynamicStatement.setString(3,adr);
                dynamicStatement.setDouble(4,rath);
                dynamicStatement.setBoolean(5,stat);
                rez=(dynamicStatement.executeUpdate()>0)?true:false;
            }
            else {
                System.out.println("SupplerCompany not found. Please check correction input values");
                rez=false;
            }
        }
        catch (SQLException ex){
            rez =false;
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                connection.close();
                staticStatement.close();
                result.close();
            }
            catch (SQLException ex1){
               System.out.println(ex1.getMessage());
            }
        }
        return  rez;
    }
    public static boolean selectSupplerCompany(String cname){
        boolean rez=true;
        try{
            connection=DriverManager.getConnection(connectionString,userName,password);
            query="select * from supplercompanies where companyname=%s";
            query=String.format(query,cname);
            staticStatement=connection.createStatement();
            result = staticStatement.executeQuery(query);
            while (result.next()){
                System.out.println("Company name: "+result.getString(2));
                System.out.println("Company adress"+result.getString(3));
                System.out.println("Description "+result.getString(4));
                System.out.println("Rathing: "+result.getDouble(5));
                System.out.println("Status "+result.getBoolean(6));
            }
        }
        catch (SQLException ex) {
            rez=false;
            System.out.println(ex.getMessage());
        }
        finally {
            try{

                connection.close();
                staticStatement.close();
                result.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }
        return rez;
    }
    //--------------------------------------------------------//
    //---------------------DELIVERYMETHODS--------------------//
    //--------------------------------------------------------//
    public static boolean addDeliveryMethod(String dnaame, String desc){
        boolean rez=true;
        try{
            connection=DriverManager.getConnection(connectionString,userName,password);
            query="insert into deliverymethods (deliverymethod, description) values (%s, %s)";
            query=String.format(query,dnaame,desc);
            staticStatement=connection.createStatement();
            rez=(staticStatement.executeUpdate(query)>0)?true:false;
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
    public  static  boolean removedeliverymethod(String dname){
        boolean rez=true;
        int id_t=-1;
        try {
            connection=DriverManager.getConnection(connectionString,userName,password);
            query="select id from deliverymethods where deliverymethod=%s";
            query=String.format(query,dname);
            staticStatement=connection.createStatement();
            result=staticStatement.executeQuery(query);
            while (result.next()){
                id_t=result.getInt(1);
            }

            if (id_t>0){
                query="update deliverymethods set Status=false where id=?";
                dynamicStatement = connection.prepareStatement(query);
                dynamicStatement.setInt(1,id_t);
                rez=((dynamicStatement.executeUpdate())>0)?true:false;
            }
            else{
                System.out.println("Delivery method not found. Please try again");
                rez=false;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
            rez=false;
        }
        finally {
            try{
                connection.close();
                staticStatement.close();
                dynamicStatement.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }
        return rez;
    }
    public static boolean updateDeliveryMethod(String olddname, String newdnname, String desc, boolean stat){
        boolean rez=true;
        int id_t=-1;
        try{
            connection=DriverManager.getConnection(connectionString,userName,password);
            query="select id from deliverymethods where deliverymethod=%s";
            query=String.format(query,olddname);
            staticStatement=connection.createStatement();
            result=staticStatement.executeQuery(query);
            while (result.next()){
                id_t=result.getInt(1);
            }
            if (id_t>0){
                query = "update deliverymethods set DeliveryMethod=?, Description=?, Status=?";
                dynamicStatement=connection.prepareStatement(query);
                dynamicStatement.setString(1,newdnname);
                dynamicStatement.setString(2,desc);
                dynamicStatement.setBoolean(3,stat);
                rez=((dynamicStatement.executeUpdate()>0))?true:false;
            }
            else {
                System.out.println("Delivery method not found. Please check correction input values");
                rez=false;
            }

        }
        catch (SQLException ex){
           rez=false;
           System.out.println(ex.getMessage());
        }
        finally {
            try{
                connection.close();
                staticStatement.close();
                dynamicStatement.close();
                result.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }

        return rez;
    }
    public static boolean selectDeliveryMethod(String dmname){
        boolean rez=true;
        try{
            connection = DriverManager.getConnection(connectionString,userName, password);
            query="select * from deliverymethods where deliverymethod=%s";
            query=String.format(query,dmname);
            staticStatement=connection.createStatement();
            result=staticStatement.executeQuery(query);
            rez=(result.getConcurrency()>0)?true:false;
            if (rez){
                while (!result.next()){
                    System.out.println("DeliveryMethod name: "+ result.getString(2));
                    System.out.println("Description: "+result.getString(3));
                    System.out.println("Status: "+result.getDouble(4));
                }
            }
            else {
                System.out.println("No element searching");
            }

        }
        catch (SQLException ex){
            try{
                rez=false;
                result.close();
                staticStatement.close();
                connection.close();
            }
            catch (SQLException ex1){
                System.out.println(ex1.getMessage());
            }
        }


        return rez;
    }
    /*----------------------------------------------------------*/
    /*-----------------COMPANY EMPLOYEES-------------------------*/
    /*-----------------------------------------------------------*/
    public  static boolean addnewemployee(String empposition, String fname, String sname, String mname, String adress, String pnumber){
        boolean rez=true;
        String empciper="";

        int id_t=-1;
        int count_reg=0;
        try{
            connection=DriverManager.getConnection(connectionString, userName, password);
            query="select id from positions where position=?";
            dynamicStatement.setString(1,empposition);

            if (dynamicStatement.executeUpdate(query)>0){
                result=dynamicStatement.executeQuery(query);
                while (!result.next()){
                    id_t=result.getInt(1);
                }
                dynamicStatement.close();
                result.close();
                query="select count(*)+1 from employees where position=? and substring_index(cipher,'/',-1)=curdate()";
                result=dynamicStatement.executeQuery(query);
                while (!result.next()){
                   count_reg=result.getInt(1);
                }
               dynamicStatement.close();
                result.close();
                if (count_reg>0){
                    query="insert into employees (employeecipher, position , firstname, secondname, middlename, adress) values ()"
                }
                else {

                }
                query="select concat(curdate(),'/',(select count (*)+1 from employees where position=? and substring_index(cipher,'/',-1)=curdate()) ))"
            }
            else {
                System.out.println("Position not found. Please try again");
            }
            result.close();

            if (id_t>0){
                query="insert into employees (emp)"
            }
        }
        catch (SQLException ex){

        }
        return rez;
    }
}
