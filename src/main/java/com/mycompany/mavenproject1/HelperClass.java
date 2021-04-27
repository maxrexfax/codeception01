/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author user
 */
public class HelperClass {
    
    public String getRandChar() 
    {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int randomNumberOfChar = (int)(Math.random() * alphabet.length());
        return String.valueOf(alphabet.charAt(randomNumberOfChar));
    }
    
    public static void startThread(String commandJs, JavascriptExecutor js)
    {
        Thread thread = new Thread(){
            public void run(){
              System.out.println("Thread Running and sending to JS:" + commandJs);
              js.executeAsyncScript(commandJs);
            }
        };
        thread.start();
    }
    
    public static void writeStringToFileInThread1(File fileToWriteLogsOfTesting, String dataToWrite)//не использовать в потоке сообщений - записывает в хаотическом порядке!
    {
        Thread thread = new Thread(){
            public void run(){
              System.out.println("Thread is trying to write in file:" + dataToWrite);
              //File fileToWriteLogsOfTesting = new File("./logs/myLogFile" + fileNamePostfix);
              //writeStringToFile(fileToWriteLogsOfTesting.getAbsolutePath(), dataToWrite);
              try
                {
                    FileWriter myWriter = new FileWriter(fileToWriteLogsOfTesting.getAbsolutePath());
                    myWriter.append(dataToWrite);
                    //myWriter.write(dataToWrite);
                    myWriter.close();
                    System.out.println("Successfully edit the file with path " + fileToWriteLogsOfTesting.getAbsolutePath());
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
                }
        };
        thread.start();
    }
    
    
    public void writeStringToFile(File fileToWriteLogsOfTesting, String content) 
        {
            try(FileWriter fw = new FileWriter(fileToWriteLogsOfTesting.getAbsolutePath(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                out.println(content);
            } catch (IOException e) {
                System.out.println("IOException in writeStringToFile function");
                System.out.println("Failed to write data ---" + content + "---");
                System.out.println(e.getMessage());
            } catch (Exception ex) {
                System.out.println("Exception in writeStringToFile function");
                System.out.println("Failed to write data ---" + content + "---");
                System.out.println(ex.getMessage());
            }
        }
    
    public void selectOneElementFromDropdownInHeper(WebDriver browser) throws InterruptedException
    {     
        //WebElement listContainerElement = browser.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
        WebElement listContainerElement = browser.findElement(By.className("menuable__content__active"));
        Thread.sleep(500);
        List<WebElement> listElements = listContainerElement.findElements(By.className("v-list-item--link"));
        //System.out.println("H_listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());        
        Thread.sleep(500);
        if (listElements.size() > 0) {
            //System.out.println("H_BEFORE CLICK ON ELEMENT");
            listElements.get(randomNumberOfElement).click(); 
            //System.out.println("H_AFTER CLICK ON ELEMENT"); 
            Thread.sleep(500);
            
        } else {
            System.out.println("H_Error, listElements.size() = " + listElements.size());
        }   
    }
    
    public String getDateInStringForWindowsLinux()
    {
        return new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new java.util.Date());
    }
    
    public void selectOneElementFromDropdownInHeperAlt(WebDriver browser, int index) throws InterruptedException
    {     
        int indexToClick;
        WebElement listContainerElement = browser.findElement(By.className("menuable__content__active"));
        Thread.sleep(500);
        List<WebElement> listElements = listContainerElement.findElements(By.className("v-list-item--link"));
        //System.out.println("H_listElements.size=" + listElements.size());
        Thread.sleep(500);
        if (index == 0) {
            indexToClick = listElements.size() - 1;
        } else if (index > listElements.size()) {
            indexToClick = (int)(Math.random() * listElements.size());
        } else {
            indexToClick = index;
        }

        Thread.sleep(500);
        if (listElements.size() > 0) {
            listElements.get(indexToClick).click();
            Thread.sleep(500);
            
        } else {
            System.out.println("H_alt_Error, listElements.size() = " + listElements.size());
        }   
    }
    
    public void selectOneElementFromDropdownAddressInHelper(WebDriver browser) throws InterruptedException
    {
        WebElement mapInput = browser.findElement(By.id("map"));
        mapInput.click();
        Thread.sleep(500);        
        mapInput.sendKeys(getRandChar());  
        Thread.sleep(500);                  
        List<WebElement> listContainerElement = browser.findElements(By.xpath("//*[contains(@class,'pac-container pac-logo')]"));
        Thread.sleep(500);        
        List<WebElement> listElements = listContainerElement.get(1).findElements(By.className("pac-item"));
        Thread.sleep(500);
        //System.out.println("Address listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());
        Thread.sleep(500);
        //System.out.println(listElements.get(randomNumberOfElement));
        Thread.sleep(500);
        if (listElements.size() > 0) {
            //listElements.get(randomNumberOfElement);   
            listElements.get(randomNumberOfElement).click();        
        } else {
            System.out.println("Error, listElements.size() = " + listElements.size());
        }
    }
    
    public WebElement safeFindElement(WebDriver browser, String identifier, String type)
    {
        WebElement foundedElement = null;
        try {
            switch (type){
                case "id":
                    foundedElement = browser.findElement(By.id(identifier));
                    break;
                case "xpath":
                    foundedElement = browser.findElement(By.xpath(identifier));
                    break;
                case "cssSelector":
                    foundedElement = browser.findElement(By.cssSelector(identifier));
                    break;
                case "className":
                    foundedElement = browser.findElement(By.className(identifier));
                    break;
                case "tagName":
                    foundedElement = browser.findElement(By.tagName(identifier));
                    break;
            }
        }
        catch(NoSuchElementException eex) {
            System.out.println(eex.getMessage());
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
        return foundedElement;
    }
    
    public void safeClickOnElement(WebElement elementToClick)
    {
        if (elementToClick != null) {
            try {
                elementToClick.click();
            }
            catch(ElementNotInteractableException eex) {
                System.out.println("Error click!(1)");
                System.out.println(eex.getMessage());
            }
            catch(Exception ex) {
                System.out.println("Error click!(2)");
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Error! Element is null!");
        }   
    }
    
    public void safeFillInput(WebElement elementToFill, String dataToFill)
    {
        //System.out.println(elementToFill.getTagName());
        if (elementToFill != null /*&& elementToFill.getTagName() == "input"*/) {
            try {
                elementToFill.sendKeys(dataToFill);
            }
            catch(ElementNotInteractableException eex) {
                System.out.println("Error sending data to input!(1)");
                System.out.println(eex.getMessage());
            }
            catch(Exception ex) {
                System.out.println("Error sending data to input!(2)");
                System.out.println(ex.getMessage());
            }
        }   else {
            System.out.println("Error! Element is null or not input!");
        } 
    }
    
    public int getRandomDigit(int min, int max)
    {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
