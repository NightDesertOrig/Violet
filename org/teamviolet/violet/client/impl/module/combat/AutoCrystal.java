//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.play.server.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.account.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;
import net.minecraft.init.*;
import net.minecraft.network.*;

@Module.Manifest(Module.Category.COMBAT)
public class AutoCrystal extends Module
{
    private final Value<Boolean> place;
    private final Value<Integer> placeDelay;
    private final Value<Double> placeRange;
    private final Value<Double> placeMinDmg;
    private final Value<Double> placeMaxSelfDmg;
    private final Value<Boolean> placePacket;
    private final Value<InventoryUtil.SwitchMode> placeSwitch;
    private final Value<Boolean> placeFast;
    private final Value<Boolean> placeFastIgnoreTimer;
    private final Value<Boolean> placeRaytrace;
    private final Value<Boolean> placeOneThirteen;
    private final Value<Boolean> placeMultiplace;
    private final Value<Boolean> explode;
    private final Value<Integer> explodeDelay;
    private final Value<Double> explodeRange;
    private final Value<Double> explodeMinDmg;
    private final Value<Double> explodeMaxSelfDmg;
    private final Value<InventoryUtil.SwitchMode> explodeAntiWeakness;
    private final Value<Integer> explodeMaxHits;
    private final Value<Boolean> render;
    private final Value<Color> renderColor;
    private final Value<RenderUtil.RenderMode> renderMode;
    private final Value<Boolean> rotate;
    private final Value<RotationMode> rotateMode;
    private final Value<Float> rotateMaxPitchError;
    private final Value<Float> rotateMaxYawError;
    private final Value<Float> rotatePitchDiff;
    private final Value<Float> rotateYawDiff;
    private final Value<Boolean> rotateAntiRubber;
    private final Value<Boolean> faceplace;
    private final Value<Double> minFaceplaceHP;
    private final Value<Double> faceplaceDmg;
    private final Value<Bind> faceplaceBind;
    private final Value<Boolean> misc;
    private final Value<SwingHand> swingHand;
    private final Value<Double> playerRange;
    private final Value<Integer> clearDelay;
    private static AutoCrystal instance;
    private final Timer placeTimer;
    private final Timer explodeTimer;
    private final Timer clearIgnoredIDsTimer;
    private final List<Integer> ignoredIDs;
    private final Map<Integer, Integer> amountHit;
    private BlockPos renderPos;
    private boolean rotated;
    private boolean busy;
    
