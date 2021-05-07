/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 *
 * @author user
 */
public class CandidatesCreateFormCheckClass {
    private int phoneNumber = 1234567890;
    private int numberOfCandidate = 0;
    private HelperClass helperClass = new HelperClass();
    private CredentialsClass credentialsClass;
    private File fileToWriteLogsOfTesting;
    private File fileToWriteErrorLogOfTesting;
    private WebDriver webDriver = null;
    private String dateTimeOfSession;
    private String mainUrl = "https://perscriptum-dev.herokuapp.com/";
    private String urlToTest = "https://perscriptum-dev.herokuapp.com/candidates/";
    private String[] candidateTypes = {"", "candidates", "assessors", "employees", "contacts"};
    private String pathToLogFileFolder;
    private String osName;
    private String firstName = "FN_" + UUID.randomUUID().toString() + "_" + helperClass.getRandomDigit(99,999);
    private String lastName = "LN_" + UUID.randomUUID().toString() + "_" + helperClass.getRandomDigit(99,999);
    String fullName = lastName + ", " + firstName;
    
    boolean isMainCycleWorks = true;//условие выхода из цикла проверок инпутов
    
    public CandidatesCreateFormCheckClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;        
        this.numberOfCandidate = helperClass.getRandomDigit(1, 4);        
    }
    
    
    public CandidatesCreateFormCheckClass(String pathToFileFolderIn, String osNameIn, int typeOfUser) {
        this.numberOfCandidate = typeOfUser;
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
        if (numberOfCandidate == 0) {//if is not set then random
            this.numberOfCandidate = helperClass.getRandomDigit(1, 4);
        } else if (numberOfCandidate < 1 || numberOfCandidate > 4) {
            this.numberOfCandidate = 1;
        } else {
            this.numberOfCandidate = helperClass.getRandomDigit(1, 4);
        }
    }
    
    public void startCheckValidation() {
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();    
        String fileName = "";
        String fileNameERRORS = "";
        
        fileName = this.pathToLogFileFolder + "testCandidatesCreationInputsValidationLogFile_" + dateTimeOfSession + ".txt";
        fileNameERRORS = this.pathToLogFileFolder + "testCandidatesCreationInputsValidationLogFile_ERRORS_" + dateTimeOfSession + ".txt";        
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
            System.out.println("Path to logfile:" + fileName);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, test log will be only in terminal");
        }
        
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Candidate creation INPUTS VALIDATION testing starts at: " + dateTimeOfSession +" OS: " + osName);
        
        try {
            if(WorkClass.CURRENT_BROWSER == WorkClass.CHANGE_CHROME_BROWSER) {
                    webDriver = new ChromeDriver();
                } else {
                    webDriver = new FirefoxDriver();
                }
            
            loginOnSite();
            goToWorkUrl();
            fillAllInputsWithNormalData();            
            Thread.sleep(1000);
            //теперь надо по очереди пытаться сохранить нового юзера с неправильными данными.
            //First name
            try {                
                checkMinMaxAndInvalidDataSaveToElement(3, 333, "!@#$%^&*()[]{}", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > input", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > label", "cssSelector", webDriver);
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //Insertion
            try {                
                checkMinMaxAndInvalidDataSaveToElement(3, 333, "!@#$%^&*()[]{}", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > input", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > label", "cssSelector", webDriver);
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //Last name
            try {                
                checkMinMaxAndInvalidDataSaveToElement(3, 333, "!@#$%^&*()[]{}", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > input", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > label", "cssSelector", webDriver);
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //Place of birth
            try {                
                checkMinMaxAndInvalidDataSaveToElement(3, 333, "!@#$%^&*()[]{}", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > input", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > label", "cssSelector", webDriver);
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //Additional field in address
            try {                
                checkMinMaxAndInvalidDataSaveToElement(3, 333, "!@#$%^&*()[]{}", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > input", "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > label", "cssSelector", webDriver);
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //EMAIL
            try {                
                checkEmailSaving();
                Thread.sleep(300); 
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform test with First name", ex.getMessage());
            }             
            Thread.sleep(500);
            //find userID by user fullName START
//            String currentUrl = webDriver.getCurrentUrl();
//            final int idOfCreatedUser = findIdOfUserByFullName();
//            webDriver.get(currentUrl);
//            System.out.println("ID of saved user =" + idOfCreatedUser);
            //find userID by user fullName END



            //Проверка отличается для 1) input type=text 2) input-dropdown ADDRESS 3) input-dropdown (Cities Nation) 4) input-dropdown TAGS
//            do {
//                if(isMainCycleWorks){
//                    //проверка полей с required - если не заполнены, кнопка "СОХРАНИТЬ" должна быть неактивна, плюс все правила для обычных инпутов
//                    //проверка для обычного инпута - если введены данные МЕНЬШЕ минимальных, БОЛЬШЕ максимальных или НЕПРАВИЛЬНЫЕ символы, то по клику на кнопку 
//                    //СОХРАНИТЬ возникает ошибка, но не сообщение об удачном сохранении
//                    //проверка работы адреса - если введены не латинская буква или цифра, то в контейнере pac-container pac-logo не должно быть детей
//                    Thread.sleep(5000);
//                }
//                } while (isMainCycleWorks);
            
        Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            helperClass.printToFileAndConsoleInformation(fileToWriteErrorLogOfTesting, "ERROR: Error in main try block of CandidatesCycleClass");
        } finally {
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: END");
            webDriver.close();
            webDriver.quit();
        }
    }

    private void fillAllInputsWithNormalData() throws InterruptedException {
        //first name        
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, firstName, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(300); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill first name", ex.getMessage());
        }            

        //second name
        String secondName = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, secondName, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(300); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill second name", ex.getMessage());
        }

        //last name        
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, lastName, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(300); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill last name", ex.getMessage());
        }

        //initials
        String dataToFillInInput = "II_" + helperClass.getRandomDigit(99,999);
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(5) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(300); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill initials", ex.getMessage());
        }  

        //date of birth
        dataToFillInInput = helperClass.getFormattedDateForTest();
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(300); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill date of birth", ex.getMessage());
        } 

        //city
        dataToFillInInput = "City_" + helperClass.getRandomDigit(99,999);
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill city", ex.getMessage());
        } 

        //phone
        dataToFillInInput = "1234" + helperClass.getRandomDigit(9999,99999);
        try {                
            helperClass.editDataInTextInputWithLabel(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8) > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill phone", ex.getMessage());
        } 

        //email
        dataToFillInInput = "email_" + helperClass.getRandomDigit(99,999) + "@mail.com";
        try {                
            helperClass.fillOneInput(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input", fileToWriteLogsOfTesting, "Email");
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill email", ex.getMessage());
        } 

        try {
            //address 
            WebElement mapElement = helperClass.safeFindElement(webDriver, "map", "id");
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to fill element  " + helperClass.leftDemarkator + mapElement.getAttribute("placeholder") + helperClass.rightDemarkator);            
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: filling random address using external service");
            mapElement.click();
            Thread.sleep(500);    
            helperClass.selectOneElementFromDropdownAddressInHelper(webDriver);
            Thread.sleep(300);
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: filled address(') is: " + helperClass.getAllAddressesOnPage(webDriver) + "\n");
            Thread.sleep(300);   
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill address", ex.getMessage());
        } 

        Thread.sleep(300);   
        //Additional field in address
        dataToFillInInput = "" + helperClass.getRandomDigit(999,9999);
        try {           
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to fill Additional field in address");     
            helperClass.editDataInTextInputWithLabel(webDriver, dataToFillInInput, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > ", fileToWriteLogsOfTesting, null);
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill Additional field in address", ex.getMessage());
        }            

        try {
            WebElement thirdPunktOfMenu = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(5)", "cssSelector");
            WebElement thirdPunktOfMenySpan = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(5) > div > span", "cssSelector");
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: click on " + thirdPunktOfMenySpan.getText());
            helperClass.safeClickOnElement(thirdPunktOfMenu);
            Thread.sleep(500);
            setInfoInThirdStage(numberOfCandidate, webDriver);
            Thread.sleep(500);               
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill data in third part of menu", ex.getMessage());
        }

        try {
            Thread.sleep(500);
            WebElement fourthPunktOfMenu = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(7)", "cssSelector");
            WebElement fourthPunktOfMenuDivText = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(7) > div", "cssSelector");
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: click on " + fourthPunktOfMenuDivText.getText());
            helperClass.safeClickOnElement(fourthPunktOfMenu);
            Thread.sleep(500);
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to click on fourth part of menu", ex.getMessage());
        }

        try {
            WebElement inputWithCompanies = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[8]/div/div/div"));//dropdown with companies
            inputWithCompanies.click();
            Thread.sleep(500);
            WebElement tagsList = webDriver.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHelper(webDriver, fileToWriteLogsOfTesting);        
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill Companies", ex.getMessage());
        }  
        Thread.sleep(500);  
    }
    
    private void setInfoInThirdStage(int numberOfCandidate, WebDriver webDriver) throws InterruptedException {
        switch (numberOfCandidate) {
            case 1:
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 1..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div:nth-child(1) > div > div.v-input__slot > div > input")).sendKeys("TestRefData_" + helperClass.getRandomDigit(99,999));
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--is-booted.v-select")).click();
                helperClass.selectOneElementFromDropdownInHelper(webDriver, fileToWriteLogsOfTesting);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                break;
            case 2:
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 2..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div")).click();
                helperClass.selectOneElementFromDropdownInHelper(webDriver, fileToWriteLogsOfTesting);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
                Thread.sleep(500);
                break;
            case 3:
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 3..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div > div.v-input__slot > div > input")).sendKeys("TestData_" + helperClass.getRandomDigit(99,999));
                Thread.sleep(500);
                break;
            case 4:
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 4..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div.v-input.mt-5.v-input--dense.theme--light.v-text-field.v-text-field--single-line.v-text-field--is-booted.v-text-field--enclosed.v-text-field--outlined.v-select.v-select--chips.v-select--chips--small.v-select--is-multi.v-autocomplete")).click();
                helperClass.selectOneElementFromDropdownInHelper(webDriver, fileToWriteLogsOfTesting);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
                Thread.sleep(500);
                break;
        }
    }

    private void loginOnSite() throws InterruptedException {
        //login to site START
        JavascriptExecutor js = (JavascriptExecutor)webDriver;
        webDriver.manage().window().maximize();
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url https://perscriptum-dev.herokuapp.com");
        webDriver.get(mainUrl);
        Thread.sleep(1500); 
        helperClass.safeFindElement(webDriver, "#materialpro > div > div > div.d-flex.align-center.col-lg-5.col-xl-6.col-12 > div > div > div.v-item-group.theme--light.v-btn-toggle > button:nth-child(2)", "cssSelector").click();
        Thread.sleep(2500);
        WebElement login = webDriver.findElement(By.id("input-11"));
        WebElement passwd = webDriver.findElement(By.id("input-14"));
        WebElement btnLogin = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
        login.sendKeys(credentialsClass.emailToLogin);
        passwd.sendKeys(credentialsClass.passwordToLogin);
        Thread.sleep(500);            
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: trying to login with email \"test2@pernexus.org\" and password \"***\" ");
        btnLogin.click();
        Thread.sleep(1500);  
        //login to site END
    }

    private void goToWorkUrl() throws InterruptedException {
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: go to url " + mainUrl + "candidates");
        webDriver.get(mainUrl + "candidates");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//create new candidate
        Thread.sleep(500);
        //Click on 4 types of candidates START
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/label")).click();
//        Thread.sleep(500);
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[3]/label")).click();
//        Thread.sleep(500);
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[4]/label")).click();
//        Thread.sleep(500);
//        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[1]/label")).click();
        //Click on 4 types of candidates END
        
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: start to create type of user: " + candidateTypes[numberOfCandidate]);
        webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div["+ numberOfCandidate +"]/label")).click();
        Thread.sleep(300);
        try {
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to click on Add person contact info");
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/div")).click();//menu Add person contact info
            Thread.sleep(300);
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to click on Add person contact info", ex.getMessage());
        }
    }

    private void clickOnSaveButton() {
        try {
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--default")).click();
                Thread.sleep(1500);
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: pressed button save, waiting for system message");
                Thread.sleep(1000);
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to click save button", ex.getMessage());
            }
    }

    private int findIdOfUserByFullName() throws InterruptedException {
        Thread.sleep(2500);
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "\r\nWork: Redirencting to " + mainUrl + candidateTypes[numberOfCandidate]);
        goToCorrespondingUrl(numberOfCandidate);
        try {
                WebElement searchInput = helperClass.safeFindElement(webDriver, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > div.v-toolbar__title > div > div > div > div.v-text-field__slot > input", "cssSelector");
                Thread.sleep(500);
                searchInput.sendKeys(Keys.CONTROL + "a");
                Thread.sleep(500);
                searchInput.sendKeys(Keys.DELETE);
                Thread.sleep(500);
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: filling user credentials:   " + helperClass.leftDemarkator + fullName + helperClass.rightDemarkator + " into the search field...");
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Trying to find on page user with credentials: " + helperClass.leftDemarkator + fullName + helperClass.rightDemarkator + " to check is data saved id DB..."); 
                searchInput.sendKeys(fullName);
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill date in Search input after saving user", ex.getMessage());
            }
        int indexOfColumnWithId = -1;
        int indexOfColumnWithName = -1;
        try {
                Thread.sleep(1500);
                WebElement containerOnThePage = helperClass.safeFindElement(webDriver, "v-data-table__wrapper", "className");
                List<WebElement> listOfTableHeads = containerOnThePage.findElements(By.tagName("th"));
                
                for (int i = 0; i < listOfTableHeads.size(); i++) {
                    if (listOfTableHeads.get(i).getText().contains("ID")) {
                        indexOfColumnWithId = i;
                        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Number of column with ID=" + indexOfColumnWithId);
                    } 
                    if (listOfTableHeads.get(i).getText().contains("Name") || listOfTableHeads.get(i).getText().contains("Naam")) {
                        indexOfColumnWithName = i;
                        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Number of column with name=" + indexOfColumnWithName);
                    }
                }
                Thread.sleep(5000);
                List<WebElement> resultTrsOnThePage = containerOnThePage.findElements(By.tagName("tr"));
                if (resultTrsOnThePage.size() > 0) {
                    for (int i = 1; i < resultTrsOnThePage.size(); i++) {
                        List<WebElement> listOfTableDatas = resultTrsOnThePage.get(i).findElements(By.tagName("td"));
                        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Looking for fullName=" + fullName); 
                        if (listOfTableDatas.get(indexOfColumnWithName).getText().contains(fullName)) {
                            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: User id=" + listOfTableDatas.get(indexOfColumnWithId).getText()); 
                            return Integer.parseInt(listOfTableDatas.get(indexOfColumnWithId).getText());
                        } else {
                            System.out.println("This iteration DO NOT contains saved user data");
                        }
                    }
                }
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to perform search on the web page", ex.getMessage());
            }
        return 0;
    }
    
    private void goToCorrespondingUrl(int numberOfCandidate) {
        String postfixUrl = "";
        switch (numberOfCandidate) {
            case 1: 
                postfixUrl = "candidates";
            break;
            case 2: 
                postfixUrl = "assessors";
            break;
            case 3: 
                postfixUrl = "employees";
            break;
            case 4: 
                postfixUrl = "contacts";
            break;            
        }
        webDriver.get(mainUrl + postfixUrl);
    }

    private String tryToSaveAndGetErrorOrSuccessMessage() throws InterruptedException {
        clickOnSaveButton();
        Thread.sleep(1000);
        String[] dataArrayMessage = helperClass.getSystemMessageWithClasses(webDriver);
        if (dataArrayMessage != null) {
            System.out.println("Message=" + dataArrayMessage[0] +  "  classes=" + dataArrayMessage[1]);
            if (dataArrayMessage[1].contains("success")) {
                System.out.println("Class success was found");
                return "success";
            } else if (dataArrayMessage[1].contains("error"))  {
                System.out.println("Class error was found");
                return "error";
            }
        }
        return "EMPTY";    
    }
    
    private String tryToGetErrorOrSuccessMessage() throws InterruptedException {
        Thread.sleep(1000);
        String[] dataArrayMessage = helperClass.getSystemMessageWithClasses(webDriver);
        if (dataArrayMessage != null) {
            System.out.println("Message=" + dataArrayMessage[0] +  "  classes=" + dataArrayMessage[1]);
            if (dataArrayMessage[1].contains("success")) {
                System.out.println("Class success was found");
                return "success";
            } else if (dataArrayMessage[1].contains("error"))  {
                System.out.println("Class error was found");
                return "error";
            }
        }
        return "EMPTY";    
    }

    private void checkMinMaxAndInvalidDataSaveToElement(int minimalStringToFillLength, int maximumStringToFillLength, String forbiddenSymbols, 
        String xpathOrSelectorOrTagForInput, String xpathOrSelectorOrTagForLabel, String typeOfIdentifier, WebDriver webDriver) throws InterruptedException {
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "\r\nWork: Start test of validation");   
        clickOnAddPersonalData();
        Thread.sleep(500); 
        //Tests of ONE input validation. All other inputs are filled with normal data. Tested input would be filled with minimum, maximum and invalid data
        WebElement elementToFillWithTestData = helperClass.safeFindElement(this.webDriver, xpathOrSelectorOrTagForInput, typeOfIdentifier);
        WebElement labelOfTheElementToFillWithTestData = helperClass.safeFindElement(this.webDriver, xpathOrSelectorOrTagForLabel, typeOfIdentifier);
        String textOnLabel = labelOfTheElementToFillWithTestData.getText();
        //тут цикл с заполнением инпута данными минимальными, попытка сохранить, если сохранило, то вернуть на страницу создания еще одного юзера и опять тест
        try {           
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "\r\nWork: try to test filling with less than minimal data");   
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to fill to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " string data that is shorter than allowed (" + minimalStringToFillLength + " symbols)");
            testFillingInputWithData(labelOfTheElementToFillWithTestData, elementToFillWithTestData, (minimalStringToFillLength - 1));
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test filling with less than minimal length of data", ex.getMessage());
        }   
        Thread.sleep(500);
        try {           
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to check if saving work");   
            checkIfSavingWorked();
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test if saving work", ex.getMessage());
        }
        Thread.sleep(500);
        
        
        //теперь с максимальными данными
        try {           
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "\r\nWork: try to test filling with more than maximal length of data");   
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to fill to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " string data that is longer than allowed (" + maximumStringToFillLength + " symbols)");
            testFillingInputWithData(labelOfTheElementToFillWithTestData, elementToFillWithTestData, (maximumStringToFillLength + 1));
            Thread.sleep(500); 
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test filling with less than maximal length of data", ex.getMessage());
        }   
        Thread.sleep(500);
        try {           
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to check if saving work");   
            checkIfSavingWorked();
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test if saving work", ex.getMessage());
        }
        Thread.sleep(500); 
        
        //теперь с недопустимыми данными - вот тут может быть большой цикл
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "\r\nWork: Start test with forbidden symbols: " + forbiddenSymbols);
        String[] arrayOfForbiddenSymbols = forbiddenSymbols.split("");
        for (int i = 0; i < arrayOfForbiddenSymbols.length; i++) {
            try {           
                Thread.sleep(500); 
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to fill to field forbidden symbol: " + helperClass.leftDemarkator + arrayOfForbiddenSymbols[i] + helperClass.rightDemarkator);                
                fillInputWithMinimalLenghtAndOneForbiddenSymbol(labelOfTheElementToFillWithTestData, elementToFillWithTestData, (minimalStringToFillLength + 1), arrayOfForbiddenSymbols[i]);
                Thread.sleep(500);
                clickOnSaveButton();
                Thread.sleep(500);
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test filling with forbidden symbol " + helperClass.leftDemarkator + arrayOfForbiddenSymbols[i] + helperClass.rightDemarkator, ex.getMessage());
            }   
            Thread.sleep(500);
            try {           
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to check if saving work");   
                checkIfSavingWorked();
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to test if saving work", ex.getMessage());
            }
            Thread.sleep(500); 
        }
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: End of test with input " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + "\r\n");
    }

    private void checkIfSavingWorked() throws InterruptedException {
        WebElement divContainerWithInput = helperClass.safeFindElement(webDriver, "v-dialog--active", "className");
        if (divContainerWithInput == null) {//сохранение отработало - валидация НЕ СРАБОТАЛА!!! - логируем этот фейл
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: ERROR! Data was saved, error validation!!");
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Return on user creation page");
            goToWorkUrl();
            Thread.sleep(500);
            fillAllInputsWithNormalData();
            Thread.sleep(500);    
            clickOnAddPersonalData();
            Thread.sleep(500); 
        } else {
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Save refusale! Validation success! Try to get system message: " + helperClass.getSystemMessage(webDriver));
        }
    }

    private void testFillingInputWithData(WebElement labelOfTheElementToFillWithTestData, WebElement elementToFillWithTestData, int lengthOfStringToFill) throws InterruptedException {
        String textOnLabel = labelOfTheElementToFillWithTestData.getText();
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Start tests with field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + "  and clear all data in it");
        helperClass.clearTextInsideInput(elementToFillWithTestData);
        String dataWithDefinedLength = helperClass.getRandomLengthString(lengthOfStringToFill);
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to fill to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " string data with length (" + lengthOfStringToFill + " symbols)    " + helperClass.leftDemarkator + dataWithDefinedLength + helperClass.rightDemarkator);
        elementToFillWithTestData.sendKeys(dataWithDefinedLength);        
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: After sending data to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " found such data "  + helperClass.leftDemarkator + elementToFillWithTestData.getAttribute("value") + helperClass.rightDemarkator);
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to click on SAVE BUTTON, awaiting save refusal");
        clickOnSaveButton();
    }

    private void fillInputWithMinimalLenghtAndOneForbiddenSymbol(WebElement labelOfTheElementToFillWithTestData, WebElement elementToFillWithTestData, int lengthOfMessage, String oneForbiddenSymbol) throws InterruptedException {
        String textOnLabel = labelOfTheElementToFillWithTestData.getText();
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Start tests with field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + "  and clear all data in it");
        helperClass.clearTextInsideInput(elementToFillWithTestData);
        String dataWithDefinedLength = helperClass.getRandomLengthString(lengthOfMessage) + oneForbiddenSymbol;
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to fill to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " with forbidden symbol  " + helperClass.leftDemarkator + oneForbiddenSymbol + helperClass.rightDemarkator);
        elementToFillWithTestData.sendKeys(dataWithDefinedLength);        
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: After sending data to field   " + helperClass.leftDemarkator + textOnLabel + helperClass.rightDemarkator + " found such data "  + helperClass.leftDemarkator + elementToFillWithTestData.getAttribute("value") + helperClass.rightDemarkator);
        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to click on SAVE BUTTON, awaiting save refusal");        
    }

    private void clickOnAddPersonalData() throws InterruptedException {
        Thread.sleep(500);
        try {
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: Try to click on Add person contact info");
            webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(3)")).click();//menu Add person contact info
        } catch (Exception ex) {
            helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to click on Add person contact info", ex.getMessage());
        }
        Thread.sleep(1000);
    }

    private void checkEmailSaving() {
        String dataToFillInInput = "email_" + helperClass.getRandomDigit(99,999) + "@mail.com";  
            helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: fill EMAIL " + dataToFillInInput);
            try {
                //email
                WebElement inputForEmail = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div.v-input.v-input--has-state.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder.error--text > div.v-input__control > div.v-input__slot > div > input", "cssSelector");
                String placeholderInEmailInput = inputForEmail.getAttribute("placeholder");System.out.println("224");
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: (EMAIL) fill data " + helperClass.leftDemarkator + dataToFillInInput + helperClass.rightDemarkator + " in element " + placeholderInEmailInput); 
                inputForEmail.sendKeys(dataToFillInInput);
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: after filling found data " + helperClass.leftDemarkator + inputForEmail.getAttribute("value") + helperClass.rightDemarkator + "\r\n"); 
                helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: try to click on SAVE EMAIL BUTTON"); 
                Thread.sleep(500);
                try {
                    WebElement buttonToSaveEmail = webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div.v-input__append-outer > div > button"));
                    buttonToSaveEmail.click();
                    helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: click on SAVE EMAIL BUTTON"); 
                    //теперь надо проверять что данные появились на странице
                    if(checkIfOnPageAppearedFilledEmail(dataToFillInInput)) {
                        helperClass.printToFileAndConsoleInformation(fileToWriteLogsOfTesting, "Work: email " + helperClass.leftDemarkator + dataToFillInInput + helperClass.rightDemarkator + " was saved"); 
                    }
                } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to click on SAVE EMAIL BUTTON", ex.getMessage());
            }
                Thread.sleep(500);
            } catch (Exception ex) {
                helperClass.writeErrorsToFiles(fileToWriteLogsOfTesting, fileToWriteErrorLogOfTesting, "ERROR: Unable to fill input for email", ex.getMessage());
            }
    }

    private boolean checkIfOnPageAppearedFilledEmail(String emailToFindOnPage) {
        List<WebElement> listOfElements = webDriver.findElements(By.className("value"));
        boolean isOnPageSavedEmail = false;
        if (listOfElements.size() > 0) {
            for (int i = 0; i < listOfElements.size(); i++) {
                if(listOfElements.get(i).getText().contains(emailToFindOnPage)) {
                    isOnPageSavedEmail = true;
                }
            }
        }
        return isOnPageSavedEmail;
    }
}
