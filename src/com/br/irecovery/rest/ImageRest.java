package com.br.irecovery.rest;

import com.br.irecovery.models.Image;
import com.br.irecovery.util.Log;
import java.util.ArrayList;
import java.util.logging.Level;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import static org.apache.http.entity.mime.MIME.UTF8_CHARSET;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author ailson
 */
public class ImageRest {
    private static String uri = "http://localhost:5000/api/v1/";
    private static ArrayList<Image> images = new ArrayList();
            
    public static ArrayList<Image> getImages() throws Exception{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(uri + "image");  
        HttpResponse response = client.execute(request);       
        
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            JSONObject jsonObject = null;
            String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
            jsonObject = new JSONObject(responseJSON);
            
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            
            for (Object object : jsonArray) {
                JSONObject rec = (JSONObject)object;
                Image image = new Image();
                image.setId(rec.optInt("id"));
                image.setName(rec.optString("name"));
                image.setDescription(rec.optString("description"));
                image.setFileName(rec.optString("filename"));
                image.setFilePath(rec.optString("filepath"));
                image.setFileHash(rec.optString("filehash"));
                images.add(image);
            }
            
            return images;     
        } else {
            return null;
        }
    }
}