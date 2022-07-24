//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.models;

public interface Camera
{
    void processMouse();
    
    void processMouse(final float p0);
    
    void processMouse(final float p0, final float p1, final float p2);
    
    void processKeyboard(final float p0);
    
    void processKeyboard(final float p0, final float p1);
    
    void processKeyboard(final float p0, final float p1, final float p2, final float p3);
    
    void moveFromLook(final float p0, final float p1, final float p2);
    
    void setPosition(final float p0, final float p1, final float p2);
    
    void applyOrthographicMatrix();
    
    void applyOptimalStates();
    
    void applyPerspectiveMatrix();
    
    void applyTranslations();
    
    void setRotation(final float p0, final float p1, final float p2);
    
    float x();
    
    float y();
    
    float z();
    
    float pitch();
    
    float yaw();
    
    float roll();
    
    float fieldOfView();
    
    void setFieldOfView(final float p0);
    
    void setAspectRatio(final float p0);
    
    float aspectRatio();
    
    float nearClippingPane();
    
    float farClippingPane();
}