    private AutoCrystal() {
        this.place = (Value<Boolean>)ValueFactory.booleanValue().withName("Place").withVal((Object)true).build((Module)this);
        this.placeDelay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)60).withBounds(0.0f, 400.0f, 0).build((Value)this.place);
        this.placeRange = (Value<Double>)ValueFactory.doubleValue().withName("Range").withVal((Object)5.5).withBounds(0.1f, 6.0f, 1).build((Value)this.place);
        this.placeMinDmg = (Value<Double>)ValueFactory.doubleValue().withName("Min Damage").withVal((Object)5.0).withBounds(0.1f, 36.0f, 1).build((Value)this.place);
        this.placeMaxSelfDmg = (Value<Double>)ValueFactory.doubleValue().withName("Max Self Damage").withVal((Object)8.0).withBounds(0.1f, 36.0f, 1).build((Value)this.place);
        this.placePacket = (Value<Boolean>)ValueFactory.booleanValue().withName("Packet").withVal((Object)true).build((Value)this.place);
        this.placeSwitch = (Value<InventoryUtil.SwitchMode>)new ValueFactory().withName("Switch").withVal((Object)InventoryUtil.SwitchMode.Silent).build((Value)this.place);
        this.placeFast = (Value<Boolean>)ValueFactory.booleanValue().withName("Fast").withVal((Object)true).build((Value)this.place);
        this.placeFastIgnoreTimer = (Value<Boolean>)ValueFactory.booleanValue().withName("Ignore Timer").withVal((Object)true).build((Value)this.placeFast);
        this.placeRaytrace = (Value<Boolean>)ValueFactory.booleanValue().withName("Raytrace").withVal((Object)false).build((Value)this.place);
        this.placeOneThirteen = (Value<Boolean>)ValueFactory.booleanValue().withName("1.13").withVal((Object)false).build((Value)this.place);
        this.placeMultiplace = (Value<Boolean>)ValueFactory.booleanValue().withName("Multiplace").withVal((Object)false).build((Value)this.place);
        this.explode = (Value<Boolean>)ValueFactory.booleanValue().withName("Break").withVal((Object)true).build((Module)this);
        this.explodeDelay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)60).withBounds(0.0f, 400.0f, 0).build((Value)this.explode);
        this.explodeRange = (Value<Double>)ValueFactory.doubleValue().withName("Range").withVal((Object)5.5).withBounds(0.1f, 6.0f, 1).build((Value)this.explode);
        this.explodeMinDmg = (Value<Double>)ValueFactory.doubleValue().withName("Min Damage").withVal((Object)5.0).withBounds(0.1f, 36.0f, 1).build((Value)this.explode);
        this.explodeMaxSelfDmg = (Value<Double>)ValueFactory.doubleValue().withName("Max Self Damage").withVal((Object)8.0).withBounds(0.1f, 36.0f, 1).build((Value)this.explode);
        this.explodeAntiWeakness = (Value<InventoryUtil.SwitchMode>)new ValueFactory().withName("Anti Weakness").withVal((Object)InventoryUtil.SwitchMode.Silent).build((Value)this.explode);
        this.explodeMaxHits = (Value<Integer>)ValueFactory.intValue().withName("Max Hits").withVal((Object)1).withBounds(1.0f, 5.0f, 0).build((Value)this.explode);
        this.render = (Value<Boolean>)ValueFactory.booleanValue().withName("Render").withVal((Object)true).build((Module)this);
        this.renderColor = (Value<Color>)ValueFactory.colorValue().withName("Color").withVal((Object)new Color(-1466683919, true)).build((Value)this.render);
        this.renderMode = (Value<RenderUtil.RenderMode>)new ValueFactory().withName("Mode").withVal((Object)RenderUtil.RenderMode.BOTH).build((Value)this.render);
        this.rotate = (Value<Boolean>)ValueFactory.booleanValue().withName("Rotate").withVal((Object)false).build((Module)this);
        this.rotateMode = (Value<RotationMode>)new ValueFactory().withName("Mode").withVal((Object)RotationMode.Instant).build((Value)this.rotate);
        this.rotateMaxPitchError = (Value<Float>)ValueFactory.floatValue().withName("Max Pitch Error").withVal((Object)5.0f).withBounds(0.0f, 30.0f, 2).build((Value)this.rotate);
        this.rotateMaxYawError = (Value<Float>)ValueFactory.floatValue().withName("Max Yaw Error").withVal((Object)5.0f).withBounds(0.0f, 30.0f, 2).build((Value)this.rotate);
        this.rotatePitchDiff = (Value<Float>)ValueFactory.floatValue().withName("Pitch Diff").withVal((Object)80.0f).withBounds(1.0f, 180.0f, 1).build((Value)this.rotate);
        this.rotateYawDiff = (Value<Float>)ValueFactory.floatValue().withName("Yaw Diff").withVal((Object)120.0f).withBounds(1.0f, 180.0f, 1).build((Value)this.rotate);
        this.rotateAntiRubber = (Value<Boolean>)ValueFactory.booleanValue().withName("Anti Rubber").withVal((Object)true).build((Value)this.rotate);
        this.faceplace = (Value<Boolean>)ValueFactory.booleanValue().withName("Faceplace").withVal((Object)true).build((Module)this);
        this.minFaceplaceHP = (Value<Double>)ValueFactory.doubleValue().withName("Min Faceplace HP").withVal((Object)12.0).withBounds(0.0f, 36.0f, 1).build((Value)this.faceplace);
        this.faceplaceDmg = (Value<Double>)ValueFactory.doubleValue().withName("Faceplace Dmg").withVal((Object)2.5).withBounds(0.0f, 6.0f, 1).build((Value)this.faceplace);
        this.faceplaceBind = (Value<Bind>)ValueFactory.bindValue().withName("Faceplace Bind").withVal((Object)new Bind(0)).build((Value)this.faceplace);
        this.misc = (Value<Boolean>)ValueFactory.booleanValue().withName("Misc").withVal((Object)false).withAction((oldVal, newVal) -> false).build((Module)this);
        this.swingHand = (Value<SwingHand>)new ValueFactory().withName("Swing Hand").withVal((Object)SwingHand.Mainhand).build((Value)this.misc);
        this.playerRange = (Value<Double>)ValueFactory.doubleValue().withName("Player Range").withVal((Object)12.0).withBounds(0.0f, 20.0f, 1).build((Value)this.misc);
        this.clearDelay = (Value<Integer>)ValueFactory.intValue().withName("Clear Delay").withVal((Object)100).withBounds(0.0f, 450.0f, 0).build((Value)this.misc);
        this.placeTimer = new Timer();
        this.explodeTimer = new Timer();
        this.clearIgnoredIDsTimer = new Timer();
        this.ignoredIDs = new ArrayList<Integer>();
        this.amountHit = new HashMap<Integer, Integer>();
        this.renderPos = null;
        this.rotated = false;
        this.busy = false;
    }
    
    public static AutoCrystal getInstance() {
        if (AutoCrystal.instance == null) {
            AutoCrystal.instance = new AutoCrystal();
        }
        return AutoCrystal.instance;
    }
    
    protected void onEnable() {
        this.placeTimer.reset();
        this.explodeTimer.reset();
        this.clearIgnoredIDsTimer.reset();
        this.ignoredIDs.clear();
        this.amountHit.clear();
        this.renderPos = null;
        this.rotated = false;
        this.busy = false;
    }
    
    protected void onDisable() {
        if (this.rotated) {
            RotationUtil.resetRotation();
        }
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (this.clearIgnoredIDsTimer.passed((int)this.clearDelay.getValue())) {
            this.ignoredIDs.clear();
            this.amountHit.clear();
        }
        else {
            this.ignoredIDs.removeIf(i -> Utils.world().getEntityByID((int)i) == null);
        }
        boolean active = false;
        if ((boolean)this.explode.getValue() && this.explodeTimer.passed((int)this.explodeDelay.getValue())) {
            active = this.explodeCrystal();
        }
        if ((boolean)this.place.getValue() && this.placeTimer.passed((int)this.placeDelay.getValue()) && !(active = (active || this.placeCrystal()))) {
            this.renderPos = null;
        }
        if (this.rotated && !active) {
            RotationUtil.resetRotation();
            this.rotated = false;
        }
    }
    
    @Listener
    public void listen(final Render3DEvent event) {
        if (!(boolean)this.render.getValue() || this.renderPos == null) {
            return;
        }
        RenderUtil.drawBoxBlockPos(this.renderPos, (Color)this.renderColor.getValue(), (RenderUtil.RenderMode)this.renderMode.getValue());
    }
    
    @Listener
    public void listen(final PacketEvent.Read event) {
        if ((boolean)this.rotate.getValue() && (boolean)this.rotateAntiRubber.getValue() && event.getPacket() instanceof SPacketPlayerPosLook) {
            final ISPacketPlayerPosLook isPacketPosLook = (ISPacketPlayerPosLook)event.getPacket();
            isPacketPosLook.setYaw(Utils.player().rotationYawHead);
            isPacketPosLook.setPitch(Utils.player().rotationPitch);
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            final SPacketExplosion sPacketExplosion = (SPacketExplosion)event.getPacket();
            for (final Entity entity : Utils.loadedEntityList()) {
                if (entity instanceof EntityEnderCrystal && entity.getDistance(sPacketExplosion.getX(), sPacketExplosion.getY(), sPacketExplosion.getZ()) < 6.0) {
                    this.ignoredIDs.add(entity.getEntityId());
                    if (!(boolean)this.placeFast.getValue() || (!this.placeTimer.passed((int)this.placeDelay.getValue()) && !(boolean)this.placeFastIgnoreTimer.getValue())) {
                        continue;
                    }
                    this.placeCrystal();
                }
            }
        }
    }
    
    private boolean placeCrystal() {
        BlockPos pos = null;
        EnumFacing facing = EnumFacing.UP;
        double damage = 0.0;
        final List<BlockPos> blockPosList = BlockUtil.getPlaceableBlocks(EntityUtil.getEntityPos((Entity)Utils.player()), (double)this.placeRange.getValue(), (boolean)this.placeOneThirteen.getValue(), (boolean)this.placeMultiplace.getValue(), this.ignoredIDs);
        for (final EntityPlayer possibleTarget : Utils.world().playerEntities) {
            if (possibleTarget == Utils.player()) {
                continue;
            }
            if (Utils.player().getDistance((Entity)possibleTarget) > (double)this.playerRange.getValue()) {
                continue;
            }
            if (possibleTarget.isDead) {
                continue;
            }
            if (Utils.getHealthPoints((EntityLivingBase)possibleTarget) <= 0.0) {
                continue;
            }
            if (FriendUtil.isFriend(possibleTarget)) {
                continue;
            }
            final boolean shouldFaceplace = this.shouldFaceplace(possibleTarget);
            for (final BlockPos possiblePos : blockPosList) {
                final double calculatedDmg = CombatUtil.getDamage((Entity)possibleTarget, possiblePos);
                if (calculatedDmg >= (double)(shouldFaceplace ? this.faceplaceDmg : this.placeMinDmg).getValue()) {
                    if (calculatedDmg < damage) {
                        continue;
                    }
                    final double calculatedSelfDmg = CombatUtil.getDamage((Entity)Utils.player(), possiblePos);
                    if (calculatedSelfDmg > (double)this.placeMaxSelfDmg.getValue()) {
                        continue;
                    }
                    if (calculatedSelfDmg > Utils.getHealthPoints((EntityLivingBase)Utils.player())) {
                        continue;
                    }
                    pos = possiblePos;
                    damage = calculatedDmg;
                }
            }
        }
        if (pos == null) {
            return false;
        }
        if (this.placeRaytrace.getValue()) {
            final RayTraceResult result = Utils.world().rayTraceBlocks(EntityUtil.getEyePos((Entity)Utils.player()), BlockUtil.getCenter(pos));
            if (result != null) {
                facing = result.sideHit;
            }
        }
        return this.placeCrystal(pos, facing);
    }
    
    private boolean placeCrystal(final BlockPos pos, final EnumFacing facing) {
        if (pos == null || facing == null) {
            return false;
        }
        if (this.rotate.getValue()) {
            final Vec2f newLook = EntityUtil.getInstantLookVec(EntityUtil.getEyePos((Entity)Utils.player()), BlockUtil.getCenter(pos));
            final Vec2f currentLook = RotationUtil.getRotation();
            if (Math.abs(newLook.x - currentLook.x) > (float)this.rotateMaxPitchError.getValue() || Math.abs(newLook.y - currentLook.y) > (float)this.rotateMaxYawError.getValue()) {
                RotationUtil.setRotation(newLook.x, newLook.y);
                this.rotated = true;
            }
        }
        final boolean offhand = Utils.player().getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        final EnumHand hand = offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;
        final Vec3d lookVec = Utils.player().getLookVec();
        this.renderPos = pos;
        if (!offhand && InventoryUtil.switchToItem(Items.END_CRYSTAL, (InventoryUtil.SwitchMode)this.placeSwitch.getValue()) == -1) {
            return false;
        }
        BlockUtil.placeCrystal(pos, facing, hand, lookVec, (boolean)this.placePacket.getValue());
        if (this.placeSwitch.getValue() == InventoryUtil.SwitchMode.Silent && !offhand) {
            InventoryUtil.switchToPos(Utils.player().inventory.currentItem, (InventoryUtil.SwitchMode)this.placeSwitch.getValue());
        }
        ((SwingHand)this.swingHand.getValue()).swing();
        this.placeTimer.reset();
        return true;
    }
    
    private boolean explodeCrystal() {
        int crystal = -1;
        Vec3d crystalPos = Vec3d.ZERO;
        double damage = 0.0;
        final List<EntityEnderCrystal> crystalList = new ArrayList<EntityEnderCrystal>();
        for (final Entity e : Utils.loadedEntityList()) {
            if (!(e instanceof EntityEnderCrystal)) {
                continue;
            }
            if (e.isDead) {
                continue;
            }
            if (this.ignoredIDs.contains(e.getEntityId())) {
                continue;
            }
            if (Utils.player().getDistance(e) > (double)this.explodeRange.getValue()) {
                continue;
            }
            crystalList.add((EntityEnderCrystal)e);
        }
        for (final EntityPlayer possibleTarget : Utils.playerEntities()) {
            if (possibleTarget == Utils.player()) {
                continue;
            }
            if (Utils.player().getDistance((Entity)possibleTarget) > (double)this.playerRange.getValue()) {
                continue;
            }
            if (possibleTarget.isDead) {
                continue;
            }
            if (Utils.getHealthPoints((EntityLivingBase)possibleTarget) <= 0.0) {
                continue;
            }
            if (FriendUtil.isFriend(possibleTarget)) {
                continue;
            }
            final boolean shouldFaceplace = this.shouldFaceplace(possibleTarget);
            for (final EntityEnderCrystal possibleCrystal : crystalList) {
                final double calculatedDamage = CombatUtil.getDamage((Entity)possibleTarget, possibleCrystal);
                if (calculatedDamage >= damage) {
                    if (calculatedDamage < (double)(shouldFaceplace ? this.faceplaceDmg : this.explodeMinDmg).getValue()) {
                        continue;
                    }
                    final double calculatedSelfDamage = CombatUtil.getDamage((Entity)Utils.player(), possibleCrystal);
                    if (calculatedSelfDamage > (double)this.explodeMaxSelfDmg.getValue()) {
                        continue;
                    }
                    if (calculatedSelfDamage > Utils.getHealthPoints((EntityLivingBase)Utils.player())) {
                        continue;
                    }
                    crystal = possibleCrystal.getEntityId();
                    crystalPos = EntityUtil.getCenterPos((Entity)possibleCrystal);
                    damage = calculatedDamage;
                }
            }
        }
        return this.explodeCrystal(crystal, crystalPos);
    }
    
    private boolean explodeCrystal(final int crystal, final Vec3d pos) {
        if (crystal == -1) {
            return false;
        }
        if (this.rotate.getValue()) {
            final Vec2f newLook = EntityUtil.getInstantLookVec(EntityUtil.getEyePos((Entity)Utils.player()), pos);
            final Vec2f currentLook = RotationUtil.getRotation();
            if (Math.abs(newLook.x - currentLook.x) > (float)this.rotateMaxPitchError.getValue() || Math.abs(newLook.y - currentLook.y) > (float)this.rotateMaxYawError.getValue()) {
                RotationUtil.setRotation(newLook.x, newLook.y);
                this.rotated = true;
            }
        }
        final boolean weakened = Utils.player().getActivePotionEffect(PotionTypes.WEAKNESS.getEffects().get(0).getPotion()) != null;
        if (weakened && !InventoryUtil.switchToSword((InventoryUtil.SwitchMode)this.explodeAntiWeakness.getValue())) {
            return false;
        }
        Utils.sendPacket((Packet<?>)Utils.attackEntityPacket(crystal));
        ((SwingHand)this.swingHand.getValue()).swing();
        if (weakened && this.explodeAntiWeakness.getValue() == InventoryUtil.SwitchMode.Silent) {
            InventoryUtil.switchToPos(Utils.player().inventory.currentItem, (InventoryUtil.SwitchMode)this.explodeAntiWeakness.getValue());
        }
        final int newAmount = this.amountHit.getOrDefault(crystal, 0) + 1;
        this.amountHit.put(crystal, newAmount);
        if (newAmount >= (int)this.explodeMaxHits.getValue()) {
            this.ignoredIDs.add(crystal);
        }
        this.explodeTimer.reset();
        return true;
    }
    
    public boolean isBusy() {
        return this.busy;
    }
    
    private boolean shouldFaceplace(final EntityPlayer entityPlayer) {
        return (boolean)this.faceplace.getValue() && (Utils.getHealthPoints((EntityLivingBase)entityPlayer) < (double)this.minFaceplaceHP.getValue() || ((Bind)this.faceplaceBind.getValue()).isDown());
    }
    
    public enum RotationMode
    {
        Instant, 
        Step;
    }
}
