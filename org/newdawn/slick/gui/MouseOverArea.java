//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\user\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.gui;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class MouseOverArea extends AbstractComponent
{
    private static final int NORMAL = 1;
    private static final int MOUSE_DOWN = 2;
    private static final int MOUSE_OVER = 3;
    private Image normalImage;
    private Image mouseOverImage;
    private Image mouseDownImage;
    private Color normalColor;
    private Color mouseOverColor;
    private Color mouseDownColor;
    private Sound mouseOverSound;
    private Sound mouseDownSound;
    private Shape area;
    private Image currentImage;
    private Color currentColor;
    private boolean over;
    private boolean mouseDown;
    private int state;
    private boolean mouseUp;
    
    public MouseOverArea(final GUIContext container, final Image image, final int x, final int y, final ComponentListener listener) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
        this.addListener(listener);
    }
    
    public MouseOverArea(final GUIContext container, final Image image, final int x, final int y) {
        this(container, image, x, y, image.getWidth(), image.getHeight());
    }
    
    public MouseOverArea(final GUIContext container, final Image image, final int x, final int y, final int width, final int height, final ComponentListener listener) {
        this(container, image, x, y, width, height);
        this.addListener(listener);
    }
    
    public MouseOverArea(final GUIContext container, final Image image, final int x, final int y, final int width, final int height) {
        this(container, image, (Shape)new Rectangle((float)x, (float)y, (float)width, (float)height));
    }
    
    public MouseOverArea(final GUIContext container, final Image image, final Shape shape) {
        super(container);
        this.normalColor = Color.white;
        this.mouseOverColor = Color.white;
        this.mouseDownColor = Color.white;
        this.state = 1;
        this.area = shape;
        this.normalImage = image;
        this.currentImage = image;
        this.mouseOverImage = image;
        this.mouseDownImage = image;
        this.currentColor = this.normalColor;
        this.state = 1;
        final Input input = container.getInput();
        this.over = this.area.contains((float)input.getMouseX(), (float)input.getMouseY());
        this.mouseDown = input.isMouseButtonDown(0);
        this.updateImage();
    }
    
    public void setLocation(final float x, final float y) {
        if (this.area != null) {
            this.area.setX(x);
            this.area.setY(y);
        }
    }
    
    public void setX(final float x) {
        this.area.setX(x);
    }
    
    public void setY(final float y) {
        this.area.setY(y);
    }
    
    public int getX() {
        return (int)this.area.getX();
    }
    
    public int getY() {
        return (int)this.area.getY();
    }
    
    public void setNormalColor(final Color color) {
        this.normalColor = color;
    }
    
    public void setMouseOverColor(final Color color) {
        this.mouseOverColor = color;
    }
    
    public void setMouseDownColor(final Color color) {
        this.mouseDownColor = color;
    }
    
    public void setNormalImage(final Image image) {
        this.normalImage = image;
    }
    
    public void setMouseOverImage(final Image image) {
        this.mouseOverImage = image;
    }
    
    public void setMouseDownImage(final Image image) {
        this.mouseDownImage = image;
    }
    
    public void render(final GUIContext container, final Graphics g) {
        if (this.currentImage != null) {
            final int xp = (int)(this.area.getX() + (this.getWidth() - this.currentImage.getWidth()) / 2);
            final int yp = (int)(this.area.getY() + (this.getHeight() - this.currentImage.getHeight()) / 2);
            this.currentImage.draw((float)xp, (float)yp, this.currentColor);
        }
        else {
            g.setColor(this.currentColor);
            g.fill(this.area);
        }
        this.updateImage();
    }
    
    private void updateImage() {
        if (!this.over) {
            this.currentImage = this.normalImage;
            this.currentColor = this.normalColor;
            this.state = 1;
            this.mouseUp = false;
        }
        else {
            if (this.mouseDown) {
                if (this.state != 2 && this.mouseUp) {
                    if (this.mouseDownSound != null) {
                        this.mouseDownSound.play();
                    }
                    this.currentImage = this.mouseDownImage;
                    this.currentColor = this.mouseDownColor;
                    this.state = 2;
                    this.notifyListeners();
                    this.mouseUp = false;
                }
                return;
            }
            this.mouseUp = true;
            if (this.state != 3) {
                if (this.mouseOverSound != null) {
                    this.mouseOverSound.play();
                }
                this.currentImage = this.mouseOverImage;
                this.currentColor = this.mouseOverColor;
                this.state = 3;
            }
        }
        this.mouseDown = false;
        this.state = 1;
    }
    
    public void setMouseOverSound(final Sound sound) {
        this.mouseOverSound = sound;
    }
    
    public void setMouseDownSound(final Sound sound) {
        this.mouseDownSound = sound;
    }
    
    public void mouseMoved(final int oldx, final int oldy, final int newx, final int newy) {
        this.over = this.area.contains((float)newx, (float)newy);
    }
    
    public void mouseDragged(final int oldx, final int oldy, final int newx, final int newy) {
        this.mouseMoved(oldx, oldy, newx, newy);
    }
    
    public void mousePressed(final int button, final int mx, final int my) {
        this.over = this.area.contains((float)mx, (float)my);
        if (button == 0) {
            this.mouseDown = true;
        }
    }
    
    public void mouseReleased(final int button, final int mx, final int my) {
        this.over = this.area.contains((float)mx, (float)my);
        if (button == 0) {
            this.mouseDown = false;
        }
    }
    
    public int getHeight() {
        return (int)(this.area.getMaxY() - this.area.getY());
    }
    
    public int getWidth() {
        return (int)(this.area.getMaxX() - this.area.getX());
    }
    
    public boolean isMouseOver() {
        return this.over;
    }
    
    public void setLocation(final int x, final int y) {
        this.setLocation((float)x, (float)y);
    }
}
