//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;

@Module.Manifest(Module.Category.PLAYER)
public class NoHitbox extends Module
{
    public final Value<Boolean> selectional;
    public final Value<Boolean> crystals;
    public final Value<Boolean> gapples;
    public final Value<Boolean> pickaxe;
    private static NoHitbox instance;
    
    public NoHitbox() {
        this.selectional = (Value<Boolean>)ValueFactory.booleanValue().withName("OnlySome").withVal((Object)true).build((Module)this);
        this.crystals = (Value<Boolean>)ValueFactory.booleanValue().withName("Crystals").withVal((Object)false).build((Value)this.selectional);
        this.gapples = (Value<Boolean>)ValueFactory.booleanValue().withName("Gapples").withVal((Object)false).build((Value)this.selectional);
        this.pickaxe = (Value<Boolean>)ValueFactory.booleanValue().withName("Pickaxe").withVal((Object)true).build((Value)this.selectional);
    }
    
    public static NoHitbox getInstance() {
        if (NoHitbox.instance == null) {
            NoHitbox.instance = new NoHitbox();
        }
        return NoHitbox.instance;
    }
}
