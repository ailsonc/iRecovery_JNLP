package com.br.irecovery.controller;

import com.br.irecovery.models.Device;
import com.br.irecovery.models.Image;
import com.br.irecovery.util.Cmd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author ailson
 */
public class IRecoveryController {
    private static String tmpdir = System.getProperty("java.io.tmpdir");
  
    private static void validate() throws Exception{
        
    }   
    
    public static void run(Device device, Image image, JLabel message, JProgressBar jProgressBar1) throws Exception{      
        ArrayList<String> cmdsBegin = new ArrayList<String>();  
        
        new Thread( new Runnable() {
            public void run() {
                try {
                    message.setText("Formatando o dispositivo"); 
                    diskpart(device);
                    cmdsBegin.add("cmd /c diskpart.exe /s "+tmpdir+"\\diskpart.txt");
                    Cmd.commands(cmdsBegin);

                    message.setText("Copiando a imagem"); 
                    copyFile(new File(image.getFileDir()), new File(tmpdir+"\\"+image.getFileName()), jProgressBar1);
                    message.setText("Copiado"); 
                    
                } catch (Exception ex) {
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
        gravarArq.println("active");
        gravarArq.println("format quick fs=ntfs label=\"iRecovery\"");
        gravarArq.println("assign letter=\""+device.getDeviceID().substring(0, 1)+"\"");
        gravarArq.println("exit");
        diskpart.close();
    }
        
    private static void copyFile(File source, File destination, JProgressBar jProgressBar1) throws Exception{
        if(destination.exists()){
            //todo hash
            destination.delete();
        }
        
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
                System.out.println(pos);
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
    }

}
