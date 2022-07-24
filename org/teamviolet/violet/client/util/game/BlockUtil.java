//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.tileentity.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import javax.annotation.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;

public class BlockUtil
{
    private static final Minecraft mc;
    
    public static boolean isSurrounded(final BlockPos pos) {
        return isHardBlock(pos) || (isHardBlock(pos.east()) && isHardBlock(pos.north()) && isHardBlock(pos.west()) && isHardBlock(pos.south()) && isHardBlock(pos.down()));
    }
    
    public static void rightClickBlock(final BlockPos pos, final Vec3d vec, final EnumHand hand, final EnumFacing direction, final boolean packet) {
        if (packet) {
            final float f = (float)(vec.x - pos.getX());
            final float f2 = (float)(vec.y - pos.getY());
            final float f3 = (float)(vec.z - pos.getZ());
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f2, f3));
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(hand));
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, pos, direction, vec, hand);
            BlockUtil.mc.player.swingArm(hand);
        }
    }
    
    public static boolean shouldSneakWhileRightClicking(final BlockPos blockPos) {
        final Block block = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        TileEntity tileEntity = null;
        for (final TileEntity tE : BlockUtil.mc.world.loadedTileEntityList) {
            if (!tE.getPos().equals((Object)blockPos)) {
                continue;
            }
            tileEntity = tE;
            break;
        }
        return tileEntity != null || block instanceof BlockBed || block instanceof BlockContainer || block instanceof BlockDoor || block instanceof BlockTrapDoor || block instanceof BlockFenceGate || block instanceof BlockButton || block instanceof BlockAnvil || block instanceof BlockWorkbench || block instanceof BlockCake || block instanceof BlockRedstoneDiode;
    }
    
    public static float[] calcAngle(final Vec3d from, final BlockPos to) {
        final double difX = to.getX() - from.x;
        final double difY = (to.getY() - from.y) * -1.0;
        final double difZ = to.getZ() - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    public static boolean isHardBlock(final BlockPos pos) {
        final Block block = Utils.world().getBlockState(pos).getBlock();
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK || block == Blocks.ENDER_CHEST || block == Blocks.ANVIL || block == Blocks.ENCHANTING_TABLE;
    }
    
    public static boolean isCrystalPlaceableBlock(final BlockPos pos) {
        final Block block = Utils.world().getBlockState(pos).getBlock();
        return block == Blocks.OBSIDIAN || block == Blocks.BEDROCK;
    }
    
    public static boolean isBreakable(final BlockPos pos) {
        final Block block = Utils.world().getBlockState(pos).getBlock();
        return block != Blocks.BEDROCK;
    }
    
    public static boolean isAirBlock(final BlockPos pos) {
        final Block block = Utils.world().getBlockState(pos).getBlock();
        return block == Blocks.AIR;
    }
    
    public static void placeCrystal(final BlockPos pos, final EnumFacing facing, final EnumHand hand, final Vec3d lookVec, final boolean packet) {
        if (packet) {
            Utils.sendPacket((Packet<?>)new CPacketPlayerTryUseItemOnBlock(pos, facing, hand, (float)lookVec.x, (float)lookVec.y, (float)lookVec.z));
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(Utils.player(), Utils.world(), pos, facing, lookVec, hand);
        }
    }
    
    public static boolean placeBlock(final BlockPos pos, final boolean packet) {
        if (!(Utils.player().getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            return false;
        }
        final EnumFacing facing = getNeighbor(pos);
        if (facing == null) {
            return false;
        }
        final Vec3d lookVec = Utils.player().getLookVec();
        if (packet) {
            Utils.sendPacket((Packet<?>)new CPacketPlayerTryUseItemOnBlock(pos.offset(facing), facing.getOpposite(), EnumHand.MAIN_HAND, (float)lookVec.x, (float)lookVec.y, (float)lookVec.z));
            Utils.player().swingArm(EnumHand.MAIN_HAND);
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(Utils.player(), Utils.world(), pos.offset(facing), facing.getOpposite(), lookVec, EnumHand.MAIN_HAND);
        }
        return true;
    }
    
    @Nullable
    public static EnumFacing getNeighbor(final BlockPos pos) {
        for (final EnumFacing facing : EnumFacing.values()) {
            if (Utils.world().getBlockState(pos.offset(facing)).getBlock() != Blocks.AIR) {
                return facing;
            }
        }
        return null;
    }
    
    public static Vec3d getCenter(final BlockPos pos) {
        return new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
    }
    
    public static List<BlockPos> getPlaceableBlocks(final BlockPos source, final double radius, final boolean oneThirteen, final boolean multiplace, final List<Integer> ignoredEntities) {
        final List<BlockPos> blockPosList = new ArrayList<BlockPos>();
        for (final BlockPos pos : getSphere(source, (float)radius, (int)radius, false, true, 0)) {
            if (canPlaceCrystal(pos, oneThirteen, multiplace, ignoredEntities)) {
                blockPosList.add(pos);
            }
        }
        return blockPosList;
    }
    
    public static boolean canPlaceCrystal(final BlockPos pos, final boolean oneThirteen, final boolean multiplace, final List<Integer> ignoredEntities) {
        final Block material = Utils.world().getBlockState(pos).getBlock();
        return (material == Blocks.OBSIDIAN || material == Blocks.BEDROCK) && Utils.world().getBlockState(pos.up()).getBlock() == Blocks.AIR && (oneThirteen || Utils.world().getBlockState(pos.up().up()).getBlock() == Blocks.AIR) && Utils.world().getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1)), entity -> (multiplace || !(entity instanceof EntityEnderCrystal)) && entity.isEntityAlive() && !ignoredEntities.contains(entity.getEntityId())).isEmpty();
    }
    
    public static boolean canPlaceCrystalTotal(final BlockPos pos, final boolean oneThirteen) {
        final Block material = Utils.world().getBlockState(pos).getBlock();
        return (material == Blocks.OBSIDIAN || material == Blocks.BEDROCK) && Utils.world().getBlockState(pos.up()).getBlock() == Blocks.AIR && (oneThirteen || Utils.world().getBlockState(pos.up().up()).getBlock() == Blocks.AIR) && Utils.world().getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1))).isEmpty();
    }
    
    public static boolean isCrystalInPos(final BlockPos pos) {
        for (final Entity entity : Utils.world().getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1)))) {
            if (entity instanceof EntityEnderCrystal) {
                return true;
            }
        }
        return false;
    }
    
    public static List<BlockPos> getSphere(final BlockPos pos, final float radius, final int height, final boolean hollow, final boolean sphere, final int yOffset) {
        final List<BlockPos> circleBlocks = new ArrayList<BlockPos>();
        for (int currentX = pos.getX() - (int)radius; currentX <= pos.getX() + radius; ++currentX) {
            for (int currentZ = pos.getZ() - (int)radius; currentZ <= pos.getZ() + radius; ++currentZ) {
                int currentY = sphere ? (pos.getY() - (int)radius) : pos.getY();
                while (true) {
                    final float f2 = sphere ? (pos.getY() + radius) : ((float)(pos.getY() + height));
                    if (currentY >= f2) {
                        break;
                    }
                    final double distance = (pos.getX() - currentX) * (pos.getX() - currentX) + (pos.getZ() - currentZ) * (pos.getZ() - currentZ) + (sphere ? ((pos.getY() - currentY) * (pos.getY() - currentY)) : 0);
                    if (distance < radius * radius || (hollow && distance < (radius - 1.0f) * (radius - 1.0f))) {
                        circleBlocks.add(new BlockPos(currentX, currentY + yOffset, currentZ));
                    }
                    ++currentY;
                }
            }
        }
        return circleBlocks;
    }
    
    public static boolean isSamePosition(final BlockPos pos1, final BlockPos pos2) {
        return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ();
    }
    
    public static float getDistance(final Entity e, final BlockPos bp) {
        final float f = (float)(e.posX - bp.getX());
        final float f2 = (float)(e.posY - bp.getY());
        final float f3 = (float)(e.posZ - bp.getZ());
        return MathHelper.sqrt(f * f + f2 * f2 + f3 * f3);
    }
    
    public static void mineBlockLegit(final BlockPos pos) {
        BlockUtil.mc.playerController.onPlayerDamageBlock(pos, BlockUtil.mc.objectMouseOver.sideHit);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    private BlockUtil() {
        throw new UnsupportedOperationException();
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
}
