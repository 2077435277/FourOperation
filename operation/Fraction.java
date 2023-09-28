package operation;

/**
 * 分数类
 *
 * @author 曾
 * Time：2023-09-27 15:29
 */
public class Fraction {
    /**
     * 分子
     */
    private int numerator;
    /**
     * 分母
     */
    private int denominator;

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    // 简化分数
    private static Fraction simplify(int numerator, int denominator) {
        int gcd = gcd(numerator, denominator);
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    // 计算最大公约数
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // 计算两个分数的加法
    public static Fraction add(Fraction a, Fraction b) {
        int newNumerator = a.numerator * b.denominator + b.numerator * a.denominator;
        int newDenominator = a.denominator * b.denominator;
        return simplify(newNumerator, newDenominator);
    }

    // 计算两个分数的除法
    public static Fraction divide(Fraction a, Fraction b) {
        int newNumerator = a.numerator * b.denominator;
        int newDenominator = a.denominator * b.numerator;
        return simplify(newNumerator, newDenominator);
    }

    // 计算两个分数的乘法
    public static Fraction multiply(Fraction a, Fraction b) {
        int newNumerator = a.numerator * b.numerator;
        int newDenominator = a.denominator * b.denominator;
        return simplify(newNumerator, newDenominator);
    }

    // 计算两个分数的减法
    public static Fraction subtract(Fraction a, Fraction b) {
        int newNumerator = a.numerator * b.denominator - b.numerator * a.denominator;
        int newDenominator = a.denominator * b.denominator;
        return simplify(newNumerator, newDenominator);
    }

    // 将分数转换为字符串表示
    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + "";
        }
        if (numerator > denominator) {
            int sum = numerator / denominator;
            int nowNum = numerator % denominator;
            return sum + "`" + nowNum + "/" + denominator;
        }
        //转成真分数
        return numerator + "/" + denominator;
    }
}
