//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

import javax.annotation.*;
import com.google.gson.*;
import java.util.*;

public class NameUtil
{
    private static final Map<String, UUID> UUID_CACHE;
    
    @Nullable
    public static UUID getUUIDFromName(final String name) {
        if (NameUtil.UUID_CACHE.containsKey(name)) {
            return NameUtil.UUID_CACHE.get(name);
        }
        String rawRequest;
        try {
            rawRequest = NetworkUtil.getJsonResponse("https://api.mojang.com/users/profiles/minecraft/" + name).getAsJsonObject().get("id").getAsString();
        }
        catch (IllegalStateException e) {
            return null;
        }
        final String formattedRequest = rawRequest.substring(0, 7) + "-" + rawRequest.substring(8, 13) + "-" + rawRequest.substring(14, 17) + "-" + rawRequest.substring(18, 21) + "-" + rawRequest.substring(22, rawRequest.length() - 1);
        UUID returnVal;
        try {
            returnVal = UUID.fromString(formattedRequest);
        }
        catch (IllegalArgumentException e2) {
            returnVal = null;
        }
        NameUtil.UUID_CACHE.put(name, returnVal);
        return returnVal;
    }
    
    public static String getNameFromUUID(final UUID uuid) {
        for (final Map.Entry<String, UUID> entry : NameUtil.UUID_CACHE.entrySet()) {
            if (entry.getValue().equals(uuid)) {
                return entry.getKey();
            }
        }
        final JsonArray array = NetworkUtil.getJsonResponse("https://api.mojang.com/user/profiles/" + uuid.toString().replace("-", "") + "/names").getAsJsonArray();
        final String returnVal = array.get(array.size() - 1).getAsJsonObject().get("name").getAsString();
        NameUtil.UUID_CACHE.put(returnVal, uuid);
        return returnVal;
    }
    
    private NameUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        UUID_CACHE = new HashMap<String, UUID>();
    }
}
