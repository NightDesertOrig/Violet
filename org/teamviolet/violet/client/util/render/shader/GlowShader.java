//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

import org.lwjgl.opengl.*;

public class GlowShader extends FrameBufferShader
{
    public static final GlowShader GLOW_SHADER;
    
    public GlowShader() {
        super("glow");
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
    }
    
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (this.radius * this.quality), 1.0f / this.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform3f(this.getUniform("color"), 1.0f, 1.0f, 1.0f);
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
    }
    
    static {
        GLOW_SHADER = new GlowShader();
    }
}
