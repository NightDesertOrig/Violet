//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.teamviolet.violet.client.util.render.models;

import org.lwjgl.util.vector.*;
import java.util.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;

public class Model
{
    private final List<Vector3f> vertices;
    private final List<Vector2f> textureCoordinates;
    private final List<Vector3f> normals;
    private final List<Face> faces;
    private final HashMap<String, Material> materials;
    private boolean enableSmoothShading;
    
    public Model() {
        this.vertices = new ArrayList<Vector3f>();
        this.textureCoordinates = new ArrayList<Vector2f>();
        this.normals = new ArrayList<Vector3f>();
        this.faces = new ArrayList<Face>();
        this.materials = new HashMap<String, Material>();
        this.enableSmoothShading = true;
    }
    
    public void enableStates() {
        if (this.hasTextureCoordinates()) {
            GL11.glEnable(3553);
        }
        if (this.isSmoothShadingEnabled()) {
            GL11.glShadeModel(7425);
        }
        else {
            GL11.glShadeModel(7424);
        }
    }
    
    public boolean hasTextureCoordinates() {
        return this.getTextureCoordinates().size() > 0;
    }
    
    public boolean hasNormals() {
        return this.getNormals().size() > 0;
    }
    
    public List<Vector3f> getVertices() {
        return this.vertices;
    }
    
    public List<Vector2f> getTextureCoordinates() {
        return this.textureCoordinates;
    }
    
    public List<Vector3f> getNormals() {
        return this.normals;
    }
    
    public List<Face> getFaces() {
        return this.faces;
    }
    
    public boolean isSmoothShadingEnabled() {
        return this.enableSmoothShading;
    }
    
    public void setSmoothShadingEnabled(final boolean smoothShadingEnabled) {
        this.enableSmoothShading = smoothShadingEnabled;
    }
    
    public HashMap<String, Material> getMaterials() {
        return this.materials;
    }
    
    public static class Material
    {
        public float specularCoefficient;
        public float[] ambientColour;
        public float[] diffuseColour;
        public float[] specularColour;
        public Texture texture;
        
        public Material() {
            this.specularCoefficient = 100.0f;
            this.ambientColour = new float[] { 0.2f, 0.2f, 0.2f };
            this.diffuseColour = new float[] { 0.3f, 1.0f, 1.0f };
            this.specularColour = new float[] { 1.0f, 1.0f, 1.0f };
        }
        
        @Override
        public String toString() {
            return "Material{specularCoefficient=" + this.specularCoefficient + ", ambientColour=" + this.ambientColour + ", diffuseColour=" + this.diffuseColour + ", specularColour=" + this.specularColour + '}';
        }
    }
    
    public static class Face
    {
        private final int[] vertexIndices;
        private final int[] normalIndices;
        private final int[] textureCoordinateIndices;
        private Material material;
        
        public Material getMaterial() {
            return this.material;
        }
        
        public boolean hasNormals() {
            return this.normalIndices[0] != -1;
        }
        
        public boolean hasTextureCoordinates() {
            return this.textureCoordinateIndices[0] != -1;
        }
        
        public int[] getVertexIndices() {
            return this.vertexIndices;
        }
        
        public int[] getTextureCoordinateIndices() {
            return this.textureCoordinateIndices;
        }
        
        public int[] getNormalIndices() {
            return this.normalIndices;
        }
        
        public Face(final int[] vertexIndices) {
            this.vertexIndices = new int[] { -1, -1, -1 };
            this.normalIndices = new int[] { -1, -1, -1 };
            this.textureCoordinateIndices = new int[] { -1, -1, -1 };
            this.vertexIndices[0] = vertexIndices[0];
            this.vertexIndices[1] = vertexIndices[1];
            this.vertexIndices[2] = vertexIndices[2];
        }
        
        public Face(final int[] vertexIndices, final int[] normalIndices) {
            this.vertexIndices = new int[] { -1, -1, -1 };
            this.normalIndices = new int[] { -1, -1, -1 };
            this.textureCoordinateIndices = new int[] { -1, -1, -1 };
            this.vertexIndices[0] = vertexIndices[0];
            this.vertexIndices[1] = vertexIndices[1];
            this.vertexIndices[2] = vertexIndices[2];
            this.normalIndices[0] = normalIndices[0];
            this.normalIndices[1] = normalIndices[1];
            this.normalIndices[2] = normalIndices[2];
        }
        
        public Face(final int[] vertexIndices, final int[] normalIndices, final int[] textureCoordinateIndices, final Material material) {
            this.vertexIndices = new int[] { -1, -1, -1 };
            this.normalIndices = new int[] { -1, -1, -1 };
            this.textureCoordinateIndices = new int[] { -1, -1, -1 };
            this.vertexIndices[0] = vertexIndices[0];
            this.vertexIndices[1] = vertexIndices[1];
            this.vertexIndices[2] = vertexIndices[2];
            this.textureCoordinateIndices[0] = textureCoordinateIndices[0];
            this.textureCoordinateIndices[1] = textureCoordinateIndices[1];
            this.textureCoordinateIndices[2] = textureCoordinateIndices[2];
            this.normalIndices[0] = normalIndices[0];
            this.normalIndices[1] = normalIndices[1];
            this.normalIndices[2] = normalIndices[2];
            this.material = material;
        }
    }
}
