//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class RenderTweaks extends Module
{
    public final Value<Boolean> crouch;
    public final Value<Boolean> staticLimbs;
    public final Value<Float> limbFactor;
    public final Value<Boolean> clearPlayers;
    public final Value<Integer> playerAlpha;
    public final Value<Float> addition;
    public final Value<Boolean> crystalSpin;
    public final Value<Float> spinFactor;
    public final Value<Boolean> crystalScale;
    public final Value<Float> scaleFactor;
    private static RenderTweaks instance;
    
    public RenderTweaks() {
        this.crouch = (Value<Boolean>)new ValueFactory().withName("Crouch").withVal((Object)true).build((Module)this);
        this.staticLimbs = (Value<Boolean>)new ValueFactory().withName("StaticLimbs").withVal((Object)true).build((Module)this);
        this.limbFactor = (Value<Float>)new ValueFactory().withName("LimbFactor").withVal((Object)1.0f).withBounds(0.0f, 15.0f, 2).build((Value)this.staticLimbs);
        this.clearPlayers = (Value<Boolean>)new ValueFactory().withName("ClearPlayers").withVal((Object)true).build((Module)this);
        this.playerAlpha = (Value<Integer>)new ValueFactory().withName("Alpha").withVal((Object)150).withBounds(0.0f, 255.0f, 0).build((Value)this.clearPlayers);
        this.addition = (Value<Float>)new ValueFactory().withName("Addition").withVal((Object)0.2f).withBounds(0.0f, 1.0f, 2).build((Value)this.clearPlayers);
        this.crystalSpin = (Value<Boolean>)new ValueFactory().withName("CrystalSpin").withVal((Object)true).build((Module)this);
        this.spinFactor = (Value<Float>)new ValueFactory().withName("SpinFactor").withVal((Object)1.0f).withBounds(0.0f, 30.0f, 2).build((Value)this.crystalSpin);
        this.crystalScale = (Value<Boolean>)new ValueFactory().withName("CrystalScale").withVal((Object)true).build((Module)this);
        this.scaleFactor = (Value<Float>)new ValueFactory().withName("ScaleFactor").withVal((Object)1.0f).withBounds(0.0f, 5.0f, 2).build((Value)this.crystalScale);
    }
    
    @Listener
    public void listen(final RenderLivingModelEvent.Pre event) {
        if (this.mc.world.getEntitiesWithinAABB((Class)EntityPlayer.class, this.mc.player.getEntityBoundingBox().grow((double)(float)this.addition.getValue())).contains(event.getEntity()) && event.getEntity() != this.mc.player) {
            GL11.glColor4f(1.0f, 1.0f, 1.0f, (int)this.playerAlpha.getValue() / 255.0f);
        }
    }
    
    public static RenderTweaks getInstance() {
        if (RenderTweaks.instance == null) {
            RenderTweaks.instance = new RenderTweaks();
        }
        return RenderTweaks.instance;
    }
}
