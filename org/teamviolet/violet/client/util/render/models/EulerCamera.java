//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.models;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;

public final class EulerCamera implements Camera
{
    private float x;
    private float y;
    private float z;
    private float pitch;
    private float yaw;
    private float roll;
    private float fov;
    private float aspectRatio;
    private final float zNear;
    private final float zFar;
    
    private EulerCamera(final Builder builder) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
        this.roll = 0.0f;
        this.fov = 90.0f;
        this.aspectRatio = 1.0f;
        this.x = builder.x;
        this.y = builder.y;
        this.z = builder.z;
        this.pitch = builder.pitch;
        this.yaw = builder.yaw;
        this.roll = builder.roll;
        this.aspectRatio = builder.aspectRatio;
        this.zNear = builder.zNear;
        this.zFar = builder.zFar;
        this.fov = builder.fov;
    }
    
    public EulerCamera() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
        this.roll = 0.0f;
        this.fov = 90.0f;
        this.aspectRatio = 1.0f;
        this.zNear = 0.3f;
        this.zFar = 100.0f;
    }
    
    public EulerCamera(final float aspectRatio) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
        this.roll = 0.0f;
        this.fov = 90.0f;
        this.aspectRatio = 1.0f;
        if (aspectRatio <= 0.0f) {
            throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
        }
        this.aspectRatio = aspectRatio;
        this.zNear = 0.3f;
        this.zFar = 100.0f;
    }
    
    public EulerCamera(final float aspectRatio, final float x, final float y, final float z) {
        this(aspectRatio);
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public EulerCamera(final float aspectRatio, final float x, final float y, final float z, final float pitch, final float yaw, final float roll) {
        this(aspectRatio, x, y, z);
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }
    
    public EulerCamera(final float aspectRatio, final float x, final float y, final float z, final float pitch, final float yaw, final float roll, final float zNear, final float zFar) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
        this.roll = 0.0f;
        this.fov = 90.0f;
        this.aspectRatio = 1.0f;
        if (aspectRatio <= 0.0f) {
            throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
        }
        if (zNear <= 0.0f) {
            throw new IllegalArgumentException("zNear " + zNear + " was 0 or was smaller than 0");
        }
        if (zFar <= zNear) {
            throw new IllegalArgumentException("zFar " + zFar + " was smaller or the same as zNear " + zNear);
        }
        this.aspectRatio = aspectRatio;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
        this.zNear = zNear;
        this.zFar = zFar;
    }
    
    public void processMouse() {
        final float MAX_LOOK_UP = 90.0f;
        final float MAX_LOOK_DOWN = -90.0f;
        final float mouseDX = Mouse.getDX() * 0.16f;
        final float mouseDY = Mouse.getDY() * 0.16f;
        if (this.yaw + mouseDX >= 360.0f) {
            this.yaw = this.yaw + mouseDX - 360.0f;
        }
        else if (this.yaw + mouseDX < 0.0f) {
            this.yaw = 360.0f - this.yaw + mouseDX;
        }
        else {
            this.yaw += mouseDX;
        }
        if (this.pitch - mouseDY >= -90.0f && this.pitch - mouseDY <= 90.0f) {
            this.pitch += -mouseDY;
        }
        else if (this.pitch - mouseDY < -90.0f) {
            this.pitch = -90.0f;
        }
        else if (this.pitch - mouseDY > 90.0f) {
            this.pitch = 90.0f;
        }
    }
    
    public void processMouse(final float mouseSpeed) {
        final float MAX_LOOK_UP = 90.0f;
        final float MAX_LOOK_DOWN = -90.0f;
        final float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
        final float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
        if (this.yaw + mouseDX >= 360.0f) {
            this.yaw = this.yaw + mouseDX - 360.0f;
        }
        else if (this.yaw + mouseDX < 0.0f) {
            this.yaw = 360.0f - this.yaw + mouseDX;
        }
        else {
            this.yaw += mouseDX;
        }
        if (this.pitch - mouseDY >= -90.0f && this.pitch - mouseDY <= 90.0f) {
            this.pitch += -mouseDY;
        }
        else if (this.pitch - mouseDY < -90.0f) {
            this.pitch = -90.0f;
        }
        else if (this.pitch - mouseDY > 90.0f) {
            this.pitch = 90.0f;
        }
    }
    
    public void processMouse(final float mouseSpeed, final float maxLookUp, final float maxLookDown) {
        final float mouseDX = Mouse.getDX() * mouseSpeed * 0.16f;
        final float mouseDY = Mouse.getDY() * mouseSpeed * 0.16f;
        if (this.yaw + mouseDX >= 360.0f) {
            this.yaw = this.yaw + mouseDX - 360.0f;
        }
        else if (this.yaw + mouseDX < 0.0f) {
            this.yaw = 360.0f - this.yaw + mouseDX;
        }
        else {
            this.yaw += mouseDX;
        }
        if (this.pitch - mouseDY >= maxLookDown && this.pitch - mouseDY <= maxLookUp) {
            this.pitch += -mouseDY;
        }
        else if (this.pitch - mouseDY < maxLookDown) {
            this.pitch = maxLookDown;
        }
        else if (this.pitch - mouseDY > maxLookUp) {
            this.pitch = maxLookUp;
        }
    }
    
    public void processKeyboard(final float delta) {
        if (delta <= 0.0f) {
            throw new IllegalArgumentException("delta " + delta + " is 0 or is smaller than 0");
        }
        final boolean keyUp = Keyboard.isKeyDown(200) || Keyboard.isKeyDown(17);
        final boolean keyDown = Keyboard.isKeyDown(208) || Keyboard.isKeyDown(31);
        final boolean keyLeft = Keyboard.isKeyDown(203) || Keyboard.isKeyDown(30);
        final boolean keyRight = Keyboard.isKeyDown(205) || Keyboard.isKeyDown(32);
        final boolean flyUp = Keyboard.isKeyDown(57);
        final boolean flyDown = Keyboard.isKeyDown(42);
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            this.moveFromLook(delta * 0.003f, 0.0f, -delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(-delta * 0.003f, 0.0f, -delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(0.0f, 0.0f, -delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            this.moveFromLook(-delta * 0.003f, 0.0f, delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            this.moveFromLook(delta * 0.003f, 0.0f, delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            this.moveFromLook(0.0f, 0.0f, delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            this.moveFromLook(-delta * 0.003f, 0.0f, 0.0f);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            this.moveFromLook(delta * 0.003f, 0.0f, 0.0f);
        }
        if (flyUp && !flyDown) {
            this.y += delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            this.y -= delta * 0.003f;
        }
    }
    
    public void processKeyboard(final float delta, final float speed) {
        if (delta <= 0.0f) {
            throw new IllegalArgumentException("delta " + delta + " is 0 or is smaller than 0");
        }
        final boolean keyUp = Keyboard.isKeyDown(200) || Keyboard.isKeyDown(17);
        final boolean keyDown = Keyboard.isKeyDown(208) || Keyboard.isKeyDown(31);
        final boolean keyLeft = Keyboard.isKeyDown(203) || Keyboard.isKeyDown(30);
        final boolean keyRight = Keyboard.isKeyDown(205) || Keyboard.isKeyDown(32);
        final boolean flyUp = Keyboard.isKeyDown(57);
        final boolean flyDown = Keyboard.isKeyDown(42);
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            this.moveFromLook(speed * delta * 0.003f, 0.0f, -speed * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(-speed * delta * 0.003f, 0.0f, -speed * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(0.0f, 0.0f, -speed * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            this.moveFromLook(-speed * delta * 0.003f, 0.0f, speed * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            this.moveFromLook(speed * delta * 0.003f, 0.0f, speed * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            this.moveFromLook(0.0f, 0.0f, speed * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            this.moveFromLook(-speed * delta * 0.003f, 0.0f, 0.0f);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            this.moveFromLook(speed * delta * 0.003f, 0.0f, 0.0f);
        }
        if (flyUp && !flyDown) {
            this.y += speed * delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            this.y -= speed * delta * 0.003f;
        }
    }
    
    public void processKeyboard(final float delta, final float speedX, final float speedY, final float speedZ) {
        if (delta <= 0.0f) {
            throw new IllegalArgumentException("delta " + delta + " is 0 or is smaller than 0");
        }
        final boolean keyUp = Keyboard.isKeyDown(200) || Keyboard.isKeyDown(17);
        final boolean keyDown = Keyboard.isKeyDown(208) || Keyboard.isKeyDown(31);
        final boolean keyLeft = Keyboard.isKeyDown(203) || Keyboard.isKeyDown(30);
        final boolean keyRight = Keyboard.isKeyDown(205) || Keyboard.isKeyDown(32);
        final boolean flyUp = Keyboard.isKeyDown(57);
        final boolean flyDown = Keyboard.isKeyDown(42);
        if (keyUp && keyRight && !keyLeft && !keyDown) {
            this.moveFromLook(speedX * delta * 0.003f, 0.0f, -speedZ * delta * 0.003f);
        }
        if (keyUp && keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(-speedX * delta * 0.003f, 0.0f, -speedZ * delta * 0.003f);
        }
        if (keyUp && !keyLeft && !keyRight && !keyDown) {
            this.moveFromLook(0.0f, 0.0f, -speedZ * delta * 0.003f);
        }
        if (keyDown && keyLeft && !keyRight && !keyUp) {
            this.moveFromLook(-speedX * delta * 0.003f, 0.0f, speedZ * delta * 0.003f);
        }
        if (keyDown && keyRight && !keyLeft && !keyUp) {
            this.moveFromLook(speedX * delta * 0.003f, 0.0f, speedZ * delta * 0.003f);
        }
        if (keyDown && !keyUp && !keyLeft && !keyRight) {
            this.moveFromLook(0.0f, 0.0f, speedZ * delta * 0.003f);
        }
        if (keyLeft && !keyRight && !keyUp && !keyDown) {
            this.moveFromLook(-speedX * delta * 0.003f, 0.0f, 0.0f);
        }
        if (keyRight && !keyLeft && !keyUp && !keyDown) {
            this.moveFromLook(speedX * delta * 0.003f, 0.0f, 0.0f);
        }
        if (flyUp && !flyDown) {
            this.y += speedY * delta * 0.003f;
        }
        if (flyDown && !flyUp) {
            this.y -= speedY * delta * 0.003f;
        }
    }
    
    public void moveFromLook(final float dx, final float dy, final float dz) {
        this.z += (float)(dx * (float)Math.cos(Math.toRadians(this.yaw - 90.0f)) + dz * Math.cos(Math.toRadians(this.yaw)));
        this.x -= (float)(dx * (float)Math.sin(Math.toRadians(this.yaw - 90.0f)) + dz * Math.sin(Math.toRadians(this.yaw)));
        this.y += (float)(dy * (float)Math.sin(Math.toRadians(this.pitch - 90.0f)) + dz * Math.sin(Math.toRadians(this.pitch)));
    }
    
    public void setPosition(final float x, final float y, final float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void applyOrthographicMatrix() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)(-this.aspectRatio), (double)this.aspectRatio, -1.0, 1.0, 0.0, (double)this.zFar);
        GL11.glPopAttrib();
    }
    
    public void applyOptimalStates() {
        if (GLContext.getCapabilities().GL_ARB_depth_clamp) {
            GL11.glEnable(34383);
        }
    }
    
    public void applyPerspectiveMatrix() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GLU.gluPerspective(this.fov, this.aspectRatio, this.zNear, this.zFar);
        GL11.glPopAttrib();
    }
    
    public void applyTranslations() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5888);
        GL11.glRotatef(this.pitch, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(this.yaw, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.roll, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-this.x, -this.y, -this.z);
        GL11.glPopAttrib();
    }
    
    public void setRotation(final float pitch, final float yaw, final float roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }
    
    public float x() {
        return this.x;
    }
    
    public float y() {
        return this.y;
    }
    
    public float z() {
        return this.z;
    }
    
    public float pitch() {
        return this.pitch;
    }
    
    public float yaw() {
        return this.yaw;
    }
    
    public float roll() {
        return this.roll;
    }
    
    public float fieldOfView() {
        return this.fov;
    }
    
    public void setFieldOfView(final float fov) {
        this.fov = fov;
    }
    
    public void setAspectRatio(final float aspectRatio) {
        if (aspectRatio <= 0.0f) {
            throw new IllegalArgumentException("aspectRatio " + aspectRatio + " is 0 or less");
        }
        this.aspectRatio = aspectRatio;
    }
    
    public float aspectRatio() {
        return this.aspectRatio;
    }
    
    public float nearClippingPane() {
        return this.zNear;
    }
    
    public float farClippingPane() {
        return this.zFar;
    }
    
    @Override
    public String toString() {
        return "EulerCamera [x=" + this.x + ", y=" + this.y + ", z=" + this.z + ", pitch=" + this.pitch + ", yaw=" + this.yaw + ", roll=" + this.roll + ", fov=" + this.fov + ", aspectRatio=" + this.aspectRatio + ", zNear=" + this.zNear + ", zFar=" + this.zFar + "]";
    }
    
    public static class Builder
    {
        private float aspectRatio;
        private float x;
        private float y;
        private float z;
        private float pitch;
        private float yaw;
        private float roll;
        private float zNear;
        private float zFar;
        private float fov;
        
        public Builder() {
            this.aspectRatio = 1.0f;
            this.x = 0.0f;
            this.y = 0.0f;
            this.z = 0.0f;
            this.pitch = 0.0f;
            this.yaw = 0.0f;
            this.roll = 0.0f;
            this.zNear = 0.3f;
            this.zFar = 100.0f;
            this.fov = 90.0f;
        }
        
        public Builder setAspectRatio(final float aspectRatio) {
            if (aspectRatio <= 0.0f) {
                throw new IllegalArgumentException("aspectRatio " + aspectRatio + " was 0 or was smaller than 0");
            }
            this.aspectRatio = aspectRatio;
            return this;
        }
        
        public Builder setNearClippingPane(final float nearClippingPane) {
            if (nearClippingPane <= 0.0f) {
                throw new IllegalArgumentException("nearClippingPane " + nearClippingPane + " is 0 or less");
            }
            this.zNear = nearClippingPane;
            return this;
        }
        
        public Builder setFarClippingPane(final float farClippingPane) {
            if (farClippingPane <= 0.0f) {
                throw new IllegalArgumentException("farClippingPane " + farClippingPane + " is 0 or less");
            }
            this.zFar = farClippingPane;
            return this;
        }
        
        public Builder setFieldOfView(final float fov) {
            this.fov = fov;
            return this;
        }
        
        public Builder setPosition(final float x, final float y, final float z) {
            this.x = x;
            this.y = y;
            this.z = z;
            return this;
        }
        
        public Builder setRotation(final float pitch, final float yaw, final float roll) {
            this.pitch = pitch;
            this.yaw = yaw;
            this.roll = roll;
            return this;
        }
        
        public EulerCamera build() {
            if (this.zFar <= this.zNear) {
                throw new IllegalArgumentException("farClippingPane " + this.zFar + " is the same or less than nearClippingPane " + this.zNear);
            }
            return new EulerCamera(this, null);
        }
    }
}
