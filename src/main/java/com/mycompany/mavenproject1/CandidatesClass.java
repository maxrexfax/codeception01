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
    public WebDriver browser = null;
    
    public void createCandidate() throws InterruptedException{
        dateTimeOfSession = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new java.util.Date());
        String fileName = "./logs/testCandidatesCreationLogFile_" + dateTimeOfSession + ".txt";
        fileToWriteLogsOfTesting = new File(fileName);
        
        //login to site START
        String osName = System.getProperty("os.name");
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Candidate creation testing starts at: " + dateTimeOfSession +" OS: " + osName);
        if (osName.contains("Linux")) {
            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        } else if (osName.contains("Windows 10")) {
            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        }
        
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url https://perscriptum-dev.herokuapp.com");
            browser.get("https://perscriptum-dev.herokuapp.com/");
            Thread.sleep(1500); 
            helperClass.safeFindElement(browser, "#materialpro > div > div > div.d-flex.align-center.col-lg-5.col-xl-6.col-12 > div > div > div.v-item-group.theme--light.v-btn-toggle > button:nth-child(2)", "cssSelector").click();
            Thread.sleep(2500);
            WebElement login = browser.findElement(By.id("input-11"));
            WebElement passwd = browser.findElement(By.id("input-14"));
            WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            login.sendKeys("test2@pernexus.org");
            passwd.sendKeys("testtest2");
            Thread.sleep(500);            
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: trying to login with email \"test2@pernexus.org\" and password \"***\" ");
            btnLogin.click();
            Thread.sleep(1500);  
            //login to site END

            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: go to url https://perscriptum-dev.herokuapp.com/candidates");
            browser.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(3000);
            browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//create new candidate
            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/label")).click();
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[3]/label")).click();
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[4]/label")).click();
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[1]/label")).click();
            Thread.sleep(1500);
            numberOfCandidate = helperClass.getRandomDigit(1, 4);        
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div["+ numberOfCandidate +"]/label")).click();
            Thread.sleep(500);
            //System.out.println("Click to check if menu Add person contact info");
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/div")).click();//menu Add person contact info
            Thread.sleep(500);

            String firstName = "FirstName_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > input, String dataToFill", firstName);
            Thread.sleep(100);
            String secondName = "SecondName_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > input", secondName);
            Thread.sleep(100);
            String lastName = "LastName_" + helperClass.getRandomDigit(99,999);
            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > input", lastName);
            Thread.sleep(100);
//            String dataToFillInInput = "II_" + helperClass.getRandomDigit(99,999);
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(5) > div > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(100);
//            int randomDay = helperClass.getRandomDigit(1,28);
//            String rndDayStr = (randomDay < 10) ? "0" + randomDay : String.valueOf(randomDay);
//            int randomMonth = helperClass.getRandomDigit(1,12);
//            String rndMonthStr = (randomMonth < 10) ? "0" + randomMonth : String.valueOf(randomMonth);
//            dataToFillInInput = "" + rndDayStr + "-" + rndMonthStr + "-" + helperClass.getRandomDigit(1960,2005);
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(100);
//            dataToFillInInput = "City_" + helperClass.getRandomDigit(99,999);
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(100);
//            dataToFillInInput = "123456789";
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(10) > div > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(100);
//            dataToFillInInput = "email_" + helperClass.getRandomDigit(99,999) + "@mail.com";
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(500);
//            helperClass.selectOneElementFromDropdownAddressInHelper(browser);
//            Thread.sleep(500);
//            dataToFillInInput = "" + helperClass.getRandomDigit(999,9999);
//            fillInputWithData("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > input", dataToFillInInput);
//            Thread.sleep(500);
//            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8)")).click();
//            Thread.sleep(500);
//            helperClass.selectOneElementFromDropdownInHeper(browser);
//            Thread.sleep(500);
//            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(9)")).click();
//            Thread.sleep(500);
//            helperClass.selectOneElementFromDropdownInHeper(browser);  
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[5]/div")).click();//menu Add candidate info
//            Thread.sleep(500);
//            setInfoInThirdStage(numberOfCandidate, browser);
//            Thread.sleep(500);
//            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
//            Thread.sleep(500);
//            WebElement inputWithCompanies = browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[8]/div/div/div"));//dropdown with companies
//            inputWithCompanies.click();
//            Thread.sleep(500);
//            WebElement tagsList = browser.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
//            Thread.sleep(500);
//            helperClass.selectOneElementFromDropdownInHeper(browser);        
            //Thread.sleep(1000);
           // browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
            Thread.sleep(1000);//
            String fullName = lastName + ", " + firstName + " " + secondName;
            System.out.println("fullName= ---" + fullName + "---");
            Thread.sleep(5000);
            
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > header > div > button.v-btn.v-btn--text.theme--dark.v-size--default")).click();
            System.out.println(" ============================= Click to save ");
            Thread.sleep(5000);
