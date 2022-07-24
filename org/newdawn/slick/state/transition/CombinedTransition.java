//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.state.transition;

import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class CombinedTransition implements Transition
{
    private ArrayList transitions;
    
    public CombinedTransition() {
        this.transitions = new ArrayList();
    }
    
    public void addTransition(final Transition t) {
        this.transitions.add(t);
    }
    
    @Override
    public boolean isComplete() {
        for (int i = 0; i < this.transitions.size(); ++i) {
            if (!this.transitions.get(i).isComplete()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public void postRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
        for (int i = this.transitions.size() - 1; i >= 0; --i) {
            this.transitions.get(i).postRender(game, container, g);
        }
    }
    
    @Override
    public void preRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
        for (int i = 0; i < this.transitions.size(); ++i) {
            this.transitions.get(i).postRender(game, container, g);
        }
    }
    
    @Override
    public void update(final StateBasedGame game, final GameContainer container, final int delta) throws SlickException {
        for (int i = 0; i < this.transitions.size(); ++i) {
            final Transition t = this.transitions.get(i);
            if (!t.isComplete()) {
                t.update(game, container, delta);
            }
        }
    }
    
    @Override
    public void init(final GameState firstState, final GameState secondState) {
        for (int i = this.transitions.size() - 1; i >= 0; --i) {
            this.transitions.get(i).init(firstState, secondState);
        }
    }
}
