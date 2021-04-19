/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.google.common.primitives.Ints;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.IntStream;
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
    final static int SCHEMAS = 2;
    final static int CANDIDATES = 3;
    final static int COMPANIES = 4;
    public static String[] typeNames = new String[15];
    
    public static void main(String[] args) throws InterruptedException, IOException 
    {
        fillClassData();
        // TODO code application logic here
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("Test Locations creation - 1");
        System.out.println("Test Schemas creation - 2");
        System.out.println("Test Candidates creation - 3");
        System.out.println("Test Company creation - 4");
        System.out.println("Enter digit and press Enter");
        int res = Integer.parseInt(br.readLine());
        int[] values = {LOCATIONS, SCHEMAS, CANDIDATES, COMPANIES, 0};
        
        if (!contains(values, res)) {
            res = 1;
            System.out.println("Error data, choise set to 1!");
        } else {
            System.out.println("Chosen number:" + res + " and type of testing: " + typeNames[res]);
        }
        
        
        switch (res){
            case LOCATIONS:
                LocationsClass locationsClass = new LocationsClass();
                locationsClass.createLocation();
                break;
            case SCHEMAS:
                SchemasClass schemasClass = new SchemasClass();
                schemasClass.createSchema();
                break;
            case CANDIDATES:
                CandidatesClass candidatesClass = new CandidatesClass();
                candidatesClass.createCandidate();
                break;
            case COMPANIES:
                CompaniesClass companiesClass = new CompaniesClass();
                companiesClass.createCompany();
                break;
            case 0:
                TestClass testClass = new TestClass();
                testClass.testFunction();
                break;
        }
        
        
    }
    
    public static boolean contains(final int[] arr, final int key) 
    {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
    public static void fillClassData()
    {
        typeNames[0] = "Testing";
        typeNames[1] = "Locations";
        typeNames[2] = "Schemas";
        typeNames[3] = "Candidates";
        typeNames[4] = "Company";
    }
    
}
