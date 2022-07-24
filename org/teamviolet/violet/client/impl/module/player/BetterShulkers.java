//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.player;

import org.teamviolet.violet.client.api.module.*;
import net.minecraft.entity.player.*;
import org.teamviolet.violet.client.util.misc.*;
import java.util.concurrent.*;
import org.teamviolet.violet.client.util.game.*;
import net.minecraft.item.*;
import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.font.*;
import net.minecraft.util.*;
import net.minecraft.inventory.*;
import net.minecraft.nbt.*;
import net.minecraft.client.renderer.vertex.*;
import org.teamviolet.violet.client.mixin.accessors.*;
import net.minecraft.client.renderer.*;

@Module.Manifest(Module.Category.PLAYER)
public class BetterShulkers extends Module
{
    private Map<EntityPlayer, ItemStack> spiedPlayers;
    private Map<EntityPlayer, Timer> playerTimers;
    private final ResourceLocation SHULKER_GUI_TEXTURE;
    
    public BetterShulkers() {
        this.spiedPlayers = new ConcurrentHashMap<EntityPlayer, ItemStack>();
        this.playerTimers = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.SHULKER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
    }
    
    @Listener
    private void listen(final UpdateEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        for (final EntityPlayer player : this.mc.world.playerEntities) {
            if (player != null && player.getHeldItemMainhand().getItem() instanceof ItemShulkerBox) {
                if (this.mc.player == player) {
                    continue;
                }
                final ItemStack stack = player.getHeldItemMainhand();
                this.spiedPlayers.put(player, stack);
            }
        }
    }
    
    @Listener
    private void listen(final Render2DEvent event) {
        if (Utils.nullCheck()) {
            return;
        }
        for (final EntityPlayer player : this.mc.world.playerEntities) {
            if (this.spiedPlayers.get(player) == null) {
                continue;
            }
            if (player.getHeldItemMainhand() == null || !(player.getHeldItemMainhand().getItem() instanceof ItemShulkerBox)) {
                final Timer playerTimer = this.playerTimers.get(player);
                if (playerTimer == null) {
                    final Timer timer = new Timer();
                    timer.reset();
                    this.playerTimers.put(player, timer);
                }
                else if (playerTimer.passed(3000L)) {
                    continue;
                }
            }
            else {
                final Timer playerTimer;
                if (player.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && (playerTimer = this.playerTimers.get(player)) != null) {
                    playerTimer.reset();
                    this.playerTimers.put(player, playerTimer);
                }
            }
            final ItemStack stack = this.spiedPlayers.get(player);
            this.drawShulkerOnBlock(new BlockPos((Entity)player), 0.2f, 176.0f, stack, player);
        }
    }
    
    private void renderShulkerToolTip(final ItemStack stack, final int x, final int y, final String name) {
        final NBTTagCompound tagCompound = stack.getTagCompound();
        final NBTTagCompound blockEntityTag;
        if (tagCompound != null && tagCompound.hasKey("BlockEntityTag", 10) && (blockEntityTag = tagCompound.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            this.mc.getTextureManager().bindTexture(this.SHULKER_GUI_TEXTURE);
            this.drawTexturedRect(x, y, 0, 0, 176, 16, 500);
            this.drawTexturedRect(x, y + 16, 0, 16, 176, 57, 500);
            this.drawTexturedRect(x, y + 16 + 54, 0, 160, 176, 8, 500);
            GlStateManager.disableDepth();
            FontUtil.drawText((name == null) ? stack.getDisplayName() : name, (float)(x + 8), (float)(y + 6), Color.white);
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            final NonNullList nonnulllist = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(blockEntityTag, nonnulllist);
            for (int i = 0; i < nonnulllist.size(); ++i) {
                final int iX = x + i % 9 * 18 + 8;
                final int iY = y + i / 9 * 18 + 18;
                final ItemStack itemStack = (ItemStack)nonnulllist.get(i);
                ((IItemRenderer)this.mc.getItemRenderer()).getRenderItem().zLevel = 501.0f;
                this.drawItem(itemStack, iX, iY, itemStack.getCount(), true);
                ((IItemRenderer)this.mc.getItemRenderer()).getRenderItem().zLevel = 0.0f;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    private void drawTexturedRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height, final int zLevel) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder BufferBuilder2 = tessellator.getBuffer();
        BufferBuilder2.begin(7, DefaultVertexFormats.POSITION_TEX);
        BufferBuilder2.pos((double)(x + 0), (double)(y + height), (double)zLevel).tex((double)((textureX + 0) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)(x + width), (double)(y + height), (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)((textureY + height) * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)(x + width), (double)(y + 0), (double)zLevel).tex((double)((textureX + width) * 0.00390625f), (double)((textureY + 0) * 0.00390625f)).endVertex();
        BufferBuilder2.pos((double)(x + 0), (double)(y + 0), (double)zLevel).tex((double)((textureX + 0) * 0.00390625f), (double)((textureY + 0) * 0.00390625f)).endVertex();
        tessellator.draw();
    }
    
    private void glScaleDistancePos(final float x, final float y, final float z, final EntityPlayer player, final float scale) {
        final float constant = 0.02666667f;
        GlStateManager.translate(x - ((IRenderManager)this.mc.getRenderManager()).getRenderPosX(), y - ((IRenderManager)this.mc.getRenderManager()).getRenderPosY(), z - ((IRenderManager)this.mc.getRenderManager()).getRenderPosZ());
        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-this.mc.player.rotationYaw, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(this.mc.player.rotationPitch, (this.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-constant, -constant, constant);
        final int distance = (int)player.getDistance((double)x, (double)y, (double)z);
        float scaleDistance = distance / 2.0f / (2.0f + (2.0f - scale));
        if (scaleDistance < 1.0f) {
            scaleDistance = 1.0f;
        }
        GlStateManager.scale(scaleDistance, scaleDistance, scaleDistance);
    }
    
    private void drawShulkerOnBlock(final BlockPos pos, final float startHeight, final float width, final ItemStack stack, final EntityPlayer player) {
        GlStateManager.pushMatrix();
        this.glScaleDistancePos(pos.getX() + 0.5f, pos.getY() + startHeight, pos.getZ() + 0.5f, (EntityPlayer)this.mc.player, 1.0f);
        GlStateManager.disableDepth();
        GlStateManager.translate(-(width / 2.0), 0.0, 0.0);
        this.renderShulkerToolTip(stack, 0, 0, player.getName());
        GlStateManager.popMatrix();
    }
    
    private void drawItem(final ItemStack item, final int x, final int y, final int amount, final boolean drawAmount) {
        final RenderItem itemRenderer = this.mc.getRenderItem();
        GlStateManager.enableTexture2D();
        itemRenderer.renderItemAndEffectIntoGUI(item, x, y);
        itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, item, x, y, String.valueOf(amount));
        GlStateManager.enableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
}
