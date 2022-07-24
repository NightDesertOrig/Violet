//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import java.util.*;
import net.minecraft.init.*;
import org.teamviolet.violet.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public final class HoleManager
{
    private static final int RANGE = 30;
    public final UpdateThread updateThread;
    private final List<HoleStatus> holes;
    
    public List<HoleStatus> getHoles() {
        return new ArrayList<HoleStatus>(this.holes);
    }
    
    public boolean isHole(final BlockPos pos) {
        for (final HoleStatus status : this.getHoles()) {
            if (status.getPos().equals((Object)pos)) {
                return true;
            }
        }
        return false;
    }
    
    public void init() {
        this.updateThread.start();
    }
    
    public HoleManager() {
        this.updateThread = new UpdateThread();
        this.holes = new ArrayList<HoleStatus>();
    }
    
    private static boolean isAir(final BlockPos pos) {
        return Utils.mc.world.getBlockState(pos).getBlock() == Blocks.AIR;
    }
    
    private static List<BlockPos> getSphere(final BlockPos pos, final float radius, final int height, final boolean hollow, final boolean sphere, final int yOffset) {
        final List<BlockPos> circleBlocks = new ArrayList<BlockPos>();
        final int centerX = pos.getX();
        final int centerY = pos.getY();
        final int centerZ = pos.getZ();
        for (int currentX = centerX - (int)radius; currentX <= centerX + radius; ++currentX) {
            for (int currentZ = centerZ - (int)radius; currentZ <= centerZ + radius; ++currentZ) {
                int currentY = sphere ? (centerY - (int)radius) : centerY;
                while (true) {
                    final float f2 = sphere ? (centerY + radius) : ((float)(centerY + height));
                    if (currentY >= f2) {
                        break;
                    }
                    final double distance = (centerX - currentX) * (centerX - currentX) + (centerZ - currentZ) * (centerZ - currentZ) + (sphere ? ((centerY - currentY) * (centerY - currentY)) : 0);
                    if (distance < radius * radius || (hollow && distance < (radius - 1.0f) * (radius - 1.0f))) {
                        circleBlocks.add(new BlockPos(currentX, currentY + yOffset, currentZ));
                    }
                    ++currentY;
                }
            }
        }
        return circleBlocks;
    }
    
    public static final class UpdateThread extends Thread
    {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    Thread.sleep(200L);
                    if (Utils.mc.player == null || Utils.mc.world == null || !Violet.getViolet().getModuleManager().getModule("HoleESP").isEnabled()) {
                        continue;
                    }
                    final List<HoleStatus> statuses = new ArrayList<HoleStatus>();
                    for (final BlockPos pos : getSphere(EntityUtil.getEntityPos((Entity)Utils.mc.player), 30.0f, 30, false, true, 0)) {
                        if (isAir(pos) && isAir(pos.up())) {
                            if (!isAir(pos.up().up())) {
                                continue;
                            }
                            boolean bedrockHole = true;
                            boolean hole = true;
                            for (final EnumFacing offset : EnumFacing.values()) {
                                if (offset != EnumFacing.UP) {
                                    final Block block = Utils.mc.world.getBlockState(pos.offset(offset)).getBlock();
                                    if (block != Blocks.BEDROCK) {
                                        bedrockHole = false;
                                        if (block != Blocks.OBSIDIAN && block != Blocks.ENDER_CHEST && block != Blocks.ANVIL) {
                                            hole = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!hole) {
                                continue;
                            }
                            statuses.add(new HoleStatus(pos, bedrockHole, Utils.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(pos))));
                        }
                    }
                    Violet.getViolet().getHoleManager().holes.clear();
                    Violet.getViolet().getHoleManager().holes.addAll(statuses);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
