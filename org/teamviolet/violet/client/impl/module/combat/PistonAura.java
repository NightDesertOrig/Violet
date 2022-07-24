//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.network.*;
import java.util.*;
import org.teamviolet.violet.client.util.account.*;
import java.util.stream.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

@Module.Manifest(Module.Category.COMBAT)
public class PistonAura extends Module
{
    private final Value<Integer> range;
    private final Value<Integer> delay;
    private final Value<Boolean> antiSuicide;
    private final Value<Boolean> mine;
    private final Value<Boolean> extraChecks;
    private final Value<Boolean> onlyHoles;
    private Stage stage;
    private EntityPlayer target;
    private EntityEnderCrystal crystal;
    private BlockPos crystalPos;
    private BlockPos pistonPos;
    private BlockPos redstonePos;
    private EnumFacing pistonDirection;
    private final Timer delayTimer;
    private Runnable action;
    private int originalSlot;
    private int crystalSlot;
    private int pistonSlot;
    private int redstoneSlot;
    private int pickaxeSlot;
    
    public PistonAura() {
        this.range = (Value<Integer>)new ValueFactory().withName("Range").withVal((Object)4).withBounds(1.0f, 6.0f, 1).build((Module)this);
        this.delay = (Value<Integer>)new ValueFactory().withName("Delay").withVal((Object)30).withBounds(1.0f, 500.0f, 1).build((Module)this);
        this.antiSuicide = (Value<Boolean>)new ValueFactory().withName("NoSuicide").withVal((Object)false).build((Module)this);
        this.mine = (Value<Boolean>)new ValueFactory().withName("Mine").withVal((Object)true).build((Module)this);
        this.extraChecks = (Value<Boolean>)new ValueFactory().withName("ExtraChecks").withVal((Object)false).build((Module)this);
        this.onlyHoles = (Value<Boolean>)new ValueFactory().withName("OnlyHoles").withVal((Object)false).build((Module)this);
        this.stage = Stage.FindingTarget;
        this.delayTimer = new Timer();
        this.originalSlot = -1;
        this.crystalSlot = -1;
        this.pistonSlot = -1;
        this.redstoneSlot = -1;
        this.pickaxeSlot = -1;
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.stage = Stage.FindingTarget;
        this.crystalPos = null;
        this.pistonPos = null;
        this.redstonePos = null;
        this.action = null;
        this.originalSlot = -1;
        this.crystalSlot = -1;
        this.pickaxeSlot = -1;
        this.redstoneSlot = -1;
        this.pistonSlot = -1;
    }
    
