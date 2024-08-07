package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Event e2;
    private Event e3;
    private Date d;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Sensor open at door"); // (1)
        d = Calendar.getInstance().getTime(); // (2)
        e2 = new Event("Sensor open at door");
        e3 = new Event("Sensor closed at door");
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(e, e);
        assertEquals(e, e2);
        assertNotEquals(e, null);
        assertNotEquals(e, "Not an Event");
        assertNotEquals(e, e3);
    }

    @Test
    public void testHashCode() {
        assertEquals(e.hashCode(), e2.hashCode());
        assertNotEquals(e.hashCode(), e3.hashCode());
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(e.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(e.equals("Not an Event"));
    }
}
