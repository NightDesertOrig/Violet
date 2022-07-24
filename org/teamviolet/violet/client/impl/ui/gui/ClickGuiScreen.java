//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.gui;

import org.teamviolet.violet.client.api.gui.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.impl.ui.gui.elements.*;
import java.util.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.render.*;
import org.lwjgl.input.*;
import org.teamviolet.violet.client.util.misc.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import java.io.*;

public class ClickGuiScreen extends GuiScreen
{
    private List<GuiPanel> panels;
    private Vec2f edgeSpace;
    private int panelSpacing;
    private int elementWidth;
    private int elementHeight;
    private Timer timer;
    private boolean open;
    private static ClickGuiScreen instance;
    
    public ClickGuiScreen() {
        this.timer = new Timer();
        this.open = false;
        this.panels = new ArrayList<GuiPanel>();
        this.edgeSpace = new Vec2f(30.0f, 20.0f);
        this.panelSpacing = 15;
        this.elementWidth = 130;
        this.elementHeight = 15;
        this.initPanels();
    }
    
    private void initPanels() {
        int x = (int)this.edgeSpace.x;
        final int y = (int)this.edgeSpace.y;
        for (final Module.Category category : Module.Category.values()) {
            if (category != Module.Category.HUD) {
                final GuiPanelCategory panel = new GuiPanelCategory(category, x, y, this.elementWidth, this.elementHeight);
                panel.initElements();
                this.panels.add(panel);
                x += this.panelSpacing + this.elementWidth;
            }
        }
    }
    
    public boolean handleDrag(final GuiPanel panel) {
        for (final GuiPanel guiPanel : this.panels) {
            if (guiPanel.dragging && guiPanel != panel) {
                return false;
            }
        }
        return true;
    }
    
    private void renderBackground() {
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f(0.0f, 0.0f), new Vec2f((float)this.width, (float)this.height), new Color(2032543270, true));
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (!this.open) {
            this.timer.reset();
            this.open = true;
        }
        this.screenOutline();
        GlStateManager.pushMatrix();
        GlStateHelper.scale(1.0f * MathUtil.clamp(this.timer.getTime() / ((float)ClickGui.getInstance().openSpeed.getValue() * 1000.0f), 0.0f, 1.0f));
        final int scroll = Mouse.getDWheel();
        int boost = 0;
        if (scroll < 0) {
            boost = -8;
        }
        else if (scroll > 0) {
            boost = 8;
        }
        for (final GuiPanel panel : this.panels) {
            panel.setPosition(panel.x, panel.y + boost);
            panel.drawElement(mouseX, mouseY);
        }
        GlStateManager.popMatrix();
        MouseKeysTracker.updateMousePos(mouseX, mouseY);
    }
    
    private void screenOutline() {
        if (ClickGui.getInstance().backgroundFill.getValue()) {
            Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f(0.0f, 0.0f), new Vec2f((float)this.width, (float)this.height), new Color(63, 63, 63, (int)(110.0f * MathUtil.clamp(this.timer.getTime() / ((float)ClickGui.getInstance().openSpeed.getValue() * 1000.0f), 0.0f, 1.0f))));
        }
        if (ClickGui.getInstance().screenOutline.getValue()) {
            Render2D.drawRectOutline(Render2D.vertexHelperUB, new Vec2f(0.0f, 0.0f), new Vec2f((float)this.width, (float)this.height), 15.0f, new Color(((Color)ClickGui.getInstance().accentColor.getValue()).getRed(), ((Color)ClickGui.getInstance().accentColor.getValue()).getGreen(), ((Color)ClickGui.getInstance().accentColor.getValue()).getBlue(), (int)MathUtil.clamp(255.0f * MathUtil.clamp(this.timer.getTime() / ((float)ClickGui.getInstance().openSpeed.getValue() * 1000.0f), 0.0f, 1.0f), 0.0f, 255.0f)));
        }
    }
    
    private void scissorTest() {
        GL11.glPushMatrix();
        GlStateHelper.enableScissor();
        Render2D.betterScissor(10.0f, 10.0f, 100.0f, 100.0f, new ScaledResolution(this.mc));
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f(10.0f, 10.0f), new Vec2f(100.0f, 100.0f), Color.WHITE);
        Render2D.disableScissor();
        GL11.glPushMatrix();
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            MouseKeysTracker.updateLeftClick();
        }
        if (mouseButton == 1) {
            MouseKeysTracker.updateRightClick();
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int state) {
        super.mouseReleased(mouseX, mouseY, state);
        if (state == 0) {
            MouseKeysTracker.updateMouseState();
        }
    }
    
    public void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        MouseKeysTracker.updateKeyState(keyCode);
    }
    
    public void onGuiClosed() {
        this.open = false;
        ClickGui.getInstance().setEnabled(false);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public static ClickGuiScreen getInstance() {
        if (ClickGuiScreen.instance == null) {
            ClickGuiScreen.instance = new ClickGuiScreen();
        }
        return ClickGuiScreen.instance;
    }
}
