public class Kata {

    static String shorterInputIs = "";
    static int howMuchShorterInputIs = 0;

    public static String sumStrings(String a, String b) {
        StringBuilder sumOfAB = new StringBuilder();

        int carry = 0; //surplus of a+b (>9)

        boolean areInputsSame = areInputsSame(a, b);

        if (!areInputsSame) {
            shorterInput(a, b);
            if (shorterInputIs == a) {
                a = inputLengthEditor(a);
            } else {
                b = inputLengthEditor(b);
            }
        }

        for (int i = a.length() - 1; i >= 0; i--) {
            int digitA = Character.getNumericValue(a.charAt(i));
            int digitB = Character.getNumericValue(b.charAt(i));

            int sumOfDigitADigitBCarry = digitA + digitB + carry;

            carry = sumOfDigitADigitBCarry / 10; //new carry
            int resultDigit = sumOfDigitADigitBCarry % 10; //result for append to sumOfAB

            sumOfAB.append(resultDigit);

            if (carry > 0 && i == 0) {
                sumOfAB.append(carry);
            }
        }

        if (sumOfAB.toString().endsWith("0")) {
            zeroCutter(sumOfAB);
        }

        return sumOfAB.reverse().toString();
    }

    public static void zeroCutter(StringBuilder sumOfAB) {
        do {
            sumOfAB.deleteCharAt(sumOfAB.length() - 1);
        } while (sumOfAB.toString().endsWith("0"));
    }

    public static boolean areInputsSame(String a, String b) {
        return a.length() == b.length();
    }

    public static void shorterInput(String a, String b) {
        int length = a.length() - b.length();
        int positiveLength = Math.abs(length);
        if (length < 0) {
            shorterInputIs = a;
            howMuchShorterInputIs = positiveLength;
        } else {
            shorterInputIs = b;
            howMuchShorterInputIs = positiveLength;
        }
    }

    public static String inputLengthEditor(String x) {
        StringBuilder editedString = new StringBuilder(x);
        for (int i = 0; i < howMuchShorterInputIs; i++) {
            editedString.insert(0, '0');
        }
        return editedString.toString();
    }
}


/*
TESTING

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class SolutionTest {
    @Test
    void test() {
         assertEquals("579", Kata.sumStrings("123", "456"));
    }
}

 */