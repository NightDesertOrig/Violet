//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

public class RenderItemFPEvent extends CancelableEvent
{
    private ItemRenderer itemRenderer;
    private AbstractClientPlayer player;
    private float drinkOffset;
    private float mapAngle;
    private EnumHand hand;
    private float x;
    private ItemStack stack;
    private float y;
    private float type;
    
    public RenderItemFPEvent(final ItemRenderer itemRenderer, final AbstractClientPlayer player, final float drinkOffset, final float mapAngle, final EnumHand hand, final float x, final ItemStack stack, final float y, final int type) {
        this.itemRenderer = itemRenderer;
        this.player = player;
        this.drinkOffset = drinkOffset;
        this.mapAngle = mapAngle;
        this.hand = hand;
        this.x = x;
        this.stack = stack;
        this.y = y;
        this.type = (float)type;
    }
    
    public ItemRenderer getItemRenderer() {
        return this.itemRenderer;
    }
    
    public AbstractClientPlayer getPlayer() {
        return this.player;
    }
    
    public float getDrinkOffset() {
        return this.drinkOffset;
    }
    
    public float getMapAngle() {
        return this.mapAngle;
    }
    
    public EnumHand getHand() {
        return this.hand;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public ItemStack getStack() {
        return this.stack;
    }
    
    public float getType() {
        return this.type;
    }
}
