//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.account;

import com.google.gson.*;

public class VioletUser
{
    private final String username;
    private final boolean admin;
    private final int uid;
    
    private VioletUser(final String username, final boolean admin, final int uid) {
        this.username = username;
        this.admin = admin;
        this.uid = uid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public boolean isAdmin() {
        return this.admin;
    }
    
    public int getUserID() {
        return this.uid;
    }
    
    public static class Builder
    {
        private String username;
        private String password;
        
        public Builder setUsername(final String username) {
            this.username = username;
            return this;
        }
        
        public Builder setPassword(final String password) {
            this.password = hash(password);
            return this;
        }
        
        public VioletUser login() {
            final String loginResult = requestLogin(this.username, this.password);
            final JsonElement element = new JsonParser().parse(loginResult);
            if (!(element instanceof JsonObject)) {
                throw new IllegalArgumentException();
            }
            final JsonObject object = (JsonObject)element;
            return new VioletUser(this.username, object.get("admin").getAsBoolean(), object.get("uid").getAsInt(), null);
        }
        
        private static String requestLogin(final String username, final String password) {
            return "{  \"admin\": true,  \"uid\": 1}";
        }
        
        private static String hash(final String password) {
            return password;
        }
    }
}
