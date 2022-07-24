//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.item.*;

@Module.Manifest(Module.Category.COMBAT)
public class AntiLava extends Module
{
    private long delay;
    private Stage stage;
    private BlockPos mouse;
    private BlockPos netherrack;
    private BlockPos obby;
    private final Timer delayTimer;
    private Runnable action;
    private int originalSlot;
    private int netherrackSlot;
    private int obsidianSlot;
    private int pickaxeSlot;
    
    public AntiLava() {
        this.delay = 120L;
        this.stage = Stage.FindSpot;
        this.delayTimer = new Timer();
        this.originalSlot = -1;
        this.netherrackSlot = -1;
        this.obsidianSlot = -1;
        this.pickaxeSlot = -1;
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.stage = Stage.FindSpot;
        this.action = null;
        this.obby = null;
        this.netherrack = null;
        this.mouse = null;
        this.originalSlot = -1;
        this.netherrackSlot = -1;
        this.pickaxeSlot = -1;
    }
    
    protected void onDisable() {
        RotationUtil.resetRotation();
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        this.doAntiLava();
        if (this.action != null && this.delayTimer.passed(this.delay)) {
            this.action.run();
            this.action = null;
        }
    }
    
    private void doAntiLava() {
        switch (this.stage) {
            case FindSpot: {
                final RayTraceResult result = this.mc.objectMouseOver;
                if (result == null || result.typeOfHit != RayTraceResult.Type.BLOCK) {
                    break;
                }
                if (BlockUtil.isCrystalPlaceableBlock(result.getBlockPos())) {
                    this.mouse = result.getBlockPos();
                    this.netherrack = this.mouse.up();
                    this.obby = this.netherrack.up();
                    this.stage = Stage.PlaceNetherrack;
                    break;
                }
                InfoUtil.chatInfo("Invalid spot!", this);
                this.setEnabled(!this.isEnabled());
            }
            case PlaceNetherrack: {
                final Vec3d vec = new Vec3d((Vec3i)this.netherrack).add(0.5, 0.0, 0.5);
                final float[] angles = RotationUtil.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                RotationUtil.setRotation(angles[1], angles[0]);
                final boolean isSprinting;
                this.action = (() -> {
                    this.delayTimer.reset();
                    isSprinting = this.mc.player.isSprinting();
                    this.netherrackSlot = this.getNetherrackSlot();
                    if (this.netherrackSlot == -1) {
                        InfoUtil.chatInfo("No netherrack found!", this);
                        this.setEnabled(!this.isEnabled());
                        return;
                    }
                    else {
                        if (this.mc.player.inventory.currentItem != this.netherrackSlot) {
                            this.mc.player.inventory.currentItem = this.netherrackSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.netherrackSlot));
                        }
                        if (isSprinting) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                        }
                        BlockUtil.placeBlock(this.netherrack, true);
                        if (isSprinting) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                        }
                        RotationUtil.resetRotation();
                        this.stage = Stage.PlaceObby;
                        return;
                    }
                });
                break;
            }
            case PlaceObby: {
                final Vec3d vec = new Vec3d((Vec3i)this.obby).add(0.5, 0.0, 0.5);
                final float[] angles = RotationUtil.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                RotationUtil.setRotation(angles[1], angles[0]);
                final boolean isSprinting2;
                this.action = (() -> {
                    this.delayTimer.reset();
                    isSprinting2 = this.mc.player.isSprinting();
                    this.obsidianSlot = this.getObbySlot();
                    if (this.obsidianSlot == -1) {
                        InfoUtil.chatInfo("No obby found!", this);
                        this.setEnabled(!this.isEnabled());
                        return;
                    }
                    else {
                        if (this.mc.player.inventory.currentItem != this.obsidianSlot) {
                            this.mc.player.inventory.currentItem = this.obsidianSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.obsidianSlot));
                        }
                        if (isSprinting2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                        }
                        BlockUtil.placeBlock(this.obby, true);
                        if (isSprinting2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                        }
                        RotationUtil.resetRotation();
                        this.stage = Stage.MineNetherrack;
                        return;
                    }
                });
                break;
            }
            case MineNetherrack: {
                final Vec3d vec = new Vec3d((Vec3i)this.netherrack).add(0.5, 0.0, 0.5);
                final float[] angles = RotationUtil.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                RotationUtil.setRotation(angles[1], angles[0]);
                this.action = (() -> {
                    this.delayTimer.reset();
                    this.pickaxeSlot = this.getPickaxeSlot();
                    if (this.pickaxeSlot == -1) {
                        InfoUtil.chatInfo("No pickaxe found!", this);
                        this.setEnabled(!this.isEnabled());
                        return;
                    }
                    else {
                        if (this.mc.player.inventory.currentItem != this.pickaxeSlot) {
                            this.mc.player.inventory.currentItem = this.pickaxeSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.pickaxeSlot));
                        }
                        if (this.netherrack == null) {
                            return;
                        }
                        else {
                            this.mc.player.swingArm(EnumHand.MAIN_HAND);
                            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.netherrack, EnumFacing.UP));
                            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.netherrack, EnumFacing.UP));
                            RotationUtil.resetRotation();
                            this.setEnabled(false);
                            return;
                        }
                    }
                });
                break;
            }
        }
    }
    
    private int getObbySlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block == Blocks.OBSIDIAN) {
                    slot = i;
                    break;
                }
            }
        }
        return slot;
    }
    
    private int getNetherrackSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block == Blocks.NETHERRACK || block == Blocks.COBBLESTONE) {
                    slot = i;
                    break;
                }
            }
        }
        return slot;
    }
    
    private int getPickaxeSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemPickaxe) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    private enum Stage
    {
        FindSpot, 
        PlaceNetherrack, 
        PlaceObby, 
        MineNetherrack;
    }
}
