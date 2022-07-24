//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.game;

import java.util.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.api.event.events.*;
import java.util.concurrent.atomic.*;
import org.teamviolet.violet.client.util.misc.*;
import net.minecraft.util.math.*;
import java.awt.*;
import org.teamviolet.violet.client.impl.module.client.*;
import org.teamviolet.violet.client.util.render.*;

public class NotificationManager
{
    private static NotificationManager instance;
    private final List<Notification> notifications;
    
    private NotificationManager() {
        this.notifications = new ArrayList<Notification>();
    }
    
    @Listener
    public void listen(final UpdateEvent event) {
        this.notifications.removeIf(rec$ -> rec$.update());
    }
    
    @Listener
    public void listen(final Render2DEvent event) {
        final AtomicInteger count = new AtomicInteger();
        this.notifications.forEach(notification -> notification.render(800.0f, 800.0f - count.getAndIncrement() * 60.0f - 20.0f));
        count.set(0);
    }
    
    void addNotification(final String text, final InfoType type) {
        this.notifications.add(new Notification(text, type));
    }
    
    public static NotificationManager getInstance() {
        if (NotificationManager.instance == null) {
            NotificationManager.instance = new NotificationManager();
        }
        return NotificationManager.instance;
    }
    
    public enum InfoType
    {
        INFO, 
        WARNING, 
        ERROR;
    }
    
    private static class Notification
    {
        private static final long ENTER_MS = 300L;
        private static final long WAIT_MS = 700L;
        private static final long FADE_MS = 300L;
        private static final float WIDTH = 100.0f;
        private static final float HEIGHT = 60.0f;
        private static final VertexHelper VERTEX_HELPER;
        private final String text;
        private final InfoType type;
        private final Timer startTimer;
        private final Timer waitTimer;
        private final Timer fadeTimer;
        private boolean entered;
        private boolean fading;
        
        private Notification(final String text, final InfoType type) {
            this.entered = false;
            this.fading = false;
            this.text = text;
            this.type = type;
            this.startTimer = new Timer();
            this.waitTimer = new Timer();
            this.fadeTimer = new Timer();
        }
        
        private boolean update() {
            if (!this.entered && this.startTimer.passed(300L)) {
                this.entered = true;
                this.waitTimer.reset();
            }
            if (this.entered && this.waitTimer.passed(700L)) {
                this.fading = true;
                this.fadeTimer.reset();
            }
            return this.fading && this.fadeTimer.passed(300L);
        }
        
        private void render(final float startX, final float startY) {
            final float xOffset = this.getXOffset(startX);
            Render2D.drawRoundedRectangleFilled(Notification.VERTEX_HELPER, new Vec2f(startX + xOffset, startY), new Vec2f(startX + 100.0f + xOffset, startY + 60.0f), 1.5f, 20, (Color)ClickGui.getInstance().accentColor.getValue());
        }
        
        private float getXOffset(final float startX) {
            if (!this.entered) {
                return startX + 100.0f - this.startTimer.getTime();
            }
            if (this.fading) {
                return startX + this.fadeTimer.getTime();
            }
            return 0.0f;
        }
        
        static {
            VERTEX_HELPER = new VertexHelper(true);
        }
    }
}
