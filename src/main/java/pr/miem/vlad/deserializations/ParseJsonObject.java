package pr.miem.vlad.deserializations;


import java.util.Map;
import java.util.HashMap;

public class ParseJsonObject {
    public final String jsonString;
    private int state;
    private Map<String, String> mapOfJson = new HashMap<>();
    int firstIndexOfStr;
    int lastIndexOfStr;

    public ParseJsonObject(String jsonString) {
        this.jsonString = jsonString;
        this.state = 0;
        this.firstIndexOfStr = 0;
    }


    private void initializeLastIndexOfValue(int currentIndex) {
        if (state != 6) {
            lastIndexOfStr = currentIndex;
        }
    }

    private boolean isSymbol(char symbol) {
        return (Character.isDigit(symbol) || Character.isLetter(symbol) || Character.isSpaceChar(symbol));
    }
    private boolean isWord(char symbol) {
        return (Character.isDigit(symbol) || Character.isLetter(symbol));
    }


    public void transmit(char symbol, int currentIndex) {
        String currentKey = "";
        String currentValue = "";
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
                if (Character.isDigit(symbol)) {
                    state = 6;
                } else if (symbol == '{') {
                    state = 8;
                } else if (symbol == '[') {
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
                initializeLastIndexOfValue(lastIndexOfStr);
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
                initializeLastIndexOfValue(lastIndexOfStr);
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
                else if (symbol == ']') {
                    state = 13;
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
                initializeLastIndexOfValue(lastIndexOfStr);
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
                initializeLastIndexOfValue(lastIndexOfStr);
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
                initializeLastIndexOfValue(lastIndexOfStr);
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
            case 25:
                if (symbol == ',') {
                    state = 7;
                } else if (symbol == '}') {
                    state = 26;
                } else {
                    throw new Error("unknown symbol at state 24!");
                }
                initializeLastIndexOfValue(lastIndexOfStr);
                break;
            case 7:
                if (Character.isSpaceChar(symbol)) {
                    state = 7;
                } else if (symbol == '"') {
                    state = 2;
                } else {
                    throw new Error("unknown symbol at state 7!");
                }
                break;
        }
    }
}
