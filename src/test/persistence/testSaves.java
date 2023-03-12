package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class testSaves {
    Saves saves;

    @BeforeEach
    public void setup() {
        saves = new Saves();
    }

    @Test
    public void testSaves1() {
        try {
            saves.open();
            saves.addFile("BALLER");
            saves.addFile("HELLOOOO");
            saves.close();
        } catch (Exception except) {
            fail("Unexppected failure");
        }

        saves = new Saves();

        try {
            saves.open();
            saves.addFile("Test2");
            saves.addFile("Test1");
            saves.close();
        } catch (Exception except) {
            fail("Unexppected failure");
        }

        try {
            saves.open();
            saves.addFile("Test2");
            saves.addFile("kenya");
            saves.close();
        } catch (Exception except) {
            fail("Unexppected failure");
        }

        try {
            saves.open();
            saves.removeFile("Test1");
            saves.removeFile("Test2");
            saves.close();
        } catch (Exception except) {
            fail("Unexppected failure");
        }
    }
}


