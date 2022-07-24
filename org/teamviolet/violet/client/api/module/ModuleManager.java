//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.module;

import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.impl.module.combat.*;
import org.teamviolet.violet.client.impl.module.movement.*;
import org.teamviolet.violet.client.impl.module.player.*;
import org.teamviolet.violet.client.impl.module.render.*;
import org.teamviolet.violet.client.impl.module.hud.*;
import net.minecraftforge.common.*;
import java.util.*;
import javax.annotation.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;

public final class ModuleManager
{
    private final List<Module> moduleList;
    
    public ModuleManager() {
        this.moduleList = new ArrayList<Module>();
    }
    
    public void init() {
        this.moduleList.add(HUDEditor.getInstance());
        this.moduleList.add(ChatInfo.getInstance());
        this.moduleList.add(ClickGui.getInstance());
        this.moduleList.add(CustomFont.getInstance());
        this.moduleList.add(Rotations.getInstance());
        this.moduleList.add(AutoCrystal.getInstance());
        this.moduleList.add(new Offhand());
        this.moduleList.add(new Surround());
        this.moduleList.add(new GodModule());
        this.moduleList.add(new PistonAura());
        this.moduleList.add(new AutoCity());
        this.moduleList.add(new AntiLava());
        this.moduleList.add(new InstantSpeed());
        this.moduleList.add(new NoVoid());
        this.moduleList.add(new Sprint());
        this.moduleList.add(new Step());
        this.moduleList.add(new Warp());
        this.moduleList.add(new AntiArmor());
        this.moduleList.add(new ClickPearl());
        this.moduleList.add(new FastUse());
        this.moduleList.add(new MiddleClickFriend());
        this.moduleList.add(new SecretClose());
        this.moduleList.add(new SelfXP());
        this.moduleList.add(new StashLogger());
        this.moduleList.add(new Velocity());
        this.moduleList.add(new NoOpen());
        this.moduleList.add(new BetterShulkers());
        this.moduleList.add(NoHitbox.getInstance());
        this.moduleList.add(new BlockHighlight());
        this.moduleList.add(new Chams());
        this.moduleList.add(new PigCrystal());
        this.moduleList.add(PopChams.getInstance());
        this.moduleList.add(new Shaders());
        this.moduleList.add(ViewModel.getInstance());
        this.moduleList.add(new Trails());
        this.moduleList.add(new HoleESP());
        this.moduleList.add(new Environment());
        this.moduleList.add(new GuiBlur());
        this.moduleList.add(Swing.getInstance());
        this.moduleList.add(RenderTweaks.getInstance());
        this.moduleList.add(new Lightning());
        this.moduleList.add((Module)new Watermark());
        this.moduleList.add((Module)new Welcomer());
        this.moduleList.add((Module)new ModuleList());
        this.moduleList.add((Module)new TextRadar());
        this.moduleList.sort(ModuleManager::order);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    private static int order(final Module m1, final Module m2) {
        return m1.getName().compareTo(m2.getName());
    }
    
    public List<Module> getModuleList() {
        return this.moduleList;
    }
    
    @Nullable
    public Module getModule(final String name) {
        for (final Module m : this.moduleList) {
            if (m.getId().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    @SubscribeEvent
    public void listen(final InputEvent.KeyInputEvent event) {
        for (final Module m : this.moduleList) {
            if (m.getBind().getOutput()) {
                m.setEnabled(!m.isEnabled());
            }
        }
    }
}
