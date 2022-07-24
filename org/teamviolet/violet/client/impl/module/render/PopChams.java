//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.client.renderer.entity.*;
import org.teamviolet.violet.client.util.game.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import java.util.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.render.*;

@Module.Manifest(Module.Category.RENDER)
public class PopChams extends Module
{
    public final Value<Boolean> colors;
    public final Value<Color> color;
    public final Value<Color> outlineColor;
    private final Value<Float> fadeTime;
    public Map<EntityPopCham, Timer> ghosts;
    private static PopChams instance;
    
    public PopChams() {
        this.colors = (Value<Boolean>)ValueFactory.booleanValue().withName("Colors").withVal((Object)true).build((Module)this);
        this.color = (Value<Color>)ValueFactory.colorValue().withName("Fill").withVal((Object)new Color(14377718)).build((Value)this.colors);
        this.outlineColor = (Value<Color>)ValueFactory.colorValue().withName("Outline").withVal((Object)new Color(14377718)).build((Value)this.colors);
        this.fadeTime = (Value<Float>)ValueFactory.floatValue().withName("FadeTime").withVal((Object)1.0f).withBounds(0.1f, 10.0f, 1).build((Module)this);
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
    }
    
    protected void onDisable() {
        this.ghosts = null;
    }
    
    @Listener
    public void listen(final TotemPopEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.ghosts == null) {
            this.ghosts = new HashMap<EntityPopCham, Timer>();
        }
        final EntityPopCham ghost = new EntityPopCham((World)this.mc.world, new GameProfile(this.mc.player.getUniqueID(), ""));
        ghost.copyLocationAndAnglesFrom((Entity)event.getPlayer());
        ghost.limbSwingAmount = event.getPlayer().limbSwingAmount;
        this.ghosts.put(ghost, new Timer());
    }
    
    @Listener
    public void listen(final Render3DEvent.Pre event) {
        if (Utils.nullCheck() || this.ghosts == null) {
            return;
        }
        for (final Map.Entry<EntityPopCham, Timer> entry : this.ghosts.entrySet()) {
            final RenderPlayer render = new RenderPlayer(this.mc.getRenderManager());
            final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos((Entity)entry.getKey(), event.getPartialTicks());
            GL11.glPushMatrix();
            GlStateManager.translate(interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z);
            GlStateManager.rotate(-entry.getKey().rotationYaw, 0.0f, 1.0f, 0.0f);
            if (render != null) {
                render.doRender((AbstractClientPlayer)entry.getKey(), 0.0, 0.0, 0.0, entry.getKey().rotationYaw, 1.0f);
            }
            GL11.glPopMatrix();
        }
        this.ghosts.entrySet().removeIf(e -> e.getValue().passed((long)((float)this.fadeTime.getValue() * 1000.0f)));
    }
    
    @Listener
    public void listen(final RenderPopChamEvent.Pre event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (event.getRender() == 0) {
            final int alpha = (int)MathUtil.clamp((float)(int)(MathUtil.clamp(this.ghosts.get(event.getPopcham()).getTime() / ((float)this.fadeTime.getValue() * 1000.0f), 0.0f, 1.0f) * ((Color)this.color.getValue()).getAlpha()), 0.0f, 255.0f);
            final Color c = new Color(((Color)this.color.getValue()).getRed(), ((Color)this.color.getValue()).getGreen(), ((Color)this.color.getValue()).getBlue(), 255 - alpha);
            this.prepareFill(false);
            GlStateHelper.color(c);
        }
        else if (event.getRender() == 1) {
            final int alpha = (int)MathUtil.clamp((float)(int)(MathUtil.clamp(this.ghosts.get(event.getPopcham()).getTime() / ((float)this.fadeTime.getValue() * 1000.0f), 0.0f, 1.0f) * ((Color)this.outlineColor.getValue()).getAlpha()), 0.0f, 255.0f);
            final Color c = new Color(((Color)this.outlineColor.getValue()).getRed(), ((Color)this.outlineColor.getValue()).getGreen(), ((Color)this.outlineColor.getValue()).getBlue(), 255 - alpha);
            this.prepareLine(2.0f, false);
            GlStateHelper.color(c);
        }
    }
    
    @Listener
    public void listen(final RenderPopChamEvent.Post event) {
        if (Utils.nullCheck()) {
            return;
        }
        this.release();
        if (event.getRender() == 0) {
            event.setRenderAgain(true);
        }
        else if (event.getRender() == 1) {
            event.setRenderAgain(false);
        }
    }
    
    private void prepareFill(final boolean depth) {
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if (!depth) {
            GL11.glDisable(2929);
        }
    }
    
    private void prepareLine(final float lineWidth, final boolean depth) {
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glPolygonMode(1032, 6913);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        if (!depth) {
            GL11.glDisable(2929);
        }
        GL11.glLineWidth(lineWidth);
    }
    
    private void release() {
        GL11.glEnable(2896);
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }
    
    public static PopChams getInstance() {
        if (PopChams.instance == null) {
            PopChams.instance = new PopChams();
        }
        return PopChams.instance;
    }
}
