//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.misc.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

@Module.Manifest(Module.Category.RENDER)
public class Trails extends Module
{
    private final Value<Boolean> projectiles;
    private final Value<Boolean> pearl;
    private final Value<Boolean> arrow;
    private final Value<Boolean> XP;
    private final Value<Float> fadeTimeProjectile;
    private final Value<Boolean> players;
    private final Value<Boolean> self;
    private final Value<Float> fadeTimePlayer;
    private final Value<Boolean> render;
    private final Value<Color> color;
    private final Value<Float> width;
    private final Value<Boolean> twod;
    private final HashMap<UUID, List<Pair<Vec3d, Long>>> projPoses;
    private final HashMap<UUID, List<Pair<Vec3d, Long>>> playPoses;
    
    public Trails() {
        this.projectiles = (Value<Boolean>)ValueFactory.booleanValue().withName("Projectiles").withVal((Object)true).build((Module)this);
        this.pearl = (Value<Boolean>)ValueFactory.booleanValue().withName("Pearls").withVal((Object)true).build((Value)this.projectiles);
        this.arrow = (Value<Boolean>)ValueFactory.booleanValue().withName("Arrows").withVal((Object)true).build((Value)this.projectiles);
        this.XP = (Value<Boolean>)ValueFactory.booleanValue().withName("XP").withVal((Object)true).build((Value)this.projectiles);
        this.fadeTimeProjectile = (Value<Float>)ValueFactory.floatValue().withName("Proj Fade Time").withVal((Object)10.0f).withBounds(0.1f, 30.0f, 1).build((Value)this.projectiles);
        this.players = (Value<Boolean>)ValueFactory.booleanValue().withName("Players").withVal((Object)true).build((Module)this);
        this.self = (Value<Boolean>)ValueFactory.booleanValue().withName("XP").withVal((Object)true).build((Value)this.players);
        this.fadeTimePlayer = (Value<Float>)ValueFactory.floatValue().withName("Player Fade Time").withVal((Object)10.0f).withBounds(0.1f, 30.0f, 1).build((Value)this.players);
        this.render = (Value<Boolean>)ValueFactory.booleanValue().withName("Render").withVal((Object)true).build((Module)this);
        this.color = (Value<Color>)ValueFactory.colorValue().withName("Color").withVal((Object)new Color(-1465819139, true)).build((Value)this.render);
        this.width = (Value<Float>)ValueFactory.floatValue().withName("Width").withVal((Object)1.5f).withBounds(0.1f, 5.0f, 2).build((Value)this.render);
        this.twod = (Value<Boolean>)ValueFactory.booleanValue().withName("2d").withVal((Object)false).build((Module)this);
        this.projPoses = new HashMap<UUID, List<Pair<Vec3d, Long>>>();
        this.playPoses = new HashMap<UUID, List<Pair<Vec3d, Long>>>();
    }
    
