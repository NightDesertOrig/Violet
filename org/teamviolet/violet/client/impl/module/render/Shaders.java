//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.util.misc.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.entity.*;
import java.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.shader.*;

@Module.Manifest(Module.Category.RENDER)
public class Shaders extends Module
{
    private final Value<Boolean> entities;
    private final Value<Boolean> players;
    private final Value<Boolean> popChams;
    private final Value<Boolean> crystals;
    private final Value<Boolean> items;
    private final Value<Boolean> pearls;
    private final Value<Boolean> xp;
    private final Value<Boolean> hand;
    private final Value<Boolean> render;
    private final Value<Boolean> useRange;
    private final Value<Float> range;
    private final Value<Float> outlineWidth;
    public final Value<Color> inside;
    public final Value<Color> outside;
    private final Value<Boolean> glow;
    private final Value<Mode> shaderMode;
    private final Value<ShaderTypeNew> insideMode;
    private final Value<ShaderTypeNew> outsideMode;
    private final Value<ShaderTypeOld> oldMode;
    
    public Shaders() {
        this.entities = (Value<Boolean>)ValueFactory.booleanValue().withName("Entity").withVal((Object)true).build((Module)this);
        this.players = (Value<Boolean>)ValueFactory.booleanValue().withName("Players").withVal((Object)true).build((Value)this.entities);
        this.popChams = (Value<Boolean>)ValueFactory.booleanValue().withName("PopChams").withVal((Object)true).build((Module)this);
        this.crystals = (Value<Boolean>)ValueFactory.booleanValue().withName("Crystals").withVal((Object)true).build((Value)this.entities);
        this.items = (Value<Boolean>)ValueFactory.booleanValue().withName("Items").withVal((Object)true).build((Value)this.entities);
        this.pearls = (Value<Boolean>)ValueFactory.booleanValue().withName("Pearls").withVal((Object)true).build((Value)this.entities);
        this.xp = (Value<Boolean>)ValueFactory.booleanValue().withName("XP").withVal((Object)true).build((Value)this.entities);
        this.hand = (Value<Boolean>)ValueFactory.booleanValue().withName("Hand").withVal((Object)true).build((Module)this);
        this.render = (Value<Boolean>)ValueFactory.booleanValue().withName("Render").withVal((Object)true).build((Module)this);
        this.useRange = (Value<Boolean>)ValueFactory.booleanValue().withName("Range").withVal((Object)true).build((Value)this.render);
        this.range = (Value<Float>)new ValueFactory().withName("Distance").withVal((Object)50.0f).withBounds(0.0f, 200.0f, 0).build((Value)this.useRange);
        this.outlineWidth = (Value<Float>)new ValueFactory().withName("Width").withVal((Object)2.0f).withBounds(0.0f, 5.0f, 1).build((Value)this.render);
        this.inside = (Value<Color>)ValueFactory.colorValue().withName("Fill").withVal((Object)new Color(14377718)).build((Value)this.render);
        this.outside = (Value<Color>)ValueFactory.colorValue().withName("Outline").withVal((Object)new Color(14377718)).build((Value)this.render);
        this.glow = (Value<Boolean>)ValueFactory.booleanValue().withName("Glow").withVal((Object)true).build((Value)this.render);
        this.shaderMode = (Value<Mode>)new ValueFactory().withName("ShaderMode").withVal((Object)Mode.New).build((Value)this.render);
        this.insideMode = (Value<ShaderTypeNew>)new ValueFactory().withName("Inside").withVal((Object)ShaderTypeNew.Color).build((Value)this.render);
        this.outsideMode = (Value<ShaderTypeNew>)new ValueFactory().withName("Outside").withVal((Object)ShaderTypeNew.Color).build((Value)this.render);
        this.oldMode = (Value<ShaderTypeOld>)new ValueFactory().withName("OldMode").withVal((Object)ShaderTypeOld.Color).build((Value)this.render);
    }
    
    @Listener
    public void listen(final Render2DEvent event) {
        if (this.hand.getValue()) {
            FrameBufferShader shader;
            if (this.shaderMode.getValue() == Mode.New) {
                shader = Shader.INSTANCE;
            }
            else {
                shader = ((ShaderTypeOld)this.oldMode.getValue()).shader;
            }
            shader.startDraw(event.getPartialTicks());
            ((IEntityRenderer)this.mc.entityRenderer).invokeRenderHand(event.getPartialTicks(), 1);
            shader.stopDraw((Color)this.inside.getValue(), (Color)this.outside.getValue(), (float)this.outlineWidth.getValue(), 1.0f, ((ShaderTypeNew)this.insideMode.getValue()).mode, ((ShaderTypeNew)this.outsideMode.getValue()).mode, (boolean)this.glow.getValue());
        }
    }
    
