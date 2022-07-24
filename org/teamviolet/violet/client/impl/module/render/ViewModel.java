//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.lwjgl.opengl.*;

@Module.Manifest(Module.Category.RENDER)
public class ViewModel extends Module
{
    private final Value<Float> offX;
    private final Value<Float> offY;
    private final Value<Float> mainX;
    private final Value<Float> mainY;
    private final Value<Float> scaleX;
    private final Value<Float> scaleY;
    private final Value<Float> scaleZ;
    private final Value<Boolean> staticTotem;
    private final Value<Float> totemOffset;
    private static final float[] DEFAULT_SCALE;
    private static ViewModel instance;
    
    public ViewModel() {
        this.offX = (Value<Float>)ValueFactory.floatValue().withName("OffhandX").withVal((Object)0.0f).withBounds(-10.0f, 10.0f, 2).build((Module)this);
        this.offY = (Value<Float>)ValueFactory.floatValue().withName("OffhandY").withVal((Object)0.0f).withBounds(-10.0f, 10.0f, 2).build((Module)this);
        this.mainX = (Value<Float>)ValueFactory.floatValue().withName("MainX").withVal((Object)0.0f).withBounds(-10.0f, 10.0f, 2).build((Module)this);
        this.mainY = (Value<Float>)ValueFactory.floatValue().withName("MainY").withVal((Object)0.0f).withBounds(-10.0f, 10.0f, 2).build((Module)this);
        this.scaleX = (Value<Float>)ValueFactory.floatValue().withName("XScale").withVal((Object)1.0f).withBounds(-10.0f, 10.0f, 1).build((Module)this);
        this.scaleY = (Value<Float>)ValueFactory.floatValue().withName("YScale").withVal((Object)1.0f).withBounds(-10.0f, 10.0f, 1).build((Module)this);
        this.scaleZ = (Value<Float>)ValueFactory.floatValue().withName("ZScale").withVal((Object)1.0f).withBounds(-10.0f, 10.0f, 1).build((Module)this);
        this.staticTotem = (Value<Boolean>)ValueFactory.booleanValue().withName("StaticTotem").withVal((Object)true).build((Module)this);
        this.totemOffset = (Value<Float>)ValueFactory.floatValue().withName("TotemOffset").withVal((Object)0.7f).withBounds(0.0f, 1.0f, 2).build((Value)this.staticTotem);
    }
    
    public float getX(final EnumHand hand) {
        if (!this.isEnabled()) {
            return 0.0f;
        }
        return (float)((hand == EnumHand.MAIN_HAND) ? this.mainX.getValue() : ((float)this.offX.getValue()));
    }
    
    public float getY(final EnumHand hand) {
        if (!this.isEnabled()) {
            return 0.0f;
        }
        return (hand == EnumHand.MAIN_HAND) ? ((float)this.mainY.getValue() / 10.0f) : ((float)this.offY.getValue() / 10.0f);
    }
    
    public float[] getScale() {
        if (!this.isEnabled()) {
            return ViewModel.DEFAULT_SCALE;
        }
        return new float[] { (float)this.scaleX.getValue(), (float)this.scaleY.getValue(), (float)this.scaleZ.getValue() };
    }
    
    @Listener
    public void listen(final Render3DEvent.Pre event) {
        if (this.staticTotem.getValue()) {
            ((IItemRenderer)this.mc.entityRenderer.itemRenderer).setequippedProgressOffHand((float)this.totemOffset.getValue());
        }
    }
    
    @Listener
    public void listen(final RenderItemFPEvent event) {
        if (event.getType() == 0.0f) {
            final float xOffset = this.getX(event.getHand());
            final float yOffset = this.getY(event.getHand());
            event.getItemRenderer().renderItemInFirstPerson(event.getPlayer(), event.getDrinkOffset(), event.getMapAngle(), event.getHand(), event.getX() + xOffset, event.getStack(), event.getY() + yOffset);
        }
        else {
            final float[] scale = this.getScale();
            GL11.glScalef(scale[0], scale[1], scale[2]);
        }
    }
    
    public static ViewModel getInstance() {
        if (ViewModel.instance == null) {
            ViewModel.instance = new ViewModel();
        }
        return ViewModel.instance;
    }
    
    static {
        DEFAULT_SCALE = new float[] { 1.0f, 1.0f, 1.0f };
    }
}
