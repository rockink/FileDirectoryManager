/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filemanager;

import static com.mycompany.filemanager.Logger.error;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author rockink
 */
public class Config {
    
    private Details details;
    static Config config;
    String configPath;
    
    public static Config getInstance(String configPath) throws IOException, FileNotFoundException, ClassNotFoundException, Exception {

        if (config == null) {

            config = new Config(configPath);
            

        }

        return config;

    }

    private Config(String configPath) throws FileNotFoundException, IOException, ClassNotFoundException, Exception 
    {

        File file = new File(configPath+"configuration");
        if (file.createNewFile()) {

            file.createNewFile();
            details = new Details();
            writeToFileAsObject();
            
        }

        int j = 2099999999;
        FileInputStream fileIn = new FileInputStream(configPath+"/configuration");
        ObjectInputStream ostream = new ObjectInputStream(fileIn);
        Object obj = ostream.readObject();
        ostream.close();
        
        if (obj instanceof Details) {

            error("config setting up");
            details = (Details) obj;
            error("value of id is "+details.id);

        }

    }

    /**
     *
     * @return creates a path for next data to be stored
     */
    public String getNextPath() {


        if (details == null){
            
            error("lol error");
        }


        if (details.getIdFill() == null){
            
            error("id fill error");
            
        }
        
        if (!details.idToFill.isEmpty()){
            
            String path = details.idToFill.poll();
            error("getting next path from queue "+ path);
            return path;
        }
        
        calc();
    


        error("path before sending it to next path"+details);
        error("for id "+details.id);
        
        if (details.fileID > FileConstants.MAX_PER_DIRECTORY){
            
            error("file id exceeded 100 "+details.fileID);
            details.branch4++;
            details.fileID = 0;
            details.id = details.branch4*100;
            error("value of new id is "+details.id);
            
        }
        
        if (details.branch4 > FileConstants.MAX_PER_DIRECTORY){
            
            details.branch3++;
            details.branch4 = 0;
            error("branch4 id exceeded 100");
            
        }
        
        if (details.branch3 > FileConstants.MAX_PER_DIRECTORY){
            
            details.branch2++;
            details.branch3 = 0;
            error("file id exceeded 100");

        }
        
        if (details.branch2 > FileConstants.MAX_PER_DIRECTORY){
            
            details.branch1++;
            details.branch2 = 0;
            error("file id exceeded 100");

        }
        
        if (details.branch1 > FileConstants.MAX_PER_DIRECTORY){
            
            details.root++;
            details.branch1 = 0;
            error("file id exceeded 100");
            
        }
        
        
        
        
        String s = (details.root < 10 ? "0"+details.root : details.root) + "/"
        + (details.branch1 < 10 ? "0"+details.branch1 : details.branch1) + "/"
        + (details.branch2 < 10 ? "0"+details.branch2 : details.branch2) + "/"
        + (details.branch3 < 10 ? "0"+details.branch3 : details.branch3) + "/"
        + (details.branch4 < 10 ? "0"+details.branch4 : details.branch4) + "/"
        + (details.fileID < 10 ? "0"+(details.fileID) : (details.fileID));

        error("new detail "+s);
//        error(s+"");
        return  s;
    }

    
    public void setNewNextPath() throws IOException, Exception{
        
    
//        error("id processing is " + details.id+"");
        details.id = details.lastElem + 1;
        
        error("setting newpath for next run");
        
        error(String.format("id change to %s from   %s", details.id, details.lastElem));
        
//        error("id processed is " + details.id+"");
        
//        details.lastElem++;
//        details.id = details.lastElem;
        
        
        calc();
        error("new path set with ew id is "+details.id);
        error(""+details);

        writeToFileAsObject();
        
        
    }
    
    public void calc(){
        
        int max = 100000000;
    

        error("processing id " + details.id);

        
        details.lastElem = details.id;
        details.root = details.id / max / 100;
        details.id = details.id - details.root * max * 100;
//        error("root "+details.root);
//        error("max is "+max);
        
        details.branch1 = details.id / max;
        details.id = details.id - details.branch1 * max;
//        error("branch 1 "+details.branch1);
//        error("max is "+max);
        
        
        max = max / 100;
        details.branch2 = details.id / max;
        details.id = details.id - details.branch2 * max;
//        error("branch 2 "+details.branch2);
//        error("max is "+max);
        
        max = max / 100;
        details.branch3 = details.id / max;
        details.id = details.id - details.branch3 * max;
//        error("branch3 "+details.branch3);
//        error("max is "+max);
        
        max = max / 100;
        details.branch4 = details.id / max;
        details.id = details.id - details.branch4 * max;
        error("branch4 "+details.branch4);
        error("max is "+max);
          
        max = max / 100;
//        error("details file"  + details.fileID);
//        error("file id though is "+details.id);
//        error("lastelem id is "+details.lastElem);
        details.fileID =  details.fileID == 0 ? 1 : details.id / max;
        details.id = details.lastElem;
//        error("max is "+max);
//        error("file id is " + details.fileID);
        
        
        error("-----------IN THE END---------------");
        error(details+"");
        error("---------------------------");
        
        
    }

    @Override
    public String toString() {
    
        return getAddress();
    }

    
    
    
    
    /**
     *
     * @return the address of newly formed path for the data so that it 
     * can be stored elsewhere, like databases
     */
    public String getAddress() {

        return details.root + "/"
                + details.branch1 + "/"
                + details.branch2 + "/"
                + details.branch3 + "/"
                + details.branch4 + "/"
                + details.fileID;

    }

    private void writeToFileAsObject() throws FileNotFoundException, IOException, Exception {
    
        if (details != null){
            
            error("----------------");
            error(details+"");
            
        }
        FileOutputStream fileIn = new FileOutputStream("/home/rockink/sam/configuration");
        ObjectOutputStream ostream = new ObjectOutputStream(fileIn);
        if (details == null){
            
            throw new Exception("Details is null..Shouldn't be this");
            
        }
        ostream.writeObject(details);
        ostream.close();
        
    }

    void addDeletedId(String path) {
    
        details.idToFill.add(path);
    }

}
