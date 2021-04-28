/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
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
public class CandidatesClass {
    
    public int phoneNumber = 1234567890;
    public int numberOfCandidate = 0;
    public HelperClass helperClass = new HelperClass();
    public String dateTimeOfSession;
    public File fileToWriteLogsOfTesting;
    public File fileToWriteErrorLogOfTesting;
    public WebDriver webDriver = null;
    public String mainUrl = "https://perscriptum-dev.herokuapp.com/";
    public String[] candidateTypes = {"", "candidates", "assessors", "employees", "contacts"};
    public CredentialsClass credentialsClass;
    private String pathToLogFileFolder;
    private String osName;
    
    public CandidatesClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    
    public CandidatesClass(String pathToFileFolderIn, String osNameIn, int typeOfUser) {
        this.numberOfCandidate = typeOfUser;
    }
    
    public void createCandidate() throws InterruptedException{
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();    
        String fileName = "";
        //String fileNameERRORS = "";
        
        fileName = this.pathToLogFileFolder + "testCandidatesCreationLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
            //fileToWriteErrorLogOfTesting = new File(fileNameERRORS);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, test log will be only in terminal");
        }
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Candidate creation testing starts at: " + dateTimeOfSession +" OS: " + osName);
        
        try {
            webDriver = new ChromeDriver();
            //WebDriver webDriver = new FirefoxDriver();
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
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: trying to login with email \"test2@pernexus.org\" and password \"***\" ");
            btnLogin.click();
            Thread.sleep(1500);  
            //login to site END

            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url https://perscriptum-dev.herokuapp.com/candidates");
            webDriver.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(3000);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//create new candidate
            Thread.sleep(500);
            //Click on 4 types of candidates START
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/label")).click();
            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[3]/label")).click();
            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[4]/label")).click();
            Thread.sleep(500);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[1]/label")).click();
            //Click on 4 types of candidates END
            Thread.sleep(1500);
            if (numberOfCandidate == 0) {//if is not set then random
                numberOfCandidate = helperClass.getRandomDigit(1, 4);
            } else if (numberOfCandidate < 1 || numberOfCandidate > 4) {
                numberOfCandidate = 1;
            }
            System.out.println("Work: start to create type of user: " + candidateTypes[numberOfCandidate]);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: start to create type of user: " + candidateTypes[numberOfCandidate]);
            //  
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div["+ numberOfCandidate +"]/label")).click();
            Thread.sleep(500);
            //System.out.println("Click to check if menu Add person contact info");
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/div")).click();//menu Add person contact info
            Thread.sleep(500);
            
            String firstName = "FirstName_" + helperClass.getRandomDigit(99,999);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > label")).getText());
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > input, String dataToFill", firstName);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > label")).getText());            
            String secondName = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date())/*"SecondName_" + helperClass.getRandomDigit(99,999)*/;
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > input", secondName);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > label")).getText());            
            String lastName = "LastName_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > input", lastName);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(5) > div > div.v-input__slot > div > label")).getText());            
            String dataToFillInInput = "II_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(5) > div > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > label")).getText());            
