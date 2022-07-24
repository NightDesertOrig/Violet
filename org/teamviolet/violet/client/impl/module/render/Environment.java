//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import java.lang.reflect.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.network.play.server.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.util.math.*;

@Module.Manifest(Module.Category.RENDER)
public class Environment extends Module
{
    private final Value<Boolean> fullbright;
    private final Value<Boolean> noWeather;
    private final Value<WeatherType> weatherType;
    private final Value<Boolean> customTime;
    private final Value<Integer> time;
    private final Value<Boolean> ambience;
    private final Value<Color> ambienceColor;
    protected boolean lightPipeLine;
    private float originalGamma;
    
    public Environment() {
        this.fullbright = (Value<Boolean>)ValueFactory.booleanValue().withName("FullBright").withVal((Object)true).build((Module)this);
        this.noWeather = (Value<Boolean>)ValueFactory.booleanValue().withName("NoWeather").withVal((Object)true).build((Module)this);
        this.weatherType = (Value<WeatherType>)new ValueFactory().withName("Weather").withVal((Object)WeatherType.Clear).build((Value)this.noWeather);
        this.customTime = (Value<Boolean>)ValueFactory.booleanValue().withName("CustomTime").withVal((Object)true).build((Module)this);
        this.time = (Value<Integer>)ValueFactory.intValue().withName("Time").withVal((Object)12).withBounds(0.0f, 24.0f, 0).build((Value)this.customTime);
        this.ambience = (Value<Boolean>)ValueFactory.booleanValue().withName("Ambience").withVal((Object)false).build((Module)this);
        this.ambienceColor = (Value<Color>)ValueFactory.colorValue().withName("AmbienceColor").withVal((Object)Color.white).build((Value)this.ambience);
        this.originalGamma = -1.0f;
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        try {
            final Field field = Class.forName("net.minecraftforge.common.ForgeModContainer", true, this.getClass().getClassLoader()).getDeclaredField("forgeLightPipelineEnabled");
            final boolean accessible = field.isAccessible();
            field.setAccessible(true);
            this.lightPipeLine = field.getBoolean(null);
            field.set(null, false);
            field.setAccessible(accessible);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final int x = (int)this.mc.player.posX;
        final int y = (int)this.mc.player.posY;
        final int z = (int)this.mc.player.posZ;
        final int d = this.mc.gameSettings.renderDistanceChunks * 16;
        this.mc.renderGlobal.markBlockRangeForRenderUpdate(x - d, y - d, z - d, x + d, y + d, z + d);
        ((IEntityRenderer)this.mc.entityRenderer).setLightmapUpdateNeeded(true);
    }
    
    protected void onDisable() {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.originalGamma != -1.0f) {
            this.mc.gameSettings.gammaSetting = this.originalGamma;
            this.originalGamma = -1.0f;
        }
        try {
            final Field field = Class.forName("net.minecraftforge.common.ForgeModContainer", true, this.getClass().getClassLoader()).getDeclaredField("forgeLightPipelineEnabled");
            final boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(null, this.lightPipeLine);
            field.setAccessible(accessible);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final int x = (int)this.mc.player.posX;
        final int y = (int)this.mc.player.posY;
        final int z = (int)this.mc.player.posZ;
        final int d = this.mc.gameSettings.renderDistanceChunks * 16;
        this.mc.renderGlobal.markBlockRangeForRenderUpdate(x - d, y - d, z - d, x + d, y + d, z + d);
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (this.originalGamma == -1.0f && (boolean)this.fullbright.getValue()) {
            this.originalGamma = this.mc.gameSettings.gammaSetting;
            this.mc.gameSettings.gammaSetting = 100.0f;
        }
        if (this.originalGamma != -1.0f && !(boolean)this.fullbright.getValue()) {
            this.mc.gameSettings.gammaSetting = this.originalGamma;
            this.originalGamma = -1.0f;
        }
        if (this.noWeather.getValue()) {
            switch ((WeatherType)this.weatherType.getValue()) {
                case Clear: {
                    this.mc.world.setRainStrength(0.0f);
                    break;
                }
                case Rain: {
                    this.mc.world.setRainStrength(1.0f);
                    break;
                }
                case Thunder: {
                    this.mc.world.setRainStrength(2.0f);
                    break;
                }
            }
        }
        if (this.customTime.getValue()) {
            this.mc.world.setWorldTime(this.getIngameTimeFromIRLTime((float)(int)this.time.getValue()));
        }
    }
    
    @Listener
    private void listen(final PacketEvent.Read event) {
        if (event.getPacket() instanceof SPacketTimeUpdate && (boolean)this.customTime.getValue()) {
            event.cancel();
        }
    }
    
    @Listener
    private void listen(final UpdateLightmapEvent event) {
        if (!(boolean)this.ambience.getValue()) {
            return;
        }
        for (int i = 0; i < event.lightmapColors.length; ++i) {
            final Color ambientColor = (Color)this.ambienceColor.getValue();
            final int alpha = ambientColor.getAlpha();
            final float modifier = alpha / 255.0f;
            final int color = event.lightmapColors[i];
            final int[] bgr = this.toRGBAArray(color);
            final Vec3d values = new Vec3d((double)(bgr[2] / 255.0f), (double)(bgr[1] / 255.0f), (double)(bgr[0] / 255.0f));
            final Vec3d newValues = new Vec3d((double)(ambientColor.getRed() / 255.0f), (double)(ambientColor.getGreen() / 255.0f), (double)(ambientColor.getBlue() / 255.0f));
            final Vec3d finalValues = this.mix(values, newValues, modifier);
            final int red = (int)(finalValues.x * 255.0);
            final int green = (int)(finalValues.y * 255.0);
            final int blue = (int)(finalValues.z * 255.0);
            event.lightmapColors[i] = (0xFF000000 | red << 16 | green << 8 | blue);
        }
    }
    
    private int[] toRGBAArray(final int colorBuffer) {
        return new int[] { colorBuffer >> 16 & 0xFF, colorBuffer >> 8 & 0xFF, colorBuffer & 0xFF };
    }
    
    private Vec3d mix(final Vec3d first, final Vec3d second, final float factor) {
        return new Vec3d(first.x * (1.0f - factor) + second.x * factor, first.y * (1.0f - factor) + second.y * factor, first.z * (1.0f - factor) + first.z * factor);
    }
    
    private long getIngameTimeFromIRLTime(final float time) {
        return (long)(time * 1000.0f);
    }
    
    private enum WeatherType
    {
        Clear, 
        Rain, 
        Thunder;
    }
}
