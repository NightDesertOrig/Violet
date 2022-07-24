//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.particles;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.teamviolet.violet.client.util.render.*;
import java.util.*;

public class ParticleManager
{
    private static final Minecraft mc;
    public static List<Particle> particles;
    private static ScaledResolution sr;
    
    public static void init(final int count, final int maxVel, final int minVel) {
        updateSR();
        final Random rand = new Random();
        for (int i = 0; i < Math.sqrt(count); ++i) {
            for (int j = 0; j < Math.sqrt(count); ++j) {
                float xVel = (float)((rand.nextInt(maxVel * 10) + minVel * 10) / 100);
                float yVel = (float)((rand.nextInt(maxVel * 10) + minVel * 10) / 100);
                xVel = ((rand.nextInt(1) == 1) ? (-xVel) : xVel);
                yVel = ((rand.nextInt(1) == 1) ? (-yVel) : yVel);
                ParticleManager.particles.add(new Particle(new Vec2f((float)(i * ParticleManager.sr.getScaledWidth() / Math.sqrt(count)), (float)(j * ParticleManager.sr.getScaledWidth() / Math.sqrt(count))), new Vec2f(xVel, yVel)));
            }
        }
    }
    
    public static void updateAndDraw(final float radius, final float lineWidth, final boolean lines, final int lineRadius, final Color color) {
        updateSR();
        for (final Particle particle : ParticleManager.particles) {
            if (particle.pos.x + particle.velocity.x > ParticleManager.sr.getScaledWidth() || particle.pos.x + particle.velocity.x < 0.0f) {
                particle.velocity = new Vec2f(-particle.velocity.x, particle.velocity.y);
            }
            if (particle.pos.y + particle.velocity.y > ParticleManager.sr.getScaledHeight() || particle.pos.y + particle.velocity.y < 0.0f) {
                particle.velocity = new Vec2f(particle.velocity.x, -particle.velocity.y);
            }
            particle.pos = new Vec2f(particle.pos.x + particle.velocity.x, particle.pos.y + particle.velocity.y);
        }
        for (final Particle particle : ParticleManager.particles) {
            if (lines) {
                for (final Particle particle2 : ParticleManager.particles) {
                    if (particle == particle2) {
                        continue;
                    }
                    final double distance = Math.sqrt(Math.pow(Math.abs(particle.pos.x - particle2.pos.x), 2.0) + Math.pow(Math.abs(particle.pos.y - particle2.pos.y), 2.0));
                    if (distance > lineRadius) {
                        continue;
                    }
                    final float alpha = (float)(distance / lineRadius * color.getAlpha());
                    Render2D.drawLine(Render2D.vertexHelperUB, new Vec2f(particle.pos.x + radius, particle.pos.y + radius), new Vec2f(particle2.pos.x + radius, particle2.pos.y + radius), lineWidth, new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)alpha));
                }
            }
            particle.draw(color, radius);
        }
    }
    
    private static void updateSR() {
        ParticleManager.sr = new ScaledResolution(ParticleManager.mc);
    }
    
    static {
        mc = Minecraft.getMinecraft();
        ParticleManager.particles = new ArrayList<Particle>();
        ParticleManager.sr = new ScaledResolution(ParticleManager.mc);
    }
}
