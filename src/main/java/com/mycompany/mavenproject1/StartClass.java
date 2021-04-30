/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import java.text.SimpleDateFormat;


/**
 *
 * @author user
 */
public class StartClass {
        
    public static final String appName = "«Solid Sulutions automatic site testing»";
    
    public static void main(String[] args) throws InterruptedException, IOException 
    {
        System.out.println(appName + " is launching");
        System.out.println("Application launch " + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
        WorkClass workClass = new WorkClass();
        workClass.startWork();   
    }
}
