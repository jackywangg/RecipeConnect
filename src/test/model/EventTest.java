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
    private Event event;
    private Event event2;
    private Event event3;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Sensor open at door"); // (1)
        date = Calendar.getInstance().getTime(); // (2)
        event2 = new Event("Sensor open at door");
        event3 = new Event("Sensor closed at door");
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Sensor open at door", event.toString());
    }

    @Test
    public void testEquals() {
        assertEquals(event, event);
        assertEquals(event, event2);
        assertNotEquals(event, null);
        assertNotEquals(event, "Not an Event");
        assertNotEquals(event, event3);
    }

    @Test
    public void testHashCode() {
        assertEquals(event.hashCode(), event2.hashCode());
        assertNotEquals(event.hashCode(), event3.hashCode());
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(event.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertFalse(event.equals("Not an Event"));
    }
}
