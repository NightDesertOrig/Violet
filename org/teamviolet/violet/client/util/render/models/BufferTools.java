//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.models;

import java.nio.*;
import org.lwjgl.*;
import org.lwjgl.util.vector.*;

public class BufferTools
{
    public static float[] asFloats(final Vector3f v) {
        return new float[] { v.x, v.y, v.z };
    }
    
    public static boolean bufferEquals(final FloatBuffer bufferOne, final FloatBuffer bufferTwo, final int elements) {
        for (int i = 0; i < elements; ++i) {
            if (bufferOne.get(i) != bufferTwo.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static ByteBuffer asByteBuffer(final byte... values) {
        final ByteBuffer buffer = BufferUtils.createByteBuffer(values.length);
        buffer.put(values);
        return buffer;
    }
    
    public static String bufferToString(final FloatBuffer buffer, final int elements) {
        final StringBuilder bufferString = new StringBuilder();
        for (int i = 0; i < elements; ++i) {
            bufferString.append(" ").append(buffer.get(i));
        }
        return bufferString.toString();
    }
    
    public static FloatBuffer asFloatBuffer(final Matrix4f matrix4f) {
        final FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.store(buffer);
        return buffer;
    }
    
    public static FloatBuffer asFlippedFloatBuffer(final Matrix4f matrix4f) {
        final FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        matrix4f.store(buffer);
        buffer.flip();
        return buffer;
    }
    
    public static FloatBuffer asFloatBuffer(final float... values) {
        final FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        return buffer;
    }
    
    public static FloatBuffer reserveData(final int amountOfElements) {
        return BufferUtils.createFloatBuffer(amountOfElements);
    }
    
    public static FloatBuffer asFlippedFloatBuffer(final float... values) {
        final FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
}
