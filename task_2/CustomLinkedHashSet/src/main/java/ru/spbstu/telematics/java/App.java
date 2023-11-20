package ru.spbstu.telematics.java;

import java.lang.SuppressWarnings;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class App {
    // Function main().
    public static void main(String[] args) {
        CustomLinkedHashSet<String> customSet = new CustomLinkedHashSet<>();
        customSet.add("Apple");
        customSet.add("Banana");
        customSet.add("Watermelon");
        customSet.add("Orange");
        customSet.add("Banana");

        System.out.println("Set customSet: " + customSet);

        System.out.println("Size: " + customSet.size());
        System.out.println("LinkedHashSet contains Apple: "+ customSet.contains("Apple"));

        System.out.println("Orange is removed from collections: " + customSet.remove("Orange"));
        System.out.println("Size: " + customSet.size());
        System.out.println("LinkedHashSet contains Orange: "+ customSet.contains("Orange"));

        Iterator<String> iterate = customSet.iterator();
        System.out.print("LinkedHashSet using Iterator: ");

        // Accessing elements
        while(iterate.hasNext()) {
            System.out.print(iterate.next());
            System.out.print(", ");
        }

        System.out.println();

        CustomLinkedHashSet<String> customSet2 = new CustomLinkedHashSet<>();
        customSet2.add("Banana");
        customSet2.add("Watermelon");
        customSet2.add("Apple");
        customSet2.add("Grape");
        customSet2.add("Orange");

        System.out.println("Set customSet2: " + customSet2);

        customSet.addAll(customSet2);
        System.out.println("Union: " + customSet);

        System.out.println("Subset: "+ customSet2.containsAll(customSet));

        customSet2.retainAll(customSet);
        System.out.println("Intersection: " + customSet2);

        customSet2.removeAll(customSet);
        System.out.println("Difference: " + customSet2);
    }
}

