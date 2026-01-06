package dev.durovo.Market_Mate;

public class AuthService {
    private static String globalPassword = null;

    public static boolean isPasswordSet() {
        return globalPassword != null;
    }

    public static void setPassword(String password) {
        globalPassword = password;
    }

    public static boolean checkPassword(String input) {
        return globalPassword != null && globalPassword.equals(input);
    }
}
