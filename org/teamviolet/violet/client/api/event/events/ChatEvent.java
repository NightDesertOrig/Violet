//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.events;

import org.teamviolet.violet.client.api.event.*;

public class ChatEvent extends CancelableEvent
{
    private String message;
    
    private ChatEvent(final String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public static class Incoming extends ChatEvent
    {
        public Incoming(final String message) {
            super(message, null);
        }
    }
    
    public static class Outgoing extends ChatEvent
    {
        public Outgoing(final String message) {
            super(message, null);
        }
    }
}
