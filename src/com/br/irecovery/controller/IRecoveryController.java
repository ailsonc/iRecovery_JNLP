package com.br.irecovery.controller;

import com.br.irecovery.models.Device;
import com.br.irecovery.models.Image;
import com.br.irecovery.util.Cmd;
import com.br.irecovery.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
    private static String size; 
    
    public static void run(Device device, Image image, JButton start, JLabel message, JProgressBar jProgressBar1) throws Exception{      
        ArrayList<String> cmdsBegin = new ArrayList<String>();  
        
        new Thread( new Runnable() {
            public void run() {
                try {
                    start.setEnabled(false);
                    message.setText("Formatando o dispositivo"); 
                    diskpart(device);
                    cmdsBegin.add("cmd /c diskpart.exe /s "+tmpdir+"\\diskpart.txt");
                    //Cmd.commands(cmdsBegin);
                    
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
        
        URL url=new URL("http://localhost:5000/uploads/" + image.getFileName());
        File destination =  new File(tmpdir+"\\"+image.getFileName());
        
        if(destination.exists()){
            message.setText("Validando a imagem");
            size = getSize(image);
            if(image.getFileSize().equals(size)){
                message.setText("Imagem Ok");
                Log.setLog(Level.INFO, "Image na maquina");
                return false;
            } else {
                destination.delete();  
            }
        }
        
        jProgressBar1.setValue(0);
        jProgressBar1.setMinimum(0);
        jProgressBar1.setMaximum(100);
        jProgressBar1.setOpaque(false);
        
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int filesize = connection.getContentLength();
            float totalDataRead=0;
            java.io.BufferedInputStream in = new java.io.BufferedInputStream(connection.getInputStream());
            java.io.FileOutputStream fos = new java.io.FileOutputStream(destination);
            java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,1024); 
            byte[] data = new byte[1024]; 
            int i=0;
            while((i=in.read(data,0,1024))>=0) {
                totalDataRead=totalDataRead+i;
		bout.write(data,0,i);
		float Percent=(totalDataRead*100)/filesize;
                jProgressBar1.setValue((int)Percent);
            }
            bout.close();
            in.close();
        } catch(Exception e) { 
            message.setText("Erro: Na copia do arquivo " + e.getMessage()); 
            Log.setLog(Level.WARNING, "Erro: Na copia do arquivo " + e.getMessage());
            throw new Exception("Erro: Na copia do arquivo");
        }

        message.setText("Validando a imagem");           
        size = getSize(image);
        if(image.getFileSize().equals(size)){
            jProgressBar1.setValue(0);
            message.setText("Imagem Ok");
            Log.setLog(Level.INFO, "Image na maquina");
            return true; 
        } else {
            message.setText("Erro: Tamanho do arquivo " + size + ", Correto " + image.getFileSize()); 
            Log.setLog(Level.WARNING, "Erro: Tamanho do arquivo");
            throw new Exception("Erro: Tamanho do arquivo");
        }
    }    
    
    private static String getSize(Image image) throws Exception{
        File file =new File(tmpdir+"\\"+image.getFileName());
        if(file.exists()){
            long bytes = file.length();
            return String.valueOf(bytes);
        }else{
            return null;
	}        
    } 

}
