package operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 四则运算类
 *
 * @author 曾
 * Time：2023-09-24 11:18
 */
public class FourOperations {

    /**
     * 四则运算符号
     */
    private static final Character[] operations = {'+', '-', '*', '/'};

    /**
     * 四则运算表达式中最多出现的运算符号
     */
    private static final Integer OPERATION_NUMBER = 3;

    /**
     * 生成n个随机表达式
     */
    public static List<String> generalExpressions(Integer maxNumber, Integer exNumber) {
        List<String> expressions = new ArrayList<>();
        //导入random包
        Random random = new Random();
        int number = 0;
        //进入循环
        while (number < exNumber) {
            StringBuilder expression = new StringBuilder();
            //定义四个变量，分别表示分子和分母
            int numerator1,numerator2,denominator1,denominator2;
            //判断生成的是分数还是整数
            if(random.nextInt(2)==1){
                numerator1 = random.nextInt(maxNumber);
                denominator1 = numerator1 + random.nextInt(maxNumber-numerator1);
                if(denominator1==0){
                    denominator1++;
                }
                expression.append(numerator1).append("/").append(denominator1);
            }else{
                //此时生成的是整数
                numerator1 = random.nextInt(maxNumber);
                denominator1 = -1;
                expression.append(numerator1);
            }
            char operation;
            //进入循环，生成最多三个运算符的表达式
            //先随机生成最多的运算符数量
            int operationNumber = random.nextInt(OPERATION_NUMBER) + 1;
            for (int i = 0; i < operationNumber; i++) {
                //运用随机数生成运算符号
                operation = operations[random.nextInt(4)];
                switch (operation) {
                    //因为减法运算时不能让前面的数小于后面的数，因此num2的随机生成要在具体的分支中生成
                    case '+' -> {
                        if(random.nextInt(2)==1){
                            numerator2 = random.nextInt(maxNumber);
                            denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                            if(denominator2==0){
                                denominator2++;
                            }
                            expression.append(" + ").append(numerator2).append("/").append(denominator2);
                            numerator1 = numerator2;
                            denominator1 = denominator2;
                        }else{
                            numerator2 = random.nextInt(maxNumber);
                            expression.append(" + ").append(numerator2);
                            numerator1 = numerator2;
                            denominator1 = -1;
                        }
                    }
                    case '-' -> {
                        //判断生成整数还是分数
                        if(random.nextInt(2)==1){
                            if(numerator1==0){
                                expression.append(" - ").append(0);
                            }else{
                                numerator2 = random.nextInt(numerator1);
                                expression.append(" - ").append(numerator2);
                            }
                            denominator1 = -1;
                        }else{
                            //如果前一位不是分数则随便生成
                            if(denominator1==-1){
                                numerator2 = random.nextInt(maxNumber);
                                denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                                if(denominator2==0){
                                    denominator2++;
                                }
                                expression.append(" - ").append(numerator2).append("/").append(denominator2);
                            }else{
                                //如果前面一位是分数要保证前面的分数更大
                                if(numerator1==1 && denominator1 == 9){
                                    expression.append(" - ").append(0);
                                    denominator1 = -1;
                                }else{
                                    numerator2 = random.nextInt(maxNumber);
                                    denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                                    while(!determineLegal(numerator1,denominator1,numerator2,denominator2)){
                                        numerator2 = random.nextInt(maxNumber);
                                        denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                                    }
                                    expression.append(" - ").append(numerator2).append("/").append(denominator2);
                                    denominator1 = denominator2;
                                    numerator1 = numerator2;
                                }
                            }
                        }
                    }
                    case '*' -> {
                        if(random.nextInt(2)==1){
                            numerator2 = random.nextInt(maxNumber);
                            denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                            if(denominator2==0){
                                denominator2++;
                            }
                            expression.append(" * ").append(numerator2).append("/").append(denominator2);
                            numerator1 = numerator2;
                            denominator1 = denominator2;
                        }else{
                            numerator2 = random.nextInt(maxNumber);
                            expression.append(" * ").append(numerator2);
                            numerator1 = numerator2;
                            denominator1 = -1;
                        }
                    }
                    case '/' -> {
                        if(random.nextInt(2)==1){
                            numerator2 = random.nextInt(maxNumber);
                            denominator2 = numerator2 + random.nextInt(maxNumber-numerator2);
                            if(denominator2==0){
                                denominator2++;
                            }
                            expression.append(" / ").append(numerator2).append("/").append(denominator2);
                            numerator1 = numerator2;
                            denominator1 = denominator2;
                        }else{
                            numerator2 = random.nextInt(maxNumber);
                            expression.append(" / ").append(numerator2);
                            numerator1 = numerator2;
                            denominator1 = -1;
                        }
                    }
                }
            }
            //将字符串存入集合中
            expressions.add(expression.toString());
            number++;
        }
        return expressions;
    }

    /**
     * 判断分数是否合法
     * @param numerator1 分子1
     * @param denominator1 分母1
     * @param numerator2 分子2
     * @param denominator2 分母2
     * @return 返回是否合法
     */
    public static boolean determineLegal(int numerator1,int denominator1,int numerator2,int denominator2){
        return numerator1*denominator2<numerator2*denominator1;
    }

    public static void main(String[] args) {
        List<String> strings = generalExpressions(10, 10);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
