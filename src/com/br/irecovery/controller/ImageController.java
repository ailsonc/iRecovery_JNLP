package com.br.irecovery.controller;

import com.br.irecovery.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author ailson
 */
public class ImageController {
    public static void getImage() throws Exception{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet("http://localhost:5000/api/v1/image");
        
        HttpResponse response = client.execute(request);

        // Get the response
        BufferedReader rd = new BufferedReader
            (new InputStreamReader(
            response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
            Log.setLog(Level.INFO, line);
        }
    }
}
