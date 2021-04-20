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
    final static int PROFILE_EDIT = 5;
    final static int DELETE_USER = 6;
    final static int EXIT = 19;
    public static String[] typeNames = new String[20];
    
    public static void main(String[] args) throws InterruptedException, IOException 
    {
        fillClassData();
        int res = 0;
        // TODO code application logic here
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        do {
            System.out.println("Testing of Locations creation - 1");
            System.out.println("Testing of Schemas creation - 2");
            System.out.println("Testing of Candidates creation - 3");
            System.out.println("Testing of Company creation - 4");
            System.out.println("Testing of Profile editing - 5");
            System.out.println("Testing of User delete - 6");
            System.out.println("EXIT - 19");
            System.out.println("Enter digit and press Enter");
            res = Integer.parseInt(br.readLine());
            int[] allowedNumbers  = {LOCATIONS, SCHEMAS, CANDIDATES, COMPANIES, PROFILE_EDIT, DELETE_USER, EXIT, 0,};

            if (!contains(allowedNumbers, res)) {
                res = 1;
                System.out.println("Error data, choise set to 1!");
            } else {
                System.out.println("Chosen number:" + res + " and type of testing: " + typeNames[res]);
            }

            try {
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
                    case PROFILE_EDIT:
                        ProfileEditClass profileEditClass = new ProfileEditClass();
                        profileEditClass.editProfile();
                        break;
                    case DELETE_USER:
                        DeleteCandidatesClass deleteCandidatesClass = new DeleteCandidatesClass();
                        deleteCandidatesClass.deleteUser();
                        break;
                    case 0:
                        TestClass testClass = new TestClass();
                        testClass.testFunction();
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (res != EXIT);
        
    }
    
    public static boolean contains(final int[] arr, final int key) 
    {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
    public static void fillClassData()
    {
        //String osName = System.getProperty("os.name");
        //System.out.println("---" + osName + "---");
        //System.setProperty("webdriver.chrome.driver", "chromedriver"); 
//        if (osName.contains("Linux")) {
//            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
//            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
//        } else if (osName.contains("Windows 10")) {
//            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
//        }
        typeNames[0] = "Testing";
        typeNames[1] = "Locations";
        typeNames[2] = "Schemas";
        typeNames[3] = "Candidates";
        typeNames[4] = "Company";
        typeNames[5] = "Profile editing";
        typeNames[6] = "Delete user(s)";
        typeNames[19] = "EXIT!";
    }
    
}
