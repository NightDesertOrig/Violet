//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.impl.module.client.*;
import com.mojang.realmsclient.gui.*;
import org.teamviolet.violet.client.util.account.*;
import net.minecraft.util.text.*;

public class InfoUtil
{
    private static final Minecraft mc;
    
    public static void chatInfo(final String message, final Module module) {
        chatInfo("[" + module.getName() + "] " + message, 0);
    }
    
    public static void chatInfoDebug(final String message) {
        chatInfo("[Debug] " + message, 0);
    }
    
    public static void chatInfo(final String message) {
        chatInfo(message, 0);
    }
    
    public static void chatInfo(final String message, final int id) {
        chat(((ChatInfo.Themes)ChatInfo.getInstance().theme.getValue()).getTheme().getMessageColor() + message, id);
    }
    
    public static void chatError(final String message) {
        chatError(message, 0);
    }
    
    public static void chatError(final String message, final int id) {
        chat(ChatFormatting.RED + message, id);
    }
    
    private static void chat(final String message, final int id) {
        if (Utils.player() == null || Utils.world() == null) {
            return;
        }
        final ChatInfo.ChatTheme theme = ((ChatInfo.Themes)ChatInfo.getInstance().theme.getValue()).getTheme();
        InfoUtil.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(theme.getBracketFormatting() + "<" + theme.getNameFormatting() + Client.name + theme.getBracketFormatting() + "> " + ChatFormatting.RESET + message), id);
    }
    
    private InfoUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
