//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests.states;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.transition.*;

public class TestState3 extends BasicGameState
{
    public static final int ID = 3;
    private Font font;
    private String[] options;
    private int selected;
    private StateBasedGame game;
    
    public TestState3() {
        this.options = new String[] { "Start Game", "Credits", "Highscores", "Instructions", "Exit" };
    }
    
    public int getID() {
        return 3;
    }
    
    public void init(final GameContainer container, final StateBasedGame game) throws SlickException {
        this.font = (Font)new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
        this.game = game;
    }
    
    public void render(final GameContainer container, final StateBasedGame game, final Graphics g) {
        g.setFont(this.font);
        g.setColor(Color.blue);
        g.drawString("This is State 3", 200.0f, 50.0f);
        g.setColor(Color.white);
        for (int i = 0; i < this.options.length; ++i) {
            g.drawString(this.options[i], (float)(400 - this.font.getWidth(this.options[i]) / 2), (float)(200 + i * 50));
            if (this.selected == i) {
                g.drawRect(200.0f, (float)(190 + i * 50), 400.0f, 50.0f);
            }
        }
    }
    
    public void update(final GameContainer container, final StateBasedGame game, final int delta) {
    }
    
    public void keyReleased(final int key, final char c) {
        if (key == 208) {
            ++this.selected;
            if (this.selected >= this.options.length) {
                this.selected = 0;
            }
        }
        if (key == 200) {
            --this.selected;
            if (this.selected < 0) {
                this.selected = this.options.length - 1;
            }
        }
        if (key == 2) {
            this.game.enterState(1, (Transition)new FadeOutTransition(Color.black), (Transition)new FadeInTransition(Color.black));
        }
        if (key == 3) {
            this.game.enterState(2, (Transition)new FadeOutTransition(Color.black), (Transition)new FadeInTransition(Color.black));
        }
    }
}
