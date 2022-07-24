//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import com.mojang.realmsclient.gui.*;

@Module.Manifest(value = Module.Category.CLIENT, alwaysEnabled = true)
public class ChatInfo extends Module
{
    public final Value<Themes> theme;
    public final Value<Boolean> modules;
    public final Value<Boolean> totemPop;
    private static ChatInfo instance;
    
    public ChatInfo() {
        this.theme = (Value<Themes>)new ValueFactory().withName("ChatTheme").withVal((Object)Themes.OG).build((Module)this);
        this.modules = (Value<Boolean>)ValueFactory.booleanValue().withName("Modules").withVal((Object)true).build((Module)this);
        this.totemPop = (Value<Boolean>)ValueFactory.booleanValue().withName("Totems").withVal((Object)true).build((Module)this);
    }
    
    public static ChatInfo getInstance() {
        if (ChatInfo.instance == null) {
            ChatInfo.instance = new ChatInfo();
        }
        return ChatInfo.instance;
    }
    
    public enum Themes
    {
        OG(new ChatTheme(ChatFormatting.DARK_PURPLE, ChatFormatting.LIGHT_PURPLE, ChatFormatting.AQUA).withEffects("", ChatFormatting.ITALIC.toString())), 
        Nk(new ChatTheme(ChatFormatting.DARK_PURPLE, ChatFormatting.LIGHT_PURPLE, ChatFormatting.LIGHT_PURPLE).withEffects("", ChatFormatting.BOLD.toString())), 
        Red(new ChatTheme(ChatFormatting.DARK_RED, ChatFormatting.RED, ChatFormatting.DARK_RED).withEffects("", ChatFormatting.ITALIC.toString())), 
        BoldName(new ChatTheme(ChatFormatting.DARK_PURPLE, ChatFormatting.LIGHT_PURPLE, ChatFormatting.AQUA).withEffects("", ChatFormatting.BOLD.toString())), 
        Blue(new ChatTheme(ChatFormatting.DARK_BLUE, ChatFormatting.BLUE, ChatFormatting.BLUE).withEffects("", ChatFormatting.ITALIC.toString())), 
        Yoda(new ChatTheme(ChatFormatting.DARK_GREEN, ChatFormatting.GREEN, ChatFormatting.GREEN).withEffects("", ChatFormatting.ITALIC.toString()));
        
        private ChatTheme theme;
        
        private Themes(final ChatTheme theme) {
            this.theme = theme;
        }
        
        public ChatTheme getTheme() {
            return this.theme;
        }
    }
    
    public static class ChatTheme
    {
        private final ChatFormatting bracketColor;
        private final ChatFormatting nameColor;
        private final ChatFormatting messageColor;
        private String bracketEffect;
        private String nameEffect;
        
        public ChatTheme(final ChatFormatting bracketColor, final ChatFormatting nameColor, final ChatFormatting messageColor) {
            this.bracketEffect = "";
            this.nameEffect = "";
            this.bracketColor = bracketColor;
            this.nameColor = nameColor;
            this.messageColor = messageColor;
        }
        
        public ChatTheme withEffects(final String bracketEffect, final String nameEffect) {
            this.bracketEffect = nameEffect;
            this.nameEffect = nameEffect;
            return this;
        }
        
        public ChatFormatting getBracketColor() {
            return this.bracketColor;
        }
        
        public ChatFormatting getNameColor() {
            return this.nameColor;
        }
        
        public ChatFormatting getMessageColor() {
            return this.messageColor;
        }
        
        public String getBracketEffect() {
            return this.bracketEffect;
        }
        
        public String getNameEffect() {
            return this.nameEffect;
        }
        
        public String getBracketFormatting() {
            return this.bracketColor.toString() + this.bracketEffect;
        }
        
        public String getNameFormatting() {
            return this.nameColor.toString() + this.nameEffect;
        }
    }
}
