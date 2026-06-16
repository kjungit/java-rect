package algorithm;

// * 제네릭의 주요 특징
//  1. 타입 안전성(Type Safety): 컴파일 시점에서 잘못된 타입 사용을 방지하여 런타임 에러를 줄일 수 있다.
//  2. 재사용성: 동일한 코드가 다양한 데이터 타입에서 동작할 수 있도록 한다.
//  3. 캐스팅 제거: 불필요한 타입 캐스팅을 줄인다.

public class B_generic2<T extends Number> {
    // 덧셈
    public T add(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() + num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof  Double && num2 instanceof Double) {
            double result = num1.doubleValue() + num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    // 뻴쎔
    public T subtract(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() - num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof  Double && num2 instanceof Double) {
            double result = num1.doubleValue() - num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    // 쎔
    public T multiply(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() * num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof  Double && num2 instanceof Double) {
            double result = num1.doubleValue() * num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    // 나눗쎔
    public T divide(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = num1.intValue() / num2.intValue();
            return (T) Integer.valueOf(result);
        } else if (num1 instanceof  Double && num2 instanceof Double) {
            double result = num1.doubleValue() / num2.doubleValue();
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    private int calculateInteger(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        }
    }

    private Double calculateDouble(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + operator);
        }
    }

    public T calculate(T num1, T num2, String operator) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            int result = calculateInteger(num1.intValue(), num2.intValue(), operator);
            return (T) Integer.valueOf(result);
        }

        if (num1 instanceof Double && num2 instanceof Double) {
            double result = calculateDouble(num1.doubleValue(), num2.doubleValue(), operator);
            return (T) Double.valueOf(result);
        }

        throw new UnsupportedOperationException("지원되지 않는 타입입니다.");
    }

    public static void main(String[] args) {
        B_generic2<Number> intCalculator = new B_generic2<>();
        System.out.println(intCalculator.calculate(10, 20, "+"));
        System.out.println(intCalculator.calculate(20, 10, "-"));
        System.out.println(intCalculator.calculate(10, 20, "*"));
        System.out.println(intCalculator.calculate(20, 10, "/"));





//        B_generic2<Integer> intCalculator = new B_generic2<>();
//        System.out.println("Integer Addition: " + intCalculator.add(10, 20));
//        System.out.println("Integer Subtraction: " + intCalculator.subtract(20, 10));
//        System.out.println("Integer Multiplication: " + intCalculator.multiply(10, 20));
//        System.out.println("Integer Division: " + intCalculator.divide(20, 10));
//
//        B_generic2<Double> doubleCalculator = new B_generic2<>();
//        System.out.println("Double Addition: " + doubleCalculator.add(10.5, 20.3));
//        System.out.println("Double Subtraction: " + doubleCalculator.subtract(20.5, 10.2));
//        System.out.println("Double Multiplication: " + doubleCalculator.multiply(10.0, 20.0));
//        System.out.println("Double Division: " + doubleCalculator.divide(20.0, 10.0));
    }


}
