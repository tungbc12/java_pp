package jsonparser;

public class JSONTokenizer {

    private final String json;
    private int index;

    public JSONTokenizer(String json) {
        this.json = json;
        this.index = 0;
    }

    public boolean hasNext() {
        skipWhitespace();
        return index < json.length();
    }

    public String nextToken() {
        skipWhitespace();
        char ch = json.charAt(index);
        if (ch == '{' || ch == '}' || ch == '[' || ch == ']' || ch == ':' || ch == ',') {
            index++;
            return String.valueOf(ch);
        }
        if (ch == '"') {
            return parseString();
        }
        return parseLiteral();
    }

    private void skipWhitespace() {
        while (index < json.length() && Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }

    private String parseString() {
        int start = index;
        index++;
        while (index < json.length() && json.charAt(index) != '"') {
            index++;
        }
        index++;
        return json.substring(start, index);
    }

    private String parseLiteral() {
        int start = index;
        while (index < json.length() && !Character.isWhitespace(json.charAt(index)) &&
                "{}[],:\"".indexOf(json.charAt(index)) == -1) {
            index++;
        }
        return json.substring(start, index);
    }
}