    protected void onDisable() {
        Violet.getViolet().getRotationUtil().resetRotations();
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.target != null) {
            this.setInfo(this.target.getName());
        }
        this.doPistonAura();
        if (this.action != null && this.delayTimer.passed((int)this.delay.getValue())) {
            this.action.run();
            this.action = null;
        }
    }
    
    private void doPistonAura() {
        switch (this.stage) {
            case FindingTarget: {
                for (final EntityPlayer target : this.getTargets()) {
                    if (this.checkTargetAndSetupPositions(target)) {
                        this.stage = Stage.Piston;
                        break;
                    }
                }
                break;
            }
            case Piston: {
                if (!this.checkTargetAndSetupPositions(this.target)) {
                    this.stage = Stage.FindingTarget;
                    break;
                }
                final Vec3d vec = new Vec3d((Vec3i)this.pistonPos).add(0.5, 0.0, 0.5).add(new Vec3d(this.pistonDirection.getDirectionVec()).scale(0.5));
                final float[] angles = Rotates.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
                final boolean isSprinting;
                final boolean shouldSneak;
                this.action = (() -> {
                    this.delayTimer.reset();
                    isSprinting = this.mc.player.isSprinting();
                    shouldSneak = BlockUtil.shouldSneakWhileRightClicking(this.pistonPos.down());
                    this.pistonSlot = this.getPistonSlot();
                    if (this.pistonSlot == -1) {
                        InfoUtil.chatInfo("No pistons found!", this);
                        this.setEnabled(!this.isEnabled());
                        return;
                    }
                    else {
                        this.originalSlot = this.mc.player.inventory.currentItem;
                        if (this.originalSlot != this.pistonSlot) {
                            this.mc.player.inventory.currentItem = this.pistonSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.pistonSlot));
                        }
                        if (isSprinting) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                        }
                        if (shouldSneak) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                        }
                        BlockUtil.placeBlock(this.pistonPos, true);
                        if (isSprinting) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                        }
                        if (shouldSneak) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                        this.stage = Stage.Crystal;
                        return;
                    }
                });
                break;
            }
            case Crystal: {
                final Vec3d vec = new Vec3d((Vec3i)this.crystalPos.down()).add(0.5, 0.0, 0.5);
                final float[] angles = Rotates.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
                EnumHand hand;
                final Vec3d lookVec;
                this.action = (() -> {
                    this.delayTimer.reset();
                    hand = EnumHand.MAIN_HAND;
                    if (this.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                        hand = EnumHand.OFF_HAND;
                    }
                    else {
                        this.crystalSlot = this.getCrystalSlot();
                        if (this.crystalSlot == -1) {
                            InfoUtil.chatInfo("No crystals found!", this);
                            this.setEnabled(!this.isEnabled());
                            return;
                        }
                        else if (this.mc.player.inventory.currentItem != this.crystalSlot) {
                            this.mc.player.inventory.currentItem = this.crystalSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.crystalSlot));
                        }
                    }
                    BlockUtil.placeCrystal(this.crystalPos.down(), EnumFacing.UP, hand, lookVec, false);
                    this.stage = Stage.Redstone;
                    return;
                });
                break;
            }
            case Redstone: {
                final Vec3d vec = new Vec3d((Vec3i)this.redstonePos).add(0.5, 0.0, 0.5);
                final float[] angles = Rotates.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
                final boolean isSprinting2;
                final boolean shouldSneak2;
                this.action = (() -> {
                    this.delayTimer.reset();
                    isSprinting2 = this.mc.player.isSprinting();
                    shouldSneak2 = BlockUtil.shouldSneakWhileRightClicking(this.pistonPos.down());
                    this.redstoneSlot = this.getRedstoneBlockSlot();
                    if (this.redstoneSlot == -1) {
                        InfoUtil.chatInfo("No redstone found!", this);
                        this.setEnabled(!this.isEnabled());
                        return;
                    }
                    else {
                        if (this.mc.player.inventory.currentItem != this.redstoneSlot) {
                            this.mc.player.inventory.currentItem = this.redstoneSlot;
                            this.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.redstoneSlot));
                        }
                        if (isSprinting2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                        }
                        if (shouldSneak2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                        }
                        BlockUtil.placeBlock(this.redstonePos, true);
                        if (isSprinting2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                        }
                        if (shouldSneak2) {
                            this.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)this.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                        }
                        this.stage = Stage.BreakCrystal;
                        return;
                    }
                });
                break;
            }
            case BreakCrystal: {
                final Vec3d vec = new Vec3d((Vec3i)this.crystalPos).add(0.5, 0.0, 0.5);
                final float[] angles = Rotates.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
                NetHandlerPlayClient connection;
                final CPacketAnimation cPacketAnimation;
                this.action = (() -> {
                    this.delayTimer.reset();
                    this.crystal = (EntityEnderCrystal)this.mc.world.loadedEntityList.stream().filter(e -> e instanceof EntityEnderCrystal).filter(e -> this.mc.player.getDistance(e) <= (int)this.range.getValue() + 4).min(Comparator.comparing(c -> this.mc.player.getDistance(c))).orElse(null);
                    if (this.crystal == null) {
                        return;
                    }
                    else {
                        if (!(boolean)this.antiSuicide.getValue() || (CombatUtil.getDamage((Entity)this.mc.player, this.crystal) < this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount() && (boolean)this.antiSuicide.getValue())) {
                            this.mc.playerController.attackEntity((EntityPlayer)this.mc.player, (Entity)this.crystal);
                            connection = this.mc.player.connection;
                            new CPacketAnimation((this.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                            connection.sendPacket((Packet)cPacketAnimation);
                        }
                        if (!BlockUtil.isAirBlock(this.redstonePos) && BlockUtil.isBreakable(this.redstonePos) && (boolean)this.mine.getValue()) {
                            this.stage = Stage.BreakRedstone;
                        }
                        else {
                            this.stage = Stage.FindingTarget;
                        }
                        return;
                    }
                });
                break;
            }
            case BreakRedstone: {
                final Vec3d vec = new Vec3d((Vec3i)this.redstonePos).add(0.5, 0.0, 0.5);
                final float[] angles = Rotates.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                Violet.getViolet().getRotationUtil().setRotations(angles[0], angles[1]);
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
                        if (this.redstonePos == null) {
                            return;
                        }
                        else {
                            this.mc.player.swingArm(EnumHand.MAIN_HAND);
                            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.redstonePos, this.pistonDirection.getOpposite()));
                            this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.redstonePos, this.pistonDirection.getOpposite()));
                            if (this.extraChecks.getValue()) {
                                this.stage = Stage.DelayPhase;
                            }
                            else {
                                this.stage = Stage.FindingTarget;
                            }
                            return;
                        }
                    }
                });
                break;
            }
            case DelayPhase: {
                this.action = (() -> {
                    this.delayTimer.reset();
                    if (!BlockUtil.isAirBlock(this.pistonPos)) {
                        this.stage = Stage.Crystal;
                    }
                    else {
                        this.stage = Stage.FindingTarget;
                    }
                    return;
                });
                break;
            }
        }
    }
    
    private boolean checkTargetAndSetupPositions(final EntityPlayer targetPlayer) {
        final BlockPos feet = new BlockPos((Entity)targetPlayer);
        if (!BlockUtil.isAirBlock(feet)) {
            return false;
        }
        final BlockPos face = feet.up();
        if (!BlockUtil.isAirBlock(face)) {
            return false;
        }
        for (final EnumFacing offset : EnumFacing.HORIZONTALS) {
            if (BlockUtil.isAirBlock(face.offset(offset)) && BlockUtil.canPlaceCrystal(face.offset(offset).down(), false, false, null) && BlockUtil.isAirBlock(face.offset(offset).offset(offset)) && !BlockUtil.isAirBlock(face.offset(offset).offset(offset).down()) && BlockUtil.isAirBlock(face.offset(offset).offset(offset).offset(offset)) && !BlockUtil.isAirBlock(face.offset(offset).offset(offset).offset(offset).down())) {
                this.target = targetPlayer;
                this.crystalPos = face.offset(offset);
                this.pistonPos = face.offset(offset).offset(offset);
                this.redstonePos = face.offset(offset).offset(offset).offset(offset);
                this.pistonDirection = offset;
                return true;
            }
        }
        final BlockPos top = face.up();
        for (final EnumFacing offset2 : EnumFacing.HORIZONTALS) {
            if (BlockUtil.isAirBlock(top.offset(offset2)) && BlockUtil.canPlaceCrystal(top.offset(offset2).down(), false, false, null) && BlockUtil.isAirBlock(top.offset(offset2).offset(offset2)) && !BlockUtil.isAirBlock(top.offset(offset2).offset(offset2).down()) && BlockUtil.isAirBlock(top.offset(offset2).offset(offset2).offset(offset2)) && !BlockUtil.isAirBlock(top.offset(offset2).offset(offset2).offset(offset2).down())) {
                this.target = targetPlayer;
                this.crystalPos = top.offset(offset2);
                this.pistonPos = top.offset(offset2).offset(offset2);
                this.redstonePos = top.offset(offset2).offset(offset2).offset(offset2);
                this.pistonDirection = offset2;
                return true;
            }
        }
        return false;
    }
    
    private List<EntityPlayer> getTargets() {
        return (List<EntityPlayer>)this.mc.world.playerEntities.stream().filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer)).filter(entityPlayer -> entityPlayer != this.mc.player).filter(e -> this.mc.player.getDistance(e) < (int)this.range.getValue()).filter(e -> {
            if (this.onlyHoles.getValue()) {
                return BlockUtil.isSurrounded(new BlockPos(e));
            }
            else {
                return true;
            }
        }).sorted(Comparator.comparing(e -> this.mc.player.getDistance(e))).collect(Collectors.toList());
    }
    
    private int getPistonSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block instanceof BlockPistonBase) {
                    slot = i;
                    break;
                }
            }
        }
        return slot;
    }
    
    private int getRedstoneBlockSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock)stack.getItem()).getBlock();
                if (block == Blocks.REDSTONE_BLOCK || block == Blocks.REDSTONE_TORCH) {
                    slot = i;
                    break;
                }
            }
        }
        return slot;
    }
    
    private int getCrystalSlot() {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = this.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemEndCrystal) {
                slot = i;
                break;
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
        FindingTarget, 
        Piston, 
        Crystal, 
        Redstone, 
        BreakCrystal, 
        BreakRedstone, 
        DelayPhase;
    }
    
    private enum Mode
    {
        Damage, 
        AntiHole;
    }
}
