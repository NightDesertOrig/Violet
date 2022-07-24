//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.loading;

import java.util.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.openal.*;
import org.newdawn.slick.util.*;

public class LoadingList
{
    private static LoadingList single;
    private ArrayList deferred;
    private int total;
    
    public static LoadingList get() {
        return LoadingList.single;
    }
    
    public static void setDeferredLoading(final boolean loading) {
        LoadingList.single = new LoadingList();
        InternalTextureLoader.get().setDeferredLoading(loading);
        SoundStore.get().setDeferredLoading(loading);
    }
    
    public static boolean isDeferredLoading() {
        return InternalTextureLoader.get().isDeferredLoading();
    }
    
    private LoadingList() {
        this.deferred = new ArrayList();
    }
    
    public void add(final DeferredResource resource) {
        ++this.total;
        this.deferred.add(resource);
    }
    
    public void remove(final DeferredResource resource) {
        Log.info("Early loading of deferred resource due to req: " + resource.getDescription());
        --this.total;
        this.deferred.remove(resource);
    }
    
    public int getTotalResources() {
        return this.total;
    }
    
    public int getRemainingResources() {
        return this.deferred.size();
    }
    
    public DeferredResource getNext() {
        if (this.deferred.size() == 0) {
            return null;
        }
        return this.deferred.remove(0);
    }
    
    static {
        LoadingList.single = new LoadingList();
    }
}
