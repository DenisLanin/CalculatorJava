import java.util.Scanner;

public class Calculator {

    private static boolean _romanResult = false;


    private Calculator(){
        Scanner in = new Scanner(System.in);
        System.out.println("Введите данные: ");

        String input = in.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input){
        final String operator;
        final int romanFirstNumber;
        final int romanSecondNumber;
        final int result;
        int firstNumber;
        int secondNumber;


        final String[] inputData = input.split(" ");

        if(inputData.length > 3){
            return "Получено некорректное количество значений " + inputData.length + ".\nВерный формат:" +
                    " число, оператор, число. Числа могут быть как римскими, так и арабскими.";
        }

        romanFirstNumber = convertRomanNumber(inputData[0]);
        romanSecondNumber = convertRomanNumber(inputData[2]);

        if(romanFirstNumber == -1 && romanSecondNumber == -1){
            try {
                firstNumber = Integer.parseInt(inputData[0]);
                secondNumber = Integer.parseInt(inputData[2]);

                if(firstNumber > 10){
                    return "Первое число больше 10";
                }

                if(secondNumber > 10){
                    return "Второе число больше 10";
                }
            }
            catch (NumberFormatException e){
                return "Число не должно превышать X";
            }
        }
        else if(romanFirstNumber != -1 && romanSecondNumber != -1){
            _romanResult = true;

            firstNumber = romanFirstNumber;
            secondNumber = romanSecondNumber;
        }
        else {
            return "Введены не верные значения. Калькулятор работает только с цифрами одного типа (римские или арабские).";
        }

        operator = inputData[1];

        if(operator.contains("/") && secondNumber == 0){
            return "Деление на 0 :С";
        }

        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            default -> result = -1;
        }

        if(result == -1){
            return "Не верный оператор.";
        }

        if(_romanResult){
            return RomanNumerals.convert(result);
        }
        else
            return Integer.toString(result);
    }

    private static int convertRomanNumber (String roman) {

        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }

    public static void main(String[] args)
    {
        new Calculator();
    }
}
