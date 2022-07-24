//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import org.newdawn.slick.opengl.renderer.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.*;

public abstract class SlickCallable
{
    private static Texture lastUsed;
    private static boolean inSafe;
    
    public static void enterSafeBlock() {
        if (SlickCallable.inSafe) {
            return;
        }
        Renderer.get().flush();
        SlickCallable.lastUsed = TextureImpl.getLastBind();
        TextureImpl.bindNone();
        GL11.glPushAttrib(1048575);
        GL11.glPushClientAttrib(-1);
        GL11.glMatrixMode(5888);
        GL11.glPushMatrix();
        GL11.glMatrixMode(5889);
        GL11.glPushMatrix();
        GL11.glMatrixMode(5888);
        SlickCallable.inSafe = true;
    }
    
    public static void leaveSafeBlock() {
        if (!SlickCallable.inSafe) {
            return;
        }
        GL11.glMatrixMode(5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode(5888);
        GL11.glPopMatrix();
        GL11.glPopClientAttrib();
        GL11.glPopAttrib();
        if (SlickCallable.lastUsed != null) {
            SlickCallable.lastUsed.bind();
        }
        else {
            TextureImpl.bindNone();
        }
        SlickCallable.inSafe = false;
    }
    
    public final void call() throws SlickException {
        enterSafeBlock();
        this.performGLOperations();
        leaveSafeBlock();
    }
    
    protected abstract void performGLOperations() throws SlickException;
    
    static {
        SlickCallable.inSafe = false;
    }
}
