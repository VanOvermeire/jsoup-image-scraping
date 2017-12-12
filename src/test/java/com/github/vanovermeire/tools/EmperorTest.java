package com.github.vanovermeire.tools;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmperorTest {

    @Test
    public void testAugustusPattern() {
        String example = "something Augustus something something";

        boolean b = Emperor.AUGUSTUS.isEmperor(example);

        assertTrue(b);
    }

    @Test
    public void testAugustusNotTrue() {
        String example = "Nero something something";

        boolean b = Emperor.AUGUSTUS.isEmperor(example);

        assertFalse(b);
    }

    @Test
    public void testAugustusNotTrueNero() {
        String example = "Nero Augustus something something";

        boolean b = Emperor.AUGUSTUS.isEmperor(example);

        assertFalse(b);
    }

    @Test
    public void testNero() {
        String example = "Nero Augustus something something";

        boolean b = Emperor.NERO.isEmperor(example);

        assertTrue(b);
    }

    @Test
    public void testNeroNotTrue() {
        String example = "Claudius something something";

        boolean b = Emperor.NERO.isEmperor(example);

        assertTrue(b);
    }

    @Test
    public void testEmperors() {
        String example = "Nero something something";

        Optional<Emperor> emperor = Emperor.getEmperor(example);

        assertTrue(emperor.isPresent());
        assertEquals(emperor.get(), Emperor.NERO);
    }

}
