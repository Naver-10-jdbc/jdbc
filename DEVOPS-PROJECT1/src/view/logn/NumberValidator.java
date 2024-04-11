package view.logn;

public class NumberValidator {
    public static boolean isValidNumber(String str) {
        return str.matches("\\d+(\\.\\d+)?"); // 정수 또는 실수 패턴 확인
    }
}
