/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.util.List;
import org.openqa.selenium.By;
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
    
    public static void selectOneElementFromDropdownInHeper(WebElement listContainerElement, WebDriver browser) throws InterruptedException
    {     
        //Thread.sleep(1000);
        List<WebElement> listElements = listContainerElement.findElements(By.className("v-list-item--link"));
        System.out.println("H_listElements.size=" + listElements.size());
        Thread.sleep(500);
        int randomNumberOfElement = (int)(Math.random() * listElements.size());        
        Thread.sleep(500);
        if (listElements.size() > 0) {
            System.out.println("H_BEFORE CLICK ON TAG");
            listElements.get(randomNumberOfElement).click(); 
            System.out.println("H_AFTER CLICK ON TAG"); 
            Thread.sleep(500);
            
        } else {
            System.out.println("H_Error, listElements.size() = " + listElements.size());
        }   
    }
}
