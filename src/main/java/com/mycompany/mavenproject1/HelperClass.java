/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author user
 */
public class HelperClass {
    public static String getRandChar() {
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
    
    public static void selectOneElementFromDropdownInHeper(WebDriver browser) throws InterruptedException
    {     
        //WebElement listContainerElement = browser.findElement(By.xpath("//*[contains(@class,'v-menu__content--fixed menuable__content__active')]"));
        WebElement listContainerElement = browser.findElement(By.className("menuable__content__active"));
        Thread.sleep(500);
        List<WebElement> listElements = listContainerElement.findElements(By.className("v-list-item--link"));
        System.out.println("H_listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());        
        Thread.sleep(500);
        if (listElements.size() > 0) {
            System.out.println("H_BEFORE CLICK ON ELEMENT");
            listElements.get(randomNumberOfElement).click(); 
            System.out.println("H_AFTER CLICK ON ELEMENT"); 
            Thread.sleep(500);
            
        } else {
            System.out.println("H_Error, listElements.size() = " + listElements.size());
        }   
    }
    
    public static void selectOneElementFromDropdownAddressInHelper(WebDriver browser) throws InterruptedException
    {
        List<WebElement> listContainerElement = browser.findElements(By.xpath("//*[contains(@class,'pac-container pac-logo')]"));
        Thread.sleep(500);        
        List<WebElement> listElements = listContainerElement.get(1).findElements(By.className("pac-item"));
        Thread.sleep(500);
        System.out.println("Address listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());
        Thread.sleep(500);
        System.out.println(listElements.get(randomNumberOfElement));
        Thread.sleep(500);
        if (listElements.size() > 0) {
            //listElements.get(randomNumberOfElement);   
            listElements.get(randomNumberOfElement).click();        
        } else {
            System.out.println("Error, listElements.size() = " + listElements.size());
        }
    }
    
    public static int getRandomDigit(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
