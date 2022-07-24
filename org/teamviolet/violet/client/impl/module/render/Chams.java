//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.account.*;
import org.lwjgl.opengl.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.entity.item.*;
import net.minecraft.client.renderer.*;

@Module.Manifest(Module.Category.RENDER)
public class Chams extends Module
{
    private final Value<Boolean> players;
    private final Value<ChamStyle> playerStyle;
    private final Value<Boolean> playerOutlineDepth;
    private final Value<Boolean> playerTex;
    private final Value<Boolean> playerFillDepth;
    private final Value<Boolean> playerUseRange;
    private final Value<Float> playerRange;
    private final Value<Float> playerOutlineWidth;
    private final Value<Boolean> playerColors;
    public final Value<Color> playerColor;
    public final Value<Color> playerColorOutline;
    public final Value<Color> friendColor;
    public final Value<Color> friendColorOutline;
    private final Value<Boolean> crystals;
    private final Value<ChamStyle> crystalStyle;
    private final Value<Boolean> crystalTex;
    private final Value<Boolean> crystalOutlineDepth;
    private final Value<Boolean> crystalFillDepth;
    private final Value<Boolean> crystalUseRange;
    private final Value<Float> crystalRange;
    private final Value<Float> crystalOutlineWidth;
    private final Value<Boolean> crystalColors;
    public final Value<Color> crystalColor;
    public final Value<Color> crystalColorOutline;
    
    public Chams() {
        this.players = (Value<Boolean>)ValueFactory.booleanValue().withName("Players").withVal((Object)true).build((Module)this);
        this.playerStyle = (Value<ChamStyle>)new ValueFactory().withName("PlayerStyle").withVal((Object)ChamStyle.Both).build((Value)this.players);
        this.playerOutlineDepth = (Value<Boolean>)ValueFactory.booleanValue().withName("PlayerLineDepth").withVal((Object)true).build((Value)this.players);
        this.playerTex = (Value<Boolean>)ValueFactory.booleanValue().withName("PlayerTex2d").withVal((Object)true).build((Value)this.players);
        this.playerFillDepth = (Value<Boolean>)ValueFactory.booleanValue().withName("PlayerFillDepth").withVal((Object)true).build((Value)this.players);
        this.playerUseRange = (Value<Boolean>)ValueFactory.booleanValue().withName("PlayerRange").withVal((Object)true).build((Value)this.players);
        this.playerRange = (Value<Float>)new ValueFactory().withName("PlayerDistance").withVal((Object)50.0f).withBounds(0.0f, 200.0f, 0).build((Value)this.playerUseRange);
        this.playerOutlineWidth = (Value<Float>)new ValueFactory().withName("PlayerOutlineWidth").withVal((Object)2.0f).withBounds(0.0f, 5.0f, 1).build((Value)this.players);
        this.playerColors = (Value<Boolean>)ValueFactory.booleanValue().withName("PlayerColors").withVal((Object)true).build((Value)this.players);
        this.playerColor = (Value<Color>)ValueFactory.colorValue().withName("Player").withVal((Object)new Color(14377718)).build((Value)this.playerColors);
        this.playerColorOutline = (Value<Color>)ValueFactory.colorValue().withName("PlayerOutline").withVal((Object)new Color(14377718)).build((Value)this.playerColors);
        this.friendColor = (Value<Color>)ValueFactory.colorValue().withName("Friend").withVal((Object)new Color(6485745)).build((Value)this.playerColors);
        this.friendColorOutline = (Value<Color>)ValueFactory.colorValue().withName("FriendOutline").withVal((Object)new Color(6485745)).build((Value)this.playerColors);
        this.crystals = (Value<Boolean>)ValueFactory.booleanValue().withName("Crystals").withVal((Object)true).build((Module)this);
        this.crystalStyle = (Value<ChamStyle>)new ValueFactory().withName("CrystalStyle").withVal((Object)ChamStyle.Both).build((Value)this.crystals);
        this.crystalTex = (Value<Boolean>)ValueFactory.booleanValue().withName("CrystalTex2d").withVal((Object)true).build((Value)this.crystals);
        this.crystalOutlineDepth = (Value<Boolean>)ValueFactory.booleanValue().withName("CrystalLineDepth").withVal((Object)true).build((Value)this.crystals);
        this.crystalFillDepth = (Value<Boolean>)ValueFactory.booleanValue().withName("CrystalFillDepth").withVal((Object)true).build((Value)this.crystals);
        this.crystalUseRange = (Value<Boolean>)ValueFactory.booleanValue().withName("CrystalRange").withVal((Object)true).build((Value)this.crystals);
        this.crystalRange = (Value<Float>)new ValueFactory().withName("CrystalDistance").withVal((Object)50.0f).withBounds(0.0f, 200.0f, 0).build((Value)this.crystalUseRange);
        this.crystalOutlineWidth = (Value<Float>)new ValueFactory().withName("CrystalOutlineWidth").withVal((Object)2.0f).withBounds(0.0f, 5.0f, 1).build((Value)this.crystals);
        this.crystalColors = (Value<Boolean>)ValueFactory.booleanValue().withName("CrystalColors").withVal((Object)true).build((Value)this.crystals);
        this.crystalColor = (Value<Color>)ValueFactory.colorValue().withName("Crystal").withVal((Object)new Color(14377718)).build((Value)this.crystalColors);
        this.crystalColorOutline = (Value<Color>)ValueFactory.colorValue().withName("CrystalOutline").withVal((Object)new Color(14377718)).build((Value)this.crystalColors);
    }
    
