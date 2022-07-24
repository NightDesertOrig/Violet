//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.impl.module.client.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import org.teamviolet.violet.client.api.event.handler.*;

@Module.Manifest(Module.Category.RENDER)
public class GuiBlur extends Module
{
    public void onDisable() {
        if (this.mc.world != null) {
            this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
        }
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (!Utils.nullCheck()) {
            if (ClickGui.getInstance().isEnabled() || this.mc.currentScreen instanceof GuiContainer || this.mc.currentScreen instanceof GuiChat || this.mc.currentScreen instanceof GuiConfirmOpenLink || this.mc.currentScreen instanceof GuiEditSign || this.mc.currentScreen instanceof GuiGameOver || this.mc.currentScreen instanceof GuiOptions || this.mc.currentScreen instanceof GuiIngameMenu || this.mc.currentScreen instanceof GuiVideoSettings || this.mc.currentScreen instanceof GuiScreenOptionsSounds || this.mc.currentScreen instanceof GuiControls || this.mc.currentScreen instanceof GuiCustomizeSkin || this.mc.currentScreen instanceof GuiModList) {
                if (OpenGlHelper.shadersSupported && this.mc.getRenderViewEntity() instanceof EntityPlayer) {
                    if (this.mc.entityRenderer.getShaderGroup() != null) {
                        this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                    }
                    try {
                        this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (this.mc.entityRenderer.getShaderGroup() != null && this.mc.currentScreen == null) {
                    this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
                }
            }
            else if (this.mc.entityRenderer.getShaderGroup() != null) {
                this.mc.entityRenderer.getShaderGroup().deleteShaderGroup();
            }
        }
    }
}
