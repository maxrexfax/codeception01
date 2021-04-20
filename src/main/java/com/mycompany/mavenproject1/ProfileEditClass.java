/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author user
 */
public class ProfileEditClass {
    
    public HelperClass helperClass = new HelperClass();
    
    public void editProfile()
    {
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
            
            // 
            browser.findElement(By.cssSelector("#inspire > div > header > div > button.mr-1.v-btn.v-btn--icon.v-btn--round.theme--dark.v-size--default")).click();
            Thread.sleep(500);            
            browser.findElement(By.cssSelector("#materialpro > div.v-menu__content.theme--light.v-menu__content--fixed.menuable__content__active > div > div")).click();
            Thread.sleep(15000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }
    
}
