//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

@Module.Manifest(Module.Category.PLAYER)
public class AntiArmor extends Module
{
    private final Value<Integer> delay;
    private Timer timer;
    
    public AntiArmor() {
        this.delay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)0).withBounds(0.0f, 4.0f, 0).build((Module)this);
        this.timer = new Timer();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (this.mc.currentScreen instanceof GuiInventory) {
            return;
        }
        final Item helm = this.mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem();
        final Item chest = this.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem();
        final Item legs = this.mc.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem();
        final Item feet = this.mc.player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem();
        if (helm != Items.AIR) {
            this.removeItem(5);
        }
        if (chest != Items.AIR) {
            this.removeItem(6);
        }
        if (legs != Items.AIR) {
            this.removeItem(7);
        }
        if (feet != Items.AIR) {
            this.removeItem(8);
        }
    }
    
    private void removeItem(final int slot) {
        if (this.timer.passed((int)this.delay.getValue() / 1000)) {
            this.mc.playerController.windowClick(this.mc.player.inventoryContainer.windowId, slot, 0, ClickType.THROW, (EntityPlayer)this.mc.player);
            this.mc.playerController.updateController();
            this.timer.reset();
        }
    }
}
