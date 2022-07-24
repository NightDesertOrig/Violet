//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.combat;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.util.game.*;
import java.util.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.client.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

@Module.Manifest(Module.Category.COMBAT)
public class GodModule extends Module
{
    private final Value<Boolean> rotate;
    private final Value<Integer> rotations;
    private final Value<Boolean> render;
    private final Value<Boolean> antiIllegal;
    private final Value<Boolean> checkPos;
    private final Value<Boolean> oneThirteen;
    private final Value<Boolean> entityCheck;
    private final Value<Integer> attacks;
    private final Value<Integer> delay;
    private float yaw;
    private float pitch;
    private boolean rotating;
    private int rotationPacketsSpoofed;
    private int highestID;
    
    public GodModule() {
        this.rotate = (Value<Boolean>)new ValueFactory().withName("Rotate").withVal((Object)false).build((Module)this);
        this.rotations = (Value<Integer>)new ValueFactory().withName("RotationSpoof").withVal((Object)1).withBounds(1.0f, 20.0f, 0).build((Module)this);
        this.render = (Value<Boolean>)new ValueFactory().withName("Render").withVal((Object)false).build((Module)this);
        this.antiIllegal = (Value<Boolean>)new ValueFactory().withName("AntiIllegal").withVal((Object)true).build((Module)this);
        this.checkPos = (Value<Boolean>)new ValueFactory().withName("CheckPos").withVal((Object)true).build((Module)this);
        this.oneThirteen = (Value<Boolean>)new ValueFactory().withName("1.13").withVal((Object)false).build((Module)this);
        this.entityCheck = (Value<Boolean>)new ValueFactory().withName("EntityCheck").withVal((Object)false).build((Module)this);
        this.attacks = (Value<Integer>)new ValueFactory().withName("Attacks").withVal((Object)1).withBounds(1.0f, 10.0f, 0).build((Module)this);
        this.delay = (Value<Integer>)new ValueFactory().withName("Delay").withVal((Object)0).withBounds(1.0f, 10.0f, 0).build((Module)this);
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.highestID = -100000;
    }
    
    protected void onEnable() {
        this.reset();
    }
    
    protected void onDisable() {
        this.reset();
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (this.render.getValue()) {
            for (final Entity entity : this.mc.world.loadedEntityList) {
                if (!(entity instanceof EntityEnderCrystal)) {
                    continue;
                }
                entity.setCustomNameTag(String.valueOf(entity.getEntityId()));
                entity.setAlwaysRenderNameTag(true);
            }
        }
    }
    
    @Listener
    private void listen(final PacketEvent.Write event) {
        if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
            if (this.mc.player.getHeldItem(packet.getHand()).getItem() instanceof ItemEndCrystal) {
                if (((boolean)this.checkPos.getValue() && !BlockUtil.canPlaceCrystal(packet.getPos(), (boolean)this.entityCheck.getValue(), (boolean)this.oneThirteen.getValue(), null)) || this.checkPlayers()) {
                    return;
                }
                this.updateEntityID();
                for (int i = 1; i < (int)this.attacks.getValue(); ++i) {
                    this.attackID(packet.getPos(), this.highestID + i);
                }
            }
        }
        if (this.rotating && (boolean)this.rotate.getValue() && event.getPacket() instanceof CPacketPlayer) {
            final ICPacketPlayer packet2 = (ICPacketPlayer)event.getPacket();
            packet2.setYaw(this.yaw);
            packet2.setPitch(this.pitch);
            ++this.rotationPacketsSpoofed;
            if (this.rotationPacketsSpoofed >= (int)this.rotations.getValue()) {
                this.rotating = false;
                this.rotationPacketsSpoofed = 0;
            }
        }
    }
    
    @Listener
    private void listen(final PacketEvent.Read event) {
        if (event.getPacket() instanceof SPacketSpawnObject) {
            this.checkID(((SPacketSpawnObject)event.getPacket()).getEntityID());
        }
        else if (event.getPacket() instanceof SPacketSpawnExperienceOrb) {
            this.checkID(((SPacketSpawnExperienceOrb)event.getPacket()).getEntityID());
        }
        else if (event.getPacket() instanceof SPacketSpawnPlayer) {
            this.checkID(((SPacketSpawnPlayer)event.getPacket()).getEntityID());
        }
        else if (event.getPacket() instanceof SPacketSpawnGlobalEntity) {
            this.checkID(((SPacketSpawnGlobalEntity)event.getPacket()).getEntityId());
        }
        else if (event.getPacket() instanceof SPacketSpawnPainting) {
            this.checkID(((SPacketSpawnPainting)event.getPacket()).getEntityID());
        }
        else if (event.getPacket() instanceof SPacketSpawnMob) {
            this.checkID(((SPacketSpawnMob)event.getPacket()).getEntityID());
        }
    }
    
    public void rotateTo(final BlockPos pos) {
        final float[] angle = this.calcAngle(this.mc.player.getPositionEyes(this.mc.getRenderPartialTicks()), new Vec3d((Vec3i)pos));
        this.yaw = angle[0];
        this.pitch = angle[1];
        this.rotating = true;
    }
    
    public float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difY = (to.y - from.y) * -1.0;
        final double difZ = to.z - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    private void attackID(final BlockPos pos, final int id) {
        final Entity entity = this.mc.world.getEntityByID(id);
        if (entity == null || entity instanceof EntityEnderCrystal) {
            final AttackThread attackThread = new AttackThread(id, pos, (int)this.delay.getValue());
            attackThread.start();
        }
    }
    
    private void checkID(final int id) {
        if (id > this.highestID) {
            this.highestID = id;
        }
    }
    
    private boolean checkPlayers() {
        if (this.antiIllegal.getValue()) {
            for (final EntityPlayer player : this.mc.world.playerEntities) {
                if (!this.checkItem(player.getHeldItemMainhand()) && !this.checkItem(player.getHeldItemOffhand())) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
    
    private boolean checkItem(final ItemStack stack) {
        return stack.getItem() instanceof ItemBow || stack.getItem() instanceof ItemExpBottle || stack.getItem() == Items.STRING;
    }
    
    private void reset() {
        this.resetFields();
        if (this.mc.world != null) {
            this.updateEntityID();
        }
    }
    
    public void updateEntityID() {
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity.getEntityId() <= this.highestID) {
                continue;
            }
            this.highestID = entity.getEntityId();
        }
    }
    
    private void resetFields() {
        this.rotating = false;
        this.highestID = -1000000;
    }
    
    protected class AttackThread extends Thread
    {
        private final BlockPos pos;
        private final int id;
        private final int delay;
        
        public AttackThread(final int idIn, final BlockPos posIn, final int delayIn) {
            this.id = idIn;
            this.pos = posIn;
            this.delay = delayIn;
        }
        
        @Override
        public void run() {
            try {
                this.wait(this.delay);
                final ICPacketUseEntity attack = (ICPacketUseEntity)new CPacketUseEntity();
                attack.setEntityId(this.id);
                attack.setAction(CPacketUseEntity.Action.ATTACK);
                GodModule.this.rotateTo(this.pos.up());
                GodModule.this.mc.player.connection.sendPacket((Packet)attack);
                GodModule.this.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
