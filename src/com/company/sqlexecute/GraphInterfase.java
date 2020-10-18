package com.company.sqlexecute;



import java.util.Scanner;

public class GraphInterfase {
    private static boolean f = true;
    static Scanner scanner = new Scanner(System.in);
    public static void genemu(){
        boolean isexit = false;
        boolean flag=true;
        while (flag)  {
            System.out.println("Please select table");
            System.out.println("1. SupplerCompanies");
            System.out.println("2. DeliveryMethods");
            System.out.println("3. Employees");
            System.out.println("4. Positions");
            System.out.println("5. Products");
            System.out.println("6. OrderStatus");
            System.out.println("7. Orders");
            System.out.println("0. Quit");
            String t = scanner.next();
            boolean data = true;
            while (data)  {
                try {
                    int n=Integer.parseInt(t);
                    if (!((n>=0)&&(n<=7)))

                        data=true;
                    else
                    {
                        getinterface(n);
                        data=false;
                    }

                }
                catch (Exception ex) {
                    System.out.println("input error. Please try again");
                    System.out.println(ex.getMessage());
                }
            }

        }


    }

    public static void getinterface(int index){
        System.out.println("Please select option:");
        System.out.println("A: adding new product");
        System.out.println("R: removing exists product");
        System.out.println("U: updating exists product");
        System.out.println("S: select parameters");
        System.out.println("Q: quit");
        String t = scanner.next();
        //t - type, index - number operation
        switch (index){
            case 1:
                String cname="";String desc="";String adress="";
                double rath=0.0;
                boolean flag_tmp = true;
                switch (t){
                    case "A":
                        while (flag_tmp){
                            System.out.println("Insert into SupplerCompany:");
                            System.out.println("Please input company name:");
                            cname = scanner.next();
                            System.out.println("Please input adress company: ");
                            adress=scanner.next();
                            System.out.println("Please input company rathing: ");
                            rath=scanner.nextDouble();
                            System.out.println("Please input description company. This field is no mandatory");
                            desc = scanner.next();
                            if (!((cname=="")||(adress!="")))
                                flag_tmp=false;
                        }
                        if (SqlExecute.addsupplercompany(cname,adress,desc,rath)){
                            System.out.println("adding successful");
                        }
                        else {
                            System.out.println("adding failed");
                        }
                        break;
                    case "R":
                        while (flag_tmp){
                            System.out.println("Removing into SupplerCompanies");
                            System.out.println("Enter company name: ");
                            cname=scanner.next();
                            if (cname!="")
                                flag_tmp=false;
                        }
                        if (SqlExecute.removesupplercompany(cname))
                            System.out.println("Removing successful");
                        else
                            System.out.println("Removing failed");
                        break;
                    case "U":

                        break;
                    case "S":
                        break;
                }
                break;
            case 2:
                switch (t){
                    case "A":
                        break;
                    case "R":
                        break;
                    case "U":
                        break;
                    case "S":
                        break;
                }

                break;
            case 3:
                switch (t){
                    case "A":

                        //SqlExecute.addPosition();
                        break;
                    case "R":
                        break;
                    case "U":
                        break;
                    case "S":
                        break;
                }
                break;
            case 4:
                switch (t){
                    case "A":
                        break;
                    case "R":
                        break;
                    case "U":
                        break;
                    case "S":
                        break;
                }
                break;
            case 5:
                switch (t){
                    case "A":
                        break;
                    case "R":
                        break;
                    case "U":
                        break;
                    case "S":
                        break;
                }
                break;
            case 6:

                break;
            case 7:
                break;
        }

    }
    public static void executesupplercompanies(){

    }
}
