//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.util.math.*;
import net.minecraft.block.material.*;
import net.minecraft.world.*;
import net.minecraft.block.state.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class BlockHighlight extends Module
{
    private final Value<Color> color;
    private final Value<RenderUtil.RenderMode> renderMode;
    private final Value<Boolean> fade;
    private final Value<Boolean> multi;
    private final Value<Float> fadeTime;
    private Map<BlockPos, Timer> timers;
    
    public BlockHighlight() {
        this.color = (Value<Color>)ValueFactory.colorValue().withName("Color").withVal((Object)new Color(2040619005, true)).build((Module)this);
        this.renderMode = (Value<RenderUtil.RenderMode>)new ValueFactory().withName("Mode").withVal((Object)RenderUtil.RenderMode.BOTH).build((Module)this);
        this.fade = (Value<Boolean>)ValueFactory.booleanValue().withName("Fade").withVal((Object)true).build((Module)this);
        this.multi = (Value<Boolean>)ValueFactory.booleanValue().withName("Multi").withVal((Object)false).build((Value)this.fade);
        this.fadeTime = (Value<Float>)ValueFactory.floatValue().withName("FadeTime").withVal((Object)0.3f).withBounds(0.01f, 5.0f, 2).build((Value)this.fade);
        this.timers = new HashMap<BlockPos, Timer>();
    }
    
    protected void onEnable() {
        this.timers.clear();
    }
    
    protected void onDisable() {
        this.timers.clear();
    }
    
    @Listener
    public void listen(final Render3DEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        final RayTraceResult result = this.mc.objectMouseOver;
        if (result != null && result.typeOfHit == RayTraceResult.Type.BLOCK) {
            final BlockPos pos = result.getBlockPos();
            final IBlockState iblockstate = this.mc.world.getBlockState(pos);
            if (iblockstate.getMaterial() != Material.AIR && this.mc.world.getWorldBorder().contains(pos)) {
                if (!(boolean)this.fade.getValue() || !(boolean)this.multi.getValue()) {
                    this.timers.clear();
                }
                if (this.timers.containsKey(pos)) {
                    this.timers.remove(pos);
                }
                this.timers.put(pos, new Timer());
            }
        }
        else if (this.timers.containsKey(result.getBlockPos())) {
            this.timers.remove(result.getBlockPos());
        }
        for (final Map.Entry<BlockPos, Timer> entry : this.timers.entrySet()) {
            if (entry.getValue().passed((long)((float)this.fadeTime.getValue() * 1000.0f))) {
                continue;
            }
            final IBlockState iblockstate2 = this.mc.world.getBlockState((BlockPos)entry.getKey());
            final double d3 = this.mc.getRenderViewEntity().lastTickPosX + (this.mc.getRenderViewEntity().posX - this.mc.getRenderViewEntity().lastTickPosX) * event.getPartialTicks();
            final double d4 = this.mc.getRenderViewEntity().lastTickPosY + (this.mc.getRenderViewEntity().posY - this.mc.getRenderViewEntity().lastTickPosY) * event.getPartialTicks();
            final double d5 = this.mc.getRenderViewEntity().lastTickPosZ + (this.mc.getRenderViewEntity().posZ - this.mc.getRenderViewEntity().lastTickPosZ) * event.getPartialTicks();
            final double fract = 1.0f - entry.getValue().getTime() / ((float)this.fadeTime.getValue() * 1000.0f);
            final Color inside = new Color(((Color)this.color.getValue()).getRed(), ((Color)this.color.getValue()).getGreen(), ((Color)this.color.getValue()).getBlue(), (int)(((Color)this.color.getValue()).getAlpha() * fract));
            if (this.fade.getValue()) {
                RenderUtil.drawBox(iblockstate2.getSelectedBoundingBox((World)this.mc.world, (BlockPos)entry.getKey()).grow(0.0020000000949949026).offset(-d3, -d4, -d5), 0.0, 0.0, 0.0, inside, (RenderUtil.RenderMode)this.renderMode.getValue(), (int)(180.0 * fract));
            }
            else {
                RenderUtil.drawBox(iblockstate2.getSelectedBoundingBox((World)this.mc.world, (BlockPos)entry.getKey()).grow(0.0020000000949949026).offset(-d3, -d4, -d5), 0.0, 0.0, 0.0, (Color)this.color.getValue(), (RenderUtil.RenderMode)this.renderMode.getValue());
            }
        }
        this.timers.entrySet().removeIf(e -> e.getValue().passed((long)((float)this.fadeTime.getValue() * 1000.0f)));
    }
}
