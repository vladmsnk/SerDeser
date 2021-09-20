package tools;

import restrictions.Animals;

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
        for (Animals animal : Animals.values()) {
            if (animal.name().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
