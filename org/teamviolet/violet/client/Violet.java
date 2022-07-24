//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client;

import org.teamviolet.violet.client.api.event.*;
import org.teamviolet.violet.client.api.command.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.impl.ui.menu.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.util.render.font.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.util.account.*;
import net.minecraftforge.fml.common.event.*;
import org.teamviolet.violet.client.util.misc.*;

public class Violet
{
    public static final String MOD_ID = "violet";
    public static final String MOD_NAME = "Violet";
    public static final String VERSION = "1.0.4";
    private static Violet violet;
    private final EventHandler eventHandler;
    private final EventManager eventManager;
    private final CommandManager commandManager;
    private final ModuleManager moduleManager;
    private final GearManager gearManager;
    private final BetterMenu menuManager;
    private final HoleManager holeManager;
    private final BetterRotationUtil rotationUtil;
    
    public Violet() {
        if (Violet.violet != null) {
            throw new UnsupportedOperationException();
        }
        Violet.violet = this;
        this.eventHandler = (EventHandler)new EventHandlerImpl();
        this.eventManager = new EventManager();
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
        this.gearManager = new GearManager();
        this.menuManager = new BetterMenu();
        this.holeManager = new HoleManager();
        this.rotationUtil = new BetterRotationUtil();
    }
    
    public void listen(final FMLPreInitializationEvent event) {
        this.eventManager.init();
        this.moduleManager.init();
        this.commandManager.init();
        this.gearManager.init();
        this.menuManager.init();
        this.holeManager.init();
        FontUtil.init("Lato", 17.0f);
    }
    
    public void listen(final FMLInitializationEvent event) {
        this.eventHandler.register((Class)InventoryUtil.class);
        this.eventHandler.register((Class)RotationUtil.class);
        this.eventHandler.register((Object)NotificationManager.getInstance());
        Config.load();
    }
    
    public void listen(final FMLPostInitializationEvent event) {
        WindowUtil.setTitle("Violet - 1.0.4");
        WindowUtil.setIcon();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Config.save()));
    }
    
    public CommandManager getCommandManager() {
        return this.commandManager;
    }
    
    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }
    
    public EventHandler getDispatcher() {
        return this.eventHandler;
    }
    
    public HoleManager getHoleManager() {
        return this.holeManager;
    }
    
    public BetterRotationUtil getRotationUtil() {
        return this.rotationUtil;
    }
    
    public static Violet getViolet() {
        if (Violet.violet == null) {
            Violet.violet = new Violet();
        }
        return Violet.violet;
    }
}
