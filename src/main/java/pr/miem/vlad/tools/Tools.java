package pr.miem.vlad.tools;

import pr.miem.vlad.restrictions.Animal;

import java.util.ArrayList;

public class Tools {
    //https://ru.stackoverflow.com

    public static int countEntry(String initialStr, String substr) {
        return (initialStr.length() - initialStr.replace(substr, "").length()) / substr.length();
    }


    public static boolean checkCorrectDecision(String decision) {
        if (decision.equals("Yes") || decision.equals("No")
                || decision.equals("1") || decision.equals("2")) {
            return true;
        }
        return  false;
    }

    public static boolean isEnumContainsString(String str) {
        for (Animal animal : Animal.values()) {
            if (animal.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> splitJsonString(String jsonString) {
        ArrayList<String> jsonArray = new ArrayList<>();
        int beginIndex = 0;
        int currentOpenBracketCount = 0;
        int currentCloseBracketCount = 0;
        for (int i = 0; i <  jsonString.length(); i++) {
            if (jsonString.charAt(i) == '{') {
                currentOpenBracketCount++;
                if (currentOpenBracketCount == 1) {
                    beginIndex = i;
                }
            }
            else if (jsonString.charAt(i) == '}') {
                currentCloseBracketCount++;
                if (currentOpenBracketCount == currentCloseBracketCount) {
                    jsonArray.add(jsonString.substring(beginIndex, i + 1));
                    currentOpenBracketCount = 0;
                    currentCloseBracketCount = 0;
                }
            }
        }
        return jsonArray;
    }
}
