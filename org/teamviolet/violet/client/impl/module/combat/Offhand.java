//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import org.lwjgl.input.*;

@Module.Manifest(Module.Category.COMBAT)
public class Offhand extends Module
{
    private final Value<ItemChoice> item;
    private final Value<Double> minHP;
    private final Value<Double> holeHP;
    private final Value<Integer> delay;
    private final Value<Boolean> searchHotbar;
    private final Value<Boolean> gapSwitch;
    private final Value<Boolean> gapSwitchStopOnCA;
    private final Value<Boolean> gapSwitchOnSword;
    private final Value<Boolean> gapSwitchOnRightClick;
    private int waited;
    
    public Offhand() {
        this.item = (Value<ItemChoice>)new ValueFactory().withName("Item").withVal((Object)ItemChoice.Crystal).build((Module)this);
        this.minHP = (Value<Double>)ValueFactory.doubleValue().withName("MinHP").withVal((Object)12.0).withBounds(0.1f, 36.0f, 1).build((Module)this);
        this.holeHP = (Value<Double>)ValueFactory.doubleValue().withName("MinHoleHP").withVal((Object)6.0).withBounds(0.1f, 36.0f, 1).build((Module)this);
        this.delay = (Value<Integer>)ValueFactory.intValue().withName("Delay").withVal((Object)0).withBounds(0.0f, 10.0f, 0).build((Module)this);
        this.searchHotbar = (Value<Boolean>)ValueFactory.booleanValue().withName("SearchHotbar").withVal((Object)false).build((Module)this);
        this.gapSwitch = (Value<Boolean>)ValueFactory.booleanValue().withName("GapSwitch").withVal((Object)true).build((Module)this);
        this.gapSwitchStopOnCA = (Value<Boolean>)ValueFactory.booleanValue().withName("StopOnCA").withVal((Object)true).build((Value)this.gapSwitch);
        this.gapSwitchOnSword = (Value<Boolean>)ValueFactory.booleanValue().withName("OnSword").withVal((Object)true).build((Value)this.gapSwitch);
        this.gapSwitchOnRightClick = (Value<Boolean>)ValueFactory.booleanValue().withName("OnRightClick").withVal((Object)true).build((Value)this.gapSwitch);
        this.waited = 0;
    }
    
    protected void onEnable() {
        this.waited = (int)this.delay.getValue();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (this.waited++ < (int)this.delay.getValue()) {
            return;
        }
        final Item item = this.getItem();
        if (Utils.player().getHeldItemOffhand().getItem() == item) {
            return;
        }
        if (InventoryUtil.putInOffhand(item, (boolean)this.searchHotbar.getValue())) {
            this.waited = 0;
        }
    }
    
    private Item getItem() {
        if (EntityUtil.getHealth((EntityLivingBase)Utils.player()) < (double)(BlockUtil.isSurrounded(EntityUtil.getEntityPos((Entity)Utils.player())) ? this.holeHP.getValue() : ((Double)this.minHP.getValue()))) {
            return Items.TOTEM_OF_UNDYING;
        }
        if ((boolean)this.gapSwitch.getValue() && (!(boolean)this.gapSwitchStopOnCA.getValue() || !AutoCrystal.getInstance().isBusy())) {
            if ((boolean)this.gapSwitchOnSword.getValue() && Utils.player().getHeldItemMainhand().getItem() instanceof ItemSword) {
                return Items.GOLDEN_APPLE;
            }
            if ((boolean)this.gapSwitchOnRightClick.getValue() && Mouse.isButtonDown(1)) {
                return Items.GOLDEN_APPLE;
            }
        }
        return ((ItemChoice)this.item.getValue()).item;
    }
    
    public enum ItemChoice
    {
        Crystal(Items.END_CRYSTAL), 
        GoldenApple(Items.GOLDEN_APPLE), 
        Totem(Items.TOTEM_OF_UNDYING);
        
        private final Item item;
        
        private ItemChoice(final Item item) {
            this.item = item;
        }
    }
}