//            int randomDay = helperClass.getRandomDigit(1,28);
//            String rndDayStr = (randomDay < 10) ? "0" + randomDay : String.valueOf(randomDay);
//            int randomMonth = helperClass.getRandomDigit(1,12);
//            String rndMonthStr = (randomMonth < 10) ? "0" + randomMonth : String.valueOf(randomMonth);
            dataToFillInInput = helperClass.getDataForTest()/*"" + rndDayStr + "-" + rndMonthStr + "-" + helperClass.getRandomDigit(1960,2005)*/;
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > label")).getText());            
            dataToFillInInput = "City_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(10) > div > div.v-input__slot > div > label")).getText());            
            dataToFillInInput = "123456789";
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(10) > div > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(100);            
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input")).getAttribute("placeholder"));
            dataToFillInInput = "email_" + helperClass.getRandomDigit(99,999) + "@mail.com";
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(500); 
            
            //address System.out.println();
            WebElement mapElement = helperClass.safeFindElement(webDriver, "map", "id");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: text in placeholder ---" + mapElement.getAttribute("placeholder") + "---");            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: filling random address using external service");
            System.out.println("Work: filling random address using external service in element " + mapElement.getAttribute("placeholder"));
            mapElement.click();
            Thread.sleep(500);    
            helperClass.selectOneElementFromDropdownAddressInHelper(webDriver);
            Thread.sleep(300);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: filled address is: " + webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-8.col-md-10.col-12 > div > span")).getText() + "\n");
            Thread.sleep(300);   
            
            
            WebElement someInfoInAddress = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > label", "cssSelector");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + someInfoInAddress.getText());
            System.out.println("Work: label text " + someInfoInAddress.getText());
            dataToFillInInput = "" + helperClass.getRandomDigit(999,9999);
            System.out.println();
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > input", dataToFillInInput);
            Thread.sleep(500);  
            
            //country of birth  
            WebElement countryOfBirthLabel = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8) > div > div.v-input__slot > div.v-select__slot > label", "cssSelector");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + countryOfBirthLabel.getText());
            WebElement countryOfBirth = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8)", "cssSelector");
            countryOfBirth.click();
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            Thread.sleep(500);
            WebElement countryOfBirthInput = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8) > div > div.v-input__slot > div.v-select__slot > input", "cssSelector");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: random selectin \nWork: filled data: ---" + countryOfBirthInput.getAttribute("value") + "---\n");
            System.out.println("Work: random selectin in country of birth \nWork: filled data: ---" + countryOfBirthInput.getAttribute("value") + "---");
            Thread.sleep(500);
            
            //nationality
            String nationality = webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(9) > div > div.v-input__slot > div.v-select__slot > label")).getText();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "195 Work: label text " + nationality);
            System.out.println("Work: label text " + nationality);
            webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(9)")).click();
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            Thread.sleep(500);
            String nationalityValue = webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(9) > div > div.v-input__slot > div.v-select__slot > input")).getAttribute("value");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "216 Work: random selectin \nWork: filled data: ---" + nationalityValue + "---\n");
            System.out.println("216 Work: random selectin \nWork: filled data: ---" + nationalityValue + "---\n");
            Thread.sleep(500);
            //webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[5]/div")).click();//menu Add candidate info
            WebElement thirdPunktOfMenu = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(5)", "cssSelector");
            WebElement thirdPunktOfMenySpan = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(5) > div > span", "cssSelector");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: click on " + thirdPunktOfMenySpan.getText());
            helperClass.safeClickOnElement(thirdPunktOfMenu);
            Thread.sleep(500);
            setInfoInThirdStage(numberOfCandidate, webDriver);
            Thread.sleep(500);
            WebElement fourthPunktOfMenu = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(7)", "cssSelector");
            WebElement fourthPunktOfMenuDivText = helperClass.safeFindElement(webDriver, "#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(7) > div", "cssSelector");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: click on " + fourthPunktOfMenuDivText.getText());
            helperClass.safeClickOnElement(fourthPunktOfMenu);
            //webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
            Thread.sleep(500);
            
           // helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: label text " + webDriver.findElement(By.cssSelector("")).getText());
            
            WebElement inputWithCompanies = webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[8]/div/div/div"));//dropdown with companies
            inputWithCompanies.click();
            Thread.sleep(500);
            WebElement tagsList = webDriver.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(webDriver);        
            Thread.sleep(1000);
            webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
            Thread.sleep(1000);//
            String fullName = lastName + ", " + firstName + " " + secondName;
            //System.out.println("fullName= ---" + fullName + "---");
            Thread.sleep(1000);            
            webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--default")).click();
            //System.out.println(" ============================= Click to save ");
            Thread.sleep(5000);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: pressed button save, waiting for system message");
            Thread.sleep(1000);
            
             //find div container with message result of saving START
            String systemMessage = helperClass.getSystemMessage(webDriver);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: system message: " + systemMessage); 
            System.out.println("Work: system message: " + systemMessage);
            //find div container with message result of saving END
            
            Thread.sleep(500);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Redirencting to " + mainUrl + candidateTypes[numberOfCandidate]);
            goToCorrespondingUrl(numberOfCandidate);            
            WebElement searchInput = helperClass.safeFindElement(webDriver, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > div.v-toolbar__title > div > div > div > div.v-text-field__slot > input", "cssSelector");
            Thread.sleep(500);
            //System.out.println("searchInput.getAttribute(\"value\").length()=" + searchInput.getAttribute("value").length());
            searchInput.sendKeys(Keys.CONTROL + "a");
            Thread.sleep(500);
            searchInput.sendKeys(Keys.DELETE);
            Thread.sleep(500);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: filling user credentials: ---" + fullName + "--- into the search field...");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Trying to find on page user with credentials: ---" + fullName + "--- to check is data saved id DB..."); 
            searchInput.sendKeys(fullName);
            Thread.sleep(1500);
            WebElement containerOnThePage = helperClass.safeFindElement(webDriver, "v-data-table__wrapper", "className");
            List<WebElement> resultTrsOnThePage = containerOnThePage.findElements(By.tagName("tr"));
            System.out.println("Work: resultTrsOnThePage.size()=" + resultTrsOnThePage.size());
            boolean isUserDataFound = false;
            if (resultTrsOnThePage.size() > 0) {
                for (int i = 1; i < resultTrsOnThePage.size(); i++) {
                    List<WebElement> listOfTableDatas = resultTrsOnThePage.get(i).findElements(By.tagName("td"));
                    System.out.println("Work: listOfTableDatas.size()=" + listOfTableDatas.size());
                    if (listOfTableDatas.size() > 1) {                        
                        System.out.println("Work: listOfTableDatas.get(2).getText()=" + listOfTableDatas.get(2).getText());
                        if (listOfTableDatas.get(2).getText().contains(fullName)) {
                            isUserDataFound = true;
                        }
                    }
                }
            }
            
            if (isUserDataFound){
                    System.out.println("Work: User saved successfully!");
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: User saved successfully!"); 
                } else {
                    System.out.println("Work: User saving FAILED!");
                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: User saving FAILED!"); 
                }
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END");            
            Thread.sleep(15000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }

    private void setInfoInThirdStage(int numberOfCandidate, WebDriver webDriver) throws InterruptedException {
        switch (numberOfCandidate) {
            case 1:
                System.out.println("Work: function setInfoInThirdStage: case 1...");
                helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 1..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div:nth-child(1) > div > div.v-input__slot > div > input")).sendKeys("TestRefData_" + helperClass.getRandomDigit(99,999));
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--is-booted.v-select")).click();
                helperClass.selectOneElementFromDropdownInHeper(webDriver);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                break;
            case 2:
                System.out.println("Work: function setInfoInThirdStage: case 2...");
                helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 2..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div")).click();
                helperClass.selectOneElementFromDropdownInHeper(webDriver);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
                Thread.sleep(500);
                break;
            case 3:
                System.out.println("Work: function setInfoInThirdStage: case 3...");
                helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 3..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div > div.v-input__slot > div > input")).sendKeys("TestData_" + helperClass.getRandomDigit(99,999));
                Thread.sleep(500);
                break;
            case 4:
                System.out.println("Work: function setInfoInThirdStage: case 4...");
                helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: function setInfoInThirdStage: case 4..."); 
                Thread.sleep(500);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div.v-input.mt-5.v-input--dense.theme--light.v-text-field.v-text-field--single-line.v-text-field--is-booted.v-text-field--enclosed.v-text-field--outlined.v-select.v-select--chips.v-select--chips--small.v-select--is-multi.v-autocomplete")).click();
                helperClass.selectOneElementFromDropdownInHeper(webDriver);
                webDriver.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
                Thread.sleep(500);
                break;
        }
    }

    private void fillInputWithData(String cssSelectorString, String dataToFill)
    {
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: trying to fill input with data ---" + dataToFill + "---");
        WebElement inputFirstName = webDriver.findElement(By.cssSelector(cssSelectorString));
        inputFirstName.sendKeys(dataToFill);
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Check if data in input is equals to ---" + dataToFill + "---");
        System.out.println(checkInputDataFunction(inputFirstName, dataToFill));
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: check filling of the data ---" + dataToFill + "--- result: " + checkInputDataFunction(inputFirstName, dataToFill) + "\n");
        //helperClass.writeStringToFile(fileToWriteLogsOfTesting, " ");
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    private String checkInputDataFunction(WebElement inputToCheck, String dataSentToInput) 
    {
        if (isInputHasString(inputToCheck, dataSentToInput)) {
            return "Input filled successfully";
        }
        return "Input text is different";
    }
    
    private boolean isInputHasString(WebElement inputToCheck, String dataSentToInput)
    {        
        return inputToCheck.getAttribute("value").contains(dataSentToInput);
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
    
    
}
