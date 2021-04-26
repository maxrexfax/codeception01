/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.google.common.primitives.Ints;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 *  typeNames[0] = "Testing";
        typeNames[1] = "Candidates";
        typeNames[2] = "ALL Candidates(s)";
        typeNames[3] = "Locations";
        typeNames[4] = "Company";
        typeNames[5] = "Profile editing";
        typeNames[6] = "Delete user(s)";
        typeNames[7] = "Sort user(s)";
        typeNames[8] = "Search user(s)";
        typeNames[9] = "Schemas";
 */
public class NewClass1 {
    final static int CANDIDATES = 1;
    final static int ALL_CANDIDATES = 2;
    final static int LOCATIONS = 3;
    final static int COMPANIES = 4;
    final static int PROFILE_EDIT = 5;
    final static int DELETE_USER = 6;
    final static int SORT_USER = 7;
    final static int SEARCH_USER = 8;
    final static int SCHEMAS = 9;
    final static int MANY = 10;
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
            System.out.println(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
            System.out.println("Testing of One random Candidates creation - 1");
            System.out.println("Testing of ALL 4 TYPES Candidates creation - 2");
            System.out.println("Testing of Locations creation - 3");
            System.out.println("Testing of Company creation - 4");
            System.out.println("Testing of Profile editing - 5");
            System.out.println("Testing of User delete - 6");
            System.out.println("Testing of User sorting - 7");
            System.out.println("Testing of User search - 8");
            System.out.println("Testing of Schemas creation - 9");
            System.out.println("Send many requests to site - 10");
            System.out.println("EXIT - 19");
            System.out.println("Enter digit and press Enter");
            try {
                res = Integer.parseInt(br.readLine());
            } catch (Exception ex) {
                System.out.println("Error");
                System.out.println(ex.getMessage());
                res = 0;
            }
            int[] allowedNumbers  = {0, LOCATIONS, SCHEMAS, CANDIDATES, COMPANIES, PROFILE_EDIT, DELETE_USER, SORT_USER, SEARCH_USER, ALL_CANDIDATES, MANY, EXIT};

            if (!contains(allowedNumbers, res)) {
                res = 1;
                System.out.println("Error data, choise set to 1!");
            } else {
                System.out.println("Chosen number:" + res + " and type of testing: " + typeNames[res]);
            }

            try {
                switch (res){
                    case CANDIDATES:
                        CandidatesClass candidatesClass = new CandidatesClass();
                        candidatesClass.createCandidate();
                        break;
                    case ALL_CANDIDATES:
                        CandidatesClass[] candidatesClassArr = new CandidatesClass[4];
                        for (int i = 1; i < 5; i++) {
                            candidatesClassArr[i] = new CandidatesClass(i);
                            candidatesClassArr[i].createCandidate();
                        }
                        break;
                    case LOCATIONS:
                        LocationsClass locationsClass = new LocationsClass();
                        locationsClass.createLocation();
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
                    case SORT_USER:
                        SortCandidatesClass sortCandidatesClass = new SortCandidatesClass();
                        sortCandidatesClass.sortCandidates();
                        break;
                    case SEARCH_USER:
                        SearchCandidateClass searchCandidateClass = new SearchCandidateClass();
                        searchCandidateClass.searchCandidate();
                        break;
                    case SCHEMAS:
                        SchemasClass schemasClass = new SchemasClass();
                        schemasClass.createSchema();
                        break;
                    case MANY:
                        ManyRequestsClass manyRequestsClass = new ManyRequestsClass();
                        manyRequestsClass.startTest();
                        break;
                    case 0:
                        TestClass testClass = new TestClass();
                        testClass.testFunction();
//                        TestClass[] testClassArr = new TestClass[50];
//                        for (int i = 1; i < 50; i++) {
//                            testClassArr[i] = new TestClass(i);
//                            testClassArr[i].testFunction();
//                        }
                        break;
                    case 19:
                        System.out.println("Exiting, BYE...");
                        break;
                    default:
                        System.out.println("Something went wrong in NewClass1 switch...");
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
    
    public static void fillClassData() throws IOException
    {
        String osName = System.getProperty("os.name");
        File theDir = null;
        if (osName.contains("Linux")) {
            System.out.println("OS Linux detected, try to create log folder in same folder where jar file");
            theDir = new File("./logs");
            theDir.mkdirs();
        } else if (osName.contains("Windows")) {
            System.out.println("OS Windows detected, try to create log folder in C:\\logs");
            theDir = new File("C:\\users\\public\\documents\\logs");
            theDir.mkdirs();
        }
//        try {            
//            Set<PosixFilePermission> perms = new HashSet<>();
//            perms.add(PosixFilePermission.OWNER_READ);
//            perms.add(PosixFilePermission.OWNER_WRITE);
//
//            Files.setPosixFilePermissions(theDir.toPath(), perms);
//        } catch (IOException e) {
//            System.out.println("IOException in fillClassData function");
//            System.out.println(e.getMessage());
//        } catch (Exception ex) {
//            System.out.println("Exception in fillClassData function");
//            System.out.println(ex.getMessage());
//        }
       
        typeNames[0] = "Testing";
        typeNames[1] = "Candidates";
        typeNames[2] = "ALL Candidates(s)";
        typeNames[3] = "Locations";
        typeNames[4] = "Company";
        typeNames[5] = "Profile editing";
        typeNames[6] = "Delete user(s)";
        typeNames[7] = "Sort user(s)";
        typeNames[8] = "Search user(s)";
        typeNames[9] = "Schemas";
        typeNames[10] = "Many requests";
        typeNames[19] = "EXIT!";
    }
    
}
