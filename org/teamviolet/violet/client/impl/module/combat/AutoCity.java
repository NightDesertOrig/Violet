//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.account.*;
import java.util.*;
import java.util.stream.*;
import net.minecraft.item.*;

@Module.Manifest(Module.Category.COMBAT)
public class AutoCity extends Module
{
    private final Value<Integer> range;
    private final Value<Integer> targetRange;
    private final Value<Boolean> crystals;
    private final Value<Logic> logic;
    private final Value<Integer> delay;
    private final Value<Boolean> rotate;
    private final Value<Boolean> oneThirteen;
    private final Value<Boolean> packet;
    private final Value<Boolean> render;
    private final Value<Color> color;
    private final Value<RenderUtil.RenderMode> renderMode;
    private Stage stage;
    private EntityPlayer target;
    private EntityEnderCrystal crystal;
    private BlockPos crystalPos;
    private BlockPos breakingPos;
    private final Timer delayTimer;
    private Runnable action;
    private EnumFacing direction;
    private int originalSlot;
    private int crystalSlot;
    private int pickaxeSlot;
    
    public AutoCity() {
        this.range = (Value<Integer>)new ValueFactory().withName("Range").withVal((Object)4).withBounds(1.0f, 6.0f, 1).build((Module)this);
        this.targetRange = (Value<Integer>)new ValueFactory().withName("TargetRange").withVal((Object)4).withBounds(1.0f, 20.0f, 1).build((Module)this);
        this.crystals = (Value<Boolean>)ValueFactory.booleanValue().withName("Crystal").withVal((Object)false).build((Module)this);
        this.logic = (Value<Logic>)new ValueFactory().withName("Logic").withVal((Object)Logic.PlaceMine).build((Module)this);
        this.delay = (Value<Integer>)new ValueFactory().withName("Delay").withVal((Object)30).withBounds(1.0f, 500.0f, 1).build((Module)this);
        this.rotate = (Value<Boolean>)ValueFactory.booleanValue().withName("Rotate").withVal((Object)true).build((Module)this);
        this.oneThirteen = (Value<Boolean>)ValueFactory.booleanValue().withName("1.13").withVal((Object)false).build((Module)this);
        this.packet = (Value<Boolean>)ValueFactory.booleanValue().withName("Packet").withVal((Object)false).build((Module)this);
        this.render = (Value<Boolean>)ValueFactory.booleanValue().withName("Render").withVal((Object)true).build((Module)this);
        this.color = (Value<Color>)ValueFactory.colorValue().withName("Color").withVal((Object)new Color(2083935470, true)).build((Value)this.render);
        this.renderMode = (Value<RenderUtil.RenderMode>)new ValueFactory().withName("Mode").withVal((Object)RenderUtil.RenderMode.BOTH).build((Value)this.render);
        this.stage = Stage.FindingTarget;
        this.delayTimer = new Timer();
        this.originalSlot = -1;
        this.crystalSlot = -1;
        this.pickaxeSlot = -1;
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.stage = Stage.FindingTarget;
        this.target = null;
        this.crystal = null;
        this.breakingPos = null;
        this.crystalPos = null;
        this.action = null;
        this.originalSlot = -1;
        this.crystalSlot = -1;
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
        if (this.target != null) {
            this.setInfo(this.target.getName());
        }
        this.doAutoCity();
        if (this.action != null && this.delayTimer.passed((int)this.delay.getValue())) {
            this.action.run();
            this.action = null;
        }
    }
    
