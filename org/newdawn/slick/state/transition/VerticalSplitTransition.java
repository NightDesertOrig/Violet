//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.state.transition;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.renderer.*;

public class VerticalSplitTransition implements Transition
{
    protected static SGL GL;
    private GameState prev;
    private float offset;
    private boolean finish;
    private Color background;
    
    public VerticalSplitTransition() {
    }
    
    public VerticalSplitTransition(final Color background) {
        this.background = background;
    }
    
    public void init(final GameState firstState, final GameState secondState) {
        this.prev = secondState;
    }
    
    public boolean isComplete() {
        return this.finish;
    }
    
    public void postRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
        g.translate(0.0f, -this.offset);
        g.setClip(0, (int)(-this.offset), container.getWidth(), container.getHeight() / 2);
        if (this.background != null) {
            final Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0f, 0.0f, (float)container.getWidth(), (float)container.getHeight());
            g.setColor(c);
        }
        VerticalSplitTransition.GL.glPushMatrix();
        this.prev.render(container, game, g);
        VerticalSplitTransition.GL.glPopMatrix();
        g.clearClip();
        g.resetTransform();
        g.translate(0.0f, this.offset);
        g.setClip(0, (int)(container.getHeight() / 2 + this.offset), container.getWidth(), container.getHeight() / 2);
        if (this.background != null) {
            final Color c = g.getColor();
            g.setColor(this.background);
            g.fillRect(0.0f, 0.0f, (float)container.getWidth(), (float)container.getHeight());
            g.setColor(c);
        }
        VerticalSplitTransition.GL.glPushMatrix();
        this.prev.render(container, game, g);
        VerticalSplitTransition.GL.glPopMatrix();
        g.clearClip();
        g.translate(0.0f, -this.offset);
    }
    
    public void preRender(final StateBasedGame game, final GameContainer container, final Graphics g) throws SlickException {
    }
    
    public void update(final StateBasedGame game, final GameContainer container, final int delta) throws SlickException {
        this.offset += delta * 1.0f;
        if (this.offset > container.getHeight() / 2) {
            this.finish = true;
        }
    }
    
    static {
        VerticalSplitTransition.GL = Renderer.get();
    }
}
