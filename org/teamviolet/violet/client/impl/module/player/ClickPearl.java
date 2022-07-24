//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.lwjgl.input.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;

@Module.Manifest(Module.Category.PLAYER)
public class ClickPearl extends Module
{
    private final Value<KeyMode> keyMode;
    private final Value<InventoryUtil.SwitchMode> switchMode;
    private boolean clicked;
    
    public ClickPearl() {
        this.keyMode = (Value<KeyMode>)new ValueFactory().withName("Mode").withVal((Object)KeyMode.MIDDLECLICK).build((Module)this);
        this.switchMode = (Value<InventoryUtil.SwitchMode>)new ValueFactory().withName("Switch").withVal((Object)InventoryUtil.SwitchMode.Silent).build((Module)this);
        this.clicked = false;
    }
    
    public void onEnable() {
        if (!Utils.nullCheck() && this.keyMode.getValue() == KeyMode.TOGGLE) {
            this.throwPearl();
            this.setEnabled(false);
        }
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.keyMode.getValue() == KeyMode.MIDDLECLICK) {
            if (Mouse.isButtonDown(2)) {
                if (!this.clicked) {
                    this.throwPearl();
                }
                this.clicked = true;
            }
            else {
                this.clicked = false;
            }
        }
    }
    
    private void throwPearl() {
        final int pearlSlot = InventoryUtil.getHotbarPos(Items.ENDER_PEARL);
        final boolean bl;
        final boolean offhand = bl = (this.mc.player.getHeldItemOffhand().getItem() == Items.ENDER_PEARL);
        if (pearlSlot != -1 || offhand) {
            final int oldslot = this.mc.player.inventory.currentItem;
            if (!offhand) {
                InventoryUtil.switchToPos(pearlSlot, (InventoryUtil.SwitchMode)this.switchMode.getValue());
            }
            this.mc.playerController.processRightClick((EntityPlayer)this.mc.player, (World)this.mc.world, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
            if (!offhand) {
                InventoryUtil.switchToPos(oldslot, InventoryUtil.SwitchMode.Client);
            }
        }
    }
    
    private enum KeyMode
    {
        TOGGLE, 
        MIDDLECLICK;
    }
}
