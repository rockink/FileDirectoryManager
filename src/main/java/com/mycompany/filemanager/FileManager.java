/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filemanager;

import static com.mycompany.filemanager.Logger.error;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;

/**
 *
 * @author rockink
 */
public class FileManager {

    String homepath;
    
    Config config;

    public FileManager(String homePath, String configPath) throws IOException, FileNotFoundException, ClassNotFoundException, Exception {

        this.homepath = homePath;
        config = Config.getInstance(configPath);
        String localPath = "00/00/00/00/00";
        File fileFolder = new File(homepath + localPath);

        error("file creation is " + fileFolder.mkdirs());

    }

    public String addFile(byte[] file) throws IOException, Exception {

        String path = getStreamForInsertion(file);
        return path;

    }

    private static void print(String string) {

        System.out.println(string);

    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException, Exception {

        String configPath = "/home/rockink/sam/";
        String paths = "/home/rockink/data/";
        FileManager fileManager = new FileManager(paths, configPath);

        PriorityQueue<String> pathList = new PriorityQueue<>();
        for (int i = 0; i < 20; i++) {
            byte[] nir = "nirmal".getBytes();
            String path = fileManager.getStreamForInsertion(nir);
            pathList.add(path);

        }
        

        for (int i = 0; i < 5; i++){
            
            fileManager.delete(pathList.poll());
        }
        
        for (int i = 0; i < 20; i++) {
        
            byte[] nir = "nirmal".getBytes();
            String path = fileManager.getStreamForInsertion(nir);
            pathList.add(path);

        }

    }
    
    
    public boolean delete(String path){
        
        File file = new File(path);
        config.addDeletedId(path);
        return file.delete();
        
    }

    private String getStreamForInsertion(byte[] file) throws FileNotFoundException, IOException, Exception {

        error("error starting");

        String path = homepath + config.getNextPath();
        File filee = new File(path);
        
        filee.getParentFile().mkdirs();
        FileOutputStream stream = new FileOutputStream(new File(path));
        stream.write(file);
        stream.close();
        config.setNewNextPath();
        return path;

    }

}
