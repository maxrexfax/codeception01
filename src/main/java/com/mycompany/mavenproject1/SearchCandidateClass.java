/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class SearchCandidateClass {
    
    public int numberOfLoops;
    public int counter;
    public HelperClass helperClass = new HelperClass();
    public WebDriver browser = null;
    public String[] iDs;
    public String[] Names;
    public String[] Statuses;
    public String[] References;
    public String[] DatesOfBirth;
    public boolean isTestGoOn;
    public String dateTimeOfSession;
    public File fileToWriteLogsOfTesting;
    public CredentialsClass credentialsClass;
    private String pathToLogFileFolder;
    private String osName;
    InputStreamReader isr;
    BufferedReader br;
            
    public SearchCandidateClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    public void searchCandidate()
    {        
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();    
        String fileName = "";
        counter = 1;
        
        fileName = this.pathToLogFileFolder + "testCandidatesSearchLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        fileToWriteLogsOfTesting = new File(fileName);
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Candidate search testing starts at: " + dateTimeOfSession +" OS: " + osName);
        System.out.println("Set number of loops - 1");
        System.out.println("Dont set number of loops (by default=100) - 2");
        isr = new InputStreamReader(System.in);
        br = new BufferedReader(isr);
        int res = tryToGetInt();
        
        if (res == 1) {
            System.out.println("Enter number of loops: 1 - 10000");
            numberOfLoops = tryToGetInt();
        } else {
            numberOfLoops = 100;
        }
        
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            browser.get("https://perscriptum-dev.herokuapp.com/"); 
            Thread.sleep(1500);
            WebElement login = helperClass.safeFindElement(browser, "input-11", "id");
            WebElement passwd = helperClass.safeFindElement(browser, "input-14", "id");
            WebElement btnLogin = helperClass.safeFindElement(browser, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");
            login.sendKeys(credentialsClass.emailToLogin);
            passwd.sendKeys(credentialsClass.passwordToLogin);
            Thread.sleep(500);
            helperClass.safeClickOnElement(btnLogin);
            Thread.sleep(2500);  
            //login to site END
           
            browser.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(1500);
            helperClass.safeFindElement(browser, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > div > div.v-data-footer > div.v-data-footer__select > div > div > div", "cssSelector").click();
            helperClass.selectOneElementFromDropdownInHeperAlt(browser, 0);//переключение количества юзеров на странице на последнее значение
            Thread.sleep(2500);
            WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
            Thread.sleep(500);
            List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
            //System.out.println("listOfTableRows.size()=" + listOfTableRows.size());
            iDs = new String[listOfTableRows.size()];
            Names = new String[listOfTableRows.size()];
            Statuses = new String[listOfTableRows.size()];
            References = new String[listOfTableRows.size()];
            DatesOfBirth = new String[listOfTableRows.size()];
            
            for (int i = 1; i < listOfTableRows.size(); i++) {
                List<WebElement> listOfTableDatas = listOfTableRows.get(i).findElements(By.tagName("td"));
                iDs[i] = listOfTableDatas.get(1).getText();
                Names[i] = listOfTableDatas.get(2).getText();
                Statuses[i] = listOfTableDatas.get(3).getText();
                References[i] = listOfTableDatas.get(5).getText();
                DatesOfBirth[i] = listOfTableDatas.get(6).getText();
            }
            //printArray(Names);
            
            isTestGoOn = true;
            //startThread(); 
            System.out.println("Results of testing will be stored in file " + fileName);
            checkDataOnThePage();
//            
//            System.out.println("Press Enter to quit");
//            String answ = br.readLine();
//            if (answ != null) {
//                isTestGoOn = false;
//            }
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END");   
            Thread.sleep(5000);            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }
    public void printArray(String[] arrayToPrint)
    {        
        for (int i = 0; i < arrayToPrint.length; i++) {            
            System.out.print(arrayToPrint[i] + " - ");
        }        
        System.out.println();
    }
    
    private int tryToGetInt(){
        int res = 0;
        try {
                res = Integer.parseInt(br.readLine());
            } catch (Exception ex) {
                System.out.println("Error");
                System.out.println(ex.getMessage());
                res = 2;
            }
        if(res < 1 || res > 100000) {
            res = 10;
        }
        return res;
    }
    
    public void startThread()
    {
        Thread thread = new Thread(){
            public void run(){            
            }
        };
        thread.start();
    }
    
    public String getCurrentTime()
    {
        return new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
    }

    private void checkDataOnThePage() {
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Search testing starting at: " + dateTimeOfSession);
            //System.out.println("Search testing starting now");
            WebElement searchInput = helperClass.safeFindElement(browser, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > div.v-toolbar__title > div > div > div > div.v-text-field__slot > input", "cssSelector");
            WebElement containerOnThePage;
            List <WebElement> resultTrsOnThePage;
            do {      
                
                if (counter > numberOfLoops) {
                    isTestGoOn = false;
                }
                try{
                    Thread.sleep(1000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(isTestGoOn){
                    System.out.println("Test N:" + counter + " of:" + numberOfLoops);
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Test N:" + counter + " from:" + numberOfLoops);
                    int type = helperClass.getRandomDigit(1,2);
                    int index = helperClass.getRandomDigit(1, (iDs.length - 1));
                    String dataToSend = "";
                    if (type == 1) {
                        dataToSend = iDs[index];
                    } else if (type == 2) {
                        dataToSend = Names[index];
                    } else if (type == 3) {
                        dataToSend = Statuses[index];
                    }  
                    
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Running test search for data ---" + dataToSend + "---  time:" + getCurrentTime());
                    //System.out.println("Running test search for data=" + dataToSend);
                    searchInput.sendKeys(Keys.CONTROL + "a");
                    try{
                        Thread.sleep(500);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }                    
                    searchInput.sendKeys(Keys.DELETE);
                    //searchInput.clear();
                    try{
                        Thread.sleep(500);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    helperClass.safeFillInput(searchInput, dataToSend);
                    try{
                        Thread.sleep(2000);
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    containerOnThePage = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
                    resultTrsOnThePage = containerOnThePage.findElements(By.tagName("tr")); 
                    List<WebElement> listOfTableHeads = resultTrsOnThePage.get(0).findElements(By.tagName("th"));
                    
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "For search request ---" + dataToSend + "--- site returns:" + (resultTrsOnThePage.size() - 1) + " results");
                    //System.out.println("Results for ---" + dataToSend + "--- found:" + (resultTrsOnThePage.size() - 1));
                    for (int i = 1; i < resultTrsOnThePage.size(); i++) {
                        List<WebElement> listOfTableDatas = resultTrsOnThePage.get(i).findElements(By.tagName("td"));
                        if (listOfTableDatas.get(type).getText().contains(dataToSend)) {
                            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "CHECKING: String ---" + dataToSend + "---  in column ----" + listOfTableHeads.get(type).getText() + "--- was found on page. Success!");
                            //System.out.println("String ---" + dataToSend + "--- used for search was found on page. Success!");
                        } else {
                            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "CHECKING: String ---" + dataToSend + "--- in column ---" + listOfTableHeads.get(type).getText() + "--- not found");
                            //System.out.println("Data ---" + dataToSend + "--- not found");
                        }
                    }
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "\r");
                }
                counter++;
              } while (isTestGoOn); 
    }
    
}
