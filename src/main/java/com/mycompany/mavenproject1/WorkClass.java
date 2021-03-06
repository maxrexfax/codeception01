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
    
    public HelperClass helperClass = new HelperClass();
    public final String appName = "«Solid Sulutions automatic site testing»";
    public String pathToLogFile;
    final String ALL_CREATION = "0";
    final String CANDIDATES = "1";
    final String ALL_CANDIDATES = "2";
    final String LOCATIONS = "3";
    final String COMPANIES = "4";
    final String SCHEMAS = "5";
    final String DELETE_USER = "6";
    final String SORT_USER = "7";
    final String SEARCH_USER = "8";
    final String PROFILE_EDIT = "9";
    final String MANY_REQUESTS_TO_PAGE = "10";
    final String TEST_ONE_URL = "11";
    final String TEST_ONE_URL_CONSOLE = "12";
    final String TESTS = "18";
    final String EXIT = "19";
    final String CHANGE_BROWSER = "20";
    public String[] typeNames = new String[50]; 
    public String osName;
    public static final int CHANGE_CHROME_BROWSER = 1;
    public static final int CHANGE_FIREFOX_BROWSER = 2;
    public static int CURRENT_BROWSER = 1;
    public InputStreamReader isr;
    public BufferedReader br;
    public File fileToWriteLogsOfTesting;
    public File fileToWriteErrorLogOfTesting;
    public String dateTimeOfSession;
    
    public void startWork() throws IOException { 
        fillClassData();
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Application starts at: " + dateTimeOfSession +" OS name: " + osName);
        String res = "";
        // TODO code application logic here
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        do {
            System.out.println();
            System.out.println("0 - Testing of of ALL creation types (1, 2, 3, 4, 5)");
            System.out.println("1 - Testing of One random Candidate creation");
            System.out.println("1.1 - Testing of One random Candidate creation PLUS all inputs SCANNING");
            System.out.println("2 - Testing of ALL 4 TYPES Candidates creation");
            System.out.println("3 - Testing of Locations creation");
            System.out.println("4 - Testing of Company creation");
            System.out.println("5 - Testing of Schemas creation");
            System.out.println("6 - Testing of User delete");
            System.out.println("7 - Testing of User sorting");
            System.out.println("8 - Testing of User search");
            System.out.println("9 - Testing of Profile editing");
            System.out.println("10 - Test one page AFTER login on site");
            System.out.println("11 - Test one URL with many requests (NO LOGIN, Browser)");
            System.out.println("12 - Send many requests to URL TERMINAL (no login, no browser)");
            System.out.println();
            System.out.println("19 - EXIT");
            System.out.println("20 - Change browser (default Chrome)");
            System.out.println("Enter digit and press Enter");
            
            try {
                res = br.readLine();
            } catch (Exception ex) {
                System.out.println("Error");
                System.out.println(ex.getMessage());
                res = "1";
            }
            
//            String[] allowedNumbers  = {ALL_CREATION, LOCATIONS, SCHEMAS, CANDIDATES, COMPANIES, PROFILE_EDIT, DELETE_USER, SORT_USER, SEARCH_USER, ALL_CANDIDATES, MANY_REQUESTS_TO_PAGE, TEST_ONE_URL, TEST_ONE_URL_CONSOLE, TESTS, EXIT, CHANGE_BROWSER};
//            
//            boolean isInArray = Arrays.asList(allowedNumbers).contains(res);
//            if (!isInArray) {
//                res = "1";
//                System.out.println("Error data, choise set to 1!");
//            } else {
//                int index = Integer.parseInt(res);
//                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Chosen number:" + res + " and type of testing is: " + typeNames[index]);
//                System.out.println();
//            }

            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work started at " + helperClass.getDateInStringForWindowsLinux());
            try {
                switch (res){
                    case "1":
                        CandidatesClass candidatesClass = new CandidatesClass(pathToLogFile, osName);
                        candidatesClass.createCandidate();
                        break;
                    case "1.1":
                        CandidatesCreateFormCheckClass candidatesCycleClass = new CandidatesCreateFormCheckClass(pathToLogFile, osName);
                        candidatesCycleClass.startCheckValidation();
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
                    case SCHEMAS:
                        SchemasClass schemasClass = new SchemasClass(pathToLogFile, osName);
                        schemasClass.createSchema();
                        break;
                    case DELETE_USER:
                        DeleteCandidatesClass deleteCandidatesClass = new DeleteCandidatesClass(pathToLogFile, osName);
                        deleteCandidatesClass.deleteUser();
                        break;
                    case SORT_USER:
                        SortCandidatesClass sortCandidatesClass = new SortCandidatesClass(pathToLogFile, osName);
                        sortCandidatesClass.sortCandidates();
                        break;
                    case SEARCH_USER:
                        SearchCandidateClass searchCandidateClass = new SearchCandidateClass(pathToLogFile, osName);
                        searchCandidateClass.searchCandidate();
                        break;
                    case PROFILE_EDIT:
                        ProfileEditClass profileEditClass = new ProfileEditClass(pathToLogFile, osName);
                        profileEditClass.editProfile();
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
                    case "0":
                        CandidatesClass candidatesClass0 = new CandidatesClass(pathToLogFile, osName);
                        candidatesClass0.createCandidate(); 
                        
                        CandidatesClass[] candidatesClassArr0 = new CandidatesClass[4];
                        for (int i = 0; i < 4; i++) {
                            candidatesClassArr0[i] = new CandidatesClass(pathToLogFile, osName, (i + 1));
                            candidatesClassArr0[i].createCandidate();
                        }
                        
                        LocationsClass locationsClass0 = new LocationsClass(pathToLogFile, osName);
                        locationsClass0.createLocation();
                        
                        CompaniesClass companiesClass0 = new CompaniesClass(pathToLogFile, osName);
                        companiesClass0.createCompany();
                        
                        SchemasClass schemasClass0 = new SchemasClass(pathToLogFile, osName);
                        schemasClass0.createSchema();
                        break;
                    case "18":
                        TestClass testClass = new TestClass();
                        testClass.testFunction();     
                        break;
                    case "19":
                        helperClass.printToFileAndConsoleInformation(fileToWriteErrorLogOfTesting, "The application " + appName + " is shutting down"); 
                        helperClass.printToFileAndConsoleInformation(fileToWriteErrorLogOfTesting, "Exiting, BYE..."); 
                        break;
                    case "20":
                        changeBrowserForTesting();
                        break;
                    default:
                        helperClass.printToFileAndConsoleInformation(fileToWriteErrorLogOfTesting, "Incorrect choice..."); 
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                helperClass.printToFileAndConsoleInformation(fileToWriteErrorLogOfTesting, "ERROR: Error in SWITCH try block of WorkClass"); 
            }            
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work finished at " + helperClass.getDateInStringForWindowsLinux());
        } while (!res.contains("19"));
    }
    
    public boolean contains(final int[] arr, final int key) 
    {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }
    
    public void fillClassData() throws IOException
    {          
        String fileName = "";
        String fileNameERRORS = "";
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux(); 
        
        osName = System.getProperty("os.name");
        File theDirectoryForLogFiles = null;
        if (osName.contains("Linux")) {
            System.out.println("OS Linux detected. Trying to create a folder for log files in the same folder as the executable file");
            System.out.println("Please make sure the appropriate chromedriver exists along the path /usr/bin/chromedriver");    
            System.out.println("Please make sure the appropriate geckodriver exists along the path /usr/bin/geckodriver");  
            System.out.println("Please make sure the appropriate chromedriver exists in the folder with jar file"); 
            System.out.println("Visit URL https://chromedriver.chromium.org/downloads to download if you have no chromedriver!");             
            pathToLogFile = "./logs/";                        
            
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");      
            System.setProperty("webdriver.gecko.driver", "/usr/bin/geckodriver");
            
        } else if (osName.contains("Windows")) {
            System.out.println("Please make sure the appropriate chromedriver exists along the path C:\\users\\public\\documents\\chromedriver.exe"); 
            System.out.println("OS Windows detected, try to create log folder in C:\\users\\public\\documents\\logs");
            pathToLogFile = "C:\\users\\public\\documents\\logs\\";            
            
            System.setProperty("webdriver.chrome.driver", "C:\\users\\public\\documents\\chromedriver.exe");
            System.setProperty("webdriver.gecko.driver", "C:\\users\\public\\documents\\geckodriver.exe");
            
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
        
        fileName = pathToLogFile + "mainApplicationLog_" + dateTimeOfSession + ".txt";
        fileNameERRORS = pathToLogFile + "mainApplicationLog_ERRORS_" + dateTimeOfSession + ".txt";        
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
            System.out.println("Path to logfile:" + fileName);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, test log will be only in terminal");
        }
       
//        typeNames[0] = "ALL types of creation tests in a row";
//        typeNames[1] = "Candidate create";
//        typeNames[2] = "ALL Candidates(s) create";
//        typeNames[3] = "Locations create";
//        typeNames[4] = "Company create";
//        typeNames[5] = "Schema create";
//        typeNames[6] = "Delete user(s)";
//        typeNames[7] = "Sort user(s)";
//        typeNames[8] = "Search user(s)";
//        typeNames[9] = "Profile editing";
//        typeNames[10] = "Many requests with login";
//        typeNames[11] = "Many requests on ONE URL";
//        typeNames[12] = "Many requests TERMINAL";
//        typeNames[18] = "Testing";
//        typeNames[19] = "EXIT!";
//        typeNames[20] = "Change browser for testing";
    }

    private void changeBrowserForTesting() {
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Changing of the current browser selected");
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Current browser for testing is " + ((CURRENT_BROWSER == 1) ? "Chrome browser" : "Firefox browser"));
        System.out.println("Current browser for testing is " + ((CURRENT_BROWSER == 1) ? "Chrome browser" : "Firefox browser"));
        System.out.println("1 - Select CHROME");
        System.out.println("2 - Select FIREFOX");
        System.out.println();
        int res = 1;
        try {
                res = Integer.parseInt(br.readLine());
            } catch (Exception ex) {
                System.out.println("Error, result set to 1");
                res = 1;
            }
        if (res == 1 || res == 2) {
            CURRENT_BROWSER = res;
        } else {
            CURRENT_BROWSER = 1;
        }
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "After changing browser for testing set to " + ((CURRENT_BROWSER == 1) ? "Chrome browser" : "Firefox browser"));        
    }
}
