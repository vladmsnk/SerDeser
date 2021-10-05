package pr.miem.vlad.deserializations;
import java.util.Map;
import java.util.HashMap;

public class ParseJsonObject {
    public final String jsonString;
    private int state;
    private final Map<String, String> jsonMap = new HashMap<>();
    private int firstIndexOfStr;
    private String currentKey;
    private int bracketCount;

    public ParseJsonObject(String jsonString) {
        this.jsonString = jsonString;
    }

    public Map<String, String>  jsonParse() {
        for (int i = 0; i < jsonString.length(); i++) {
            transmit(jsonString.charAt(i), i);
        }
        return new HashMap<>(jsonMap);
    }

    private void transmit(char charElement, int currentIndex) {
        switch (state) {
            case 0:
                if (!Character.isSpaceChar(charElement)) {
                    if (charElement == '{') {
                        state = 1;
                    } else {
                        throw new IllegalStateException("State 0, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 1:
                if (!Character.isSpaceChar(charElement)) {
                    if (charElement == '"') {
                        firstIndexOfStr = currentIndex + 1;
                        state = 2;
                    } else {
                        throw new IllegalStateException("State 1, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 2:
                if (!Character.isLetterOrDigit(charElement)) {
                    if (charElement == '"') {
                        state = 3;
                        currentKey = jsonString.substring(firstIndexOfStr, currentIndex);
                    } else {
                        throw new IllegalStateException("State 2, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 3:
                if (!Character.isSpaceChar(charElement)) {
                    if (charElement == ':') {
                        state = 4;
                    } else {
                        throw new IllegalStateException("State 3, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;

            case 4:
                if (!Character.isSpaceChar(charElement)) {
                    if (Character.isDigit(charElement)) {
                        state = 5;
                    } else if (charElement == '{') {
                        bracketCount++;
                        state = 6;
                    } else if (charElement == '[') {
                        bracketCount++;
                        state = 7;
                    } else if (charElement == 't') {
                        state = 8;
                    } else if (charElement == 'f') {
                        state = 11;
                    } else if (charElement == '"') {
                        state = 14;
                    } else {
                        throw new IllegalStateException("State 4, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                    firstIndexOfStr = currentIndex;
                }
                break;
            case 5:
                if (!Character.isDigit(charElement)) {
                    if (charElement == ',' || charElement == '}') {
                        String currentValue = jsonString.substring(firstIndexOfStr, currentIndex);
                        jsonMap.put(currentKey, currentValue);
                        state = (charElement == ',')? 1 : 16;
                    } else {
                        throw new IllegalStateException("State 5 Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 6:
                if (isNotSymbol(charElement)) {
                    if (charElement == '{') {
                        bracketCount++;
                    } else if (charElement == '}') {
                        bracketCount--;
                        if (bracketCount == 0) {
                            String currentValue = jsonString.substring(firstIndexOfStr, currentIndex + 1);
                            jsonMap.put(currentKey, currentValue);
                            state = 15;
                        }
                    } else {
                        throw new IllegalStateException("State 6, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 7:
                if (isNotSymbol(charElement) && !isFigureBracket(charElement)) {
                    if (charElement == '[') {
                        bracketCount++;
                    } else if (charElement == ']') {
                        bracketCount--;
                        if (bracketCount == 0) {
                            String currentValue = jsonString.substring(firstIndexOfStr, currentIndex + 1);
                            jsonMap.put(currentKey, currentValue);
                            state = 15;
                        }
                    } else {
                        throw new IllegalStateException("State 7, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 8:
                if (charElement == 'r') {
                    state = 9;
                } else {
                    throw new IllegalStateException("State 8, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 9:
                if (charElement == 'u') {
                    state = 10;
                } else {
                    throw new IllegalStateException("State 9, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 10:
                if (charElement == 'e') {
                    String currentValue = jsonString.substring(firstIndexOfStr, currentIndex + 1);
                    jsonMap.put(currentKey, currentValue);
                    state = 15;
                } else {
                    throw new IllegalStateException("State 10, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 11:
                if (charElement == 'a') {
                    state = 12;
                } else {
                    throw new IllegalStateException("State 11, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 12:
                if (charElement == 'l') {
                    state = 13;
                } else {
                    throw new IllegalStateException("State 12, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 13:
                if (charElement == 's') {
                    state = 10;
                } else {
                    throw new IllegalStateException("State 13, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                }
                break;
            case 14:
                if (!Character.isLetterOrDigit(charElement)) {
                    if (charElement == '"') {
                        String currentValue = jsonString.substring(firstIndexOfStr + 1, currentIndex);
                        jsonMap.put(currentKey, currentValue);
                        state = 15;
                    }
                    else {
                        throw new IllegalStateException("State 14, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
                break;
            case 15:
                if (!Character.isSpaceChar(charElement)) {
                    if (charElement == ',') {
                        state = 1;
                    } else if (charElement == '}') {
                        state = 16;
                    }
                    else {
                        throw new IllegalStateException("State 15, Unknown charElement " + charElement + " at the index " + currentIndex + "!");
                    }
                }
            case 16:
                break;
        }
    }

    private boolean isNotSymbol(char symbol) {
        return (!Character.isLetterOrDigit(symbol) && !Character.isSpaceChar(symbol)
                && symbol != '"' && symbol != ':' && symbol != ',' && symbol != '.');
    }

    private  boolean isFigureBracket(char symbol) {
        return (symbol == '{' || symbol == '}');
    }
}
