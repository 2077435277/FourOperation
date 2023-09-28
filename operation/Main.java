package operation;

import java.util.List;

/**
 * description
 *
 * @author 曾
 * Time：2023-09-27 21:24
 */
public class Main {
    public static void main(String[] args) {
        List<String> expressions = IOUtils.readFile("Exercises.txt");
        List<String> answers = IOUtils.readFile("answer.txt");
        for (String expression : expressions) {
            System.out.println(expression);
        }
        for (String answer : answers) {
            System.out.println(answer);
        }
    }
}
