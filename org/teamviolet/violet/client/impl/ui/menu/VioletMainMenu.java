//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.ui.menu;

import net.minecraft.util.*;
import org.teamviolet.violet.client.impl.ui.menu.button.*;
import java.io.*;
import org.teamviolet.violet.client.util.render.font.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import org.teamviolet.violet.client.util.render.*;
import java.awt.*;

public class VioletMainMenu extends GuiScreen
{
    private final ResourceLocation background;
    private final ResourceLocation account;
    private final ResourceLocation singleplayer;
    private final ResourceLocation multiplayer;
    private final ResourceLocation options;
    private FontRenderer menu;
    private FontRenderer btn;
    private ImageButton singleplayerButton;
    private ImageButton multiplayerButton;
    private ImageButton optionButton;
    private ImageButton accountButton;
    private ImageButton quitButton;
    
    public VioletMainMenu() throws IOException, FontFormatException {
        this.background = new ResourceLocation("textures/violet/images/space.png");
        this.account = new ResourceLocation("textures/violet/images/account.png");
        this.singleplayer = new ResourceLocation("textures/violet/images/singleplayer.png");
        this.multiplayer = new ResourceLocation("textures/violet/images/multiplayer.png");
        this.options = new ResourceLocation("textures/violet/images/options.png");
    }
    
    public void initGui() {
        try {
            this.menu = new FontRenderer(FontUtil.getFontByName("ProductSans").deriveFont(30.0f), true, true);
            this.btn = new FontRenderer(FontUtil.getFontByName("ProductSans").deriveFont(22.0f), true, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (FontFormatException e2) {
            e2.printStackTrace();
        }
        this.singleplayerButton = new ImageButton(0, this.width / 2 - 91, this.height / 2 - this.height / 4 + 30, 182, 30, "Singleplayer", this.singleplayer, this.btn, this) {
            public void onClicked() {
                VioletMainMenu.this.mc.displayGuiScreen((GuiScreen)new GuiWorldSelection(this.screen));
            }
        };
        this.buttonList.add(this.singleplayerButton);
        this.multiplayerButton = new ImageButton(0, this.width / 2 - 91, this.height / 2 - this.height / 4 + 66, 88, 30, "Multiplayer", this.multiplayer, this.btn, this) {
            public void onClicked() {
                VioletMainMenu.this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer(this.screen));
            }
        };
        this.buttonList.add(this.multiplayerButton);
        this.optionButton = new ImageButton(0, this.width / 2 + 91 - 88, this.height / 2 - this.height / 4 + 66, 88, 30, "Options", this.multiplayer, this.btn, this) {
            public void onClicked() {
                VioletMainMenu.this.mc.displayGuiScreen((GuiScreen)new GuiOptions(this.screen, VioletMainMenu.this.mc.gameSettings));
            }
        };
        this.buttonList.add(this.optionButton);
        this.accountButton = new ImageButton(0, this.width / 2 - 91, this.height / 2 - this.height / 4 + 102, 88, 30, "Violet Account", this.multiplayer, this.btn, this) {
            public void onClicked() {
            }
        };
        this.buttonList.add(this.accountButton);
        this.quitButton = new ImageButton(0, this.width / 2 + 91 - 88, this.height / 2 - this.height / 4 + 102, 88, 30, "Quit", this.multiplayer, this.btn, this) {
            public void onClicked() {
                VioletMainMenu.this.mc.shutdown();
            }
        };
        this.buttonList.add(this.quitButton);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        Render2D.drawImageSimple(this.background, new Vec2f(0.0f, 0.0f), new Vec2f((float)this.width, (float)this.height));
        this.menu.drawCenteredString("Violet Client!", (float)(this.width / 2), (float)(this.height / 2 - this.height / 4), Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
