package com.br.irecovery.controller;

import com.br.irecovery.models.Device;
import com.br.irecovery.models.Image;
import com.br.irecovery.util.Cmd;
import com.br.irecovery.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author ailson
 */
public class IRecoveryController {
    private static String tmpdir = System.getProperty("java.io.tmpdir");
    private static String hash; 
    
    public static void run(Device device, Image image, JButton start, JLabel message, JProgressBar jProgressBar1) throws Exception{      
        ArrayList<String> cmdsBegin = new ArrayList<String>();  
        
        new Thread( new Runnable() {
            public void run() {
                try {
                    start.setEnabled(false);
                    message.setText("Formatando o dispositivo"); 
                    diskpart(device);
                    cmdsBegin.add("cmd /c diskpart.exe /s "+tmpdir+"\\diskpart.txt");
                    Cmd.commands(cmdsBegin);
                    
                    System.out.println("copyFile");
                    copyFile(image, message, jProgressBar1); 
                    
                    start.setEnabled(true);
                    
                } catch (Exception ex) {
                    start.setEnabled(true);
                    Logger.getLogger(IRecoveryController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();    
    }
    
    private static void diskpart(Device device)throws Exception{
        FileWriter diskpart = new FileWriter(tmpdir+"\\diskpart.txt");
        PrintWriter gravarArq = new PrintWriter(diskpart);
        gravarArq.println("select disk "+device.getIndex());
        gravarArq.println("clean");
        gravarArq.println("create partition primary");
        gravarArq.println("select partition 1");
        gravarArq.println("active");
        gravarArq.println("format quick fs=ntfs label=\"iRecovery\"");
        gravarArq.println("assign letter=\""+device.getDeviceID().substring(0, 1)+"\"");
        gravarArq.println("exit");
        diskpart.close();
    }
        
    private static Boolean copyFile(Image image, JLabel message, JProgressBar jProgressBar1) throws Exception{
        
        File source = new File(image.getFilePath());
        File destination =  new File(tmpdir+"\\"+image.getFileName()+".zip");
        
        if(destination.exists()){
            message.setText("Validando a imagem");
            hash = getHash(image);
            if(image.getFileHash().equals(hash)){
                message.setText("Imagem Ok");
                Log.setLog(Level.INFO, "Image na maquina");
                return false;
            } else {
                destination.delete();  
            }
        }
        
        message.setText("Copiando a imagem"); 
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        
        long fileSize = source.length();
        long readSeg = fileSize / 100;
        long readRemain = fileSize % 100;
        
        jProgressBar1.setValue(0);
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(100);
        jProgressBar1.setOpaque(false);
        
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            for (int pos = 0; pos < 100; pos ++) {
                destinationChannel.transferFrom(sourceChannel, pos * readSeg, readSeg);
                jProgressBar1.setValue(pos);  
            }
            if (readRemain > 0) {
                destinationChannel.transferFrom(sourceChannel, readSeg *100, readRemain);
                jProgressBar1.setValue(100);
            }
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
        
        message.setText("Validando a imagem");           
        hash = getHash(image);
        if(image.getFileHash().equals(hash)){
            return true; 
        } else {
            message.setText("Erro: CRC do arquivo " + hash + ", Correto " + image.getFileHash()); 
            Log.setLog(Level.WARNING, "Erro: CRC do arquivo");
            throw new Exception("Erro: CRC do arquivo");
        }
    }    
    
    private static String getHash(Image image) throws Exception{
        FileInputStream fis = new FileInputStream(new File(tmpdir+"\\"+image.getFileName()+".zip"));
        CRC32 crcMaker = new CRC32();
        byte[] buffer = new byte[65536];
        int bytesRead;
        while((bytesRead = fis.read(buffer)) != -1) {
            crcMaker.update(buffer, 0, bytesRead);
        }
        long crc = crcMaker.getValue(); // This is your error checking code    
        Log.setLog(Level.INFO, "CRC code is " + crc);
        return String.valueOf(crc);        
    } 

}
