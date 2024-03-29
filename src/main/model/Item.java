package model;

import static ui.GraphicsGame.BLOCK_SIZE;

public abstract class Item extends Block {
    protected boolean activated;
    protected long timeOfActivate;
    protected long period;

    public Item(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
        timeOfActivate = System.currentTimeMillis();
        loadImg();
        makeHitBox();
    }

    // MODIFIES: this
    // EFFECTS: loads different images based on activated
    public abstract void loadImg();

    // MODIFIES: this
    // EFFECTS: reset activate if possible
    public void update() {
        if (System.currentTimeMillis() - timeOfActivate >= period) {
            activated = false;
            loadImg();
        }
    }

    // MODIFIES: this
    // EFFECTS: activate the item, loads new image, and record activate time
    public void activate() {
        this.activated = true;
        setTimeOfActivate(System.currentTimeMillis());
        loadImg();
    }

    public void setTimeOfActivate(long timeOfActivate) {
        this.timeOfActivate = timeOfActivate;
    }

    public boolean isActivated() {
        return activated;
    }
}
