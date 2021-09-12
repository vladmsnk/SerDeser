package tools;

public class Tools {
    //https://ru.stackoverflow.com

    public static int countEntry(String initialStr, String substr) {
        return (initialStr.length() - initialStr.replace(substr, "").length()) / substr.length();
    }

}
