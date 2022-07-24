//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.hudeditor;

import net.minecraft.client.gui.*;
import org.teamviolet.violet.client.api.gui.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.impl.ui.hudeditor.elements.*;
import org.teamviolet.violet.client.impl.ui.gui.elements.*;
import java.util.*;
import org.teamviolet.violet.client.util.render.*;
import java.awt.*;
import org.teamviolet.violet.client.util.misc.*;
import java.io.*;
import org.teamviolet.violet.client.impl.module.client.*;

public class ClickHudScreen extends GuiScreen
{
    private List<GuiPanel> panels;
    private Vec2f edgeSpace;
    private int panelSpacing;
    private int elementWidth;
    private int elementHeight;
    public HudModule dragging;
    private static ClickHudScreen instance;
    
    public ClickHudScreen() {
        this.dragging = null;
        this.panels = new ArrayList<GuiPanel>();
        this.edgeSpace = new Vec2f(30.0f, 20.0f);
        this.panelSpacing = 15;
        this.elementWidth = 130;
        this.elementHeight = 20;
        this.initPanels();
    }
    
    private void initPanels() {
        final int x = (int)this.edgeSpace.x;
        final int y = (int)this.edgeSpace.y;
        final GuiPanelCategory panel = new GuiPanelHud(Module.Category.HUD, x, y, this.elementWidth, this.elementHeight);
        panel.initElements();
        this.panels.add((GuiPanel)panel);
    }
    
    public boolean handleDrag(final GuiPanel panel) {
        for (final GuiPanel guiPanel : this.panels) {
            if (guiPanel.dragging && guiPanel != panel) {
                return false;
            }
        }
        return true;
    }
    
    public boolean handleDrag(final HudModule dragging) {
        if ((this.dragging != null && this.dragging == dragging) || this.dragging == null) {
            this.dragging = dragging;
            return true;
        }
        return false;
    }
    
    private void renderBackground() {
        Render2D.drawRectFill(Render2D.vertexHelperUB, new Vec2f(0.0f, 0.0f), new Vec2f((float)this.width, (float)this.height), new Color(2032543270, true));
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        for (final GuiPanel panel : this.panels) {
            panel.drawElement(mouseX, mouseY);
        }
        MouseKeysTracker.updateMousePos(mouseX, mouseY);
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
        HUDEditor.getInstance().setEnabled(false);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public static ClickHudScreen getInstance() {
        if (ClickHudScreen.instance == null) {
            ClickHudScreen.instance = new ClickHudScreen();
        }
        return ClickHudScreen.instance;
    }
}