    @Listener
    public void listen(final Render3DEvent event) {
        if (this.entities.getValue()) {
            FrameBufferShader shader;
            if (this.shaderMode.getValue() == Mode.New) {
                shader = Shader.INSTANCE;
            }
            else {
                shader = ((ShaderTypeOld)this.oldMode.getValue()).shader;
            }
            GlStateManager.matrixMode(5889);
            GlStateManager.pushMatrix();
            GlStateManager.matrixMode(5888);
            GlStateManager.pushMatrix();
            shader.startDraw(event.getPartialTicks());
            for (final Entity e : this.mc.world.loadedEntityList) {
                if (e == this.mc.getRenderViewEntity()) {
                    continue;
                }
                if ((boolean)this.useRange.getValue() && this.mc.getRenderViewEntity().getDistance(e) >= (float)this.range.getValue()) {
                    continue;
                }
                final Render getEntityRenderObject = this.mc.getRenderManager().getEntityRenderObject(e);
                if (getEntityRenderObject == null) {
                    continue;
                }
                final Render entityRenderObject = getEntityRenderObject;
                final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos(e, event.getPartialTicks());
                if ((!(e instanceof EntityPlayer) || !(boolean)this.players.getValue()) && (!(e instanceof EntityEnderCrystal) || !(boolean)this.crystals.getValue()) && (!(e instanceof EntityExpBottle) || !(boolean)this.xp.getValue()) && (!(e instanceof EntityXPOrb) || !(boolean)this.xp.getValue()) && (!(e instanceof EntityEnderPearl) || !(boolean)this.pearls.getValue()) && (!(e instanceof EntityItem) || !(boolean)this.items.getValue())) {
                    continue;
                }
                entityRenderObject.doRender(e, interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z, e.rotationYaw, event.getPartialTicks());
            }
            if ((boolean)this.popChams.getValue() && PopChams.getInstance().isEnabled() && PopChams.getInstance().ghosts != null) {
                for (final Map.Entry<EntityPopCham, Timer> entry : PopChams.getInstance().ghosts.entrySet()) {
                    final RenderPlayer render = new RenderPlayer(this.mc.getRenderManager());
                    final Vec3d interpolatedRenderPos2 = EntityUtil.getInterpolatedRenderPos((Entity)entry.getKey(), event.getPartialTicks());
                    GL11.glPushMatrix();
                    GlStateManager.translate(interpolatedRenderPos2.x, interpolatedRenderPos2.y, interpolatedRenderPos2.z);
                    GlStateManager.rotate(-entry.getKey().rotationYaw, 0.0f, 1.0f, 0.0f);
                    if (render != null) {
                        render.doRender((AbstractClientPlayer)entry.getKey(), 0.0, 0.0, 0.0, entry.getKey().rotationYaw, 1.0f);
                    }
                    GL11.glPopMatrix();
                }
            }
            shader.stopDraw((Color)this.inside.getValue(), (Color)this.outside.getValue(), (float)this.outlineWidth.getValue(), 1.0f, ((ShaderTypeNew)this.insideMode.getValue()).mode, ((ShaderTypeNew)this.outsideMode.getValue()).mode, (boolean)this.glow.getValue());
            ((IEntityRenderer)this.mc.entityRenderer).invokeRenderHand(event.getPartialTicks(), 1);
            GlStateManager.matrixMode(5889);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            GlStateManager.popMatrix();
        }
    }
    
    private enum Mode
    {
        Old, 
        New;
    }
    
    public enum ShaderTypeNew
    {
        Color(0), 
        Matter(1), 
        PinkGalaxy(2), 
        DeepSpace(3);
        
        public int mode;
        
        private ShaderTypeNew(final int mode) {
            this.mode = mode;
        }
    }
    
    public enum ShaderTypeOld
    {
        Galaxy(CustomShader.GALAXY), 
        Water(CustomShader.WATER), 
        Lava(CustomShader.LAVA), 
        Smooth1(CustomShader.SMOOTH1), 
        Smooth2(CustomShader.SMOOTH2), 
        Smooth3(CustomShader.SMOOTH3), 
        PinkGalaxy(CustomShader.PINK_GALACTIC), 
        Matter(CustomShader.MATTER), 
        BARS(CustomShader.BARS), 
        CATTYN(CustomShader.CATTYN), 
        HACKER(CustomShader.HACKER), 
        Color(CustomShader.OUTLINE);
        
        public CustomShader shader;
        
        private ShaderTypeOld(final CustomShader shader) {
            this.shader = shader;
        }
    }
}
