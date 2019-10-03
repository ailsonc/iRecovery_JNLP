package com.br.irecovery.util;

import java.util.ArrayList;

/**
 *
 * @author ailson
 */
public class Cmd {
   
    public static final String runtime(final String cmd) throws Exception{
	try {
            // Run reg query, then read output with StreamReader (internal class)
            final Process process = Runtime.getRuntime().exec(cmd);
            System.out.println(cmd);

            final StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            final int res = process.waitFor();
            reader.join();

            System.out.println(res);
            System.out.println(reader.getResult());
            System.out.println("");

            if (res != 0)
		return "";

            return reader.getResult();
	} catch (final Exception e) {
            e.printStackTrace();
	}

            return "";
    }
    
    public static void commands(ArrayList<String> cmds) throws Exception{
        for (String cmd : cmds) {
            runtime(cmd);
        }        
    }
}
