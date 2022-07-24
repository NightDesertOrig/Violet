//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.api.event.handler;

public interface EventHandler
{
    void register(final Object p0);
    
    void unregister(final Object p0);
    
    void register(final Class<?> p0);
    
    void unregister(final Class<?> p0);
    
    boolean isRegistered(final Object p0);
    
    boolean isRegistered(final Class<?> p0);
    
    void attach(final EventHandler p0);
    
    void unattach(final EventHandler p0);
    
    boolean isAttached(final EventHandler p0);
    
     <T> void post(final T p0);
}
