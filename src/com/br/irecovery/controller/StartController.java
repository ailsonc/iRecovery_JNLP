package com.br.irecovery.controller;

import com.br.irecovery.models.Device;
import com.br.irecovery.models.Image;
import com.br.irecovery.util.Cmd;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author ailson
 */
public class StartController {
    private static String tmpdir = System.getProperty("java.io.tmpdir");
    
    private static void validate() throws Exception{
        
    }
       
    public static void runRecovery(Device device, Image image) throws Exception{
        ArrayList<String> cmdsBegin = new ArrayList<String>();  
        validate();
        
        // cmdsBegin.add("cmd /c start HPUSBF.EXE"); 
        diskpart(device);
        cmdsBegin.add("cmd /c diskpart.exe /s "+tmpdir+"\\diskpart.txt");
        Cmd.commands(cmdsBegin);       
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
}
