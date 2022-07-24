//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.util.render.font.*;

@Module.Manifest(Module.Category.CLIENT)
public class CustomFont extends Module
{
    public final Value<Boolean> overriden;
    private Fonts fonty;
    private final Value<Float> fontSize;
    private final Value<Fonts> font;
    private static CustomFont instance;
    
    private CustomFont() {
        this.overriden = (Value<Boolean>)ValueFactory.booleanValue().withName("Override").withVal((Object)false).build((Module)this);
        this.fonty = Fonts.Lato;
        this.fontSize = (Value<Float>)ValueFactory.floatValue().withName("Size").withVal((Object)17.0f).withBounds(10.0f, 40.0f, 1).withAction((oldVal, newVal) -> {
            if (oldVal.equals(newVal)) {
                return Boolean.valueOf(false);
            }
            else {
                FontUtil.init(this.fonty.name(), newVal);
                return Boolean.valueOf(true);
            }
        }).build((Module)this);
        this.font = (Value<Fonts>)new ValueFactory().withName("Font").withVal((Object)Fonts.Lato).withBounds(10.0f, 40.0f, 1).withAction((oldVal, newVal) -> {
            if (oldVal.equals(newVal)) {
                return Boolean.valueOf(false);
            }
            else {
                this.fonty = newVal;
                FontUtil.init(newVal.name(), (float)this.fontSize.getValue());
                return Boolean.valueOf(true);
            }
        }).build((Module)this);
    }
    
    public static CustomFont getInstance() {
        if (CustomFont.instance == null) {
            CustomFont.instance = new CustomFont();
        }
        return CustomFont.instance;
    }
    
    public enum Fonts
    {
        Lato, 
        OpenSans, 
        Oxygen, 
        ProductSans, 
        Roboto;
    }
}
