/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author user
 */
public class TestClass {
    
    public void testFunction(){
        for(int i = 0; i < 20; i++) {
            System.out.println(getRandChar(i));
        }
    }
    
    

    private String getRandChar(int n) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int randomNumberOfChar = (int)(Math.random() * alphabet.length());
        return String.valueOf(alphabet.charAt(randomNumberOfChar));
    }
}
