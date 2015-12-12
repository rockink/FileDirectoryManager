/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filemanager;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 *
 * @author rockink
 */
    public class Details implements Serializable{
        
    public int id = 000000000000;
    public PriorityQueue<String> idToFill;
    public int root, branch1, branch2, branch3, branch4;
    public int lastElem;
    public int fileID;

    public Details() {
    
        idToFill = new PriorityQueue<>();
    
    }

    
    
    
    @Override
    public String toString() {
    
        String s = (root < 9 ? "0"+root : root) + "/"
        + (branch1 < 9 ? "0"+branch1 : branch1) + "/"
        + (branch2 < 9 ? "0"+branch2 : branch2) + "/"
        + (branch3 < 9 ? "0"+branch3 : branch3) + "/"
        + (branch4 < 9 ? "0"+branch4 : branch4) + "/"
        + (fileID < 9 ? "0"+(fileID) : (fileID));

        return  s;

    }

     public PriorityQueue<String> getIdFill() {

         return idToFill;
    }
    
    
    
        
    }
