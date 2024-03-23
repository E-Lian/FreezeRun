package model;

import java.util.ArrayList;

import static model.Game.GRAVITY;
import static ui.GraphicsGame.BLOCK_SIZE;

// a tool for checking collision between different objects
public class CollisionChecker {

    // EFFECTS: returns true if given enemy collides with given player
    public boolean checkEnemyPlayerCollision(Enemy e, Player p) {
        return e.getHitBox().intersects(p.getHitBox());
    }

    // EFFECTS: returns the fireball that collides with given enemy, if any, otherwise returns null
    public Fireball checkEnemyFireballCollsion(Enemy e, ArrayList<Fireball> fireballs) {
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
        for (Block block : blocks) {
            if (c.getHitBox().intersects(block.getHitBox())) {
                checkBottomCollision(c, block);
                checkTopCollision(c, block);
                checkHorizontalCollision(c, block);
            }
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dy to 0 and correct c.cy if its bottom hits the block's top
    private void checkBottomCollision(Character c, Block block) {
        if (!c.isJumping()) {
            c.setFalling(true);
        }

        if (c.getBottomBox().intersects(block.getTopBox())) {
            c.setDy(0);
            c.setCy(block.getCy() - BLOCK_SIZE);
            c.setFalling(false);
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dy to gravity and correct c.cy if its top hits the block's bottom
    private void checkTopCollision(Character c, Block block) {
        if (c.getTopBox().intersects(block.getBottomBox())) {
            c.setDy(GRAVITY);
            c.setCy(block.getCy() + BLOCK_SIZE);
            c.setFalling(true);
            c.setJumping(false);
        }
    }

    // MODIFIES: c
    // EFFECTS: set c.dx to 0 and correct c.cx if its left/right box hits the block's left/right box
    // if c is an Enemy, then simply reverse its dx direction
    private void checkHorizontalCollision(Character c, Block block) {
        if (c instanceof Enemy) {
            if (c.getRightBox().intersects(block.getLeftBox()) || c.getLeftBox().intersects(block.getRightBox())) {
                c.setDx(-c.getDx());
                return;
            }
        }

        if (c.getRightBox().intersects(block.getLeftBox())) {
            // TODO: setCx needs to be fixed
            c.setDx(0);
            c.setCx((int) (block.getCx() - BLOCK_SIZE + c.getCx() - (c.getHitBox().getX() + c.getHitBox().width)));
        } else if (c.getLeftBox().intersects(block.getRightBox())) {
            c.setDx(0);
            c.setCx(block.getCx() + BLOCK_SIZE - (c.getHitBox().x - c.getCx()));
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
}