//            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: pressed button save, checking if saving successfully");
//            WebElement searchInput = helperClass.safeFindElement(browser, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > header > div > div.v-toolbar__title > div > div > div > div.v-text-field__slot > input", "cssSelector");
//            Thread.sleep(500);
//            searchInput.sendKeys(Keys.CONTROL + "a");
//            Thread.sleep(500);
//            searchInput.sendKeys(Keys.DELETE);
//            Thread.sleep(500);
//            WebElement containerOnThePage = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
//            List<WebElement> resultTrsOnThePage = containerOnThePage.findElements(By.tagName("tr"));
//            
//            searchInput.sendKeys(fullName);
//            boolean isUserDataFound = false;
//            for (int i = 1; i < resultTrsOnThePage.size(); i++) {
//                List<WebElement> listOfTableDatas = resultTrsOnThePage.get(i).findElements(By.tagName("td"));
//                if (listOfTableDatas.get(2).getText().contains(fullName)) {
//                    isUserDataFound = true;
//                }
//            }
//            
//            if (isUserDataFound){
//                    System.out.println("Work: User saved successfully!");
//                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: User saved successfully!"); 
//                } else {
//                    System.out.println("Work: User saving FAILED!");
//                    helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: User saving FAILED!"); 
//                }
            helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: END");            
            Thread.sleep(15000);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }

    private void setInfoInThirdStage(int numberOfCandidate, WebDriver browser) throws InterruptedException {
        switch (numberOfCandidate) {
            case 1:
                //
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div:nth-child(1) > div > div.v-input__slot > div > input")).sendKeys("TestRefData_" + helperClass.getRandomDigit(99,999));
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.v-input--is-label-active.v-input--is-dirty.theme--light.v-text-field.v-text-field--is-booted.v-select")).click();
                helperClass.selectOneElementFromDropdownInHeper(browser);
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div.v-input.theme--light.v-input--selection-controls.v-input--switch > div > div.v-input__slot > label")).click();
                break;
            case 2:
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div")).click();
                helperClass.selectOneElementFromDropdownInHeper(browser);
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
                break;
            case 3:
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div > div.v-input__slot > div > input")).sendKeys("TestData_" + helperClass.getRandomDigit(99,999));
                break;
            case 4:
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(6) > div > div > div > div.v-input.mt-5.v-input--dense.theme--light.v-text-field.v-text-field--single-line.v-text-field--is-booted.v-text-field--enclosed.v-text-field--outlined.v-select.v-select--chips.v-select--chips--small.v-select--is-multi.v-autocomplete")).click();
                helperClass.selectOneElementFromDropdownInHeper(browser);
                browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div.v-stepper__step.v-stepper__step--active.v-stepper__step--editable")).click();
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
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: trying to fill first name with data ---" + dataToFill + "---");
        WebElement inputFirstName = browser.findElement(By.cssSelector(cssSelectorString));
        inputFirstName.sendKeys(dataToFill);
        try{
            Thread.sleep(500);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Check if data in input is equals to ---" + dataToFill + "---");
        System.out.println(checkInputDataFunction(inputFirstName, dataToFill));
        helperClass.writeStringToFile(fileToWriteLogsOfTesting, "Work: check filling of the data ---" + dataToFill + "--- result: " + checkInputDataFunction(inputFirstName, dataToFill));
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
    
    
}
