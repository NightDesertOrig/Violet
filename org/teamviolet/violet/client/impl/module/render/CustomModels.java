//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.impl.module.render;

import org.teamviolet.violet.client.api.module.*;
import org.teamviolet.violet.client.util.game.*;
import org.teamviolet.violet.client.api.event.events.*;
import org.teamviolet.violet.client.api.event.handler.*;
import org.teamviolet.violet.client.util.render.models.*;
import org.lwjgl.util.vector.*;
import java.io.*;
import java.util.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;

@Module.Manifest(Module.Category.RENDER)
public class CustomModels extends Module
{
    private static int bunnyDisplayList;
    private static EulerCamera camera;
    private static final String MODEL_LOCATION = "/models/bunny.obj";
    boolean set;
    
    public CustomModels() {
        this.set = false;
    }
    
    protected void onEnable() {
        if (Utils.nullCheck()) {
            return;
        }
        this.set = true;
        this.setUpDisplayLists();
    }
    
    protected void onDisable() {
        cleanUp();
        CustomModels.camera = null;
        this.set = false;
    }
    
    @Listener
    private void listen(final Render3DEvent event) {
        if (!this.set) {
            return;
        }
        GL11.glPushMatrix();
        this.applyPerspectiveMatrix();
        render();
        GL11.glPopMatrix();
    }
    
    private void setUpDisplayLists() {
        GL11.glNewList(CustomModels.bunnyDisplayList = GL11.glGenLists(1), 4864);
        Model m = null;
        try {
            final InputStream s = CustomModels.class.getResourceAsStream("/models/bunny.obj");
            if (s == null) {
                this.setEnabled(false);
            }
            m = CustomOBJLoader.loadModel(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        GL11.glBegin(4);
        for (final Model.Face face : m.getFaces()) {
            final Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
            GL11.glNormal3f(n1.x, n1.y, n1.z);
            final Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
            GL11.glVertex3f(v1.x, v1.y, v1.z);
            final Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
            GL11.glNormal3f(n2.x, n2.y, n2.z);
            final Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
            GL11.glVertex3f(v2.x, v2.y, v2.z);
            final Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
            GL11.glNormal3f(n3.x, n3.y, n3.z);
            final Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
            GL11.glVertex3f(v3.x, v3.y, v3.z);
        }
        GL11.glEnd();
        GL11.glEndList();
    }
    
    private static void render() {
        GL11.glLoadIdentity();
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5888);
        final float x = -1.38f;
        final float y = 1.36f;
        final float z = 7.95f;
        GL11.glTranslatef(-x, -y, -z);
        GL11.glPopAttrib();
        GL11.glPolygonMode(1032, 6913);
        GL11.glCallList(CustomModels.bunnyDisplayList);
    }
    
    private static void setUpCamera() {
        (CustomModels.camera = new EulerCamera.Builder().setAspectRatio(Display.getWidth() / (float)Display.getHeight()).setRotation(-1.12f, 0.16f, 0.0f).setPosition(-1.38f, 1.36f, 7.95f).setFieldOfView(60.0f).build()).applyOptimalStates();
        CustomModels.camera.applyPerspectiveMatrix();
    }
    
    public void applyPerspectiveMatrix() {
        GL11.glPushAttrib(4096);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GLU.gluPerspective(60.0f, Display.getWidth() / (float)Display.getHeight(), 0.3f, 100.0f);
        GL11.glPopAttrib();
    }
    
    private static void cleanUp() {
        GL11.glDeleteLists(CustomModels.bunnyDisplayList, 1);
    }
}
