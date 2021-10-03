package pr.miem.vlad.deserializations;


import java.util.Map;
import java.util.HashMap;

public class ParseJsonObject {
    public final String jsonString;
    private int state;
    private Map<String, String> mapOfJson = new HashMap<>();
    private int firstIndexOfStr;
    private int lastIndexOfStr;
    private String currentKey;
    private String currentValue;
    private int openBracketCnt;
    private int closeBracketCnt;

    public ParseJsonObject(String jsonString) {
        this.jsonString = jsonString;
        this.state = 0;
        this.firstIndexOfStr = 0;
    }

    private void initializeLastIndexOfValue(int currentIndex) {
        lastIndexOfStr = currentIndex;
    }

    private boolean isSymbol(char symbol) {
        return (Character.isDigit(symbol) || Character.isLetter(symbol) || Character.isSpaceChar(symbol)
                || symbol == '"' || symbol == ':' || symbol == ',' || symbol == '{' || symbol == '}');
    }

    private boolean isWord(char symbol) {
        return (Character.isDigit(symbol) || Character.isLetter(symbol));
    }

    private void jsonParse() {
        for (int i = 0; i < jsonString.length(); i++) {
            transmit(jsonString.charAt(i), i);
        }
        if (state == 26) {
            transmit('}', jsonString.length() - 1);
        }
    }
    public Map<String, String> getMapOfJson() {
        jsonParse();
        return new HashMap<>(mapOfJson);
    }

