package jsonparser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JSONParserTest {
    @Test
    public void testFromJsonToObject() throws Exception {
        String json = "{\"name\":\"John\",\"age\":30,\"skills\":[\"Java\", \"Python\"],\"hobbies\":[\"Reading\", \"Traveling\"]}";
        Person person = JSONParser.fromJson(json, Person.class);

        assertEquals("John", person.getName());
        assertEquals(30, person.getAge());
        assertArrayEquals(new String[]{"Java", "Python"}, person.getSkills());
        assertEquals(Arrays.asList("Reading", "Traveling"), person.getHobbies());
    }

    @Test
    public void testToJsonFromObject() throws IllegalAccessException {
        Person person = new Person();
        person.setName("Alice");
        person.setAge(25);
        person.setSkills(new String[]{"C++", "Go"});
        person.setHobbies(Arrays.asList("Hiking", "Swimming"));

        String json = JSONParser.toJson(person);
        assertEquals("{\"name\":\"Alice\",\"age\":25,\"skills\":[\"C++\",\"Go\"],\"hobbies\":[\"Hiking\",\"Swimming\"]}", json);
    }

    @Test
    public void testFromJsonToArray() throws Exception {
        String json = "[1, 2, 3, 4, 5]";
        Integer[] intArray = JSONParser.fromJson(json, Integer[].class);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, intArray);
    }

    @Test
    public void testToJsonFromArray() throws IllegalAccessException {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String json = JSONParser.toJson(intArray);
        assertEquals("[1,2,3,4,5]", json);
    }

    @Test
    public void testFromJsonToCollection() throws Exception {
        String json = "[\"apple\", \"banana\", \"cherry\"]";
        List<String> stringList = JSONParser.fromJson(json, List.class);
        assertEquals(Arrays.asList("apple", "banana", "cherry"), stringList);
    }

    @Test
    public void testToJsonFromCollection() throws IllegalAccessException {
        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        String json = JSONParser.toJson(stringList);
        assertEquals("[\"apple\",\"banana\",\"cherry\"]", json);
    }

    @Test
    public void testFromJsonToMap() throws Exception {
        String json = "{\"key1\":\"value1\",\"key2\":2,\"key3\":true}";
        Map<String, Object> map = JSONParser.parse(json);

        assertEquals("value1", map.get("key1"));
        assertEquals(2, map.get("key2"));
        assertEquals(true, map.get("key3"));
    }

    /*
    * In Java, Map does not guarantee the order of its elements.
    Therefore, directly comparing the exact content of a JSON string
    with another JSON string may lead to unexpected errors.
    * We will perform the following checks:
    - Remove the outer curly braces (if any) from both the expected JSON string
     and the actual JSON string.
    - Split the expected JSON string into key-value pairs.
    - Check if each key-value pair exists in the actual JSON string.
    - If a pair is found, remove it from the actual JSON string to avoid
    duplicates.
    - Finally, check if the remaining actual JSON string is empty or contains
    only commas (handling the edge case where commas are not removed).

    * */

    @Test
    public void testToJsonFromMap() throws Exception {
        Map<String, Object> map = Map.of(
                "key1", "value1",
                "key2", 2,
                "key3", true
        );
        String json = JSONParser.toJson(map);

        String expectedJson = "{\"key1\":\"value1\",\"key2\":2,\"key3\":true}";
        assertTrue(containsAllKeyValuePairs(expectedJson, json));
    }

    private boolean containsAllKeyValuePairs(String expectedJson, String actualJson) {
        // Remove outer curly braces if they exist
        expectedJson = expectedJson.trim();
        actualJson = actualJson.trim();
        if (expectedJson.startsWith("{")) {
            expectedJson = expectedJson.substring(1, expectedJson.length() - 1).trim();
        }
        if (actualJson.startsWith("{")) {
            actualJson = actualJson.substring(1, actualJson.length() - 1).trim();
        }

        // Split the expected JSON into key-value pairs
        String[] expectedPairs = expectedJson.split(",");
        for (String pair : expectedPairs) {
            pair = pair.trim();
            if (!actualJson.contains(pair)) {
                return false;
            }
            // Remove the found pair from the actual JSON to prevent duplicate matches
            actualJson = actualJson.replaceFirst(pair, "").replaceFirst(",\\s*", "");
        }

        return actualJson.isEmpty() || actualJson.equals(",");
    }
}

