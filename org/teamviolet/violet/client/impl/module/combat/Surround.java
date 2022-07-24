//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;

@Module.Manifest(Module.Category.COMBAT)
public class Surround extends Module
{
    private final Value<Integer> bpt;
    private final Value<Integer> delay;
    private final Value<Boolean> packet;
    private final Value<InventoryUtil.SwitchMode> switchMode;
    private final Value<Boolean> constraints;
    private final Value<Boolean> onlyWhileStopped;
    private final Value<Bind> onlyOnKey;
    private int waited;
    
    public Surround() {
        this.bpt = (Value<Integer>)ValueFactory.intValue().withName("BPT").withVal((Object)6).withBounds(1.0f, 12.0f, 0).build((Module)this);
        this.delay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)1).withBounds(0.0f, 10.0f, 0).build((Module)this);
        this.packet = (Value<Boolean>)ValueFactory.booleanValue().withName("Packet").withVal((Object)false).build((Module)this);
        this.switchMode = (Value<InventoryUtil.SwitchMode>)new ValueFactory().withName("Switch").withVal((Object)InventoryUtil.SwitchMode.Silent).build((Module)this);
        this.constraints = (Value<Boolean>)ValueFactory.booleanValue().withName("Constraints").withVal((Object)false).withAction((oldVal, newVal) -> false).build((Module)this);
        this.onlyWhileStopped = (Value<Boolean>)ValueFactory.booleanValue().withName("Only While Stopped").withVal((Object)true).build((Value)this.constraints);
        this.onlyOnKey = (Value<Bind>)ValueFactory.bindValue().withName("Only On Key").withVal((Object)new Bind(0)).build((Value)this.constraints);
        this.waited = 0;
    }
    
    protected void onEnable() {
        this.waited = (int)this.delay.getValue();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (this.waited++ < (int)this.delay.getValue()) {
            return;
        }
        if ((boolean)this.onlyWhileStopped.getValue() && EntityUtil.isPlayerMoving()) {
            return;
        }
        if (((Bind)this.onlyOnKey.getValue()).getKey() != 0 && !((Bind)this.onlyOnKey.getValue()).isDown()) {
            return;
        }
        this.waited = 0;
        final BlockPos pos = EntityUtil.getEntityPos((Entity)Utils.player());
        final int old = Utils.player().inventory.currentItem;
        int blocks = 0;
        for (final EnumFacing facing : EnumFacing.VALUES) {
            if (facing != EnumFacing.UP) {
                final BlockPos offset = pos.offset(facing);
                if (Utils.world().getBlockState(offset).getBlock() == Blocks.AIR) {
                    final EnumFacing neighbor = BlockUtil.getNeighbor(offset);
                    if (neighbor != null) {
                        if (this.block(offset, ++blocks)) {
                            break;
                        }
                    }
                    else {
                        for (final EnumFacing facing2 : EnumFacing.VALUES) {
                            final BlockPos offset2 = offset.offset(facing2);
                            if (BlockUtil.getNeighbor(offset2) != null) {
                                if (BlockUtil.getNeighbor(offset) == null && this.block(offset2, ++blocks)) {
                                    break;
                                }
                                if (this.block(offset, ++blocks)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        InventoryUtil.switchToPos(old, (InventoryUtil.SwitchMode)this.switchMode.getValue());
    }
    
    private boolean block(final BlockPos pos, final int count) {
        if (!Utils.world().getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos)).isEmpty()) {
            return false;
        }
        final int old = InventoryUtil.switchToItem(Item.getItemFromBlock(Blocks.OBSIDIAN), (InventoryUtil.SwitchMode)this.switchMode.getValue());
        BlockUtil.placeBlock(pos, (boolean)this.packet.getValue());
        if (this.switchMode.getValue() == InventoryUtil.SwitchMode.Silent && count < (int)this.bpt.getValue()) {
            InventoryUtil.switchToPos(old, (InventoryUtil.SwitchMode)this.switchMode.getValue());
        }
        return count >= (int)this.bpt.getValue();
    }
}
