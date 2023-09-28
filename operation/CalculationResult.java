package operation;

import java.util.Stack;

/**
 * description
 *
 * @author 曾
 * Time：2023-09-26 22:13
 */
public class CalculationResult {

    /**
     * 计算结果
     */
    public static String getResult(String expression){
        //定义一个栈
        Stack<Fraction> stack = new Stack<>();
        String[] s = expression.split(" ");
        if(s[0].contains("/")){
            stack.push(new Fraction(s[0].charAt(0)-'0',s[0].charAt(2)-'0'));
        }else{
            stack.push(new Fraction(s[0].charAt(0)-'0',1));
        }
        for(int i=2;i<s.length;i+=2){
            Fraction fraction;
            if(s[i].contains("/")){
                fraction = new Fraction(s[i].charAt(0)-'0',s[i].charAt(2)-'0');
            }else{
                fraction = new Fraction(s[i].charAt(0)-'0',1);
            }
            switch (s[i - 1]) {
                case "+" -> {
                    stack.push(fraction);
                }
                case "-" -> {
                    int numerator = fraction.getNumerator();
                    fraction.setNumerator(-1*numerator);
                    stack.push(fraction);
                }
                case "*" -> {
                    Fraction pop = stack.pop();
                    int numerator = pop.getNumerator();
                    if(numerator<0){
                        pop.setNumerator(-1*numerator);
                        Fraction multiply = Fraction.multiply(fraction, pop);
                        int numerator1 = multiply.getNumerator();
                        multiply.setNumerator(-1*numerator1);
                        stack.push(multiply);
                    }else{
                        Fraction multiply = Fraction.multiply(pop, fraction);
                        stack.push(multiply);
                    }
                }
                case "/" -> {
                    Fraction pop = stack.pop();
                    int numerator = pop.getNumerator();
                    if(numerator<0){
                        pop.setNumerator(-1*numerator);
                        Fraction multiply = Fraction.divide(pop,fraction);
                        int numerator1 = multiply.getNumerator();
                        multiply.setNumerator(-1*numerator1);
                        stack.push(multiply);
                    }else{
                        Fraction multiply = Fraction.divide(pop, fraction);
                        stack.push(multiply);
                    }
                }
            }
        }
        while(stack.size()!=1){
            Fraction pop1 = stack.pop();
            Fraction pop2 = stack.pop();
            if(pop1.getNumerator()<0){
                int numerator = pop1.getNumerator();
                pop1.setNumerator(-1*numerator);
                Fraction subtract = Fraction.subtract(pop2, pop1);
                stack.push(subtract);
            }else {
                Fraction add = Fraction.add(pop1, pop2);
                stack.push(add);
            }
        }
        return stack.pop().toString();
    }
}
