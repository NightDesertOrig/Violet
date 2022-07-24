//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.command;

import org.teamviolet.violet.client.api.command.*;
import com.mojang.brigadier.*;
import com.mojang.brigadier.builder.*;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.context.*;
import org.teamviolet.violet.client.util.account.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.game.*;
import com.google.gson.stream.*;
import java.io.*;
import com.google.gson.*;
import com.mojang.brigadier.exceptions.*;
import java.util.*;

public class FriendCommand implements VioletCommand
{
    public void populate(final CommandDispatcher<Object> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)LiteralArgumentBuilder.literal("friend").then(((RequiredArgumentBuilder)RequiredArgumentBuilder.argument("command", (ArgumentType)StringArgumentType.string()).then(RequiredArgumentBuilder.argument("name", (ArgumentType)StringArgumentType.string()).executes(c -> {
            final String command = StringArgumentType.getString(c, "command").toLowerCase();
            final String name = StringArgumentType.getString(c, "name");
            final String s = command;
            int n = -1;
            switch (s.hashCode()) {
                case 96417: {
                    if (s.equals("add")) {
                        n = 0;
                        break;
                    }
                    break;
                }
                case 99339: {
                    if (s.equals("del")) {
                        n = 1;
                        break;
                    }
                    break;
                }
            }
            Label_0249: {
                switch (n) {
                    case 0: {
                        switch (FriendUtil.addFriend(name)) {
                            case FAIL: {
                                InfoUtil.chatError("Could not find player: '" + name + "'.");
                                return 2;
                            }
                            case SUCCESS: {
                                InfoUtil.chatInfo("Added player: '" + NameUtil.getNameFromUUID(NameUtil.getUUIDFromName(name)) + "' as a friend.");
                                return 1;
                            }
                            case NO_CHANGE: {
                                InfoUtil.chatInfo("Player: '" + NameUtil.getNameFromUUID(NameUtil.getUUIDFromName(name)) + "' is already a friend.");
                                return 1;
                            }
                            default: {
                                break Label_0249;
                            }
                        }
                        break;
                    }
                    case 1: {
                        switch (FriendUtil.removeFriend(name)) {
                            case FAIL: {
                                InfoUtil.chatError("Could not find player: '" + name + "'.");
                                return 2;
                            }
                            case SUCCESS: {
                                InfoUtil.chatInfo("Player: '" + NameUtil.getNameFromUUID(NameUtil.getUUIDFromName(name)) + "' is no longer a friend.");
                                return 1;
                            }
                            case NO_CHANGE: {
                                InfoUtil.chatInfo("Player: '" + NameUtil.getNameFromUUID(NameUtil.getUUIDFromName(name)) + "' is already not a friend.");
                                return 1;
                            }
                            default: {
                                break Label_0249;
                            }
                        }
                        break;
                    }
                }
            }
            InfoUtil.chatError("Could not recognize parameter: '" + command + "'.");
            return 2;
        }))).executes(c -> {
            final String command = StringArgumentType.getString(c, "command");
            if (command.equalsIgnoreCase("list")) {
                final StringBuilder builder = new StringBuilder().append("Friend list:").append("\n");
                FriendUtil.FRIENDS.forEach(uuid -> builder.append(NameUtil.getNameFromUUID(uuid)).append("\n"));
                InfoUtil.chatInfo(builder.toString());
                return 1;
            }
            if (command.equalsIgnoreCase("import")) {
                final File file = new File(System.getProperty("user.home") + "\\Future\\friends.json");
                JsonElement element;
                try {
                    element = new JsonParser().parse(new JsonReader((Reader)new FileReader(file)));
                }
                catch (FileNotFoundException e) {
                    InfoUtil.chatInfo("File not found");
                    return 2;
                }
                for (final JsonElement jsonElement : element.getAsJsonArray()) {
                    if (!(jsonElement instanceof JsonObject)) {
                        continue;
                    }
                    final JsonObject object = jsonElement.getAsJsonObject();
                    System.out.println(object.get("friend-label").getAsString());
                    FriendUtil.addFriend(object.get("friend-label").getAsString());
                }
                InfoUtil.chatInfo("Added Future Friends!");
                return 1;
            }
            InfoUtil.chatError("You should input a name!");
            return 2;
        })));
    }
}
