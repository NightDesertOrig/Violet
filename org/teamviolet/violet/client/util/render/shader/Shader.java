//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;

public class Shader extends FrameBufferShader
{
    private static final Minecraft mc;
    public static final Shader INSTANCE;
    
    public Shader(final String name) {
        super(name);
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("insideColor");
        this.setupUniform("insideMode");
        this.setupUniform("outsideColor");
        this.setupUniform("outsideMode");
        this.setupUniform("glow");
    }
    
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / Shader.mc.displayWidth * (this.radius * this.quality), 1.0f / Shader.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform2f(this.getUniform("resolution"), (float)Shader.mc.displayWidth, (float)Shader.mc.displayHeight);
        GL20.glUniform1f(this.getUniform("time"), (System.currentTimeMillis() - this.startTime) / 1000.0f);
        GL20.glUniform4f(this.getUniform("insideColor"), this.inside.getRed() / 255.0f, this.inside.getGreen() / 255.0f, this.inside.getBlue() / 255.0f, this.inside.getAlpha() / 255.0f);
        GL20.glUniform1i(this.getUniform("insideMode"), this.insideMode);
        GL20.glUniform4f(this.getUniform("outsideColor"), this.outside.getRed() / 255.0f, this.outside.getGreen() / 255.0f, this.outside.getBlue() / 255.0f, this.outside.getAlpha() / 255.0f);
        GL20.glUniform1i(this.getUniform("outsideMode"), this.outsideMode);
        GL20.glUniform1i(this.getUniform("glow"), this.glow);
    }
    
    static {
        mc = Minecraft.getMinecraft();
        INSTANCE = new Shader("shader");
    }
}
