/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public final class HttpRequestUtils {

    private static final Gson GSON = new GsonBuilder().create();
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";
    private static final String AUTH_HEADER = "Authorization";
    private static final String ACCEPT_HEADER = "Accept";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String AUTH_PREFIX = "Bearer";
    private static final String MEDIA_TYPE_JSON = "application/json";
    private static final String CHARSET = "utf-8";
    private HttpRequestUtils() {

    }

    public static <T> Response post(String link, T model) throws IOException {
        return post(link, model, null);
    }
    
    public static <T> Response post(String link, T model, String token) throws IOException {
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(METHOD_POST);
        con.setRequestProperty(CONTENT_TYPE_HEADER, MEDIA_TYPE_JSON);
        con.setRequestProperty(ACCEPT_HEADER, MEDIA_TYPE_JSON);
        con.setDoOutput(true);
        
        if(token != null) {
            con.setRequestProperty(AUTH_HEADER, String.format("%s %s", AUTH_PREFIX, token));
        }

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = GSON.toJson(model).getBytes(CHARSET);
            os.write(input, 0, input.length);
        }
        
        return parseRequest(con);
    }

    public static Response get(String link, Map<String, String> params) throws IOException {
        return get(link, params, null);
    }
    
    public static Response get(String link, Map<String, String> params, String token) throws IOException {
        URL url = new URL(String.format("%s?%s", link, getParamsString(params)));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(METHOD_GET);
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        
        if(token != null) {
            con.setRequestProperty(AUTH_HEADER, String.format("%s %s", AUTH_PREFIX, token));
        }
        
        return parseRequest(con);
    }
    
    private static Response parseRequest(HttpURLConnection con) throws IOException {

        int statusCode = con.getResponseCode();
        
        InputStream is = null;

        if (statusCode >= 200 && statusCode < 400) {
            is = con.getInputStream();
        } else {
            is = con.getErrorStream();
        }
        
        StringBuilder responseMessage = new StringBuilder();
        if(is != null) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, CHARSET))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    responseMessage.append(responseLine.trim());
                }
            }
        }
        
        Response response = new Response();
        response.setStatusCode(con.getResponseCode());
        response.setResponseMessage(responseMessage.toString());
          
        con.disconnect();
        
        return response;
    }
    
    private static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), CHARSET));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), CHARSET));
          result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }
}
