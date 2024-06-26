package jsonparser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        String json = "{\"name\":\"John\",\"age\":30,\"skills\":[\"Java\", \"Python\"],\"hobbies\":[\"Reading\", \"Traveling\"]}";
        Person person = JSONParser.fromJson(json, Person.class);
        System.out.println(person.getName()); // John
        System.out.println(person.getAge()); // 30
        System.out.println(Arrays.toString(person.getSkills())); // [Java, Python]
        System.out.println(person.getHobbies()); // [Reading, Traveling]

        String generatedJson = JSONParser.toJson(person);
        System.out.println(generatedJson); // {"name":"John","age":30,"skills":["Java","Python"],"hobbies":["Reading","Traveling"]}
    }
}