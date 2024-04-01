package model;

import java.awt.*;
import java.util.ArrayList;

import static ui.GraphicsGame.BLOCK_SIZE;

// a tool for checking collision between different objects
public class CollisionChecker {

    // EFFECTS: returns true if given enemy collides with given player
    public boolean checkEnemyPlayerCollision(Enemy e, Player p) {
        return e.getHitBox().intersects(p.getHitBox());
    }

    // EFFECTS: returns the fireball that collides with given enemy, if any, otherwise returns null
    public Fireball checkEnemyFireballCollision(Enemy e, ArrayList<Fireball> fireballs) {
        for (Fireball f : fireballs) {
            if (f.getHitBox().intersects(e.getHitBox())) {
                return f;
            }
        }
        return null;
    }

    // MODIFIES: c
    // EFFECTS: check for collision with solid blocks, reset dx/dy and cx/cy if character does collide with block
    public void checkBlockCollision(Character c, ArrayList<Block> blocks) {
        checkBottomCollision(c, blocks);
        checkTopCollision(c, blocks);
        checkHorizontalCollision(c, blocks);
    }

    // MODIFIES: player
    // EFFECTS: set
    public void checkBottomCollision(Player player, ArrayList<Fireball> fireballs) {
        for (Fireball fireball : fireballs) {
            Rectangle blockTopBox = fireball.getTopBox();
            if (player.getBottomBox().intersects(blockTopBox)) {
                player.setCy(fireball.getCy() - BLOCK_SIZE + 1);
                player.setFalling(false);
                player.setDy(0);
            }
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dy to 0 and correct c.cy if its bottom hits any block's top
    private void checkBottomCollision(Character c, ArrayList<Block> blocks) {
        if (!c.isJumping()) {
            c.setFalling(true);
        }

        for (Block block : blocks) {
            Rectangle blockTopBox = block.getTopBox();
            if (c.getBottomBox().intersects(blockTopBox)) {
                c.setCy(block.getCy() - BLOCK_SIZE + 1);
                c.setFalling(false);
                c.setDy(0);
            }
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dy to gravity and correct c.cy if its top hits any block's bottom
    private void checkTopCollision(Character c, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            Rectangle blockBottomBox = block.getBottomBox();
            if (c.getTopBox().intersects(blockBottomBox)) {
                c.setDy(0);
                c.setCy(block.getCy() + BLOCK_SIZE);
            }
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dx to 0 and correct c.cx if its left/right box hits any block's left/right box
    // if c is an Enemy, then simply reverse its dx direction
    private void checkHorizontalCollision(Character c, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (c instanceof Enemy) {
                if (c.getRightBox().intersects(block.getLeftBox()) || c.getLeftBox().intersects(block.getRightBox())) {
                    c.setDx(-c.getDx());
                    return;
                }
            }
            if (c.getRightBox().intersects(block.getLeftBox())) {
                c.setDx(0);
                c.setCx((int) (block.getCx() + c.getCx() - c.getRightBox().getX() - c.getRightBox().getWidth()));
            } else if (c.getLeftBox().intersects(block.getRightBox())) {
                c.setDx(0);
                c.setCx(block.getCx() + BLOCK_SIZE - (c.getHitBox().x - c.getCx()));
            }
        }
    }

    // EFFECTS: returns true if fireball touch any blocks, false otherwise
    public boolean checkFireballBlocksCollision(Fireball f, ArrayList<Block> blocks) {
        for (Block block : blocks) {
            if (f.getHitBox().intersects(block.getHitBox())) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: door
    // EFFECTS: set door to open if player is at door
    public void checkPlayerDoor(Player player, Door door) {
        door.setOpen(player.getHitBox().intersects(door.getHitBox()));
    }

    // MODIFIES: c, items
    // EFFECTS: check c's collisions with items, set dx/dy and cx/cy if applicable
    public void checkItemsCollision(Character c, ArrayList<Item> items) {
        checkBottomCollisionItem(c, items);
        checkHorizontalCollisionItem(c, items);
    }

    // MODIFIES: c< items
    // EFFECTS: set dx and cx if c's left/right collides with item
    private void checkHorizontalCollisionItem(Character c, ArrayList<Item> items) {
        for (Block item : items) {
            if (c.getRightBox().intersects(item.getLeftBox())) {
                c.setDx(0);
                c.setCx((int) (item.getCx() + c.getCx() - c.getRightBox().getX() - c.getRightBox().getWidth()));
            } else if (c.getLeftBox().intersects(item.getRightBox())) {
                c.setDx(0);
                c.setCx(item.getCx() + BLOCK_SIZE - (c.getHitBox().x - c.getCx()));
            }
        }
    }

    // MODIFIES: c, items
    // EFFECTS: set dy and cy if c's bottom touches item's top
    private void checkBottomCollisionItem(Character c, ArrayList<Item> items) {
        for (Block item : items) {
            if (c.getBottomBox().intersects(item.getTopBox())) {
                if (item instanceof Trampoline && !((Trampoline) item).isActivated()) {
                    ((Trampoline) item).activate();
                    c.setDy(-20);
                    c.setCy(item.getCy() - BLOCK_SIZE);
                    c.setJumping(true);
                }
            }
        }
    }
}