    @Listener
    public void listen(final RenderLivingModelEvent.Pre event) {
        if (event.getEntity() instanceof EntityPlayer && (boolean)this.players.getValue()) {
            final EntityPlayer e = (EntityPlayer)event.getEntity();
            if ((boolean)this.playerUseRange.getValue() && this.mc.getRenderViewEntity().getDistance((Entity)e) > (float)this.playerRange.getValue()) {
                return;
            }
            if (this.playerStyle.getValue() == ChamStyle.Both) {
                if (event.getRender() == 0) {
                    final Color c = (Color)(FriendUtil.isFriend(e) ? this.friendColor.getValue() : ((Color)this.playerColor.getValue()));
                    this.prepareFill((boolean)this.playerFillDepth.getValue());
                    if (!(boolean)this.playerTex.getValue()) {
                        GL11.glDisable(3553);
                    }
                    GlStateHelper.color(c);
                }
                else if (event.getRender() == 1) {
                    final Color c = (Color)(FriendUtil.isFriend(e) ? this.friendColorOutline.getValue() : ((Color)this.playerColorOutline.getValue()));
                    this.prepareLine((float)this.playerOutlineWidth.getValue(), (boolean)this.playerOutlineDepth.getValue());
                    GlStateHelper.color(c);
                }
            }
            else if (this.playerStyle.getValue() == ChamStyle.Outlined) {
                final Color c = (Color)(FriendUtil.isFriend(e) ? this.friendColorOutline.getValue() : ((Color)this.playerColorOutline.getValue()));
                this.prepareLine((float)this.playerOutlineWidth.getValue(), (boolean)this.playerOutlineDepth.getValue());
                GlStateHelper.color(c);
            }
            else {
                final Color c = (Color)(FriendUtil.isFriend(e) ? this.friendColor.getValue() : ((Color)this.playerColor.getValue()));
                this.prepareFill((boolean)this.playerFillDepth.getValue());
                if (!(boolean)this.playerTex.getValue()) {
                    GL11.glDisable(3553);
                }
                GlStateHelper.color(c);
            }
        }
    }
    
    @Listener
    public void listen(final RenderLivingModelEvent.Post event) {
        if (event.getEntity() instanceof EntityPlayer && (boolean)this.players.getValue()) {
            final EntityPlayer e = (EntityPlayer)event.getEntity();
            if ((boolean)this.playerUseRange.getValue() && this.mc.getRenderViewEntity().getDistance((Entity)e) > (float)this.playerRange.getValue()) {
                return;
            }
            this.release();
            if (this.playerStyle.getValue() == ChamStyle.Both) {
                if (event.getRender() == 0) {
                    event.setRenderAgain(true);
                }
                else if (event.getRender() == 1) {
                    event.setRenderAgain(false);
                }
            }
        }
    }
    
    @Listener
    public void listen(final RenderEnderCrystalEvent.Pre event) {
        if (!(boolean)this.crystals.getValue()) {
            return;
        }
        final EntityEnderCrystal e = event.getEntityEnderCrystal();
        if ((boolean)this.crystalUseRange.getValue() && this.mc.getRenderViewEntity().getDistance((Entity)e) > (float)this.crystalRange.getValue()) {
            return;
        }
        if (this.crystalStyle.getValue() == ChamStyle.Both) {
            if (event.getRender() == 0) {
                this.prepareFill((boolean)this.crystalFillDepth.getValue());
                if (!(boolean)this.crystalTex.getValue()) {
                    GL11.glDisable(3553);
                }
                GlStateHelper.color((Color)this.crystalColor.getValue());
            }
            else if (event.getRender() == 1) {
                this.prepareLine((float)this.crystalOutlineWidth.getValue(), (boolean)this.crystalOutlineDepth.getValue());
                GlStateHelper.color((Color)this.crystalColorOutline.getValue());
            }
        }
        else if (this.crystalStyle.getValue() == ChamStyle.Outlined) {
            this.prepareLine((float)this.crystalOutlineWidth.getValue(), (boolean)this.crystalOutlineDepth.getValue());
            GlStateHelper.color((Color)this.crystalColorOutline.getValue());
        }
        else {
            this.prepareFill((boolean)this.crystalFillDepth.getValue());
            if (!(boolean)this.crystalTex.getValue()) {
                GL11.glDisable(3553);
            }
            GlStateHelper.color((Color)this.crystalColor.getValue());
        }
    }
    
    @Listener
    public void listen(final RenderEnderCrystalEvent.Post event) {
        if (!(boolean)this.crystals.getValue()) {
            return;
        }
        final EntityEnderCrystal e = event.getEntityEnderCrystal();
        if ((boolean)this.crystalUseRange.getValue() && this.mc.getRenderViewEntity().getDistance((Entity)e) > (float)this.crystalRange.getValue()) {
            return;
        }
        this.release();
        if (this.crystalStyle.getValue() == ChamStyle.Both) {
            if (event.getRender() == 0) {
                event.setRenderAgain(true);
            }
            else if (event.getRender() == 1) {
                event.setRenderAgain(false);
            }
        }
    }
    
    private void prepareFill(final boolean depth) {
        GlStateManager.pushMatrix();
        GL11.glPushAttrib(1048575);
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
    
    public enum ChamStyle
    {
        Outlined, 
        Filled, 
        Both;
    }
}
