//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

import java.net.*;
import javax.net.ssl.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import java.security.*;
import java.io.*;

public class NetworkUtil
{
    public static JsonElement getJsonResponse(final String website) {
        URL url;
        try {
            url = new URL(website);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return (JsonElement)JsonNull.INSTANCE;
        }
        try {
            final HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setSSLSocketFactory(SSLContext.getDefault().getSocketFactory());
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(120000);
            return new JsonParser().parse(new JsonReader((Reader)new InputStreamReader(connection.getInputStream())));
        }
        catch (IOException | NoSuchAlgorithmException ex2) {
            final Exception ex;
            final Exception e2 = ex;
            return (JsonElement)JsonNull.INSTANCE;
        }
    }
    
    public static void sendContent(final String website, final String content) {
        URL url;
        try {
            url = new URL(website);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        try {
            final HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setSSLSocketFactory(SSLContext.getDefault().getSocketFactory());
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(120000);
            final OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(content);
            writer.flush();
            writer.close();
        }
        catch (IOException | NoSuchAlgorithmException ex2) {
            final Exception ex;
            final Exception e2 = ex;
            e2.printStackTrace();
        }
    }
    
    private NetworkUtil() {
        throw new UnsupportedOperationException();
    }
}
