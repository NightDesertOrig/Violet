//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.openal;

import org.newdawn.slick.util.*;
import java.io.*;
import org.lwjgl.openal.*;
import org.lwjgl.*;
import java.nio.*;

public class StreamSound extends AudioImpl
{
    private OpenALStreamPlayer player;
    
    public StreamSound(final OpenALStreamPlayer player) {
        this.player = player;
    }
    
    public boolean isPlaying() {
        return SoundStore.get().isPlaying(this.player);
    }
    
    public int playAsMusic(final float pitch, final float gain, final boolean loop) {
        try {
            this.cleanUpSource();
            this.player.setup(pitch);
            this.player.play(loop);
            SoundStore.get().setStream(this.player);
        }
        catch (IOException e) {
            Log.error("Failed to read OGG source: " + this.player.getSource());
        }
        return SoundStore.get().getSource(0);
    }
    
    private void cleanUpSource() {
        final SoundStore store = SoundStore.get();
        AL10.alSourceStop(store.getSource(0));
        final IntBuffer buffer = BufferUtils.createIntBuffer(1);
        for (int queued = AL10.alGetSourcei(store.getSource(0), 4117); queued > 0; --queued) {
            AL10.alSourceUnqueueBuffers(store.getSource(0), buffer);
        }
        AL10.alSourcei(store.getSource(0), 4105, 0);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop, final float x, final float y, final float z) {
        return this.playAsMusic(pitch, gain, loop);
    }
    
    public int playAsSoundEffect(final float pitch, final float gain, final boolean loop) {
        return this.playAsMusic(pitch, gain, loop);
    }
    
    public void stop() {
        SoundStore.get().setStream((OpenALStreamPlayer)null);
    }
    
    public boolean setPosition(final float position) {
        return this.player.setPosition(position);
    }
    
    public float getPosition() {
        return this.player.getPosition();
    }
}
