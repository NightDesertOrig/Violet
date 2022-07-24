//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.shader;

public class ImageShader extends FrameBufferShader
{
    public static final ImageShader FRUITY;
    
    public ImageShader(final String name) {
        super(name);
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("image");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("radius");
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("alpha");
    }
    
    public void updateUniforms() {
    }
    
    static {
        FRUITY = new ImageShader("fruity");
    }
}
