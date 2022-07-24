//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import com.mojang.realmsclient.gui.*;
import org.teamviolet.violet.client.api.value.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.*;
import net.minecraft.client.network.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.gui.*;
import org.teamviolet.violet.client.util.render.font.*;
import net.minecraft.util.math.*;
import java.util.*;

@Module.Manifest(Module.Category.CLIENT)
public class HUD extends Module
{
    public final Value<Color> hudColor;
    private final Value<Boolean> effects;
    private final Value<ColorMode> mode;
    private final Value<PulseMode> pulseMode;
    private final Value<Integer> pulseRate;
    private final Value<Boolean> watermark;
    private final Value<Corner> watermarkCorner;
    private final Value<Boolean> moduleList;
    private final Value<Corner> moduleListCorner;
    private final Value<Boolean> infoList;
    private final Value<Corner> infoListCorner;
    private final Value<Boolean> showPotions;
    private final Value<Boolean> showPing;
    private final Value<Boolean> showFPS;
    private final Value<Boolean> showSpeed;
    private final Value<Boolean> showTPS;
    private final Value<Boolean> coords;
    private final Value<Boolean> welcomer;
    private static final Map<Potion, Color> POTION_COLOR_MAP;
    private final ColorController controller;
    private final ChatFormatting gr;
    
    public HUD() {
        this.hudColor = (Value<Color>)ValueFactory.colorValue().withName("Text Color").withVal((Object)new Color(16777215)).build((Module)this);
        this.effects = (Value<Boolean>)ValueFactory.booleanValue().withName("Effects").withVal((Object)true).build((Module)this);
        this.mode = (Value<ColorMode>)new ValueFactory().withName("Effect Mode").withVal((Object)ColorMode.Pulse).build((Value)this.effects);
        this.pulseMode = (Value<PulseMode>)new ValueFactory().withName("Pulse Mode").withVal((Object)PulseMode.Brightness).build((Value)this.effects);
        this.pulseRate = (Value<Integer>)ValueFactory.intValue().withName("Pulse Rate").withVal((Object)4).withBounds(0.0f, 20.0f, 0).build((Value)this.effects);
        this.watermark = (Value<Boolean>)ValueFactory.booleanValue().withName("Watermark").withVal((Object)true).build((Module)this);
        this.watermarkCorner = (Value<Corner>)new ValueFactory().withName("Watermark Corner").withVal((Object)Corner.TopLeft).build((Value)this.watermark);
        this.moduleList = (Value<Boolean>)ValueFactory.booleanValue().withName("Modules").withVal((Object)true).build((Module)this);
        this.moduleListCorner = (Value<Corner>)new ValueFactory().withName("Modules Corner").withVal((Object)Corner.TopRight).build((Value)this.moduleList);
        this.infoList = (Value<Boolean>)ValueFactory.booleanValue().withName("Info List").withVal((Object)true).build((Module)this);
        this.infoListCorner = (Value<Corner>)new ValueFactory().withName("Info Corner").withVal((Object)Corner.BottomRight).build((Value)this.infoList);
        this.showPotions = (Value<Boolean>)ValueFactory.booleanValue().withName("Potions").withVal((Object)true).build((Value)this.infoList);
        this.showPing = (Value<Boolean>)ValueFactory.booleanValue().withName("Potions").withVal((Object)true).build((Value)this.infoList);
        this.showFPS = (Value<Boolean>)ValueFactory.booleanValue().withName("Potions").withVal((Object)true).build((Value)this.infoList);
        this.showSpeed = (Value<Boolean>)ValueFactory.booleanValue().withName("Potions").withVal((Object)true).build((Value)this.infoList);
        this.showTPS = (Value<Boolean>)ValueFactory.booleanValue().withName("Potions").withVal((Object)true).build((Value)this.infoList);
        this.coords = (Value<Boolean>)ValueFactory.booleanValue().withName("Coords").withVal((Object)true).build((Module)this);
        this.welcomer = (Value<Boolean>)ValueFactory.booleanValue().withName("Welcomer").withVal((Object)true).build((Module)this);
        this.gr = ChatFormatting.GRAY;
        this.controller = new ColorController(0);
        HUD.POTION_COLOR_MAP.put(MobEffects.SPEED, new Color(124, 175, 198));
        HUD.POTION_COLOR_MAP.put(MobEffects.SLOWNESS, new Color(90, 108, 129));
        HUD.POTION_COLOR_MAP.put(MobEffects.HASTE, new Color(217, 192, 67));
        HUD.POTION_COLOR_MAP.put(MobEffects.MINING_FATIGUE, new Color(74, 66, 23));
        HUD.POTION_COLOR_MAP.put(MobEffects.STRENGTH, new Color(147, 36, 35));
        HUD.POTION_COLOR_MAP.put(MobEffects.INSTANT_HEALTH, new Color(67, 10, 9));
        HUD.POTION_COLOR_MAP.put(MobEffects.INSTANT_DAMAGE, new Color(67, 10, 9));
        HUD.POTION_COLOR_MAP.put(MobEffects.JUMP_BOOST, new Color(34, 255, 76));
        HUD.POTION_COLOR_MAP.put(MobEffects.NAUSEA, new Color(85, 29, 74));
        HUD.POTION_COLOR_MAP.put(MobEffects.REGENERATION, new Color(205, 92, 171));
        HUD.POTION_COLOR_MAP.put(MobEffects.RESISTANCE, new Color(153, 69, 58));
        HUD.POTION_COLOR_MAP.put(MobEffects.FIRE_RESISTANCE, new Color(228, 154, 58));
        HUD.POTION_COLOR_MAP.put(MobEffects.WATER_BREATHING, new Color(46, 82, 153));
        HUD.POTION_COLOR_MAP.put(MobEffects.INVISIBILITY, new Color(127, 131, 146));
        HUD.POTION_COLOR_MAP.put(MobEffects.BLINDNESS, new Color(31, 31, 35));
        HUD.POTION_COLOR_MAP.put(MobEffects.NIGHT_VISION, new Color(31, 31, 161));
        HUD.POTION_COLOR_MAP.put(MobEffects.HUNGER, new Color(88, 118, 83));
        HUD.POTION_COLOR_MAP.put(MobEffects.WEAKNESS, new Color(72, 77, 72));
        HUD.POTION_COLOR_MAP.put(MobEffects.POISON, new Color(78, 147, 49));
        HUD.POTION_COLOR_MAP.put(MobEffects.WITHER, new Color(53, 42, 39));
        HUD.POTION_COLOR_MAP.put(MobEffects.HEALTH_BOOST, new Color(248, 125, 35));
        HUD.POTION_COLOR_MAP.put(MobEffects.ABSORPTION, new Color(37, 82, 165));
        HUD.POTION_COLOR_MAP.put(MobEffects.SATURATION, new Color(248, 36, 35));
        HUD.POTION_COLOR_MAP.put(MobEffects.GLOWING, new Color(148, 160, 97));
        HUD.POTION_COLOR_MAP.put(MobEffects.LEVITATION, new Color(206, 255, 255));
        HUD.POTION_COLOR_MAP.put(MobEffects.LUCK, new Color(51, 153, 0));
        HUD.POTION_COLOR_MAP.put(MobEffects.UNLUCK, new Color(192, 164, 77));
    }
    
