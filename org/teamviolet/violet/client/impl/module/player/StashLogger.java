//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.tileentity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.util.account.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;

@Module.Manifest(Module.Category.PLAYER)
public class StashLogger extends Module
{
    private final Value<Integer> distanceThreshold;
    private final Value<Integer> chestThreshold;
    private final Value<Integer> cartThreshold;
    private final Value<Integer> hopperThreshold;
    private final Value<Boolean> useFile;
    private final Value<Boolean> sound;
    private final Value<Integer> soundRefresh;
    private ArrayList<Vec3d> coordList;
    
    public StashLogger() {
        this.distanceThreshold = (Value<Integer>)new ValueFactory<Integer>() {}.withName("DistanceThresh").withVal((Object)500).withBounds(0.0f, 10000.0f, 0).build((Module)this);
        this.chestThreshold = (Value<Integer>)new ValueFactory<Integer>() {}.withName("ChestThresh").withVal((Object)10).withBounds(0.0f, 50.0f, 0).build((Module)this);
        this.cartThreshold = (Value<Integer>)new ValueFactory<Integer>() {}.withName("CartThreshold").withVal((Object)20).withBounds(0.0f, 50.0f, 0).build((Module)this);
        this.hopperThreshold = (Value<Integer>)new ValueFactory<Integer>() {}.withName("HopperThresh").withVal((Object)10).withBounds(0.0f, 50.0f, 0).build((Module)this);
        this.useFile = (Value<Boolean>)ValueFactory.booleanValue().withName("LogToFile").withVal((Object)true).build((Module)this);
        this.sound = (Value<Boolean>)ValueFactory.booleanValue().withName("Sound").withVal((Object)true).build((Module)this);
        this.soundRefresh = (Value<Integer>)new ValueFactory<Integer>() {}.withName("SoundRepeat").withVal((Object)1).withBounds(1.0f, 20.0f, 0).build((Value)this.sound);
        this.coordList = new ArrayList<Vec3d>();
    }
    
    protected void onEnable() {
        this.coordList.clear();
    }
    
    protected void onDisable() {
        this.coordList.clear();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        int chests = 0;
        int hoppers = 0;
        int carts = 0;
        for (final TileEntity tileEntity : this.mc.world.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityChest) {
                ++chests;
            }
            else {
                if (!(tileEntity instanceof TileEntityHopper)) {
                    continue;
                }
                ++hoppers;
            }
        }
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityMinecartChest) {
                ++carts;
            }
        }
        if (((chests >= (int)this.chestThreshold.getValue() && (int)this.chestThreshold.getValue() != 0) || (hoppers >= (int)this.hopperThreshold.getValue() && (int)this.hopperThreshold.getValue() != 0) || (carts >= (int)this.cartThreshold.getValue() && (int)this.cartThreshold.getValue() != 0)) && this.isOutsideDistThreshold()) {
            final Stash stash = new Stash(this.mc.player.getPositionVector(), chests, carts, hoppers);
            if (this.sound.getValue()) {
                for (int i = 0; i < (int)this.soundRefresh.getValue(); ++i) {
                    this.sound();
                }
            }
            InfoUtil.chatInfo("Stash has been spotted. Chests: " + chests + " Hoppers: " + hoppers + " Carts: " + carts, this);
            if (this.useFile.getValue()) {
                Config.saveStash(stash);
            }
            this.coordList.add(this.mc.player.getPositionVector());
        }
    }
    
    private boolean isOutsideDistThreshold() {
        for (final Vec3d vec3d : this.coordList) {
            if ((this.mc.player.getPositionVector().x - vec3d.x) * (this.mc.player.getPositionVector().x - vec3d.x) + (this.mc.player.getPositionVector().z - vec3d.z) * (this.mc.player.getPositionVector().z - vec3d.z) < Math.pow((int)this.distanceThreshold.getValue(), 2.0)) {
                return false;
            }
        }
        return true;
    }
    
    private void sound() {
        Minecraft.getMinecraft().getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0f));
    }
}
