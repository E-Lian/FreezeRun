package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Game.GRAVITY;
import static org.junit.jupiter.api.Assertions.*;
import static ui.GraphicsGame.BLOCK_SIZE;

public class CollisionCheckerTest {
    public CollisionChecker checker;
    public Enemy e;
    public Player player;
    public Block block;
    public ArrayList<Block> blocks;
    public ArrayList<Fireball> fireballs;

    @BeforeEach
    public void setup() {
        checker = new CollisionChecker();
        e = new Enemy(10, 10, 20, 0);
        player = new Player(50, 60);
        fireballs = new ArrayList<>();
        block = new Brick(200, 200);
        blocks = new ArrayList<>();
        blocks.add(block);
    }

    @Test
    public void testCheckEnemyPlayerCollision() {
        player.setCx(12);
        player.setCy(9);
        assertTrue(checker.checkEnemyPlayerCollision(e, player));
        player.setCx(100);
        assertFalse(checker.checkEnemyPlayerCollision(e, player));
    }

    @Test
    public void testCheckEnemyFireballCollisionNoFireballs() {
        assertNull(checker.checkEnemyFireballCollision(e, fireballs));
    }

    @Test
    public void testCheckEnemyFireballCollisionNoCollision() {
        fireballs.add(new Fireball(10, 100, false));
        fireballs.add(new Fireball(400, 10, true));
        assertNull(checker.checkEnemyFireballCollision(e, fireballs));
    }

    @Test
    public void testCheckEnemyFireballCollisionCollided() {
        fireballs.add(new Fireball(10, 100, false));
        fireballs.add(new Fireball(400, 10, true));
        Fireball f = new Fireball(12, 10, true);
        fireballs.add(f);
        assertEquals(f, checker.checkEnemyFireballCollision(e, fireballs));
    }

    // enemy collide with block at its right
    @Test
    public void testCheckBlockCollisionEnemyRight() {
        blocks.add(new Brick(40, 10));
        checker.checkBlockCollision(e, blocks);
        assertEquals(-20, e.getDx());
    }

    // enemy collide with block at its left
    @Test
    public void testCheckBlockCollisionEnemyLeft() {
        e.setCx(220);
        e.setCy(201);
        e.setDx(-20);
        checker.checkBlockCollision(e, blocks);
        assertEquals(20, e.getDx());
    }


    // bottom collision
    @Test
    public void testCheckBlockCollisionBottom() {
        blocks.add(new Brick(50, 90));
        checker.checkBlockCollision(player, blocks);
        assertFalse(player.isFalling());
        assertEquals(0, player.getDy());
        assertEquals(90 - BLOCK_SIZE + 1, player.getCy());
        assertEquals(50, player.getCx());
    }

    // top collision
    @Test
    public void testCheckBlockCollisionTop() {
        player.setJumping(true);
        blocks.add(new Brick(50, 35));
        checker.checkBlockCollision(player, blocks);
        assertTrue(player.isFalling());
        assertEquals(0, player.getDy());
        assertEquals(35 + BLOCK_SIZE, player.getCy());
        assertEquals(50, player.getCx());
    }

    // left collision
    @Test
    public void testCheckBlockCollisionLeft() {
        player.setDx(-10);
        blocks.add(new Brick(30, 60));
        checker.checkBlockCollision(player, blocks);
        assertEquals(54, player.getCx());
        assertEquals(60, player.getCy());
    }


    // right collision
    @Test
    public void testCheckBlockCollisionRight() {
        player.setDx(10);
        blocks.add(new Brick(70, 60));
        checker.checkBlockCollision(player, blocks);
        assertEquals(42, player.getCx());
        assertEquals(60, player.getCy());
    }

    // top left
    @Test
    public void testCheckBlockCollisionTopLeft() {
        player.setJumping(true);
        player.setDx(-10);
        player.setDy(-4);
        blocks.add(new Brick(40, 35));
        checker.checkBlockCollision(player, blocks);
        assertTrue(player.isFalling());
        assertEquals(0, player.getDy());
        assertEquals(50, player.getCx());
        assertEquals(35 + BLOCK_SIZE, player.getCy());
    }

    // bottom right
    @Test
    public void testCheckBlockCollisionBottomRight() {
        //player: 50, 60
        player.setFalling(true);
        player.setDx(20);
        player.setDy(10);
        blocks.add(new Brick(65, 80));
        checker.checkBlockCollision(player, blocks);
        assertEquals(50, player.getCx());
        assertFalse(player.isFalling());
        assertEquals(0, player.getDy());
        assertEquals(80 - BLOCK_SIZE + 1, player.getCy());
    }

    // no collision
    @Test
    public void testCheckBlockCollisionNoCollision() {
        checker.checkBlockCollision(player, blocks);
        assertEquals(50, player.getCx());
        assertEquals(60, player.getCy());
        assertEquals(0, player.getDx());
        assertEquals(0, player.getDy());
        assertTrue(player.isFalling());
        assertFalse(player.isJumping());
    }

    @Test
    public void testCheckFireballBlocksCollisionNoCollision() {
        Fireball f = new Fireball(10, 200, true);
        assertFalse(checker.checkFireballBlocksCollision(f, blocks));
    }

    @Test
    public void testCheckFireballBlockCollisionLeft() {
        Fireball f = new Fireball(210, 200, false);
        assertTrue(checker.checkFireballBlocksCollision(f, blocks));
    }

    @Test
    public void testCheckFireballBlockCollisionRight() {
        Fireball f = new Fireball(190, 210, true);
        assertTrue(checker.checkFireballBlocksCollision(f, blocks));
    }
}
