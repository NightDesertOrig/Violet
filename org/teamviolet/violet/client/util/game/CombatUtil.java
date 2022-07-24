//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.math.*;
import net.minecraft.potion.*;

public class CombatUtil
{
    public static float getDamage(final Entity entity, final EntityEnderCrystal crystal) {
        return getDamage(crystal.posX, crystal.posY, crystal.posZ, entity);
    }
    
    public static float getDamage(final Entity entity, final BlockPos pos) {
        return getDamage(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, entity);
    }
    
    public static float getDamage(final double posX, final double posY, final double posZ, final Entity entity) {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).isCreative()) {
            return 0.0f;
        }
        final float doubleExplosionSize = 12.0f;
        final double distancedSize = entity.getDistance(posX, posY, posZ) / doubleExplosionSize;
        final double blockDensity = entity.world.getBlockDensity(new Vec3d(posX, posY, posZ), entity.getEntityBoundingBox());
        final double v = (1.0 - distancedSize) * blockDensity;
        final float damage = (float)(int)((v * v + v) / 2.0 * 7.0 * doubleExplosionSize + 1.0);
        if (entity instanceof EntityLivingBase) {
            return getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(damage), new Explosion((World)Utils.world(), (Entity)null, posX, posY, posZ, 6.0f, false, true));
        }
        return 1.0f;
    }
    
    private static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer)entity;
            damage = CombatRules.getDamageAfterAbsorb(damage, (float)ep.getTotalArmorValue(), (float)ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            damage *= 1.0f - MathHelper.clamp(EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), DamageSource.causeExplosionDamage(explosion)), 0, 20) / 25.0f;
            if (entity.isPotionActive(Potion.getPotionById(11))) {
                damage -= damage / 4.0f;
            }
            return Math.max(damage, 0.0f);
        }
        return CombatRules.getDamageAfterAbsorb(damage, (float)entity.getTotalArmorValue(), (float)entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
    }
    
    private static float getDamageMultiplied(final float damage) {
        final int diff = Utils.world().getDifficulty().getId();
        return damage * ((diff == 0) ? 0.0f : ((diff == 1) ? 0.5f : ((diff == 2) ? 1.0f : 1.5f)));
    }
    
    public static int ping() {
        if (Utils.mc.getConnection() == null) {
            return 50;
        }
        if (Utils.mc.player == null) {
            return 50;
        }
        try {
            return Utils.mc.getConnection().getPlayerInfo(Utils.mc.player.getUniqueID()).getResponseTime();
        }
        catch (NullPointerException ex) {
            return 50;
        }
    }
    
    private CombatUtil() {
        throw new UnsupportedOperationException();
    }
}
