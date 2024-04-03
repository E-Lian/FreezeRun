package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ui.GraphicsGame.BLOCK_SIZE;

public class BlockTest {
    public Block block;

    @BeforeEach
    public void setup() {
        block = new Brick(0, 0);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, block.getCx());
        assertEquals(0, block.getCy());
    }

    @Test
    public void testSetter() {
        assertEquals(0, block.getCx());
        assertEquals(0, block.getCy());
        block.setCx(10);
        block.setCy(20);
        assertEquals(10, block.getCx());
        assertEquals(20, block.getCy());
    }

    @Test
    public void testGetImg() {
        assertEquals(16, block.getImg().getHeight());
    }

    @Test
    public void testHitBoxGeneral() {
        Rectangle hitBox = block.getHitBox();
        assertEquals(0, hitBox.getX());
        assertEquals(0, hitBox.getY());
        assertEquals(BLOCK_SIZE, hitBox.getWidth());
        assertEquals(BLOCK_SIZE, hitBox.getHeight());
    }

    @Test
    public void testGetLeftBox() {
        Rectangle leftBox = block.getLeftBox();
        assertEquals(0, leftBox.getX());
        assertEquals(8, leftBox.getY());
        assertEquals(8, leftBox.getWidth());
        assertEquals(16, leftBox.getHeight());
    }

    @Test
    public void testGetRightBox() {
        Rectangle rightBox = block.getRightBox();
        assertEquals(24, rightBox.getX());
        assertEquals(8, rightBox.getY());
        assertEquals(8, rightBox.getWidth());
        assertEquals(16, rightBox.getHeight());
    }

    @Test
    public void testGetTopBox() {
        Rectangle topBox = block.getTopBox();
        assertEquals(5, topBox.getX());
        assertEquals(0, topBox.getY());
        assertEquals(21, topBox.getWidth());
        assertEquals(16, topBox.getHeight());
    }

    @Test
    public void testGetBottomBox() {
        Rectangle bottomBox = block.getBottomBox();
        assertEquals(5, bottomBox.getX());
        assertEquals(16, bottomBox.getY());
        assertEquals(21, bottomBox.getWidth());
        assertEquals(16, bottomBox.getHeight());
    }
}
