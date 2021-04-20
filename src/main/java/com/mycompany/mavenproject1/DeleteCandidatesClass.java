/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    
    public void deleteUser()
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
            Thread.sleep(1500);
            WebElement login = helperClass.safeFindElement(browser, "input-11", "id");
            WebElement passwd = helperClass.safeFindElement(browser, "input-14", "id");
            WebElement btnLogin = helperClass.safeFindElement(browser, "/html/body/div[1]/div/div/div[2]/div/div/div[2]/div/form/button", "xpath");
            helperClass.safeFillInput(login, "test2@pernexus.org");
            helperClass.safeFillInput(passwd, "testtest2");
            Thread.sleep(500);
            helperClass.safeClickOnElement(btnLogin);
            Thread.sleep(2500);  
            //login to site END
            
            browser.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(2500);
            WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
            //WebElement tableContainer = browser.findElement(By.className("v-data-table__wrapper"));
            System.out.println("tableContainer.getTagName()=" + tableContainer.getTagName());        

    //        WebElement tableBody1 = tableContainer.findElement(By.tagName("tbody"));
    //        System.out.println("tableBody1.getTagName()=" + tableBody1.getTagName());        
            Thread.sleep(500);
            List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
            System.out.println("listOfTableRows1.size()=" + listOfTableRows.size());       
            Thread.sleep(500);
            listOfTableRows.get(0).click();
           
            
//            System.out.println("Change options to delete user");
//            int res = getNuberOfAnswer();
//            switch (res) {
//                case 1:
//                    deleteOneRandomUser();
//                    break;
//                case 2:
//                    deleteOneUserById();
//                    break;
//                case 3:
//                    deleteFewRandomUsers();
//                    break;
//                case 4:
//                    deleteFewUsersByIds();
//                    break;
//            }
            
            
            Thread.sleep(15000);
            
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

    private void deleteOneRandomUser(WebDriver browser) throws InterruptedException {
        Thread.sleep(500);
        
        
        
        
    }

    private void deleteOneUserById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteFewRandomUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteFewUsersByIds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
