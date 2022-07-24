//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.account;

import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.module.*;
import com.google.gson.stream.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.module.hud.*;
import java.io.*;
import org.teamviolet.violet.client.util.misc.*;
import org.lwjgl.input.*;
import java.awt.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.client.multiplayer.*;
import java.util.*;
import java.time.format.*;
import java.time.*;
import java.time.temporal.*;
import net.minecraft.client.*;
import com.google.gson.*;

public class Config
{
    public static final File DIRECTORY;
    static final Gson GSON;
    private static String currentConfig;
    private static File currentDir;
    
    public static void load() {
        load("Default");
    }
    
    public static void load(final String name) {
        if (!Config.DIRECTORY.exists() || Config.DIRECTORY.isFile()) {
            Config.DIRECTORY.delete();
            Config.DIRECTORY.mkdir();
            return;
        }
        Config.currentDir = new File(Config.DIRECTORY, Config.currentConfig = name);
        if (!Config.currentDir.exists() || Config.currentDir.isFile()) {
            Config.currentDir.delete();
            Config.currentDir.mkdir();
        }
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            final File categoryDir = new File(Config.currentDir, module.getCategory().name().toLowerCase());
            if (!categoryDir.exists() || !categoryDir.isDirectory()) {
                categoryDir.delete();
                categoryDir.mkdir();
            }
            final File file = new File(categoryDir, module.getName() + ".json");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JsonElement element = null;
            try {
                element = new JsonParser().parse(new JsonReader((Reader)new FileReader(file)));
            }
            catch (FileNotFoundException e2) {
                e2.printStackTrace();
            }
            if (!(element instanceof JsonObject)) {
                continue;
            }
            final JsonObject object = element.getAsJsonObject();
            for (final Value<?> value : module.getValueList()) {
                fromJsonObject(value, object);
            }
            if (module.getCategory() == Module.Category.HUD) {
                try {
                    ((HudModule)module).x = object.get("HudPosX").getAsInt();
                    ((HudModule)module).y = object.get("HudPosY").getAsInt();
                }
                catch (Exception ex) {}
            }
            if (!object.has("Enabled")) {
                continue;
            }
            module.setEnabled(object.get("Enabled").getAsBoolean());
        }
    }
    
    public static void save() {
        save(Config.currentConfig);
    }
    
    public static void save(final String name) {
        if (!Config.DIRECTORY.exists() || Config.DIRECTORY.isFile()) {
            Config.DIRECTORY.delete();
            Config.DIRECTORY.mkdir();
        }
        Config.currentDir = new File(Config.DIRECTORY, Config.currentConfig = name);
        if (!Config.currentDir.exists() || Config.currentDir.isFile()) {
            Config.currentDir.delete();
            Config.currentDir.mkdir();
        }
        for (final Module module : Violet.getViolet().getModuleManager().getModuleList()) {
            final File categoryDir = new File(Config.currentDir, module.getCategory().name().toLowerCase());
            if (!categoryDir.exists() || !categoryDir.isDirectory()) {
                categoryDir.delete();
                categoryDir.mkdir();
            }
            final File file = new File(categoryDir, module.getName() + ".json");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            final JsonObject object = new JsonObject();
            for (final Value<?> value : module.getValueList()) {
                object.add(value.getName(), toJsonElement(value));
            }
            object.add("Enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(module.isEnabled())));
            if (module.getCategory() == Module.Category.HUD) {
                object.add("HudPosX", (JsonElement)new JsonPrimitive((Number)((HudModule)module).x));
                object.add("HudPosY", (JsonElement)new JsonPrimitive((Number)((HudModule)module).y));
            }
            try {
                final FileWriter fw = new FileWriter(file);
                fw.write(Config.GSON.toJson((JsonElement)object));
                fw.flush();
                fw.close();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    private static JsonElement toJsonElement(final Value<?> value) {
        if (value.getValue() instanceof Bind) {
            return (JsonElement)new JsonPrimitive(Keyboard.getKeyName(((Bind)value.getValue()).getKey()));
        }
        if (value.getValue() instanceof Boolean) {
            if (value.getValueList() != null) {
                final JsonObject object1 = new JsonObject();
                object1.add(value.getName(), (JsonElement)new JsonPrimitive((Boolean)value.getValue()));
                for (final Value<?> value2 : value.getValueList()) {
                    object1.add(value2.getName(), toJsonElement(value2));
                }
                return (JsonElement)object1;
            }
            return (JsonElement)new JsonPrimitive((Boolean)value.getValue());
        }
        else {
            if (value.getValue() instanceof Color) {
                final JsonObject object2 = new JsonObject();
                final Color color = (Color)value.getValue();
                object2.add("Hex Code", (JsonElement)new JsonPrimitive(String.format("#%06X", color.getRGB() & 0xFFFFFF)));
                object2.add("Alpha", (JsonElement)new JsonPrimitive((Number)color.getAlpha()));
                object2.add("Rainbow", (JsonElement)new JsonPrimitive(Boolean.valueOf(value.isRainbow())));
                return (JsonElement)object2;
            }
            if (value.getValue() instanceof Enum) {
                return (JsonElement)new JsonPrimitive(value.getValue().toString());
            }
            if (value.getValue() instanceof Number) {
                return (JsonElement)new JsonPrimitive((Number)value.getValue());
            }
            return (JsonElement)JsonNull.INSTANCE;
        }
    }
    
    private static void fromJsonObject(final Value<?> value, final JsonObject object) {
        if (!object.has(value.getName())) {
            return;
        }
        if (value.getValue() instanceof Bind) {
            ((Bind)value.getValue()).setKey(Keyboard.getKeyIndex(object.get(value.getName()).getAsString()));
        }
        if (value.getValue() instanceof Boolean) {
            if (value.getValueList() != null) {
                final JsonObject object2 = object.get(value.getName()).getAsJsonObject();
                value.setValue((Object)object2.get(value.getName()).getAsBoolean());
                for (final Value<?> value2 : value.getValueList()) {
                    fromJsonObject(value2, object2);
                }
            }
            else {
                value.setValue((Object)object.get(value.getName()).getAsBoolean());
            }
        }
        if (value.getValue() instanceof Color) {
            final JsonObject object2 = object.get(value.getName()).getAsJsonObject();
            final Color rgb = Color.decode(object2.get("Hex Code").getAsString());
            try {
                value.setRainbow(object2.get("Rainbow").getAsBoolean());
            }
            catch (Exception ex) {}
            value.setValue((Object)new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), object2.get("Alpha").getAsInt()));
        }
        if (value.getValue() instanceof Double) {
            value.setValue((Object)object.get(value.getName()).getAsDouble());
        }
        if (value.getValue() instanceof Enum) {
            final Value<Enum<?>> enumValue = (Value<Enum<?>>)value;
            final String enumName = object.get(value.getName()).getAsString();
            final Enum<?>[] array;
            final Enum<?>[] enumArray = array = ((Enum)enumValue.getValue()).getDeclaringClass().getEnumConstants();
            for (final Enum<?> e : array) {
                if (e.name().equalsIgnoreCase(enumName)) {
                    enumValue.setValue((Object)e);
                    break;
                }
            }
        }
        if (value.getValue() instanceof Float) {
            value.setValue((Object)object.get(value.getName()).getAsFloat());
        }
        if (value.getValue() instanceof Integer) {
            value.setValue((Object)object.get(value.getName()).getAsInt());
        }
    }
    
    public static void saveStash(final Stash stash) {
        System.out.println("Saving Stash!");
        final File stashDir = new File(Config.DIRECTORY, "Stashes");
        if (!stashDir.exists() || stashDir.isFile()) {
            stashDir.delete();
            stashDir.mkdir();
        }
        String server = "singleplayer";
        if (!Utils.mc.isIntegratedServerRunning()) {
            try {
                server = Objects.requireNonNull(Utils.mc.getCurrentServerData()).serverIP;
            }
            catch (Exception e3) {
                server = "Error";
            }
        }
        final File serverDir = new File(stashDir, server);
        if (!serverDir.exists() || serverDir.isFile()) {
            serverDir.delete();
            serverDir.mkdir();
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        final File dateDir = new File(serverDir, dtf.format(now).replaceAll("/", "_"));
        if (!dateDir.exists() || dateDir.isFile()) {
            dateDir.delete();
            dateDir.mkdir();
        }
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        now = LocalDateTime.now();
        final File file = new File(dateDir, dtf.format(now).replaceAll(":", "_") + ".json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        final JsonObject object = new JsonObject();
        object.add("X", (JsonElement)new JsonPrimitive((Number)stash.getCoords().x));
        object.add("Z", (JsonElement)new JsonPrimitive((Number)stash.getCoords().z));
        object.add("Chests", (JsonElement)new JsonPrimitive((Number)stash.getChests()));
        object.add("Hoppers", (JsonElement)new JsonPrimitive((Number)stash.getHoppers()));
        object.add("Carts", (JsonElement)new JsonPrimitive((Number)stash.getCarts()));
        try {
            final FileWriter fw = new FileWriter(file);
            fw.write(Config.GSON.toJson((JsonElement)object));
            fw.flush();
            fw.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    private Config() {
        throw new UnsupportedOperationException();
    }
    
    static {
        DIRECTORY = new File(Minecraft.getMinecraft().gameDir, "violet");
        GSON = new GsonBuilder().setPrettyPrinting().create();
        Config.currentConfig = "Default";
        Config.currentDir = null;
    }
}
