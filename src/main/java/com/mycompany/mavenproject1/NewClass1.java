/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author user
 */
public class NewClass1 {
    final static int LOCATIONS = 1;
    public static void main(String[] args) throws InterruptedException, IOException {
        // TODO code application logic here
        /*InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.print("Test Locations - enter number:");
        int res = Integer.parseInt(br.readLine());
        if (res <1 || res > 100) {
            res = 1;
        }*/
        int res = 1;
        switch (res){
            case LOCATIONS:
                LocationsClass locationsClass = new LocationsClass();
                locationsClass.createLocation();
                break;
            case 934:
                TestClass testClass = new TestClass();
                testClass.testFunction();
                break;
        }
        
        
    }
    
}
