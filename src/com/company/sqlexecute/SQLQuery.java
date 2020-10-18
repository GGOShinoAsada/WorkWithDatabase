package com.company.sqlexecute;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;

import java.io.InputStream;

public class SQLQuery {


   static Document  document;
    static String UserName;
    static  String Password;
    static String URL;
    static String Driver;
    static String Properties;
    static String ConnectionString;
    public static String getDriverName(){
        return Driver;
    }
    public static String getUserName(){
        return UserName;
    }
    public static String getPassword(){
        return Password;
    }
    public static String getUrlDatabase(){
        return URL;
    }
    public static String getProperties(){
        return Properties;
    }
    public static String getConnectionString(){
        return URL+"?"+Properties;
    }
    public static void init(){

        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/company/config.xml");
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = (Document) documentBuilder.parse("src/com/company/config.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            UserName = (String)xpath.compile("//config//jdbc//username").evaluate(document, XPathConstants.STRING);
            Password = (String)xpath.compile("//config//jdbc//password").evaluate(document, XPathConstants.STRING);
            URL = (String)xpath.compile("//config//jdbc//url").evaluate(document,XPathConstants.STRING);
            Driver = (String)xpath.compile("//config//jdbc//driver").evaluate(document,XPathConstants.STRING);
            Properties =(String)xpath.compile("//config//jdbc//connectionproperties").evaluate(document,XPathConstants.STRING);

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

        public static void printvales(){
        System.out.println("Values");
        System.out.println("DriverName: "+Driver);
        System.out.println("UserName: "+UserName);
        System.out.println("Password"+Password);
        System.out.println("URL database: "+URL);
        System.out.println("Properties: "+Properties);
        }
    }
