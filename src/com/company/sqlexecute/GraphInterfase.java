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
    private static boolean status;
    private static boolean flag_st;
    private static boolean flag_tmp;
    private static String status_string;
    private static void getinterface(int index){
        System.out.println("Please select option:");
        System.out.println("A: adding new item");
        System.out.println("R: removing exists item");
        System.out.println("U: updating exists item");
        System.out.println("S: select exists item(s)");
        System.out.println("Q: quit");
        String t = scanner.next();
        //t - type, index - number operation

        switch (index){
            case 1:
                String cname="";String desc="";String adress="";String newcname="";
                double rath=-1.0;
                rewrite();
                switch (t){
                    case "A":
                        while (flag_tmp){
                            System.out.println("Insert into SupplerCompany:");
                            System.out.println("Please input company name:");
                            cname = scanner.next();
                            System.out.println("Please input adress company: ");
                            adress=scanner.next();
                            System.out.println("Please input company rating: ");
                            rath=scanner.nextDouble();
                            System.out.println("Please input description company. This field is no mandatory");
                            desc = scanner.next();
                            if (!((cname=="")&&(adress=="")&&(desc=="")&&(rath<0)))
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
                        while (flag_tmp){
                            System.out.println("Updating SupplerCompany");
                            System.out.println("Please input company name, which you need to update:");
                            cname=scanner.next();
                            System.out.println("Please input new company name: ");
                            newcname=scanner.next();
                            System.out.println("Please input company address:");
                            adress=scanner.next();
                            System.out.println("Please input description. This field is no mandatory");
                            desc=scanner.next();
                            System.out.println("Please input company rating: ");
                            while (flag_st){
                                flag_st=printStatusMenu();
                            }
                            rath = Double.parseDouble(scanner.next());
                            if (!((cname=="")&&(adress=="")&&(desc=="")&&(rath<0)))
                                flag_tmp=false;
                        }
                        if (SqlExecute.updatesupplercompany(cname,newcname,adress,desc,rath,status))
                            System.out.println("Updating successful");
                        else
                            System.out.println("Updating failed");
                        break;
                    case "S":
                        while (flag_tmp){
                            System.out.println("Selecting SupplerCompany");
                            System.out.println("Please input CompanyName for search: ");
                            cname=scanner.next();
                            if (cname!=""){
                                flag_tmp=false;
                            }
                        }
                        if (SqlExecute.selectSupplerCompany(cname))
                            System.out.println("Selecting success");
                        else
                            System.out.println("Selecting failed");
                        break;
                    case "Q":
                        System.out.println("Good luck");
                        break;
                }
                break;
            case 2:
                rewrite();
                String dmetod="";  desc=""; String newdmetod="";
                flag_tmp=true;
                switch (t){
                    case "A":

                        while (flag_tmp){
                            System.out.println("Adding delivery method");
                            System.out.println("Please input delivery method");
                            dmetod=scanner.next();
                            System.out.println("Please input description");
                            desc=scanner.next();
                            if ((dmetod!="")&&(desc!=""))
                                flag_tmp=false;
                        }
                        if (SqlExecute.addDeliveryMethod(dmetod,desc))
                            System.out.println("Adding success");
                        else
                            System.out.println("Adding failed");
                        break;
                    case "R":
                        while (flag_tmp){
                            System.out.println("Removing delivery method");
                            System.out.println("Please input delivery method: ");
                            dmetod=scanner.next();
                            if (dmetod!="")
                                flag_tmp=false;
                        }
                        if (SqlExecute.removedeliverymethod(dmetod))
                            System.out.println("Removing success");
                        else
                            System.out.println("Removing failed");
                        break;
                    case "U":
                        {
                        while (flag_tmp){
                            System.out.println("Updating delivery method");
                            System.out.println("Please input delivery method for search: ");
                            dmetod=scanner.next();
                            System.out.println("Please input new delivery method");
                            newdmetod=scanner.next();
                            System.out.println("Please input new description");
                            desc=scanner.next();
                            while (flag_st)
                               flag_st=printStatusMenu();
                            }
                            if (!((dmetod=="")&&(newdmetod=="")&&(desc=="")))
                            {
                                status=(status_string=="1")?true:false;
                                flag_tmp=false;
                            }
                        }
                        if (SqlExecute.updateDeliveryMethod(dmetod,newdmetod,desc,status))
                            System.out.println("Updating success");
                        else
                            System.out.println("Updating failed");
                        break;
                    case "S":
                        System.out.println("Select method");
                        System.out.println("Please input DeliveryMethod for searching: ");
                        dmetod=scanner.next();
                        if (dmetod==""){
                            flag_tmp=false;
                        }
                        if (SqlExecute.selectDeliveryMethod(dmetod)){
                            System.out.println("Selecting success");
                        }
                        else {
                            System.out.println("Selecting failed");
                        }
                        break;
                    case "Q":
                        System.out.println("Good luck");
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



    public static void rewrite(){
        status=true;
        flag_st=true;
        flag_tmp=true;
    }
    private static boolean printStatusMenu(){
        System.out.println("Please select status: 1-true; 0-false: ");
        status_string=scanner.next();
        if ((status_string.equals("1"))||(status_string.equals("0")))
            flag_st=false;
        return flag_st;
    }
}
