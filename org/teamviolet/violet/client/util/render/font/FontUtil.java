//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.font;

import net.minecraft.client.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import java.io.*;

public class FontUtil
{
    private static final Minecraft mc;
    public static FontRenderer fontRenderer;
    public static FontRenderer fontRendererSettings;
    private static Font font;
    
    public static void init(final String font, final float size) {
        try {
            FontUtil.fontRenderer = new FontRenderer(getFontByName(font).deriveFont(size), true, true);
            FontUtil.fontRendererSettings = new FontRenderer(getFontByName(font).deriveFont(14.0f), true, true);
        }
        catch (IOException | FontFormatException ex2) {
            final Exception ex;
            final Exception e = ex;
            e.printStackTrace();
        }
    }
    
    public static float drawText(final String text, final float x, final float y, final Color color) {
        if (CustomFont.getInstance().isEnabled()) {
            return FontUtil.fontRenderer.drawStringWithShadow(text, (double)x, (double)y, color.getRGB());
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(text, (float)(int)x, (float)(int)y, color.getRGB());
    }
    
    public static float drawTextCF(final String text, final double x, final double y, final Color color) {
        return FontUtil.fontRenderer.drawStringWithShadow(text, x, y, color.getRGB());
    }
    
    public static float drawTextSetting(final String text, final float x, final float y, final Color color) {
        if (CustomFont.getInstance().isEnabled()) {
            return FontUtil.fontRendererSettings.drawStringWithShadow(text, (double)x, (double)y, color.getRGB());
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(text, (float)(int)x, (float)(int)y, color.getRGB());
    }
    
    public static float drawTextCenter(final String text, final float x, final float y, final Color color) {
        if (CustomFont.getInstance().isEnabled()) {
            return FontUtil.fontRenderer.drawCenteredStringWithShadow(text, x, y - getStringHeight(text) / 2.0f, color.getRGB());
        }
        return (float)FontUtil.mc.fontRenderer.drawStringWithShadow(text, (int)x - FontUtil.mc.fontRenderer.getStringWidth(text) / 2.0f, (int)y - 4.5f, color.getRGB());
    }
    
    public static void drawTextRainbow(final String text, final float x, final float y, final Color startColor, final float factor, final boolean customFont) {
        Color currentColor = startColor;
        final float hueIncrement = 1.0f / factor;
        final String[] rainbowStrings = text.split("§.");
        float currentHue = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[0];
        final float saturation = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[1];
        final float brightness = Color.RGBtoHSB(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), null)[2];
        int currentWidth = 0;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        for (int i = 0; i < text.length(); ++i) {
            final char currentChar = text.charAt(i);
            final char nextChar = text.charAt(Math.min(i + 1, text.length() - 1));
            if ((String.valueOf(currentChar) + nextChar).equals("§r")) {
                shouldRainbow = false;
            }
            else if ((String.valueOf(currentChar) + nextChar).equals("§+")) {
                shouldRainbow = true;
            }
            if (shouldContinue) {
                shouldContinue = false;
            }
            else {
                if ((String.valueOf(currentChar) + nextChar).equals("§r")) {
                    final String escapeString = text.substring(i);
                    drawText(escapeString, x + currentWidth, y, Color.WHITE);
                    break;
                }
                drawText(String.valueOf(currentChar).equals("§") ? "" : String.valueOf(currentChar), x + currentWidth, y, shouldRainbow ? currentColor : Color.WHITE);
                if (String.valueOf(currentChar).equals("§")) {
                    shouldContinue = true;
                }
                currentWidth += (int)getStringWidth(String.valueOf(currentChar));
                if (!String.valueOf(currentChar).equals(" ")) {
                    currentColor = new Color(Color.HSBtoRGB(currentHue, saturation, brightness));
                    currentHue += hueIncrement;
                }
            }
        }
    }
    
    public static float getStringWidth(final String text) {
        if (CustomFont.getInstance().isEnabled()) {
            return (float)FontUtil.fontRenderer.getStringWidth(text);
        }
        return (float)FontUtil.mc.fontRenderer.getStringWidth(text);
    }
    
    public static float getStringHeight(final String text) {
        if (CustomFont.getInstance().isEnabled()) {
            return (float)FontUtil.fontRenderer.getStringHeight(text);
        }
        return (float)FontUtil.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public static Font getFontFromInput(final String path) throws IOException, FontFormatException {
        return FontUtil.font = Font.createFont(0, FontUtil.class.getResourceAsStream(path));
    }
    
    public static Font getFontByName(final String name) throws IOException, FontFormatException {
        if (name.equals("ProductSans")) {
            return getFontFromInput("/assets/violet/fonts/ProductSans.ttf");
        }
        if (name.equals("Roboto")) {
            return getFontFromInput("/assets/violet/fonts/Roboto.ttf");
        }
        if (name.equals("OpenSans")) {
            return getFontFromInput("/assets/violet/fonts/OpenSans.ttf");
        }
        if (name.equals("Lato")) {
            return getFontFromInput("/assets/violet/fonts/Lato.ttf");
        }
        return Font.createFont(0, new File("/assets/violet/fonts/" + name + ".ttf"));
    }
    
    static {
        mc = Minecraft.getMinecraft();
        FontUtil.font = null;
    }
}
