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

/**
 *
 * @author user
 */
public class SearchCandidateClass {
    
    public HelperClass helperClass = new HelperClass();
    public WebDriver browser = null;
    public String[] iDs;
    public String[] Names;
    public String[] Statuses;
    public String[] References;
    public String[] DatesOfBirth;
    
    public void searchCandidate()
    {
        String osName = System.getProperty("os.name");
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
            Thread.sleep(1500);
            helperClass.safeFindElement(browser, "#inspire > div > main > div > div > div > div:nth-child(2) > div > div > div > div > div > div.v-data-footer > div.v-data-footer__select > div > div > div", "cssSelector").click();
            helperClass.selectOneElementFromDropdownInHeperAlt(browser, 0);//переключение списка юзеров на странице на последнее значение
            Thread.sleep(2500);
            WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
            Thread.sleep(500);
            List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
            System.out.println("listOfTableRows.size()=" + listOfTableRows.size());
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
            //TODO - get random values from random arrays and put it in search field
            
            Thread.sleep(15000);
            
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
    
}