    @Listener
    private void listen(final Render3DEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.breakingPos != null) {
            RenderUtil.drawBoxBlockPos(this.breakingPos, (Color)this.color.getValue(), (RenderUtil.RenderMode)this.renderMode.getValue());
        }
        if (this.crystalPos != null) {
            RenderUtil.drawBoxBlockPos(this.crystalPos, (Color)this.color.getValue(), (RenderUtil.RenderMode)this.renderMode.getValue());
        }
    }
    
    private void doAutoCity() {
        if (this.target != null && !this.checkTargetAndSetupPositions(this.target)) {
            this.stage = Stage.FindingTarget;
            this.target = null;
            this.crystal = null;
            this.breakingPos = null;
            this.crystalPos = null;
            this.action = null;
            this.originalSlot = -1;
            this.crystalSlot = -1;
            this.pickaxeSlot = -1;
        }
        switch (this.stage) {
            case FindingTarget: {
                for (final EntityPlayer target : this.getTargets()) {
                    if (this.checkTargetAndSetupPositions(target)) {
                        if (this.crystals.getValue()) {
                            if (this.logic.getValue() == Logic.PlaceMine) {
                                this.stage = Stage.PlacingCrystal;
                            }
                            else {
                                this.stage = Stage.Mining;
                            }
                        }
                        else {
                            this.stage = Stage.Mining;
                        }
                        return;
                    }
                }
                break;
            }
            case Mining: {
                final Vec3d vec = new Vec3d((Vec3i)this.breakingPos).add(0.5, 0.0, 0.5);
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
                        if (this.breakingPos == null) {
                            return;
                        }
                        else {
                            if (this.packet.getValue()) {
                                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                                this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.breakingPos, this.direction.getOpposite()));
                                this.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.breakingPos, this.direction.getOpposite()));
                            }
                            else {
                                this.mc.playerController.processRightClickBlock(this.mc.player, this.mc.world, this.breakingPos, this.direction.getOpposite(), this.mc.player.getLookVec(), EnumHand.MAIN_HAND);
                                this.mc.player.swingArm(EnumHand.MAIN_HAND);
                            }
                            RotationUtil.resetRotation();
                            if (this.crystals.getValue()) {
                                if (this.logic.getValue() == Logic.PlaceMine) {
                                    this.stage = Stage.Mining;
                                }
                                else {
                                    this.stage = Stage.FindingTarget;
                                }
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
            case PlacingCrystal: {
                final Vec3d vec = new Vec3d((Vec3i)this.crystalPos).add(0.5, 0.0, 0.5);
                final float[] angles = RotationUtil.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), vec);
                RotationUtil.setRotation(angles[1], angles[0]);
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
                    BlockUtil.placeCrystal(this.crystalPos, EnumFacing.UP, hand, lookVec, false);
                    RotationUtil.resetRotation();
                    if (this.logic.getValue() == Logic.PlaceMine) {
                        this.stage = Stage.Mining;
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
        if (!BlockUtil.isSurrounded(feet)) {
            return false;
        }
        for (final EnumFacing offset : EnumFacing.HORIZONTALS) {
            if (BlockUtil.isBreakable(feet.offset(offset)) && !BlockUtil.isAirBlock(feet.offset(offset)) && BlockUtil.canPlaceCrystal(feet.offset(offset).offset(offset).down(), (boolean)this.oneThirteen.getValue(), false, null)) {
                this.target = targetPlayer;
                this.breakingPos = feet.offset(offset);
                this.crystalPos = feet.offset(offset).offset(offset).down();
                this.direction = offset;
                return true;
            }
        }
        return false;
    }
    
    private List<EntityPlayer> getTargets() {
        return (List<EntityPlayer>)this.mc.world.playerEntities.stream().filter(entityPlayer -> !FriendUtil.isFriend(entityPlayer)).filter(entityPlayer -> entityPlayer != this.mc.player).filter(e -> this.mc.player.getDistance(e) < (int)this.range.getValue()).filter(e -> BlockUtil.isSurrounded(new BlockPos(e))).sorted(Comparator.comparing(e -> this.mc.player.getDistance(e))).collect(Collectors.toList());
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
    
    private enum Logic
    {
        MinePlace, 
        PlaceMine;
    }
    
    private enum Stage
    {
        FindingTarget, 
        PlacingCrystal, 
        Mining;
    }
}
