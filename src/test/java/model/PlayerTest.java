package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player playerWithArgsConstructor;
    private Player playerWithNoArgsConstructor;

    @BeforeEach
    public void setUp() {
        playerWithArgsConstructor = new Player("John", 0, "127.0.0.1", "First Question");
        playerWithNoArgsConstructor = new Player();
    }

    @Test
    void testGetNameWithArgsConstructor() {
        assertEquals("John", playerWithArgsConstructor.getName());
    }

    @Test
    void testSetNameWithArgsConstructor() {
        playerWithArgsConstructor.setName("Doe");
        assertEquals("Doe", playerWithArgsConstructor.getName());
    }

    @Test
    void testGetScoreWithArgsConstructor() {
        assertEquals(0, playerWithArgsConstructor.getScore());
    }

    @Test
    void testSetScoreWithArgsConstructor() {
        playerWithArgsConstructor.setScore(10);
        assertEquals(10, playerWithArgsConstructor.getScore());
    }

    @Test
    void testIncreaseScoreWithArgsConstructor() {
        playerWithArgsConstructor.increaseScore();
        assertEquals(1, playerWithArgsConstructor.getScore());
    }

    @Test
    void testGetIpWithArgsConstructor() {
        assertEquals("127.0.0.1", playerWithArgsConstructor.getIp());
    }

    @Test
    void testSetIpWithArgsConstructor() {
        playerWithArgsConstructor.setIp("192.168.0.1");
        assertEquals("192.168.0.1", playerWithArgsConstructor.getIp());
    }

    @Test
    void testGetNameWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setName("Jane");
        assertEquals("Jane", playerWithNoArgsConstructor.getName());
    }

    @Test
    void testSetNameWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setName("Doe");
        assertEquals("Doe", playerWithNoArgsConstructor.getName());
    }

    @Test
    void testGetScoreWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setScore(5);
        assertEquals(5, playerWithNoArgsConstructor.getScore());
    }

    @Test
    void testSetScoreWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setScore(15);
        assertEquals(15, playerWithNoArgsConstructor.getScore());
    }

    @Test
    void testIncreaseScoreWithNoArgsConstructor() {
        playerWithNoArgsConstructor.increaseScore();
        assertEquals(1, playerWithNoArgsConstructor.getScore());
    }

    @Test
    void testGetIpWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setIp("10.0.0.1");
        assertEquals("10.0.0.1", playerWithNoArgsConstructor.getIp());
    }

    @Test
    void testSetIpWithNoArgsConstructor() {
        playerWithNoArgsConstructor.setIp("172.16.0.1");
        assertEquals("172.16.0.1", playerWithNoArgsConstructor.getIp());
    }
}