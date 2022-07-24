//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.hud;

import org.teamviolet.violet.client.api.module.hud.*;
import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.api.value.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.entity.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.util.account.*;
import org.teamviolet.violet.client.util.render.*;
import org.teamviolet.violet.client.util.render.font.*;
import java.awt.*;
import java.util.*;

@Module.Manifest(Module.Category.HUD)
public class TextRadar extends HudModule
{
    private int topHeight;
    private int longestNameLength;
    private final Value<Boolean> yLevel;
    
    public TextRadar() {
        super(2, 2);
        this.topHeight = 0;
        this.longestNameLength = 0;
        this.yLevel = (Value<Boolean>)ValueFactory.booleanValue().withName("yLevel").withVal((Object)true).build((Module)this);
    }
    
    public void updateBounds() {
        this.width = this.longestNameLength;
        this.height = this.topHeight;
        this.validatePosition();
    }
    
    public void draw() {
        this.updateBounds();
        this.topHeight = 0;
        int drawY = this.y;
        boolean right = false;
        if (this.x > this.sr().getScaledWidth() / 2) {
            right = true;
        }
        for (final Entity entity : this.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer) {
                if (entity.equals((Object)this.mc.getRenderViewEntity())) {
                    continue;
                }
                final EntityPlayer player = (EntityPlayer)entity;
                final StringBuilder sb = new StringBuilder();
                if ((boolean)this.yLevel.getValue() && Math.round(entity.posY) != Math.round(this.mc.getRenderViewEntity().posY)) {
                    sb.append((entity.posY > this.mc.getRenderViewEntity().posY) ? (ChatFormatting.GREEN + "+ " + ChatFormatting.RESET) : (ChatFormatting.RED + "- " + ChatFormatting.RESET));
                }
                sb.append(EntityUtil.getPlayerHPColor(EntityUtil.getHealth((EntityLivingBase)entity)) + String.valueOf(Math.round(EntityUtil.getHealth((EntityLivingBase)entity))) + ChatFormatting.RESET + " ");
                sb.append((FriendUtil.isFriend(player) ? ChatFormatting.AQUA : "") + entity.getName() + ChatFormatting.RESET + " ");
                sb.append(ColorUtil.getPercentColor(this.mc.getRenderViewEntity().getDistance(entity), 60.0f) + String.valueOf(Math.round(this.mc.getRenderViewEntity().getDistance(entity))) + ChatFormatting.RESET);
                if (FontUtil.getStringWidth(sb.toString()) > this.longestNameLength) {
                    this.longestNameLength = (int)FontUtil.getStringWidth(sb.toString());
                }
                FontUtil.drawText(sb.toString(), right ? (this.x + this.longestNameLength - FontUtil.getStringWidth(sb.toString())) : ((float)this.x), (float)drawY, (Color)this.defaultColor.getValue());
                drawY += (int)(FontUtil.getStringHeight(sb.toString()) + 2.0f);
            }
        }
        this.topHeight = drawY - this.y;
    }
}
