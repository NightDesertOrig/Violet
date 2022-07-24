//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import net.minecraft.entity.passive.*;
import org.teamviolet.violet.client.api.value.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;

@Module.Manifest(Module.Category.RENDER)
public class PigCrystal extends Module
{
    private final Value<Double> rotateSpeed;
    private EntityPig pig;
    private float yaw;
    
    public PigCrystal() {
        this.rotateSpeed = (Value<Double>)ValueFactory.doubleValue().withName("Rotate Speed").withVal((Object)1.0).withBounds(0.1f, 10.0f, 1).build((Module)this);
        this.yaw = 0.0f;
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        if (this.pig == null) {
            return;
        }
        final EntityPig pig = this.pig;
        final EntityPig pig2 = this.pig;
        final float yaw = this.yaw;
        pig2.prevRotationYawHead = yaw;
        pig.prevRenderYawOffset = yaw;
        this.yaw += (float)(double)this.rotateSpeed.getValue();
        while (this.yaw > 180.0f) {
            this.yaw -= 360.0f;
        }
        final EntityPig pig3 = this.pig;
        final EntityPig pig4 = this.pig;
        final float yaw2 = this.yaw;
        pig4.rotationYawHead = yaw2;
        pig3.renderYawOffset = yaw2;
    }
    
    @Listener
    public void listen(final RenderEnderCrystalEvent.Pre event) {
        event.cancel();
        if (this.pig == null || this.pig.world != Utils.world()) {
            this.pig = new EntityPig((World)Utils.world());
            Utils.world().addEntityToWorld(this.pig.getEntityId(), (Entity)this.pig);
        }
        final Render<EntityPig> pigRender = (Render<EntityPig>)this.mc.getRenderManager().getEntityRenderObject((Entity)this.pig);
        if (pigRender != null) {
            pigRender.doRender((Entity)this.pig, event.getX(), event.getY(), event.getZ(), this.yaw, this.mc.getRenderPartialTicks());
        }
    }
}