    @Listener
    public void listener(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if ((entity instanceof EntityEnderPearl && (boolean)this.pearl.getValue()) || (entity instanceof EntityArrow && (boolean)this.arrow.getValue()) || (entity instanceof EntityExpBottle && (boolean)this.XP.getValue())) {
                if (!this.projPoses.containsKey(entity.getUniqueID())) {
                    this.projPoses.put(entity.getUniqueID(), new ArrayList<Pair<Vec3d, Long>>());
                }
                if (!this.projPoses.get(entity.getUniqueID()).contains(entity.getPositionVector())) {
                    this.projPoses.get(entity.getUniqueID()).add(new Pair<Vec3d, Long>(entity.getPositionVector(), System.currentTimeMillis()));
                }
            }
            if (entity instanceof EntityPlayer && (boolean)this.players.getValue()) {
                if (entity == this.mc.player && !(boolean)this.self.getValue()) {
                    continue;
                }
                if (!this.playPoses.containsKey(entity.getUniqueID())) {
                    this.playPoses.put(entity.getUniqueID(), new ArrayList<Pair<Vec3d, Long>>());
                }
                if (this.playPoses.get(entity.getUniqueID()).contains(entity.getPositionVector())) {
                    continue;
                }
                this.playPoses.get(entity.getUniqueID()).add(new Pair<Vec3d, Long>(entity.getPositionVector(), System.currentTimeMillis()));
            }
        }
    }
    
    @Listener
    public void listen(final Render3DEvent event) {
        final IRenderManager irm = (IRenderManager)this.mc.getRenderManager();
        prepareGL();
        for (final Map.Entry<UUID, List<Pair<Vec3d, Long>>> entry : this.projPoses.entrySet()) {
            GL11.glLineWidth((float)this.width.getValue());
            GL11.glBegin(1);
            for (int i = 1; i < entry.getValue().size(); ++i) {
                final long timePassed = System.currentTimeMillis() - entry.getValue().get(i).getB();
                final float alpha = ((Color)this.color.getValue()).getAlpha() / 255.0f - timePassed / ((float)this.fadeTimeProjectile.getValue() * 1000.0f) * ((Color)this.color.getValue()).getAlpha() / 255.0f;
                GL11.glColor4f(((Color)this.color.getValue()).getRed() / 255.0f, ((Color)this.color.getValue()).getGreen() / 255.0f, ((Color)this.color.getValue()).getBlue() / 255.0f, alpha);
                GL11.glVertex3d(entry.getValue().get(i).getA().x - irm.getRenderPosX(), entry.getValue().get(i).getA().y - irm.getRenderPosY(), entry.getValue().get(i).getA().z - irm.getRenderPosZ());
                GL11.glVertex3d(entry.getValue().get(i - 1).getA().x - irm.getRenderPosX(), entry.getValue().get(i - 1).getA().y - irm.getRenderPosY(), entry.getValue().get(i - 1).getA().z - irm.getRenderPosZ());
            }
            for (int i = 0; i < entry.getValue().size(); ++i) {
                if (System.currentTimeMillis() - entry.getValue().get(i).getB() >= (float)this.fadeTimeProjectile.getValue() * 1000.0f) {
                    this.projPoses.get(entry.getKey()).remove(i);
                }
            }
            GL11.glEnd();
        }
        releaseGL();
        prepareGL();
        for (final Map.Entry<UUID, List<Pair<Vec3d, Long>>> entry : this.playPoses.entrySet()) {
            if (!(boolean)this.twod.getValue()) {
                GL11.glLineWidth((float)this.width.getValue());
                GL11.glBegin(1);
                for (int i = 1; i < entry.getValue().size(); ++i) {
                    final long timePassed = System.currentTimeMillis() - entry.getValue().get(i).getB();
                    final float alpha = ((Color)this.color.getValue()).getAlpha() / 255.0f - timePassed / ((float)this.fadeTimePlayer.getValue() * 1000.0f) * ((Color)this.color.getValue()).getAlpha() / 255.0f;
                    GL11.glColor4f(((Color)this.color.getValue()).getRed() / 255.0f, ((Color)this.color.getValue()).getGreen() / 255.0f, ((Color)this.color.getValue()).getBlue() / 255.0f, alpha);
                    GL11.glVertex3d(entry.getValue().get(i).getA().x - irm.getRenderPosX(), entry.getValue().get(i).getA().y - irm.getRenderPosY(), entry.getValue().get(i).getA().z - irm.getRenderPosZ());
                    GL11.glVertex3d(entry.getValue().get(i - 1).getA().x - irm.getRenderPosX(), entry.getValue().get(i - 1).getA().y - irm.getRenderPosY(), entry.getValue().get(i - 1).getA().z - irm.getRenderPosZ());
                }
                for (int i = 0; i < entry.getValue().size(); ++i) {
                    if (System.currentTimeMillis() - entry.getValue().get(i).getB() >= (float)this.fadeTimePlayer.getValue() * 1000.0f) {
                        this.playPoses.get(entry.getKey()).remove(i);
                    }
                }
                GL11.glEnd();
            }
            else {
                GL11.glBegin(8);
                for (int i = 1; i < entry.getValue().size(); ++i) {
                    final long timePassed = System.currentTimeMillis() - entry.getValue().get(i).getB();
                    final float alpha = ((Color)this.color.getValue()).getAlpha() / 255.0f - timePassed / ((float)this.fadeTimePlayer.getValue() * 1000.0f) * ((Color)this.color.getValue()).getAlpha() / 255.0f;
                    GL11.glColor4f(((Color)this.color.getValue()).getRed() / 255.0f, ((Color)this.color.getValue()).getGreen() / 255.0f, ((Color)this.color.getValue()).getBlue() / 255.0f, alpha);
                    GL11.glVertex3d(entry.getValue().get(i).getA().x - irm.getRenderPosX(), entry.getValue().get(i).getA().y - irm.getRenderPosY(), entry.getValue().get(i).getA().z - irm.getRenderPosZ());
                    GL11.glVertex3d(entry.getValue().get(i).getA().x - irm.getRenderPosX(), entry.getValue().get(i).getA().y - irm.getRenderPosY() + 1.8, entry.getValue().get(i).getA().z - irm.getRenderPosZ());
                    GL11.glVertex3d(entry.getValue().get(i - 1).getA().x - irm.getRenderPosX(), entry.getValue().get(i - 1).getA().y - irm.getRenderPosY(), entry.getValue().get(i - 1).getA().z - irm.getRenderPosZ());
                    GL11.glVertex3d(entry.getValue().get(i - 1).getA().x - irm.getRenderPosX(), entry.getValue().get(i - 1).getA().y - irm.getRenderPosY() + 1.8, entry.getValue().get(i - 1).getA().z - irm.getRenderPosZ());
                }
                for (int i = 0; i < entry.getValue().size(); ++i) {
                    if (System.currentTimeMillis() - entry.getValue().get(i).getB() >= (float)this.fadeTimePlayer.getValue() * 1000.0f) {
                        this.playPoses.get(entry.getKey()).remove(i);
                    }
                }
                GL11.glEnd();
            }
        }
        releaseGL();
    }
    
    public static void prepareGL() {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
    }
    
    public static void prepareGLFill() {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }
    
    public static void releaseGL() {
        GL11.glDisable(2848);
        GlStateManager.depthMask(true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
