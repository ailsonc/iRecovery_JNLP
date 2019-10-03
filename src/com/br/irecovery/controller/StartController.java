package com.br.irecovery.controller;

import com.br.irecovery.models.Device;
import com.br.irecovery.util.Cmd;
import java.util.ArrayList;

/**
 *
 * @author ailson
 */
public class StartController {
    private static void validate() throws Exception{
        
    }
       
    public static void runRecovery(Device device) throws Exception{
        ArrayList<String> cmdsBegin = new ArrayList<String>();  
        validate();
        
        cmdsBegin.add("cmd /c start notepad"); 
        Cmd.commands(cmdsBegin);        
    }
}