class CustomLinkedHashSet<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    // Hash table maintains the uniqueness of the elements.
    private Entry<T>[] table;

    // External doubly linked list to maintain insertion order of elements.
    private Entry<T> head;  // Head of the external linked list.
    private Entry<T> tail;  // Tail of the external linked list.

    private int size;
    private int capacity;
    private float loadFactor;

    // Constructors of class CustomLinkedHashSet.
    public CustomLinkedHashSet() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public CustomLinkedHashSet(int initialCapacity, float loadFactor) {
        this.capacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Elements in hashtable and external doubly linked list.
    private static class Entry<T> {
        T value;
        Entry<T> next;
        Entry<T> prev;

        Entry(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private class LinkedHashSetIterator implements Iterator<T> {

        private Entry<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = current.value;
            current = current.next;
            return value;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedHashSetIterator();
    }

    // Hash function.
    private int hash(Object key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // Method add().
    public boolean add(T value) {
        int index = hash(value);
    
        // Check if the element already exists.
        Entry<T> existingEntry = findEntry(index, value);
        if (existingEntry != null) {
            return false; // Element already exists, nothing to do.
        }
    
        // Add the new element to the table.
        Entry<T> newEntry = new Entry<>(value);
        if (table[index] == null) {
            // If the bucket is empty.
            table[index] = newEntry;
        } else {
            // Add the new element to the end of the bucket's linked list.
            Entry<T> last = getLastEntry(table[index]);
            last.next = newEntry;
            newEntry.prev = last;
        }
    
        // Add the new element to the external doubly linked list.
        Entry<T> newEntryList = new Entry<>(value);
        if (head == null) {
            head = newEntryList;
            tail = newEntryList;
        } else {
            newEntryList.prev = tail;
            tail.next = newEntryList;
            tail = newEntryList;
        }
    
        size++;
    
        // Resize if necessary.
        if ((float) size / capacity > loadFactor) {
            resize();
        }

        return true;
    }
    
    // Get last element of linked list in bucket[index] (in hashtable).  
    private Entry<T> getLastEntry(Entry<T> entry) {
        while (entry != null && entry.next != null) {
            entry = entry.next;
        }
        return entry;
    }

    // Find a element in hashtable.
    private Entry<T> findEntry(int index, T value) {
        Entry<T> current = table[index];
        while (current != null) {
            if (current.value.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Find a element in external linked list.
    private Entry<T> findEntryList(T value){
        Entry<T> current = head;
        while(current != null) {
            if (current.value.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Method resize() if necessary.
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Entry<T>[] newTable = new Entry[capacity];
    
        // Rehash all existing elements
        for (Entry<T> entry : table) {
            while (entry != null) {
                Entry<T> newEntry = new Entry<>(entry.value);

                int index = hash(newEntry.value);
    
                if (newTable[index] == null) {
                    newTable[index] = newEntry;
                } else {
                    // Add the new element to the end of the bucket's linked list.
                    Entry<T> last = getLastEntry(newTable[index]);
                    last.next = newEntry;
                    newEntry.prev = last;
                }
    
                entry = entry.next;
            }
        }
    
        table = newTable;
    }
    
    // Method size().
    public int size() {
        return this.size;
    }

    // Method contains().
    public boolean contains(T value) {
        int index = hash(value);
        Entry<T> entry = findEntry(index, value);
        return entry != null;
    }

    //Method remove().
    public boolean remove(T value) {
        int index = hash(value);
        Entry<T> entry = findEntry(index, value);
        Entry<T> entryList = findEntryList(value);
    
        // Remove from the table.
        if (entry != null) {
            if (entry == table[index]) {
                table[index] = entry.next;
                if (entry.next != null) {
                    entry.next.prev = null;
                }
            }
            else {
                entry.prev.next = entry.next;
                if (entry.next != null) {
                    entry.next.prev = entry.prev;
                }
            }
    
            // Remove from the external doubly linked list
            if (entryList == head){
                head = entryList.next;
                if (entryList.next != null) {
                    entryList.next.prev = null;
                }
            }
            else if (entryList == tail){
                tail = entryList.prev;
                if (entryList.prev != null) {
                    entryList.prev.next = null;
                }
            }
            else {
                entryList.prev.next = entryList.next;
                if (entryList.next != null) {
                    entryList.next.prev = entryList.prev;
                }
            }

            entry = null;
            entryList = null;

            size--;
            return true;
        }
    
        return false;
    }    

    // Union of two sets.
    public boolean addAll(CustomLinkedHashSet<T> otherSet) {
        boolean modified = false;
        for (T element : otherSet) {
            if (add(element)) {
                modified = true;
            }
        }

        return modified;
    }

    // Intersection of two sets.
    public boolean retainAll(CustomLinkedHashSet<T> otherSet) {
        boolean modified = false;
        CustomLinkedHashSet<T> newSet = new CustomLinkedHashSet<T>();
        for (T element : this) {
            if (!otherSet.contains(element)) {
                newSet.add(element);
            }
        }

        for (T element : newSet) {
            if (remove(element)) {
                modified = true;
            }
        }

        return modified;
    }    

    // Difference between two sets.
    public boolean removeAll(CustomLinkedHashSet<T> otherSet) {
        boolean modified = false;
        for (T element : otherSet) {
            if (remove(element)) {
                modified = true;
            }
        }

        return modified;
    }
    
    // Check the subset.
    public boolean containsAll(CustomLinkedHashSet<T> otherSet){
        for (T element : otherSet){
            if (!contains(element)){
                return false;
            }
        }
        return true;
    }

    // Override method toString to display instances.
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (T element : this) {
            result.append(element).append(", ");
        }
        result.append("]");
        if (result.length() > 3 && result.charAt(result.length() - 3) == ','){
            result.deleteCharAt(result.length() - 3);
            result.deleteCharAt(result.length() - 2);
        }
        return result.toString();
    }
}