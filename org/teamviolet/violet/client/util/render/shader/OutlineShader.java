//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

public class OutlineShader extends FrameBufferShader
{
    public static final OutlineShader OUTLINE_SHADER;
    
    public OutlineShader() {
        super("differents");
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("startingHeight");
        this.setupUniform("viewPos");
        this.setupUniform("resolution");
        this.setupUniform("time");
    }
    
    public void updateUniforms() {
    }
    
    static {
        OUTLINE_SHADER = new OutlineShader();
    }
}
