//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.client;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;

@Module.Manifest(value = Module.Category.CLIENT, alwaysEnabled = true)
public class Rotations extends Module
{
    public final Value<Boolean> betterRotate;
    private static Rotations instance;
    
    public Rotations() {
        this.betterRotate = (Value<Boolean>)new ValueFactory().withName("Better").withVal((Object)false).build((Module)this);
    }
    
    public static Rotations getInstance() {
        if (Rotations.instance == null) {
            Rotations.instance = new Rotations();
        }
        return Rotations.instance;
    }
}
