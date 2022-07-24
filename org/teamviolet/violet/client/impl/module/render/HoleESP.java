//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.*;
import org.teamviolet.violet.client.util.game.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class HoleESP extends Module
{
    private final Value<Integer> range;
    private final Value<Boolean> render;
    private final Value<Color> obby;
    private final Value<Color> bedrock;
    private final Value<Float> height;
    private final Value<RenderUtil.RenderMode> renderMode;
    private final Value<Boolean> fade;
    
    public HoleESP() {
        this.range = (Value<Integer>)ValueFactory.intValue().withName("Range").withVal((Object)7).withBounds(1.0f, 50.0f, 0).build((Module)this);
        this.render = (Value<Boolean>)ValueFactory.booleanValue().withName("Render").withVal((Object)true).build((Module)this);
        this.obby = (Value<Color>)ValueFactory.colorValue().withName("Obby").withVal((Object)new Color(-1470219385, true)).build((Value)this.render);
        this.bedrock = (Value<Color>)ValueFactory.colorValue().withName("Bedrock").withVal((Object)new Color(-1473412048, true)).build((Value)this.render);
        this.height = (Value<Float>)ValueFactory.floatValue().withName("Height").withVal((Object)0.0f).withBounds(-1.0f, 1.0f, 2).build((Value)this.render);
        this.renderMode = (Value<RenderUtil.RenderMode>)new ValueFactory().withName("Mode").withVal((Object)RenderUtil.RenderMode.BOTH).build((Value)this.render);
        this.fade = (Value<Boolean>)ValueFactory.booleanValue().withName("Fade").withVal((Object)true).build((Value)this.render);
    }
    
    @Listener
    public void listen(final Render3DEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        for (final HoleStatus status : Violet.getViolet().getHoleManager().getHoles()) {
            if (this.mc.player.getDistance(status.getPos().getX() + 0.5, status.getPos().getY() + 0.5, status.getPos().getZ() + 0.5) <= (int)this.range.getValue()) {
                final Color renderColor = (Color)(status.isBedrockHole() ? this.bedrock.getValue() : ((Color)this.obby.getValue()));
                float a = (float)renderColor.getAlpha();
                if (this.fade.getValue()) {
                    a -= (float)(a * this.mc.player.getDistance(status.getPos().getX() + 0.5, status.getPos().getY() + 0.5, status.getPos().getZ() + 0.5) / (int)this.range.getValue());
                }
                RenderUtil.drawBoxBlockPos(status.getPos(), (float)this.height.getValue(), 0.0, 0.0, new Color(renderColor.getRed(), renderColor.getGreen(), renderColor.getBlue(), (int)a), (RenderUtil.RenderMode)this.renderMode.getValue(), (int)(a / 255.0f * 180.0f));
            }
        }
    }
}
