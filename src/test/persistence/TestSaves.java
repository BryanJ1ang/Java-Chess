package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class TestSaves {
    Saves saves;

    @BeforeEach
    public void setup() {
        saves = new Saves();
    }

    @Test
    public void testSaves1() {
        try {
            saves.clearSaves();
            saves.open();
            saves.addFile("BALLER");
            saves.addFile("HELLOOOO");
            saves.addFile("HELLOOOO");
            saves.close();
        } catch (Exception except) {
            fail("Unexpected failure");
        }

        assertTrue(saves.doesSaveExist("BALLER"));
        assertTrue(saves.doesSaveExist("HELLOOOO"));
        assertEquals(saves.listOfSaves().size(), 2);
    }

    @Test
    public void testSaves2() {
        try {
            saves.open();
            saves.addFile("Test2");
            saves.addFile("Test1");
            saves.close();
        } catch (Exception except) {
            fail("Unexpected failure");
        }
        assertEquals(saves.listOfSaves().size(), 4);

    assertTrue(saves.doesSaveExist("Test2"));
    assertTrue(saves.doesSaveExist("Test1"));
        try {
            saves.open();
            saves.removeFile("Test1");
            saves.removeFile("Test2");
            saves.close();
        } catch (Exception except) {
            fail("Unexpected failure");
        }
        assertFalse(saves.doesSaveExist("Test1"));
        assertFalse(saves.doesSaveExist("Test2"));
        assertEquals(saves.listOfSaves().size(), 4);

        try {
            saves.open();
            saves.clearSaves();
            saves.close();
        } catch (FileNotFoundException e) {
            fail("Unexpected failure");
        }
        assertEquals(0, saves.listOfSaves().size());

    }
}