    @Listener
    public void listen(final Render2DEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        if (this.watermark.getValue()) {
            this.drawWatermark();
        }
        if (this.moduleList.getValue()) {
            this.drawModuleList();
        }
        if (this.infoList.getValue()) {
            this.drawInfoList();
        }
        if (this.coords.getValue()) {
            this.drawCoords();
        }
        if (this.welcomer.getValue()) {
            this.drawWelcomer();
        }
        for (final Corner value : Corner.values()) {
            value.resetPos();
        }
    }
    
    private void drawWatermark() {
        final String text = "Violet v1.0.4";
        final Vec2f drawPos = this.getDrawCoords(text, ((Corner)this.watermarkCorner.getValue()).pos, (Corner)this.watermarkCorner.getValue());
        this.drawText(text, drawPos, ((Corner)this.watermarkCorner.getValue()).pos);
        final Corner corner = (Corner)this.watermarkCorner.getValue();
        ++corner.pos;
    }
    
    private void drawModuleList() {
        final List<Module> modules = (List<Module>)Violet.getViolet().getModuleManager().getModuleList();
        modules.sort(HUD::order);
        for (final Module module : modules) {
            if (module.isEnabled() && !module.isHidden()) {
                String text = module.getName();
                if (!module.getInfo().isEmpty()) {
                    text = text + ChatFormatting.RESET.toString() + ChatFormatting.GRAY + " [" + ChatFormatting.WHITE + module.getInfo() + ChatFormatting.GRAY + "]";
                }
                final Vec2f drawPos = this.getDrawCoords(text, ((Corner)this.moduleListCorner.getValue()).pos, (Corner)this.moduleListCorner.getValue());
                this.drawText(text, drawPos, ((Corner)this.moduleListCorner.getValue()).pos);
                final Corner corner = (Corner)this.moduleListCorner.getValue();
                ++corner.pos;
            }
        }
    }
    
