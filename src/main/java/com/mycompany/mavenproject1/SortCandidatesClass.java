/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.google.common.collect.Ordering;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
public class SortCandidatesClass {
    
    public HelperClass helperClass = new HelperClass();
    final boolean DEFAULT = true;
    final boolean REVERSED = false;
    
    public void sortCandidates()
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
            browser.get("https://perscriptum-dev.herokuapp.com/candidates");
            Thread.sleep(2500);  
            WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
            Thread.sleep(500);
            List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr"));
            List<WebElement> listOfFlagsToSort = listOfTableRows.get(0).findElements(By.tagName("th"));
            for (int i = 1; i < 7; i++) {           
                Thread.sleep(1500);
                if (i ==4) continue;
                listOfFlagsToSort.get(i).click();             
                Thread.sleep(1500);
                if (checkisTableSortedByCurrentColumn(browser, i, DEFAULT)) {
                    System.out.println("Table sorting correct");
                } else {
                    System.out.println("ERROR table sorting");
                }            
                Thread.sleep(1500);
                listOfFlagsToSort.get(i).click();             
                Thread.sleep(1500); 
                if (checkisTableSortedByCurrentColumn(browser, i, REVERSED)) {
                    System.out.println("Table sorting correct");
                } else {
                    System.out.println("ERROR table sorting");
                }       
            
                Thread.sleep(1500);
                listOfFlagsToSort.get(i).click(); 
                Thread.sleep(5000);
            }
            Thread.sleep(15000);
            
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            browser.close();
            browser.quit();
        }
    }

    private boolean checkisTableSortedByCurrentColumn(WebDriver browser, int numberOfColumnTocheck , boolean flagOfSorting) {
        String nameOfSorting = null;
        if (flagOfSorting) {
            nameOfSorting = " in default order";
        } else {
            nameOfSorting = " in reverced order";
        }
        WebElement tableContainer = helperClass.safeFindElement(browser, "v-data-table__wrapper", "className");
        List<WebElement> listOfTableRows = tableContainer.findElements(By.tagName("tr")); 
        List<WebElement> listOfTableHeaders = listOfTableRows.get(0).findElements(By.tagName("th"));
        System.out.println("Testing sorting of the column " + listOfTableHeaders.get(numberOfColumnTocheck).getText() + nameOfSorting);
        String[] listToCheckArr = new String[listOfTableRows.size()-1];
        for (int i = 1; i < listOfTableRows.size(); i++) {
            List<WebElement> listOfTableDatas = listOfTableRows.get(i).findElements(By.tagName("td"));
            listToCheckArr[i-1] = listOfTableDatas.get(numberOfColumnTocheck).getText();
            //System.out.println(listToCheckArr[i-1]);
        }
        
        //System.out.println("listToCheck.size()=" + listToCheck.size());
        
        //System.out.println("Array of values from current column=");
        //printArray(listToCheckArr);        
        
        String[] tmp = listToCheckArr.clone();
        if (flagOfSorting) {
            Arrays.sort(tmp);
        } else {
            Arrays.sort(tmp, Collections.reverseOrder());
        }
        
        
        boolean isArrayEquals = Arrays.equals(listToCheckArr, tmp);
        //System.out.println("boolean isArrayEquals ==" + isArrayEquals);
                
        return isArrayEquals;
    }
    
    public void printArray(String[] arrayToPrint)
    {
        String dataToPrint = "";
        for (int i = 0; i < arrayToPrint.length; i++) {
            dataToPrint.concat(arrayToPrint[i]);
            dataToPrint.concat(" - ");
            System.out.print(arrayToPrint[i] + " - ");
        }
        //System.out.println(dataToPrint);
        System.out.println();
    }
    
}
