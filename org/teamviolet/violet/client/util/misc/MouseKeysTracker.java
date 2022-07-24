//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.misc;

import java.awt.*;

public class MouseKeysTracker
{
    public static int mouseX;
    public static int mouseY;
    public static int keyDown;
    public static boolean leftClicked;
    public static boolean leftDown;
    public static boolean rightClicked;
    public static Rectangle scissor;
    
    public static boolean mouseOver(final int minX, final int minY, final int maxX, final int maxY) {
        return MouseKeysTracker.mouseX >= minX && MouseKeysTracker.mouseY >= minY && MouseKeysTracker.mouseX <= maxX && MouseKeysTracker.mouseY <= maxY;
    }
    
    public static void updateMousePos(final int mouseXPos, final int mouseYPos) {
        MouseKeysTracker.mouseX = mouseXPos;
        MouseKeysTracker.mouseY = mouseYPos;
        MouseKeysTracker.leftClicked = false;
        MouseKeysTracker.rightClicked = false;
        MouseKeysTracker.keyDown = 0;
    }
    
    public static void updateLeftClick() {
        MouseKeysTracker.leftClicked = true;
        MouseKeysTracker.leftDown = true;
    }
    
    public static void updateRightClick() {
        MouseKeysTracker.rightClicked = true;
    }
    
    public static void updateMouseState() {
        MouseKeysTracker.leftDown = false;
    }
    
    public static void updateKeyState(final int key) {
        MouseKeysTracker.keyDown = key;
    }
}