    private void drawInfoList() {
        try {
            final int responseTime = Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.player.getUniqueID()).getResponseTime();
        }
        catch (Exception exception) {
            final int responseTime = 0;
        }
        final int framesPerSecond = Minecraft.getDebugFPS();
        final int ticksPerSecond = 69;
        final double kmh = 69.0;
        if (this.showPotions.getValue()) {
            for (final PotionEffect effect : this.mc.player.getActivePotionEffects()) {
                final String pText = I18n.format(effect.getPotion().getName(), new Object[0]) + this.gr + " " + (effect.getAmplifier() + 1) + " " + Potion.getPotionDurationString(effect, 1.0f);
                final Vec2f drawPos = this.getDrawCoords(pText, ((Corner)this.infoListCorner.getValue()).pos, (Corner)this.infoListCorner.getValue());
                this.drawText(pText, drawPos, ((Corner)this.infoListCorner.getValue()).pos);
                final Corner corner = (Corner)this.infoListCorner.getValue();
                ++corner.pos;
            }
        }
        if (this.showSpeed.getValue()) {
            final String pText2 = "Speed: " + this.gr + kmh + " km/h";
            final Vec2f drawPos2 = this.getDrawCoords(pText2, ((Corner)this.infoListCorner.getValue()).pos, (Corner)this.infoListCorner.getValue());
            this.drawText(pText2, drawPos2, ((Corner)this.infoListCorner.getValue()).pos);
            final Corner corner2 = (Corner)this.infoListCorner.getValue();
            ++corner2.pos;
        }
        if (this.showTPS.getValue()) {
            final String pText2 = "TPS: " + this.gr + ticksPerSecond;
            final Vec2f drawPos2 = this.getDrawCoords(pText2, ((Corner)this.infoListCorner.getValue()).pos, (Corner)this.infoListCorner.getValue());
            this.drawText(pText2, drawPos2, ((Corner)this.infoListCorner.getValue()).pos);
            final Corner corner3 = (Corner)this.infoListCorner.getValue();
            ++corner3.pos;
        }
        if (this.showFPS.getValue()) {
            final String pText2 = "FPS: " + this.gr + framesPerSecond;
            final Vec2f drawPos2 = this.getDrawCoords(pText2, ((Corner)this.infoListCorner.getValue()).pos, (Corner)this.infoListCorner.getValue());
            this.drawText(pText2, drawPos2, ((Corner)this.infoListCorner.getValue()).pos);
            final Corner corner4 = (Corner)this.infoListCorner.getValue();
            ++corner4.pos;
        }
        if (this.showPing.getValue()) {
            final String pText2 = "Ping: " + this.gr + ticksPerSecond;
            final Vec2f drawPos2 = this.getDrawCoords(pText2, ((Corner)this.infoListCorner.getValue()).pos, (Corner)this.infoListCorner.getValue());
            this.drawText(pText2, drawPos2, ((Corner)this.infoListCorner.getValue()).pos);
            final Corner corner5 = (Corner)this.infoListCorner.getValue();
            ++corner5.pos;
        }
    }
    
    private void drawCoords() {
        final boolean inHell = this.mc.world.getBiome(this.mc.player.getPosition()).getBiomeName().equals("Hell");
        final int posX = (int)this.mc.player.posX;
        final int posY = (int)this.mc.player.posY;
        final int posZ = (int)this.mc.player.posZ;
        final float nether = inHell ? 8.0f : 0.125f;
        final int hposX = (int)(this.mc.player.posX * nether);
        final int hposZ = (int)(this.mc.player.posZ * nether);
        final String coordinates = "XYZ " + posX + ", " + posY + ", " + posZ + " [" + hposX + ", " + hposZ + "]";
        final String dir = this.getFacing() + " [" + this.getTowards() + "]";
        Vec2f drawPos = this.getDrawCoords(coordinates, 0, null);
        this.drawText(coordinates, drawPos, 0);
        drawPos = this.getDrawCoords(dir, 1, null);
        this.drawText(dir, drawPos, 1);
    }
    
    private void drawWelcomer() {
        final String text = "Hey, " + this.mc.player.getName();
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        this.drawText(text, new Vec2f(scaledResolution.getScaledWidth() / 2 - FontUtil.getStringWidth(text) / 2.0f, 2.0f), 0);
    }
    
    private void drawText(final String text, final Vec2f drawPos, final int height) {
        Color color = this.getColor();
        if (this.mode.getValue() == ColorMode.Pulse && (boolean)this.effects.getValue()) {
            color = this.getColorFade(height);
        }
        FontUtil.drawText(text, (float)(int)drawPos.x, (float)(int)drawPos.y, color);
    }
    
    private Color getColor() {
        return (Color)this.hudColor.getValue();
    }
    
    private Color getColorFade(final int height) {
        final Color current = this.getColor();
        final float[] hsb = Color.RGBtoHSB(current.getRed(), current.getGreen(), current.getBlue(), null);
        this.controller.updateColor((int)this.pulseRate.getValue());
        if (this.pulseMode.getValue() == PulseMode.Hue) {
            return new Color(Color.HSBtoRGB(this.controller.getAddition(height), hsb[1], hsb[2]));
        }
        if (this.pulseMode.getValue() == PulseMode.Saturation) {
            return new Color(Color.HSBtoRGB(hsb[0], this.controller.getAddition(height), hsb[2]));
        }
        if (this.pulseMode.getValue() == PulseMode.Brightness) {
            return new Color(Color.HSBtoRGB(hsb[0], hsb[1], this.controller.getAddition(height)));
        }
        return current;
    }
    
    private Vec2f getDrawCoords(final String text, final int itemInList, final Corner corner) {
        final ScaledResolution scaledResolution = new ScaledResolution(this.mc);
        final int yBoost = (int)(FontUtil.getStringHeight(text) * itemInList);
        if (corner != null) {
            switch (corner) {
                case TopLeft: {
                    return new Vec2f(2.0f, (float)(yBoost + 2));
                }
                case TopRight: {
                    final int startX = scaledResolution.getScaledWidth();
                    final int startY = 0;
                    return new Vec2f(startX - FontUtil.getStringWidth(text) - 2.0f, (float)(startY + yBoost + 2));
                }
                case BottomRight: {
                    final int startX2 = scaledResolution.getScaledWidth();
                    final int startY2 = scaledResolution.getScaledHeight();
                    return new Vec2f(startX2 - FontUtil.getStringWidth(text) - 2.0f, startY2 - FontUtil.getStringHeight(text) - yBoost - 2.0f);
                }
            }
        }
        final int startY3 = scaledResolution.getScaledHeight();
        return new Vec2f(2.0f, startY3 - FontUtil.getStringHeight(text) - yBoost - 2.0f);
    }
    
    public String getFacing() {
        switch (MathHelper.floor(this.mc.player.rotationYaw * 8.0f / 360.0f + 0.5) & 0x7) {
            case 0: {
                return "South";
            }
            case 1: {
                return "South West";
            }
            case 2: {
                return "West";
            }
            case 3: {
                return "North West";
            }
            case 4: {
                return "North";
            }
            case 5: {
                return "North East";
            }
            case 6: {
                return "East";
            }
            case 7: {
                return "South East";
            }
            default: {
                return "Invalid";
            }
        }
    }
    
    public String getTowards() {
        switch (MathHelper.floor(this.mc.player.rotationYaw * 8.0f / 360.0f + 0.5) & 0x7) {
            case 0: {
                return "+Z";
            }
            case 1: {
                return "-X +Z";
            }
            case 2: {
                return "-X";
            }
            case 3: {
                return "-X -Z";
            }
            case 4: {
                return "-Z";
            }
            case 5: {
                return "+X -Z";
            }
            case 6: {
                return "+X";
            }
            case 7: {
                return "+X +Z";
            }
            default: {
                return "Invalid";
            }
        }
    }
    
    private static int order(final Module m1, final Module m2) {
        final String s1 = m1.getName();
        final String s2 = m2.getName();
        return Integer.compare(s2.length(), s1.length());
    }
    
    static {
        POTION_COLOR_MAP = new HashMap<Potion, Color>();
    }
    
    public enum ColorMode
    {
        Rainbow, 
        Pulse;
    }
    
    public enum PulseMode
    {
        Hue, 
        Saturation, 
        Brightness;
    }
    
    public enum Corner
    {
        TopLeft, 
        TopRight, 
        BottomRight;
        
        public int pos;
        
        private Corner() {
            this.pos = 0;
        }
        
        void resetPos() {
            this.pos = 0;
        }
    }
}
