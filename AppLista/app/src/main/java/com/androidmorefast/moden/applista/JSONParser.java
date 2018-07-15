package com.androidmorefast.moden.applista;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;

/**
 * Created by Moden on 25/05/2016.
 */
public class JSONParser {
    static JSONArray jarray= null;

    public JSONParser(){

    }
    public JSONArray GetJSONfromUrl(String url){
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet getData = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getData);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine())!=null) {
                    builder.append(line);
                }


            }else{
                Log.e("==>"," fallo la descarga de JSON");

            }

        }catch(ClientProtocolException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jarray = new JSONArray(builder.toString());

        }catch (JSONException e){
            Log.e("JSON Parser","Error traduciendo datos"+ e.toString());
        }

        return  jarray;
    }
}
