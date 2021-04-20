/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
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
public class CandidatesClass {
    
    public int phoneNumber = 1234567890;
    public int numberOfCandidate = 0;
    public HelperClass helperClass = new HelperClass();
    public void createCandidate() throws InterruptedException{
        
        //login to site START
        String osName = System.getProperty("os.name");
        if (osName.contains("Linux")) {
            System.out.println("Set webdriver.chrome.driver from path /usr/bin/chromedriver");
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); 
        } else if (osName.contains("Windows 10")) {
            System.out.println("Set webdriver.chrome.driver from path C:\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe"); 
        }
        WebDriver browser = null;
        try {
            browser = new ChromeDriver();
            //WebDriver browser = new FirefoxDriver();
            JavascriptExecutor js = (JavascriptExecutor)browser;
            browser.manage().window().maximize();
            browser.get("https://perscriptum-dev.herokuapp.com/"); 
            WebElement login = browser.findElement(By.id("input-11"));
            WebElement passwd = browser.findElement(By.id("input-14"));
            WebElement btnLogin = browser.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button"));
            login.sendKeys("test2@pernexus.org");
            passwd.sendKeys("testtest2");
            Thread.sleep(500);
            btnLogin.click();
            Thread.sleep(1500);  
            //login to site END

            browser.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(2000);
            browser.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/main/div/div/div/div[2]/div/div/div/div/header/div/button[2]")).click();//menu Choose person type
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[2]/label")).click();
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[3]/label")).click();
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[4]/label")).click();
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div[1]/label")).click();
            Thread.sleep(500);
            numberOfCandidate = helperClass.getRandomDigit(1, 4);        
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[2]/div/div/div/div/div[1]/div/div["+ numberOfCandidate +"]/label")).click();
            Thread.sleep(500);
            //System.out.println("Click to check if menu Add person contact info");
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[3]/div")).click();//menu Add person contact info
            Thread.sleep(500);

            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(2) > div > div.v-input__slot > div > input")).sendKeys("FirstName001");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(3) > div > div.v-input__slot > div > input")).sendKeys("Insertion001");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(4) > div > div.v-input__slot > div > input")).sendKeys("LastName001");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(5) > div > div.v-input__slot > div > input")).sendKeys("III");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.v-input.theme--light.v-text-field.v-text-field--is-booted.v-text-field--placeholder > div > div.v-input__slot > div > input")).sendKeys("25-11-1980");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(7) > div > div.v-input__slot > div > input")).sendKeys("PlaceOfBirth");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div:nth-child(8) > div > div.v-input__slot > div > input")).sendKeys("123456789");
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > div.row > div > form > div > div.v-input__control > div.v-input__slot > div > input")).sendKeys("email@email.com");
            Thread.sleep(500);
            WebElement mapInput = browser.findElement(By.id("map"));
            mapInput.click();
            mapInput.sendKeys(helperClass.getRandChar());
            Thread.sleep(1000);
            helperClass.selectOneElementFromDropdownAddressInHelper(browser);
            Thread.sleep(500);
            browser.findElement(By.cssSelector("#materialpro > div.v-dialog__content.v-dialog__content--active > div > div > div > div:nth-child(4) > div > div > form > div.row > div.col-sm-4.col-md-2.col-12 > div > div > div.v-input__slot > div > input")).sendKeys("1234");
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[5]/div")).click();//menu Add candidate info
            Thread.sleep(500);
            setInfoInThirdStage(numberOfCandidate, browser);
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
            Thread.sleep(500);
            WebElement inputWithCompanies = browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[8]/div/div/div"));//dropdown with companies
            inputWithCompanies.click();
            Thread.sleep(500);
            WebElement tagsList = browser.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
            Thread.sleep(500);
            helperClass.selectOneElementFromDropdownInHeper(browser);        
            Thread.sleep(1000);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div/div[7]/div")).click();//menu attach_to_companies
            Thread.sleep(500);
            browser.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/header/div/button[2]")).click();
            Thread.sleep(5000);
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
    
    
}
