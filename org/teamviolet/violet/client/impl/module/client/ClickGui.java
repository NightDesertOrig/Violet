//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import java.awt.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.misc.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.impl.ui.gui.*;
import net.minecraft.client.gui.*;

@Module.Manifest(Module.Category.CLIENT)
public class ClickGui extends Module
{
    public final Value<Color> accentColor;
    public final Value<Color> pickerColor;
    public final Value<Color> pickerBackground;
    public final Value<Color> background;
    public final Value<RectangleMode> headerMode;
    public final Value<RectangleMode> sliderMode;
    public final Value<Float> openSpeed;
    public final Value<Integer> height;
    public final Value<Boolean> backgroundFill;
    public final Value<Boolean> screenOutline;
    private static ClickGui clickGui;
    
    public static ClickGui getInstance() {
        if (ClickGui.clickGui == null) {
            ClickGui.clickGui = new ClickGui();
        }
        return ClickGui.clickGui;
    }
    
    private ClickGui() {
        this.accentColor = (Value<Color>)ValueFactory.colorValue().withName("Accent Color").withVal((Object)new Color(14377718)).build((Module)this);
        this.pickerColor = (Value<Color>)ValueFactory.colorValue().withName("Picker Color").withVal((Object)new Color(16777215)).build((Module)this);
        this.pickerBackground = (Value<Color>)ValueFactory.colorValue().withName("Picker Background").withVal((Object)new Color(1447446)).build((Module)this);
        this.background = (Value<Color>)ValueFactory.colorValue().withName("Background").withVal((Object)new Color(3552822)).build((Module)this);
        this.headerMode = (Value<RectangleMode>)new ValueFactory().withName("HeaderMode").withVal((Object)RectangleMode.Normal).build((Module)this);
        this.sliderMode = (Value<RectangleMode>)new ValueFactory().withName("SliderMode").withVal((Object)RectangleMode.Normal).build((Module)this);
        this.openSpeed = (Value<Float>)ValueFactory.floatValue().withName("OpenSpeed").withVal((Object)1.0f).withBounds(0.0f, 10.0f, 1).build((Module)this);
        this.height = (Value<Integer>)ValueFactory.intValue().withName("Height").withVal((Object)16).withBounds(14.0f, 24.0f, 0).build((Module)this);
        this.backgroundFill = (Value<Boolean>)ValueFactory.booleanValue().withName("GuiBackground").withVal((Object)false).build((Module)this);
        this.screenOutline = (Value<Boolean>)ValueFactory.booleanValue().withName("ScreenOutline").withVal((Object)false).build((Module)this);
        this.bind.setValue((Object)new Bind(24));
        this.hidden.setValue((Object)true);
    }
    
    public void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.mc.displayGuiScreen((GuiScreen)ClickGuiScreen.getInstance());
    }
    
    public enum RectangleMode
    {
        Round, 
        Normal;
    }
}
