/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class ProfileEditClass {
    
    public HelperClass helperClass = new HelperClass();
    public CredentialsClass credentialsClass;
    private String pathToLogFileFolder;
    public File fileToWriteLogsOfTesting;
    public WebDriver webDriver = null;
    public String dateTimeOfSession;
    private String osName;
    private int numberOfAdresses = 3;
    
    public ProfileEditClass(String pathToFileFolderIn, String osNameIn) {
        this.pathToLogFileFolder = pathToFileFolderIn;
        this.osName = osNameIn;
    }
    
    public void editProfile()
    {
        credentialsClass = new CredentialsClass();
        dateTimeOfSession = helperClass.getDateInStringForWindowsLinux();  
        
        String fileName = this.pathToLogFileFolder + "EditProfileLogFile_" + dateTimeOfSession + ".txt";
        System.out.println("Path to logfile:" + fileName);
        
        try {
            fileToWriteLogsOfTesting = new File(fileName);
        } catch (Exception exx) {
            System.out.println(exx.getMessage());
            System.out.println("Error file creation, testing log will be only in terminal");
        }
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Edit profile testing starts at: " + dateTimeOfSession +" OS: " + osName);
        
        try {
            webDriver = new ChromeDriver();
            //WebDriver webDriver = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)webDriver;
            webDriver.manage().window().maximize();
            webDriver.get("https://perscriptum-dev.herokuapp.com/"); 
            Thread.sleep(1500);
            WebElement login = webDriver.findElement(By.id("input-11"));
            WebElement passwd = webDriver.findElement(By.id("input-14"));
            WebElement btnLogin = webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            login.sendKeys(credentialsClass.emailToLogin);
            passwd.sendKeys(credentialsClass.passwordToLogin);
            Thread.sleep(500);
            btnLogin.click();
            Thread.sleep(2500);  
            //login to site END
            
            webDriver.get("https://perscriptum-dev.herokuapp.com/profile"); 
            Thread.sleep(2500);
            //first name
            editDataInTextInput(null, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(1) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //insertion
            editDataInTextInput(null, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(2) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //last name
            editDataInTextInput(null, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(3) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //initials
            editDataInTextInput(null, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(4) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //email
            editDataInTextInput(null, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div > div > div > div > div > div:nth-child(5) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            Thread.sleep(500);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: test switching languages"); 
            System.out.println("Work: test switching languages");
            webDriver.findElement(By.className("v-item--active")).click();
            Thread.sleep(1500);
            webDriver.findElement(By.className("v-item--active")).click();
            Thread.sleep(1500);
            
            //click on TAB contact_information
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: click on tab Contact information"); 
            System.out.println("Work: click on tab Contact information");
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text > div.v-slide-group__wrapper > div > div:nth-child(3)")).click();
            Thread.sleep(500);
            //phone number
            editDataInTextInput("123456789", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(1) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //mobile number
            editDataInTextInput("987654321", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(2) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //fax number
            editDataInTextInput("1234", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(3) > div > div.v-input__slot > div > ");
            Thread.sleep(500);
            
            //email
            WebElement inputForEmail = webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input"));
            String placeholderInEmailInput = inputForEmail.getAttribute("placeholder");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: fill data ---test2@pernexus.org--- in element " + placeholderInEmailInput); 
            System.out.println("Work: fill data ---test2@pernexus.org--- in element " +  placeholderInEmailInput);
            inputForEmail.sendKeys("test2@pernexus.org");
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: after filling found data ---" + inputForEmail.getAttribute("value") + "---\r\n"); 
            System.out.println("Work: after filling found data ---" + inputForEmail.getAttribute("value") + "---");
            Thread.sleep(500);
            
            //address - click and dropdown
            
            
//            for (int i = 1; i <= numberOfAdresses; i++) {
//                helperClass.selectOneElementFromDropdownAddressInHelper(webDriver);
//                Thread.sleep(1500);  
//                webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-item-group.theme--light.v-slide-group.v-tabs-bar.primary--text > div.v-slide-group__wrapper > div > div:nth-child(3)")).click();
//            }            
//            
//            String allAddresses = helperClass.getAllAddressesOnPage(webDriver);
//            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: Data in the input Address:" + allAddresses);
//            System.out.println("Work: Data in the input Address:" + allAddresses);    
//            //input Address END
//            
//            Thread.sleep(1500);
//            
//            //input Additional (appears after filling address) START
//            for (int i = 1; i <= numberOfAdresses; i++) {
//                editDataInTextInput("" + helperClass.getRandomDigit(999, 9999), "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > form > div:nth-child(" + i + ") > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > ");                
//            } 
            //input Additional (appears after filling address) END
            Thread.sleep(1500);  
            
            //country of birth  - click and dropdown
            workWithDropdownElementCitiesNation("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(6)", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(6) > div > div.v-input__slot > div.v-select__slot > ");
            Thread.sleep(500);  
            
            //place of birth
            editDataInTextInput("PP", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(7) > div > div.v-input__slot > div > ");
            Thread.sleep(500);            
            
            //nationality  - click and dropdown
            workWithDropdownElementCitiesNation("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(8)", "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(8) > div > div.v-input__slot > div.v-select__slot > ");
            Thread.sleep(500);              
//            
            //date of birth  - 11-11-1999
            editDataInTextInput(helperClass.getDataForTest(), "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > ");
            Thread.sleep(500);          
            
            //gender  - click and dropdown
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div:nth-child(10)")).click();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: attempt to change gender"); 
            System.out.println("Work: attempt to change gender");
            Thread.sleep(500); 
            helperClass.selectOneElementFromDropdownInHeper(webDriver);
            Thread.sleep(500); 
            String valueOfGender = webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div.row > div:nth-child(2) > div > div.v-window.v-item-group.theme--light.v-tabs-items > div > div.v-window-item.v-window-item--active > div > div > div > div > div.v-input.v-input--is-label-active.v-input--is-dirty.v-input--is-focused.theme--light.v-text-field.v-text-field--is-booted.v-select.primary--text > div > div.v-input__slot > div.v-select__slot > div.v-select__selections > div")).getText();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: gender property: ---" + valueOfGender + "---"); 
            System.out.println("Work: gender property: ---" + valueOfGender + "---");
            
            Thread.sleep(500);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: try to click save button"); 
            System.out.println("Work: try to click save button");
            webDriver.findElement(By.cssSelector("#inspire > div > main > div > div > div > div:nth-child(2) > div > div > header > div > button")).click();
            Thread.sleep(1500);
            //webDriver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/header/div/button[2]")).click();
            
            //find div container with message result of saving START
            String systemMessage = helperClass.getSystemMessage(webDriver);
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: system message: " + systemMessage); 
            System.out.println("Work: system message: " + systemMessage);
            //find div container with message result of saving END
            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END"); 
            System.out.println("Work: END");
            Thread.sleep(15000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            webDriver.close();
            webDriver.quit();
        }
    }

    private void editDataInTextInput(String dataToFill, String cssSelectorOfElement) throws InterruptedException {
        WebElement inputToEditValueLabel = webDriver.findElement(By.cssSelector(cssSelectorOfElement + "label"));
        String textInLabel = inputToEditValueLabel.getText();
        WebElement inputToEditValue = webDriver.findElement(By.cssSelector(cssSelectorOfElement + "input"));
        String inputValue;
        if (dataToFill != null) {
            inputValue = dataToFill;
        } else {
            inputValue = inputToEditValue.getAttribute("value");
        }
         
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: found in element " + textInLabel + "  data: ---" + inputToEditValue.getAttribute("value") + "---"); 
        System.out.println("Work: found in element: " + inputToEditValue.getAttribute("value"));
        System.out.println("Check : inputToEditValue.getAttribute(\"value\").length()=" + inputToEditValue.getAttribute("value").length());
        
        if (inputToEditValue.getAttribute("value").length() > 0) {
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: clear data in element"); 
            System.out.println("Work: clear data in element");

            inputToEditValue.sendKeys(Keys.CONTROL + "a");
            Thread.sleep(500);
            inputToEditValue.sendKeys(Keys.DELETE);
            Thread.sleep(500);
        }
        
        
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: fill data ---" + inputValue + "--- in element " + textInLabel); 
        System.out.println("Work: fill data ---" + inputValue + "--- in element " +  textInLabel);
        //System.out.println("Str 239");
        inputToEditValue.sendKeys(inputValue);
        //System.out.println("Str 241");
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: after filling found data ---" + inputToEditValue.getAttribute("value") + "---\r\n"); 
        //System.out.println("Str 243");
        System.out.println("Work: after filling found data ---" + inputToEditValue.getAttribute("value") + "---\r");
        //System.out.println("Str 245");
        
    }

    private void workWithDropdownElementCitiesNation(String elementToClickCss, String labelAndInput) throws InterruptedException {
        WebElement inputToEditValueLabel = webDriver.findElement(By.cssSelector(labelAndInput + "label"));
        String textInLabel = inputToEditValueLabel.getText();
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: attempt to change dropdown ---" + textInLabel + "---"); 
        System.out.println("Work: attempt to change dropdown ---" + textInLabel + "---");
        //WebElement inputToEditValue = webDriver.findElement(By.cssSelector(labelAndInput + "input"));
        WebElement divToEditValue = webDriver.findElement(By.cssSelector(labelAndInput + "div"));
        webDriver.findElement(By.cssSelector(elementToClickCss)).click();
        Thread.sleep(500);
        helperClass.selectOneElementFromDropdownInHeper(webDriver);
        Thread.sleep(500);
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: after changing found data ---" + divToEditValue.getText() + "---\r\n"); 
        System.out.println("Work: after changing found data ---" + divToEditValue.getText() + "---");        
    }
    
    private void jsInThread()
    {
        JavascriptExecutor js = (JavascriptExecutor) webDriver; 
        String docInfoVal = (String) js.executeAsyncScript("" +
                "var done = arguments[0]; " +
                "getCurrentDocumentInfo(\"somestuff\"," +
                    "function(docId) {" +
                        "done(docId);" +
                    "}" +
                ");");
    }
    
}
