//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.account;

import com.google.gson.stream.*;
import java.io.*;
import com.google.gson.*;
import org.teamviolet.violet.client.util.misc.*;
import javax.annotation.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class FriendUtil
{
    private static final File JSON_FILE;
    public static final List<UUID> FRIENDS;
    
    public static void init() {
        if (FriendUtil.JSON_FILE.exists()) {
            if (FriendUtil.JSON_FILE.isFile()) {
                JsonArray jsonArray;
                try {
                    jsonArray = new JsonParser().parse(new JsonReader((Reader)new FileReader(FriendUtil.JSON_FILE))).getAsJsonArray();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }
                jsonArray.forEach(element -> FriendUtil.FRIENDS.add(UUID.fromString(element.getAsString())));
                return;
            }
        }
        try {
            FriendUtil.JSON_FILE.createNewFile();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public static void save() {
        if (!FriendUtil.JSON_FILE.exists() || !FriendUtil.JSON_FILE.isFile()) {
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("reap1"));
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("ninethousand"));
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("wawdle"));
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("gum14_"));
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("gcgubobo"));
            FriendUtil.FRIENDS.add(NameUtil.getUUIDFromName("x8mvreace_"));
            try {
                FriendUtil.JSON_FILE.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        final JsonArray jsonArray = new JsonArray();
        FriendUtil.FRIENDS.forEach(uuid -> jsonArray.add(uuid.toString()));
        try {
            final FileWriter fileWriter = new FileWriter(FriendUtil.JSON_FILE);
            fileWriter.write(Config.GSON.toJson((JsonElement)jsonArray));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public static EnumChangeResult addFriend(final String name) {
        final UUID uuid = NameUtil.getUUIDFromName(name);
        if (uuid == null) {
            return EnumChangeResult.FAIL;
        }
        return addFriend(uuid);
    }
    
    public static EnumChangeResult addFriend(@Nonnull final UUID friend) {
        if (!FriendUtil.FRIENDS.contains(friend)) {
            FriendUtil.FRIENDS.add(friend);
            return EnumChangeResult.SUCCESS;
        }
        return EnumChangeResult.NO_CHANGE;
    }
    
    public static EnumChangeResult removeFriend(final String name) {
        final UUID uuid = NameUtil.getUUIDFromName(name);
        if (uuid == null) {
            return EnumChangeResult.FAIL;
        }
        return removeFriend(uuid);
    }
    
    public static EnumChangeResult removeFriend(@Nonnull final UUID friend) {
        return FriendUtil.FRIENDS.remove(friend) ? EnumChangeResult.SUCCESS : EnumChangeResult.NO_CHANGE;
    }
    
    public static boolean toggleFriend(@Nonnull final UUID friend) {
        if (FriendUtil.FRIENDS.contains(friend)) {
            removeFriend(friend);
            return false;
        }
        addFriend(friend);
        return true;
    }
    
    public static boolean isFriend(final EntityPlayer player) {
        return FriendUtil.FRIENDS.stream().anyMatch(uuid -> NameUtil.getNameFromUUID(uuid).equals(player.getName()));
    }
    
    private FriendUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        JSON_FILE = new File(Config.DIRECTORY, "Friends.json");
        FRIENDS = new ArrayList<UUID>();
    }
}
