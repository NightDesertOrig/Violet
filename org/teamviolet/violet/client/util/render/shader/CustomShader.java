//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;

public class CustomShader extends FrameBufferShader
{
    private static final Minecraft mc;
    public static final CustomShader SMOOTH1;
    public static final CustomShader SMOOTH2;
    public static final CustomShader SMOOTH3;
    public static final CustomShader GALAXY;
    public static final CustomShader WATER;
    public static final CustomShader LAVA;
    public static final CustomShader OUTLINE;
    public static final CustomShader PINK_GALACTIC;
    public static final CustomShader MATTER;
    public static final CustomShader CATTYN;
    public static final CustomShader HACKER;
    public static final CustomShader BARS;
    public static final CustomShader STARS;
    
    public CustomShader(final String name) {
        super(name);
    }
    
    @Override
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("radius");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("color");
        this.setupUniform("alpha");
    }
    
    @Override
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / CustomShader.mc.displayWidth * (this.radius * this.quality), 1.0f / CustomShader.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform2f(this.getUniform("resolution"), (float)CustomShader.mc.displayWidth, (float)CustomShader.mc.displayHeight);
        GL20.glUniform1f(this.getUniform("time"), (System.currentTimeMillis() - this.startTime) / 1000.0f);
        GL20.glUniform3f(this.getUniform("color"), this.inside.getRed() / 255.0f, this.inside.getGreen() / 255.0f, this.inside.getBlue() / 255.0f);
        GL20.glUniform2f(this.getUniform("alpha"), this.inside.getAlpha() / 255.0f, this.outside.getAlpha() / 255.0f);
    }
    
    static {
        mc = Minecraft.getMinecraft();
        SMOOTH1 = new CustomShader("smooth1");
        SMOOTH2 = new CustomShader("smooth2");
        SMOOTH3 = new CustomShader("smooth3");
        GALAXY = new CustomShader("galaxy");
        WATER = new CustomShader("water");
        LAVA = new CustomShader("lava");
        OUTLINE = new CustomShader("outline");
        PINK_GALACTIC = new CustomShader("pink_galaxy");
        MATTER = new CustomShader("matter");
        CATTYN = new CustomShader("cattyn");
        HACKER = new CustomShader("hacker");
        BARS = new CustomShader("bars");
        STARS = new CustomShader("cool_stars");
    }
}
