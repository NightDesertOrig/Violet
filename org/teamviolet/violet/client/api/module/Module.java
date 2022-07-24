//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.module;

import net.minecraft.client.*;
import org.teamviolet.violet.client.util.misc.*;
import java.util.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.*;
import net.minecraftforge.common.*;
import org.teamviolet.violet.client.impl.module.client.*;
import com.mojang.realmsclient.gui.*;
import org.teamviolet.violet.client.util.game.*;
import java.lang.annotation.*;

public abstract class Module
{
    protected final String name;
    protected final String description;
    protected final String id;
    protected final Category category;
    protected String info;
    protected final List<Value<?>> valueList;
    protected boolean enabled;
    protected boolean alwaysEnabled;
    protected boolean open;
    protected final Minecraft mc;
    protected final Value<Boolean> hidden;
    protected final Value<Bind> bind;
    
    protected Module() {
        this.info = "";
        this.valueList = new ArrayList<Value<?>>();
        this.enabled = false;
        this.alwaysEnabled = false;
        this.open = false;
        this.mc = Minecraft.getMinecraft();
        this.hidden = ValueFactory.booleanValue().withName("Hidden").withVal(false).withDesc("Hides the module from the ArrayList and stops showing toggle messages.").build(this);
        this.bind = ValueFactory.bindValue().withName("Bind").withVal(new Bind(0)).build(this);
        final Manifest manifest = this.getClass().getAnnotation(Manifest.class);
        if (manifest == null) {
            throw new NullPointerException();
        }
        this.category = manifest.value();
        this.name = (manifest.name().isEmpty() ? this.getClass().getSimpleName() : manifest.name());
        this.alwaysEnabled = manifest.alwaysEnabled();
        this.enabled = (manifest.enabledByDefault() || this.alwaysEnabled);
        this.description = manifest.description();
        this.id = this.getClass().getSimpleName().toLowerCase();
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getId() {
        return this.id;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public boolean isHidden() {
        return this.hidden.getValue();
    }
    
    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(final String info) {
        this.info = info;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void setEnabled(final boolean enabled) {
        if (this.enabled == enabled || (!enabled && this.alwaysEnabled)) {
            return;
        }
        if (this.enabled = enabled) {
            Violet.getViolet().getDispatcher().register((Object)this);
            MinecraftForge.EVENT_BUS.register((Object)this);
            this.onEnable();
            if (ChatInfo.getInstance().modules.getValue() && ChatInfo.getInstance().isEnabled() && !this.hidden.getValue()) {
                InfoUtil.chatInfo("Enabled " + ChatFormatting.BOLD + this.name + ChatFormatting.RESET + ".", this.hashCode());
            }
        }
        else {
            Violet.getViolet().getDispatcher().unregister((Object)this);
            MinecraftForge.EVENT_BUS.unregister((Object)this);
            this.onDisable();
            if (ChatInfo.getInstance().modules.getValue() && ChatInfo.getInstance().isEnabled() && !this.hidden.getValue()) {
                InfoUtil.chatInfo("Disabled " + ChatFormatting.BOLD + this.name + ChatFormatting.RESET + ".", this.hashCode());
            }
        }
    }
    
    public Bind getBind() {
        return this.bind.getValue();
    }
    
    public List<Value<?>> getValueList() {
        return this.valueList;
    }
    
    protected void onEnable() {
    }
    
    protected void onDisable() {
    }
    
    public enum Category
    {
        COMBAT, 
        MOVEMENT, 
        PLAYER, 
        RENDER, 
        CLIENT, 
        HUD;
        
        private boolean open;
        
        private Category() {
            this.open = true;
        }
        
        public boolean isOpen() {
            return this.open;
        }
        
        public void setOpen(final boolean open) {
            this.open = open;
        }
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    public @interface Manifest {
        Category value();
        
        String name() default "";
        
        boolean enabledByDefault() default false;
        
        boolean alwaysEnabled() default false;
        
        String description() default "No description.";
    }
}
