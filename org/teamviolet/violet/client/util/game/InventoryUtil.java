//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.network.play.server.*;
import org.teamviolet.violet.client.api.event.handler.*;

public class InventoryUtil
{
    private static final Minecraft mc;
    private static int spoofedSlot;
    
    public static boolean putInOffhand(final Item item, final boolean searchHotbar) {
        final int inventoryPos = getInventoryPos(item, searchHotbar);
        if (inventoryPos == -1) {
            return false;
        }
        InventoryUtil.mc.playerController.windowClick(Utils.player().inventoryContainer.windowId, inventoryPos, 0, ClickType.PICKUP, (EntityPlayer)Utils.player());
        InventoryUtil.mc.playerController.windowClick(Utils.player().inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)Utils.player());
        InventoryUtil.mc.playerController.windowClick(Utils.player().inventoryContainer.windowId, inventoryPos, 0, ClickType.PICKUP, (EntityPlayer)Utils.player());
        return true;
    }
    
    public static int switchToItem(final Item item, final SwitchMode switchMode) {
        if (Utils.player().getHeldItemMainhand().getItem() == item) {
            return Utils.player().inventory.currentItem;
        }
        return switchToPos(getHotbarPos(item), switchMode);
    }
    
    public static int switchToPos(final int pos, final SwitchMode switchMode) {
        if (pos == -1) {
            return -1;
        }
        switch (switchMode) {
            case Client: {
                Utils.player().inventory.currentItem = pos;
                break;
            }
            case Silent: {
                if (pos == Utils.player().inventory.currentItem) {
                    return Utils.player().inventory.currentItem;
                }
                Utils.sendPacket((Packet<?>)new CPacketHeldItemChange(pos));
                break;
            }
        }
        return pos;
    }
    
    public static boolean switchToSword(final SwitchMode switchMode) {
        for (int i = 0; i < 9; ++i) {
            if (Utils.player().inventory.getStackInSlot(i).getItem() instanceof ItemSword) {
                return switchToPos(i, switchMode) != -1;
            }
        }
        return false;
    }
    
    public static int getHotbarPos(final Item item) {
        for (int i = 0; i < 9; ++i) {
            if (Utils.player().inventory.getStackInSlot(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getInventoryPos(final Item item, final boolean searchHotbar) {
        for (int i = searchHotbar ? 0 : 9; i < 45; ++i) {
            if (Utils.player().inventory.getStackInSlot(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public static Item getServerItem() {
        return Utils.player().inventory.getStackInSlot(InventoryUtil.spoofedSlot).getItem();
    }
    
    public static int getSpoofedSlot() {
        return InventoryUtil.spoofedSlot;
    }
    
    @Listener
    public static void listen(final PacketEvent event) {
        if (event.getPacket() instanceof CPacketHeldItemChange) {
            InventoryUtil.spoofedSlot = ((CPacketHeldItemChange)event.getPacket()).getSlotId();
        }
        if (event.getPacket() instanceof SPacketHeldItemChange) {
            InventoryUtil.spoofedSlot = ((SPacketHeldItemChange)event.getPacket()).getHeldItemHotbarIndex();
        }
    }
    
    private InventoryUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        mc = Minecraft.getMinecraft();
        InventoryUtil.spoofedSlot = -1;
    }
    
    public enum SwitchMode
    {
        None, 
        Client, 
        Silent;
    }
}
