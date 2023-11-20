package ru.spbstu.telematics.java;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;

public class AppTest {

    @Test
    public void testAdd() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        assertTrue(javaSet.add("Apple"));
        assertTrue(customSet.add("Apple"));

        assertTrue(javaSet.add("Banana"));
        assertTrue(customSet.add("Banana"));

        assertFalse(javaSet.add("Apple")); // Duplicate element
        assertFalse(customSet.add("Apple")); // Duplicate element

        assertEquals(javaSet.toString(), customSet.toString());
    }

    @Test
    public void testContains() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        customSet.add("Apple");

        javaSet.add("Banana");
        customSet.add("Banana");

        assertTrue(javaSet.contains("Apple"));
        assertTrue(customSet.contains("Apple"));

        assertTrue(javaSet.contains("Banana"));
        assertTrue(customSet.contains("Banana"));

        assertFalse(javaSet.contains("Orange")); // Element not in the set
        assertFalse(customSet.contains("Orange")); // Element not in the set

        assertEquals(javaSet.toString(), customSet.toString());
    }

    @Test
    public void testRemove() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        customSet.add("Apple");

        javaSet.add("Banana");
        customSet.add("Banana");

        assertTrue(javaSet.remove("Apple"));
        assertTrue(customSet.remove("Apple"));

        assertFalse(javaSet.remove("Orange")); // Element not in the set
        assertFalse(customSet.remove("Orange")); // Element not in the set

        assertEquals(javaSet.toString(), customSet.toString());
    }

    @Test
    public void testSize() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        assertEquals(0, javaSet.size());
        assertEquals(0, customSet.size());

        javaSet.add("Apple");
        customSet.add("Apple");

        javaSet.add("Banana");
        customSet.add("Banana");

        assertEquals(2, javaSet.size());
        assertEquals(2, customSet.size());
    }

    @Test
    public void testAddAll() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        javaSet.add("Banana");

        customSet.add("Apple");
        customSet.add("Banana");

        LinkedHashSet<String> javaSetAdd = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSetAdd = new CustomLinkedHashSet<>();
        javaSetAdd.add("Orange");
        javaSetAdd.add("Melon");
        customSetAdd.add("Orange");
        customSetAdd.add("Melon");

        assertTrue(javaSet.addAll(javaSetAdd));
        assertTrue(customSet.addAll(customSetAdd));

        assertEquals(javaSet.toString(), customSet.toString());
    }

     @Test
    public void testRemoveAll() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        javaSet.add("Banana");
        javaSet.add("Orange");

        customSet.add("Apple");
        customSet.add("Banana");
        customSet.add("Orange");

        LinkedHashSet<String> javaSetRemove = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSetRemove = new CustomLinkedHashSet<>();
        javaSetRemove.add("Banana");
        javaSetRemove.add("Orange");
        customSetRemove.add("Banana");
        customSetRemove.add("Orange");

        assertTrue(javaSet.removeAll(javaSetRemove));
        assertTrue(customSet.removeAll(customSetRemove));

        assertEquals(javaSet.toString(), customSet.toString());
    }

    @Test
    public void testRetainAll() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        javaSet.add("Banana");
        javaSet.add("Orange");

        customSet.add("Apple");
        customSet.add("Banana");
        customSet.add("Orange");

        LinkedHashSet<String> javaSetRetain = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSetRetain = new CustomLinkedHashSet<>();
        javaSetRetain.add("Banana");
        javaSetRetain.add("Orange");
        javaSetRetain.add("Melon");
        customSetRetain.add("Banana");
        customSetRetain.add("Orange");
        customSetRetain.add("Melon");


        assertTrue(javaSet.retainAll(javaSetRetain));
        assertTrue(customSet.retainAll(customSetRetain));

        assertEquals(javaSet.toString(), customSet.toString());
    }

    @Test
    public void testContainsAll() {
        LinkedHashSet<String> javaSet = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();

        javaSet.add("Apple");
        javaSet.add("Banana");
        javaSet.add("Orange");

        customSet.add("Apple");
        customSet.add("Banana");
        customSet.add("Orange");

        LinkedHashSet<String> javaSetCheck = new LinkedHashSet<>();
        CustomLinkedHashSet<String> customSetCheck = new CustomLinkedHashSet<>();
        javaSetCheck.add("Banana");
        javaSetCheck.add("Orange");
        customSetCheck.add("Banana");
        customSetCheck.add("Orange");

        assertTrue(javaSet.containsAll(javaSetCheck));
        assertTrue(customSet.containsAll(customSetCheck));
    }
}