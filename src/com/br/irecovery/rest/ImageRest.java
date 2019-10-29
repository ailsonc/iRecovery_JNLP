package com.br.irecovery.rest;

import com.br.irecovery.models.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ailson
 */
public class ImageRest {
    private static String uri = "http://localhost:5000/api/image";
            
    public static ArrayList<Image> getImages() throws Exception{
        ArrayList<Image> images = new ArrayList();
        
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:5000/api/image");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      
        String line;
        while ((line = rd.readLine()) != null) {
           result.append(line);
        }
        rd.close();
       
        JSONArray jsonArray = new JSONArray(result.toString());
        
        for (Object object : jsonArray) {
            JSONObject rec = (JSONObject)object;
            Image image = new Image();
            image.setId(rec.optInt("id"));
            image.setName(rec.optString("name"));
            image.setDescription(rec.optString("description"));
            image.setOriginalName(rec.optString("originalname"));
            image.setFileName(rec.optString("filename"));
            image.setFileSize(rec.optString("filesize"));
            images.add(image);
        }
        
        return images; 
       
    }
}