    private void transmit(char symbol, int currentIndex) {
        switch (state) {
            case 0:
                if (symbol == '{') {
                    state = 1;
                } else {
                    throw new Error("unknown symbol at state 0!");
                }
                break;
            case 1:
                if (symbol == '"') {
                    firstIndexOfStr = currentIndex + 1;
                    state = 2;
                } else {
                    throw new Error("unknown symbol at state 1!");
                }
                break;
            case 2:
                if (Character.isLetter(symbol)) {
                    state = 3;
                } else {
                    throw new Error("unknown symbol at state 2!");
                }
                break;
            case 3:
                if (Character.isLetter(symbol)) {
                    state = 3;
                } else if (symbol == '"') {
                    currentKey = jsonString.substring(firstIndexOfStr, currentIndex);

                    state = 4;
                } else {
                    throw new Error("unknown symbol at state 3!");
                }
                break;
            case 4:
                if (Character.isSpaceChar(symbol)) {
                    state = 4;
                } else if (symbol == ':') {
                    state = 5;
                } else {
                    throw new Error("unknown symbol at state 4!");
                }
                break;
            case 5:
                if (Character.isSpaceChar(symbol)) {
                    state = 5;
                }else if (Character.isDigit(symbol)) {
                    state = 6;
                } else if (symbol == '{') {
                    state = 8;
                } else if (symbol == '[') {
                    openBracketCnt++;
                    state = 11;
                } else if (symbol == 't') {
                    state = 14;
                } else if (symbol == 'f') {
                    state = 18;
                } else if (symbol == '"') {
                    state = 23;
                }
                else {
                    throw new Error("unknown symbol at state 5!");
                }
                firstIndexOfStr = (state == 6) ?  currentIndex - 1: currentIndex;
                break;
            case 6:
                if (Character.isDigit(symbol)) {
                    state = 6;
                } else if (symbol == ',') {
                    state = 7;
                } else  if (symbol == '}') {
                    state = 26;
                } else  {
                    throw new Error("unknown symbol at state 6!");
                }
                if (state == 7 || state == 26) {
                    initializeLastIndexOfValue(currentIndex + 1);
                } else {
                    initializeLastIndexOfValue(currentIndex);
                }
                break;
            case 8:
                if (isSymbol(symbol)) {
                    state = 9;
                } else {
                    throw new Error("unknown symbol at state 8!");
                }
                break;
            case 9:
                if (isSymbol(symbol)) {
                    state = 9;
                } else if (symbol == '}') {
                    state = 10;
                } else {
                    throw new Error("unknown symbol at state 9!");
                }
                break;
            case 10:
                if (symbol == ',') {
                    state = 7;
                } else if (symbol == '}') {
                    state = 26;
                } else {
                    throw new Error("unknown symbol at state 10!");
                }
                initializeLastIndexOfValue(currentIndex);
                break;
            case 11:
                if (isSymbol(symbol)) {
                    state = 12;
                }
                else {
                    throw new Error("unknown symbol at state 11!");
                }
                break;
            case 12:
                if (isSymbol(symbol)) {
                    state = 12;
                }
                else if (symbol == '[') {
                    openBracketCnt++;
                    state = 12;
                }
                else if (symbol == ']') {
                    closeBracketCnt++;
                    if (openBracketCnt == closeBracketCnt) {
                        state = 13;
                        openBracketCnt = 0;
                        closeBracketCnt = 0;
                    }
                    else {
                        state = 12;
                    }
                }
                else {
                    throw new Error("unknown symbol at state 12!");
                }
                break;
            case 13:
                if (symbol == '}') {
                    state = 26;
                } else if (symbol == ',') {
                    state = 7;
                } else {
                    throw new Error("unknown symbol at state 13!");
                }
                initializeLastIndexOfValue(currentIndex);
                break;
            case 14:
                if (symbol == 'r') {
                    state = 15;
                } else {
                    throw new Error("unknown symbol at state 14!");
                }
                break;
            case 15:
                if (symbol == 'u') {
                    state = 16;
                } else {
                    throw new Error("unknown symbol at state 15!");
                }
                break;
            case 16:
                if (symbol == 'e') {
                    state = 17;
                } else {
                    throw new Error("unknown symbol at state 16!");
                }
                break;
            case 17:
                if (symbol == '}') {
                    state = 26;
                } else if (symbol == ',') {
                    state = 7;
                } else {
                    throw new Error("unknown symbol at state 17!");
                }
                initializeLastIndexOfValue(currentIndex);
                break;
            case 18:
                if (symbol == 'a') {
                    state = 19;
                } else {
                    throw new Error("unknown symbol at state 18!");
                }
                break;
            case 19:
                if (symbol == 'l') {
                    state = 20;
                } else {
                    throw new Error("unknown symbol at state 19!");
                }
                break;
            case 20:
                if (symbol == 's') {
                    state = 21;
                } else {
                    throw new Error("unknown symbol at state 21!");
                }
                break;
            case 21:
                if (symbol == 'e') {
                    state = 22;
                } else {
                    throw new Error("unknown symbol at state 21!");
                }
                break;
            case 22:
                if (symbol == ',') {
                    state = 7;
                } else if (symbol == '}') {
                    state = 26;
                } else {
                    throw new Error("unknown symbol at state 22!");
                }
                initializeLastIndexOfValue(currentIndex);
                break;
            case 23:
                if (isWord(symbol)) {
                    state = 24;
                } else {
                    throw new Error("unknown symbol at state 23!");
                }
            case 24:
                if (isWord(symbol)) {
                    state = 24;
                } else if (symbol == '"') {
                    state = 25;
                } else {
                    throw new Error("unknown symbol at state 24!");
                }
                break;
            case 25:
                if (symbol == ',') {
                    state = 7;
                } else if (symbol == '}') {
                    state = 26;
                } else {
                    throw new Error("unknown symbol at state 25!");
                }
                initializeLastIndexOfValue(currentIndex);
                break;
            case 7:
                if (Character.isSpaceChar(symbol)) {
                    state = 7;
                } else if (symbol == '"') {
                    currentValue = jsonString.substring(firstIndexOfStr + 1, lastIndexOfStr - 1);
                    firstIndexOfStr = currentIndex + 1;
                    mapOfJson.put(currentKey, currentValue);
                    state = 2;
                } else {
                    throw new Error("unknown symbol at state 7!");
                }
                break;
            case 26:
                currentValue = jsonString.substring(firstIndexOfStr + 1, lastIndexOfStr - 1);
                mapOfJson.put(currentKey, currentValue);
                break;
        }
    }
}
