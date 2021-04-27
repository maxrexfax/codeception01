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
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class DeleteCandidatesClass {
    
    public HelperClass helperClass = new HelperClass();
    public WebDriver browser = null;
    public CredentialsClass credentialsClass;
    
    public File fileToWriteLogsOfTesting;
    public String dateTimeOfSession;
    private String pathToLogFileFolder;
    private String osName;
    
    public DeleteCandidatesClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    public void deleteUser()
    {
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();  
        
        String fileName = this.pathToLogFileFolder + "DeleteCandidateLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            //fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, testing log will be only in terminal");
        }
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Delete Candidate testing starts at: " + dateTimeOfSession +" OS: " + osName);
        
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
            System.out.println("Change options to delete user");
            System.out.println("Delete one random user - 1");
            System.out.println("Delete user(s) by ID or NAME - 2");
            int res = getNuberOfAnswer();
            switch (res) {
                case 1:
                    deleteOneRandomUser();
                    break;
                case 2:
                    deleteUserByData();
                    break;
                default:
                    System.out.println("Wrong choise, exiting");
                    break;
            }
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END"); 
            System.out.println("Work: END");
            Thread.sleep(5000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }
    
    private int getNuberOfAnswer() throws IOException
    {
        System.out.println("Enter number here:");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return Integer.parseInt(br.readLine());
    }
    
    private String getStringAnswer() throws IOException
    {
        System.out.println("Enter number here:");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    private void deleteOneRandomUser() throws InterruptedException {
        Thread.sleep(500);
        browser.get("https://perscriptum-dev.herokuapp.com/candidates");
        Thread.sleep(2500);        
        
        WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        Thread.sleep(500);
        List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
        
        //START sort by id
        List<WebElement> listOfFlagsToSort = listOfTableRows.get(0).findElements(By.tagName("th"));
        listOfFlagsToSort.get(1).click();             
        Thread.sleep(500);
        listOfFlagsToSort.get(1).click();
        Thread.sleep(2500);
        //END sort by id
        
        System.out.println("listOfTableRows.size()=" + listOfTableRows.size());       
        Thread.sleep(500);
        int oneRandomUseNumberToDelete = helperClass.getRandomDigit(1, listOfTableRows.size());
        
        System.out.println("oneRandomUseNumberToDelete=" + oneRandomUseNumberToDelete);        
        //reload list of trs
        tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        listOfTableRows = tableContainer.findElements(By.tagName("tr"));
        
        List<WebElement> listOfTableDatasInTr = listOfTableRows.get(oneRandomUseNumberToDelete).findElements(By.tagName("td"));
        System.out.println("listOfTableDatasInTr.get(1).getText()=" + listOfTableDatasInTr.get(1).getText()); 
        int idToDeleteIdAndCheck = Integer.parseInt(listOfTableDatasInTr.get(1).getText());
        listOfTableDatasInTr.get(0).click();
        System.out.println("deleteIdToCheck=" + idToDeleteIdAndCheck);     
        System.out.println("==== Name to delete=" + listOfTableDatasInTr.get(2).getText());             
        Thread.sleep(1000);
        
        WebElement deleteButton =  helperClass.safeFindElement(browser,"#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.red--text","cssSelector");
        helperClass.safeClickOnElement(deleteButton);
        
        Thread.sleep(1000);
        WebElement modalWindowDialog = helperClass.safeFindElement(browser, "v-dialog--active", "className");
        List<WebElement> listOfButtonsInModal = modalWindowDialog.findElements(By.tagName("button"));
        System.out.println("listOfButtonsInModal.get(1).getText()=" + listOfButtonsInModal.get(1).getText());
        listOfButtonsInModal.get(1).click();
        Thread.sleep(2000);
        
        //Check if delete was successful
        WebElement tableContainerAfterDelete = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        listOfTableRows = tableContainer.findElements(By.tagName("tr"));
        boolean isDeleted = true;
        for (int i = 1; i < listOfTableRows.size(); i++) {
            List<WebElement> tmpListOfTableDatasInTr = listOfTableRows.get(i).findElements(By.tagName("td"));
            if (Integer.parseInt(tmpListOfTableDatasInTr.get(1).getText()) == idToDeleteIdAndCheck) {
                isDeleted = false;
                System.out.println("Deleted id found again!");   
            }
        }
        
        if (isDeleted) {
            System.out.println("Candidate deleted successfully");  
        } else {
            System.out.println("ERROR delete Candidate");  
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        Thread.sleep(5000);
    }

    private void deleteUserByData() throws IOException, InterruptedException {
        //CHANGE SORT BY ID START
        WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        Thread.sleep(500);
        List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
        List<WebElement> listOfFlagsToSort = listOfTableRows.get(0).findElements(By.tagName("th"));
        listOfFlagsToSort.get(1).click();             
        Thread.sleep(500);
        listOfFlagsToSort.get(1).click();
        Thread.sleep(2500);
        //CHANGE SORT BY ID END
        
        String[] dataToFindUsers = null;
        System.out.println("Delete user(s) by ID - 1");
        System.out.println("Delete user(s) by name - 2");
        int answer = getNuberOfAnswer();
        if (answer == 1) {
            System.out.println("Enter user(s) ID(s) separated by commas");            
        } else if (answer == 2) {
            System.out.println("Enter user(s) name(s) separated by commas");
        } else {
            System.out.println("Something went wrong");
            return;
        }
        dataToFindUsers = getStringAnswer().split(",");
        markAndDeleteUsers(dataToFindUsers, answer);
        
    }    

    private void markAndDeleteUsers(String[] dataToFindUsers, int answer) throws InterruptedException {
        
        WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
        for (int i = 1; i < listOfTableRows.size(); i++) {
            List<WebElement> listOfTableDatas = listOfTableRows.get(i).findElements(By.tagName("td"));
            if (answer == 1) {
                if(Arrays.stream(dataToFindUsers).anyMatch(listOfTableDatas.get(answer).getText()::equals)) {
                    listOfTableDatas.get(0).click();
                }
            }
        }
        
        WebElement deleteButton =  helperClass.safeFindElement(browser,"#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > button.v-btn.v-btn--text.theme--light.v-size--small.red--text","cssSelector");
        helperClass.safeClickOnElement(deleteButton);
        Thread.sleep(1000);
        WebElement modalWindowDialog = helperClass.safeFindElement(browser, "v-dialog--active", "className");
        List<WebElement> listOfButtonsInModal = modalWindowDialog.findElements(By.tagName("button"));
        listOfButtonsInModal.get(1).click();
        Thread.sleep(2000);
    }

    
    
}
