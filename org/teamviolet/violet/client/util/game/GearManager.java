//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import org.teamviolet.violet.client.*;
import net.minecraft.entity.player.*;
import org.teamviolet.violet.client.impl.module.client.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;

public class GearManager
{
    public final Map<String, Integer> totemPopMap;
    
    public GearManager() {
        this.totemPopMap = new HashMap<String, Integer>();
    }
    
    public void init() {
        Violet.getViolet().getDispatcher().register((Object)this);
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        for (final EntityPlayer player : Utils.mc.world.playerEntities) {
            if (player != null) {
                if (player.getHealth() > 0.0f) {
                    continue;
                }
                if (!this.totemPopMap.containsKey(player.getName())) {
                    continue;
                }
                if (ChatInfo.getInstance().totemPop.getValue()) {
                    InfoUtil.chatInfo(ChatFormatting.BOLD + player.getName() + ChatFormatting.RESET + ChatFormatting.AQUA + " has died after popping " + ChatFormatting.BOLD + this.totemPopMap.get(player.getName()) + ChatFormatting.RESET + ChatFormatting.AQUA + " totems." + ChatFormatting.RESET, this.hashCode());
                }
                this.totemPopMap.remove(player.getName());
            }
        }
    }
    
    @Listener
    public void listen(final TotemPopEvent event) {
        final int pops = this.totemPopMap.getOrDefault(event.getPlayer().getName(), 0) + 1;
        this.totemPopMap.put(event.getPlayer().getName(), pops);
        if (ChatInfo.getInstance().totemPop.getValue()) {
            InfoUtil.chatInfo(ChatFormatting.BOLD + event.getPlayer().getName() + ChatFormatting.RESET + ChatFormatting.AQUA + " has popped " + ChatFormatting.BOLD + pops + ChatFormatting.RESET + ChatFormatting.AQUA + " totems." + ChatFormatting.RESET, this.hashCode());
        }
    }
}
