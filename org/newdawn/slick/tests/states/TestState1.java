//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.states;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

public class TestState1 extends BasicGameState
{
    public static final int ID = 1;
    private Font font;
    private StateBasedGame game;
    
    public int getID() {
        return 1;
    }
    
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
        this.game = game;
        this.font = (Font)new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    }
    
    public void render(final GameContainer container, final StateBasedGame game, final Graphics g) {
        g.setFont(this.font);
        g.setColor(Color.white);
        g.drawString("State Based Game Test", 100.0f, 100.0f);
        g.drawString("Numbers 1-3 will switch between states.", 150.0f, 300.0f);
        g.setColor(Color.red);
        g.drawString("This is State 1", 200.0f, 50.0f);
    }
    
    public void update(final GameContainer container, final StateBasedGame game, final int delta) {
    }
    
    public void keyReleased(final int key, final char c) {
        if (key == 3) {
            final GameState target = this.game.getState(2);
            final long start = System.currentTimeMillis();
            final CrossStateTransition t = new CrossStateTransition(target) {
                public boolean isComplete() {
                    return System.currentTimeMillis() - start > 2000L;
                }
                
                public void init(final GameState firstState, final GameState secondState) {
                }
            };
            this.game.enterState(2, (Transition)t, (Transition)new EmptyTransition());
        }
        if (key == 4) {
            this.game.enterState(3, (Transition)new FadeOutTransition(Color.black), (Transition)new FadeInTransition(Color.black));
        }
    }
}
