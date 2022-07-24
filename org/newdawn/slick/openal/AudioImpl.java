//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.openal;

import org.lwjgl.openal.*;

public class AudioImpl implements Audio
{
    private SoundStore store;
    private int buffer;
    private int index;
    private float length;
    
    AudioImpl(final SoundStore store, final int buffer) {
        this.index = -1;
        this.store = store;
        this.buffer = buffer;
        final int bytes = AL10.alGetBufferi(buffer, 8196);
        final int bits = AL10.alGetBufferi(buffer, 8194);
        final int channels = AL10.alGetBufferi(buffer, 8195);
        final int freq = AL10.alGetBufferi(buffer, 8193);
        final int samples = bytes / (bits / 8);
        this.length = samples / (float)freq / channels;
    }
    
    public int getBufferID() {
        return this.buffer;
    }
    
    protected AudioImpl() {
        this.index = -1;
    }
    
    public void stop() {
        if (this.index != -1) {
            this.store.stopSource(this.index);
            this.index = -1;
        }
    }
    
    public boolean isPlaying() {
        return this.index != -1 && this.store.isPlaying(this.index);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop) {
        this.index = this.store.playAsSound(this.buffer, pitch, gain, loop);
        return this.store.getSource(this.index);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop, final float x, final float y, final float z) {
        this.index = this.store.playAsSoundAt(this.buffer, pitch, gain, loop, x, y, z);
        return this.store.getSource(this.index);
    }
    
    public int playAsMusic(final float pitch, final float gain, final boolean loop) {
        this.store.playAsMusic(this.buffer, pitch, gain, loop);
        this.index = 0;
        return this.store.getSource(0);
    }
    
    public static void pauseMusic() {
        SoundStore.get().pauseLoop();
    }
    
    public static void restartMusic() {
        SoundStore.get().restartLoop();
    }
    
    public boolean setPosition(float position) {
        position %= this.length;
        AL10.alSourcef(this.store.getSource(this.index), 4132, position);
        return AL10.alGetError() == 0;
    }
    
    public float getPosition() {
        return AL10.alGetSourcef(this.store.getSource(this.index), 4132);
    }
}
