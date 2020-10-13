package com.company;
import com.company.sqlexecute.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
	    SqlExecute.setDataBaseName("computerstore");
	    SqlExecute.createConnectionString();
	    SqlExecute.setUser("root");

	    SqlExecute.setPassword("0000");
	    String con_succ=SqlExecute.getConnectionStatus()?"y":"n";
	    System.out.println("connection succes: "+con_succ);
	    //System.out.println(SqlExecute.GetConnectionStatus());
    }
}
