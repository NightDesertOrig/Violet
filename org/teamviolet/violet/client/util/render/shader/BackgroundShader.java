//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

import net.minecraft.client.*;

public class BackgroundShader extends FrameBufferShader
{
    private static final Minecraft mc;
    public static final BackgroundShader FRUITY;
    public static final BackgroundShader GALAXY;
    public static final BackgroundShader WATER;
    public static final BackgroundShader LAVA;
    public static final BackgroundShader OUTLINE;
    
    public BackgroundShader(final String name) {
        super(name);
    }
    
    @Override
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("radius");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("alpha");
    }
    
    @Override
    public void updateUniforms() {
    }
    
    static {
        mc = Minecraft.getMinecraft();
        FRUITY = new BackgroundShader("fruity");
        GALAXY = new BackgroundShader("galaxy");
        WATER = new BackgroundShader("water");
        LAVA = new BackgroundShader("lava");
        OUTLINE = new BackgroundShader("outline");
    }
}
