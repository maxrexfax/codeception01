/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 *
 * @author user
 */
public class WorkClass {
    
    public final String appName = "«Solid Sulutions automatic site testing»";
    public String pathToLogFile;
    final int CANDIDATES = 1;
    final int ALL_CANDIDATES = 2;
    final int LOCATIONS = 3;
    final int COMPANIES = 4;
    final int PROFILE_EDIT = 5;
    final int DELETE_USER = 6;
    final int SORT_USER = 7;
    final int SEARCH_USER = 8;
    final int SCHEMAS = 9;
    final int MANY_REQUESTS_TO_PAGE = 10;
    final int TEST_ONE_URL = 11;
    final int TEST_ONE_URL_CONSOLE = 12;
    final int EXIT = 19;
    public String[] typeNames = new String[20]; 
    public String osName;
    
    public void startWork() throws IOException {        
        fillClassData();
        int res = 0;
        // TODO code application logic here
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        do {            
            System.out.println();
            System.out.println("1 - Testing of One random Candidate creation");
            System.out.println("2 - Testing of ALL 4 TYPES Candidates creation");
            System.out.println("3 - Testing of Locations creation");
            System.out.println("4 - Testing of Company creation");
            System.out.println("5 - Testing of Profile editing");
            System.out.println("6 - Testing of User delete");
            System.out.println("7 - Testing of User sorting");
            System.out.println("8 - Testing of User search");
            System.out.println("9 - Testing of Schemas creation");
            System.out.println("10 - Test one page AFTER login on site");
            System.out.println("11 - Test one URL with many requests (NO LOGIN, Browser)");
            System.out.println("12 - Send many requests to URL TERMINAL (no login, no browser)");
            System.out.println("19 - EXIT");
            System.out.println("Enter digit and press Enter");
            
            try {
                res = Integer.parseInt(br.readLine());
            } catch (Exception ex) {
                System.out.println("Error");
                System.out.println(ex.getMessage());
                res = 0;
            }
            
            int[] allowedNumbers  = {0, LOCATIONS, SCHEMAS, CANDIDATES, COMPANIES, PROFILE_EDIT, DELETE_USER, SORT_USER, SEARCH_USER, ALL_CANDIDATES, MANY_REQUESTS_TO_PAGE, TEST_ONE_URL, TEST_ONE_URL_CONSOLE, EXIT};

            if (!contains(allowedNumbers, res)) {
                res = 1;
                System.out.println("Error data, choise set to 1!");
            } else {
                System.out.println("Chosen number:" + res + " and type of testing: " + typeNames[res]);
            }

            try {
                switch (res){
                    case CANDIDATES:
                        CandidatesClass candidatesClass = new CandidatesClass(pathToLogFile, osName);
                        candidatesClass.createCandidate();
                        break;
                    case ALL_CANDIDATES:
                        CandidatesClass[] candidatesClassArr = new CandidatesClass[4];
                        for (int i = 0; i < 4; i++) {
                            candidatesClassArr[i] = new CandidatesClass(pathToLogFile, osName, (i + 1));
                            candidatesClassArr[i].createCandidate();
                        }
                        break;
                    case LOCATIONS:
                        LocationsClass locationsClass = new LocationsClass(pathToLogFile, osName);
                        locationsClass.createLocation();
                        break;
                    case COMPANIES:
                        CompaniesClass companiesClass = new CompaniesClass(pathToLogFile, osName);
                        companiesClass.createCompany();
                        break;
                    case PROFILE_EDIT:
                        ProfileEditClass profileEditClass = new ProfileEditClass(pathToLogFile, osName);
                        profileEditClass.editProfile();
                        break;
                    case DELETE_USER:
                        DeleteCandidatesClass deleteCandidatesClass = new DeleteCandidatesClass(pathToLogFile, osName);
                        deleteCandidatesClass.deleteUser();
                        break;
                    case SORT_USER:
                        SortCandidatesClass sortCandidatesClass = new SortCandidatesClass();
                        sortCandidatesClass.sortCandidates();
                        break;
                    case SEARCH_USER:
                        SearchCandidateClass searchCandidateClass = new SearchCandidateClass(pathToLogFile, osName);
                        searchCandidateClass.searchCandidate();
                        break;
                    case SCHEMAS:
                        SchemasClass schemasClass = new SchemasClass(pathToLogFile, osName);
                        schemasClass.createSchema();
                        break;
                    case MANY_REQUESTS_TO_PAGE:
                        ManyRequestsClass manyRequestsClass = new ManyRequestsClass(pathToLogFile, osName);
                        manyRequestsClass.startTest();
                        break;
                    case TEST_ONE_URL:
                        TestOneUrlClass testOneUrlClass = new TestOneUrlClass(pathToLogFile, osName);
                        testOneUrlClass.testUrl();
                        break;
                    case TEST_ONE_URL_CONSOLE:
                        JavaCheckWebPageLoadClass javaWebRequestClass = new JavaCheckWebPageLoadClass(pathToLogFile, osName);
                        javaWebRequestClass.checkWebPage();
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
                        System.out.println("The application " + appName + " is shutting down");
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
    
    public boolean contains(final int[] arr, final int key) 
    {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
    public void fillClassData() throws IOException
    {
        osName = System.getProperty("os.name");
        File theDirectoryForLogFiles = null;
        if (osName.contains("Linux")) {
            System.out.println("OS Linux detected. Trying to create a folder for log files in the same folder as the executable file");
            System.out.println("Please make sure the appropriate chromedriver exists along the path /usr/bin/chromedriver"); 
            System.out.println("Visit URL https://chromedriver.chromium.org/downloads to download if you have no chromedriver!"); 
            pathToLogFile = "./logs/";            
            
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");    
            
        } else if (osName.contains("Windows")) {
            System.out.println("Please make sure the appropriate chromedriver exists along the path C:\\users\\public\\documents\\chromedriver.exe"); 
            System.out.println("OS Windows detected, try to create log folder in C:\\users\\public\\documents\\logs");
            pathToLogFile = "C:\\users\\public\\documents\\logs\\";            
            
            System.setProperty("webdriver.chrome.driver", "C:\\users\\public\\documents\\chromedriver.exe");  
            
        } else {
            pathToLogFile = "./";
        }
        
        try {
            theDirectoryForLogFiles = new File(pathToLogFile);
            theDirectoryForLogFiles.mkdirs();
            System.out.println("Create folder for log files at path " + pathToLogFile);
        } catch (Exception ex) {
            System.out.println("ERROR with creation of the folder for log files at path " + pathToLogFile);            
        }
       
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
        typeNames[10] = "Many requests with login";
        typeNames[11] = "Many requests on ONE URL";
        typeNames[12] = "Many requests TERMINAL";
        typeNames[19] = "EXIT!";
    }
}
