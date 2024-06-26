package jsonparser;

import java.util.*;
import java.lang.reflect.*;

public class JSONParser {

    // Parse JSON string to Map<String, Object>
    public static Map<String, Object> parse(String json) throws Exception {
        if (json == null || json.isEmpty()) {
            return new HashMap<>();
        }
        return parseObject(new JSONTokenizer(json));
    }

    private static Map<String, Object> parseObject(JSONTokenizer tokenizer) throws Exception {
        Map<String, Object> map = new HashMap<>();
        tokenizer.nextToken(); // Skip '{'
        while (tokenizer.hasNext()) {
            String key = tokenizer.nextToken();
            if (key.startsWith("\"")) {
                key = key.substring(1, key.length() - 1); // Remove quotes
            }
            tokenizer.nextToken(); // Skip ':'
            Object value = parseValue(tokenizer);
            map.put(key, value);
            String token = tokenizer.nextToken();
            if (token.equals("}")) break;
            else if (!token.equals(",")) throw new RuntimeException("Unexpected token: " + token);
        }
        return map;
    }

    private static List<Object> parseArray(JSONTokenizer tokenizer) throws Exception {
        List<Object> list = new ArrayList<>();
        //tokenizer.nextToken(); // Skip '['
        while (tokenizer.hasNext()) {
            list.add(parseValue(tokenizer));
            String token = tokenizer.nextToken();
            if (token.equals("]")) break;
            else if (!token.equals(",")) throw new RuntimeException("Unexpected token: " + token);
        }
        return list;
    }

    private static Object parseValue(JSONTokenizer tokenizer) throws Exception {
        String token = tokenizer.nextToken();
        switch (token) {
            case "{":
                return parseObject(tokenizer);
            case "[":
                return parseArray(tokenizer);
            case "true":
                return true;
            case "false":
                return false;
            case "null":
                return null;
            default:
                if (token.startsWith("\"")) {
                    return token.substring(1, token.length() - 1); // Remove quotes
                } else {
                    try {
                        if (token.contains(".")) {
                            return Double.parseDouble(token);
                        } else {
                            return Integer.parseInt(token);
                        }
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Unexpected token: " + token);
                    }
                }
        }
    }

    // Convert JSON string to a specified class
    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        Map<String, Object> map = parse(json);
        return fromMap(map, clazz);
    }

    private static <T> T fromMap(Map<String, Object> map, Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = map.get(field.getName());
            if (value != null) {
                if (field.getType().isPrimitive() || value.getClass().equals(field.getType())) {
                    field.set(obj, value);
                } else if (field.getType().isArray()) {
                    Object array = fromArray((List<Object>) value, field.getType().getComponentType());
                    field.set(obj, array);
                } else if (value instanceof Map) {
                    field.set(obj, fromMap((Map<String, Object>) value, field.getType()));
                } else if (value instanceof List) {
                    field.set(obj, fromList((List<Object>) value, field.getType().getComponentType()));
                }
            }
        }
        return obj;
    }

    private static Object fromArray(List<Object> list, Class<?> componentType) throws Exception {
        Object array = Array.newInstance(componentType, list.size());
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            if (item instanceof Map) {
                Array.set(array, i, fromMap((Map<String, Object>) item, componentType));
            } else if (item instanceof List) {
                Array.set(array, i, fromArray((List<Object>) item, componentType));
            } else {
                Array.set(array, i, item);
            }
        }
        return array;
    }

    private static <T> List<T> fromList(List<Object> list, Class<T> componentType) throws Exception {
        List<T> result = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof Map) {
                result.add(fromMap((Map<String, Object>) item, componentType));
            } else {
                result.add((T) item);
            }
        }
        return result;
    }

    // Convert Java object to JSON string
    public static String toJson(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String || obj instanceof Character) {
            return "\"" + obj + "\"";
        }
        if (obj instanceof Number || obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return mapToJson((Map<String, Object>) obj);
        }
        if (obj.getClass().isArray()) {
            return arrayToJson(obj);
        }
        if (obj instanceof Collection) {
            return listToJson((Collection<?>) obj);
        }
        return objectToJson(obj);
    }

    private static String mapToJson(Map<String, Object> map) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\":").append(toJson(entry.getValue())).append(",");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // Remove trailing comma
        }
        json.append("}");
        return json.toString();
    }

    private static String arrayToJson(Object array) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("[");
        int length = Array.getLength(array);
        for (int i = 0; i < length; i++) {
            json.append(toJson(Array.get(array, i))).append(",");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // Remove trailing comma
        }
        json.append("]");
        return json.toString();
    }

    private static String listToJson(Collection<?> list) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("[");
        for (Object item : list) {
            json.append(toJson(item)).append(",");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // Remove trailing comma
        }
        json.append("]");
        return json.toString();
    }

    private static String objectToJson(Object obj) throws IllegalAccessException {
        StringBuilder json = new StringBuilder("{");
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            json.append("\"").append(field.getName()).append("\":").append(toJson(field.get(obj))).append(",");
        }
        if (json.length() > 1) {
            json.setLength(json.length() - 1); // Remove trailing comma
        }
        json.append("}");
        return json.toString();
    }
}
