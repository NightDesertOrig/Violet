//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.models;

import java.util.*;
import java.nio.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import java.nio.charset.*;
import org.lwjgl.util.vector.*;
import java.io.*;
import org.newdawn.slick.opengl.*;

public class CustomOBJLoader
{
    public static int createDisplayList(final Model m) {
        final int displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, 4864);
        GL11.glMaterialf(1028, 5633, 120.0f);
        GL11.glColor3f(0.4f, 0.27f, 0.17f);
        GL11.glBegin(4);
        for (final Model.Face face : m.getFaces()) {
            if (face.hasNormals()) {
                final Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
                GL11.glNormal3f(n1.x, n1.y, n1.z);
            }
            final Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
            GL11.glVertex3f(v1.x, v1.y, v1.z);
            if (face.hasNormals()) {
                final Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
                GL11.glNormal3f(n2.x, n2.y, n2.z);
            }
            final Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
            GL11.glVertex3f(v2.x, v2.y, v2.z);
            if (face.hasNormals()) {
                final Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
                GL11.glNormal3f(n3.x, n3.y, n3.z);
            }
            final Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
            GL11.glVertex3f(v3.x, v3.y, v3.z);
        }
        GL11.glEnd();
        GL11.glEndList();
        return displayList;
    }
    
    private static FloatBuffer reserveData(final int size) {
        return BufferUtils.createFloatBuffer(size);
    }
    
    private static float[] asFloats(final Vector3f v) {
        return new float[] { v.x, v.y, v.z };
    }
    
    public static int[] createVBO(final Model model) {
        final int vboVertexHandle = GL15.glGenBuffers();
        final int vboNormalHandle = GL15.glGenBuffers();
        final FloatBuffer vertices = reserveData(model.getFaces().size() * 9);
        final FloatBuffer normals = reserveData(model.getFaces().size() * 9);
        for (final Model.Face face : model.getFaces()) {
            vertices.put(asFloats(model.getVertices().get(face.getVertexIndices()[0] - 1)));
            vertices.put(asFloats(model.getVertices().get(face.getVertexIndices()[1] - 1)));
            vertices.put(asFloats(model.getVertices().get(face.getVertexIndices()[2] - 1)));
            normals.put(asFloats(model.getNormals().get(face.getNormalIndices()[0] - 1)));
            normals.put(asFloats(model.getNormals().get(face.getNormalIndices()[1] - 1)));
            normals.put(asFloats(model.getNormals().get(face.getNormalIndices()[2] - 1)));
        }
        vertices.flip();
        normals.flip();
        GL15.glBindBuffer(34962, vboVertexHandle);
        GL15.glBufferData(34962, vertices, 35044);
        GL11.glVertexPointer(3, 5126, 0, 0L);
        GL15.glBindBuffer(34962, vboNormalHandle);
        GL15.glBufferData(34962, normals, 35044);
        GL11.glNormalPointer(5126, 0, 0L);
        GL15.glBindBuffer(34962, 0);
        return new int[] { vboVertexHandle, vboNormalHandle };
    }
    
    private static Vector3f parseVertex(final String line) {
        final String[] xyz = line.split(" ");
        final float x = Float.valueOf(xyz[1]);
        final float y = Float.valueOf(xyz[2]);
        final float z = Float.valueOf(xyz[3]);
        return new Vector3f(x, y, z);
    }
    
    private static Vector3f parseNormal(final String line) {
        final String[] xyz = line.split(" ");
        final float x = Float.valueOf(xyz[1]);
        final float y = Float.valueOf(xyz[2]);
        final float z = Float.valueOf(xyz[3]);
        return new Vector3f(x, y, z);
    }
    
    private static Model.Face parseFace(final boolean hasNormals, final String line) {
        final String[] faceIndices = line.split(" ");
        final int[] vertexIndicesArray = { Integer.parseInt(faceIndices[1].split("/")[0]), Integer.parseInt(faceIndices[2].split("/")[0]), Integer.parseInt(faceIndices[3].split("/")[0]) };
        if (hasNormals) {
            final int[] normalIndicesArray = { Integer.parseInt(faceIndices[1].split("/")[2]), Integer.parseInt(faceIndices[2].split("/")[2]), Integer.parseInt(faceIndices[3].split("/")[2]) };
            return new Model.Face(vertexIndicesArray, normalIndicesArray);
        }
        return new Model.Face(vertexIndicesArray);
    }
    
    public static Model loadModel(final InputStream in) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        final Model m = new Model();
        String line;
        while ((line = reader.readLine()) != null) {
            final String prefix = line.split(" ")[0];
            if (prefix.equals("#")) {
                continue;
            }
            if (prefix.equals("v")) {
                m.getVertices().add(parseVertex(line));
            }
            else if (prefix.equals("vn")) {
                m.getNormals().add(parseNormal(line));
            }
            else {
                if (!prefix.equals("f")) {
                    throw new RuntimeException("OBJ file contains line which cannot be parsed correctly: " + line);
                }
                m.getFaces().add(parseFace(m.hasNormals(), line));
            }
        }
        reader.close();
        return m;
    }
    
    public static Model loadModel(final File f) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(f));
        final Model m = new Model();
        String line;
        while ((line = reader.readLine()) != null) {
            final String prefix = line.split(" ")[0];
            if (prefix.equals("#")) {
                continue;
            }
            if (prefix.equals("v")) {
                m.getVertices().add(parseVertex(line));
            }
            else if (prefix.equals("vn")) {
                m.getNormals().add(parseNormal(line));
            }
            else {
                if (!prefix.equals("f")) {
                    throw new RuntimeException("OBJ file contains line which cannot be parsed correctly: " + line);
                }
                m.getFaces().add(parseFace(m.hasNormals(), line));
            }
        }
        reader.close();
        return m;
    }
    
    public static int createTexturedDisplayList(final Model m) {
        final int displayList = GL11.glGenLists(1);
        GL11.glNewList(displayList, 4864);
        GL11.glBegin(4);
        for (final Model.Face face : m.getFaces()) {
            if (face.hasTextureCoordinates()) {
                GL11.glMaterial(1028, 4609, BufferTools.asFlippedFloatBuffer(new float[] { face.getMaterial().diffuseColour[0], face.getMaterial().diffuseColour[1], face.getMaterial().diffuseColour[2], 1.0f }));
                GL11.glMaterial(1028, 4608, BufferTools.asFlippedFloatBuffer(new float[] { face.getMaterial().ambientColour[0], face.getMaterial().ambientColour[1], face.getMaterial().ambientColour[2], 1.0f }));
                GL11.glMaterialf(1028, 5633, face.getMaterial().specularCoefficient);
            }
            if (face.hasNormals()) {
                final Vector3f n1 = m.getNormals().get(face.getNormalIndices()[0] - 1);
                GL11.glNormal3f(n1.x, n1.y, n1.z);
            }
            if (face.hasTextureCoordinates()) {
                final Vector2f t1 = m.getTextureCoordinates().get(face.getTextureCoordinateIndices()[0] - 1);
                GL11.glTexCoord2f(t1.x, t1.y);
            }
            final Vector3f v1 = m.getVertices().get(face.getVertexIndices()[0] - 1);
            GL11.glVertex3f(v1.x, v1.y, v1.z);
            if (face.hasNormals()) {
                final Vector3f n2 = m.getNormals().get(face.getNormalIndices()[1] - 1);
                GL11.glNormal3f(n2.x, n2.y, n2.z);
            }
            if (face.hasTextureCoordinates()) {
                final Vector2f t2 = m.getTextureCoordinates().get(face.getTextureCoordinateIndices()[1] - 1);
                GL11.glTexCoord2f(t2.x, t2.y);
            }
            final Vector3f v2 = m.getVertices().get(face.getVertexIndices()[1] - 1);
            GL11.glVertex3f(v2.x, v2.y, v2.z);
            if (face.hasNormals()) {
                final Vector3f n3 = m.getNormals().get(face.getNormalIndices()[2] - 1);
                GL11.glNormal3f(n3.x, n3.y, n3.z);
            }
            if (face.hasTextureCoordinates()) {
                final Vector2f t3 = m.getTextureCoordinates().get(face.getTextureCoordinateIndices()[2] - 1);
                GL11.glTexCoord2f(t3.x, t3.y);
            }
            final Vector3f v3 = m.getVertices().get(face.getVertexIndices()[2] - 1);
            GL11.glVertex3f(v3.x, v3.y, v3.z);
        }
        GL11.glEnd();
        GL11.glEndList();
        return displayList;
    }
    
    public static Model loadTexturedModel(final File f) throws IOException {
        final BufferedReader reader = new BufferedReader(new FileReader(f));
        final Model m = new Model();
        Model.Material currentMaterial = new Model.Material();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("#")) {
                continue;
            }
            if (line.startsWith("mtllib ")) {
                final String materialFileName = line.split(" ")[1];
                final File materialFile = new File(f.getParentFile().getAbsolutePath() + "/" + materialFileName);
                final BufferedReader materialFileReader = new BufferedReader(new FileReader(materialFile));
                Model.Material parseMaterial = new Model.Material();
                String parseMaterialName = "";
                String materialLine;
                while ((materialLine = materialFileReader.readLine()) != null) {
                    if (materialLine.startsWith("#")) {
                        continue;
                    }
                    if (materialLine.startsWith("newmtl ")) {
                        if (!parseMaterialName.equals("")) {
                            m.getMaterials().put(parseMaterialName, parseMaterial);
                        }
                        parseMaterialName = materialLine.split(" ")[1];
                        parseMaterial = new Model.Material();
                    }
                    else if (materialLine.startsWith("Ns ")) {
                        parseMaterial.specularCoefficient = Float.valueOf(materialLine.split(" ")[1]);
                    }
                    else if (materialLine.startsWith("Ka ")) {
                        final String[] rgb = materialLine.split(" ");
                        parseMaterial.ambientColour[0] = Float.valueOf(rgb[1]);
                        parseMaterial.ambientColour[1] = Float.valueOf(rgb[2]);
                        parseMaterial.ambientColour[2] = Float.valueOf(rgb[3]);
                    }
                    else if (materialLine.startsWith("Ks ")) {
                        final String[] rgb = materialLine.split(" ");
                        parseMaterial.specularColour[0] = Float.valueOf(rgb[1]);
                        parseMaterial.specularColour[1] = Float.valueOf(rgb[2]);
                        parseMaterial.specularColour[2] = Float.valueOf(rgb[3]);
                    }
                    else if (materialLine.startsWith("Kd ")) {
                        final String[] rgb = materialLine.split(" ");
                        parseMaterial.diffuseColour[0] = Float.valueOf(rgb[1]);
                        parseMaterial.diffuseColour[1] = Float.valueOf(rgb[2]);
                        parseMaterial.diffuseColour[2] = Float.valueOf(rgb[3]);
                    }
                    else if (materialLine.startsWith("map_Kd")) {
                        parseMaterial.texture = TextureLoader.getTexture("PNG", (InputStream)new FileInputStream(new File(f.getParentFile().getAbsolutePath() + "/" + materialLine.split(" ")[1])));
                    }
                    else {
                        System.err.println("[MTL] Unknown Line: " + materialLine);
                    }
                }
                m.getMaterials().put(parseMaterialName, parseMaterial);
                materialFileReader.close();
            }
            else if (line.startsWith("usemtl ")) {
                currentMaterial = m.getMaterials().get(line.split(" ")[1]);
            }
            else if (line.startsWith("v ")) {
                final String[] xyz = line.split(" ");
                final float x = Float.valueOf(xyz[1]);
                final float y = Float.valueOf(xyz[2]);
                final float z = Float.valueOf(xyz[3]);
                m.getVertices().add(new Vector3f(x, y, z));
            }
            else if (line.startsWith("vn ")) {
                final String[] xyz = line.split(" ");
                final float x = Float.valueOf(xyz[1]);
                final float y = Float.valueOf(xyz[2]);
                final float z = Float.valueOf(xyz[3]);
                m.getNormals().add(new Vector3f(x, y, z));
            }
            else if (line.startsWith("vt ")) {
                final String[] xyz = line.split(" ");
                final float s = Float.valueOf(xyz[1]);
                final float t = Float.valueOf(xyz[2]);
                m.getTextureCoordinates().add(new Vector2f(s, t));
            }
            else if (line.startsWith("f ")) {
                final String[] faceIndices = line.split(" ");
                final int[] vertexIndicesArray = { Integer.parseInt(faceIndices[1].split("/")[0]), Integer.parseInt(faceIndices[2].split("/")[0]), Integer.parseInt(faceIndices[3].split("/")[0]) };
                final int[] textureCoordinateIndicesArray = { -1, -1, -1 };
                if (m.hasTextureCoordinates()) {
                    textureCoordinateIndicesArray[0] = Integer.parseInt(faceIndices[1].split("/")[1]);
                    textureCoordinateIndicesArray[1] = Integer.parseInt(faceIndices[2].split("/")[1]);
                    textureCoordinateIndicesArray[2] = Integer.parseInt(faceIndices[3].split("/")[1]);
                }
                final int[] normalIndicesArray = { 0, 0, 0 };
                if (m.hasNormals()) {
                    normalIndicesArray[0] = Integer.parseInt(faceIndices[1].split("/")[2]);
                    normalIndicesArray[1] = Integer.parseInt(faceIndices[2].split("/")[2]);
                    normalIndicesArray[2] = Integer.parseInt(faceIndices[3].split("/")[2]);
                }
                m.getFaces().add(new Model.Face(vertexIndicesArray, normalIndicesArray, textureCoordinateIndicesArray, currentMaterial));
            }
            else if (line.startsWith("s ")) {
                final boolean enableSmoothShading = !line.contains("off");
                m.setSmoothShadingEnabled(enableSmoothShading);
            }
            else {
                System.err.println("[OBJ] Unknown Line: " + line);
            }
        }
        reader.close();
        return m;
    }
}
