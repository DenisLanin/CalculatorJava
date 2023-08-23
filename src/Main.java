import java.util.Scanner;

public class Main {

    private static boolean _romanResult = false;


    private Main(){
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
        int firstNumber = 0;
        int secondNumber = 0;


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

        if(!_romanResult && firstNumber > 10){
            return "Первое число больше 10";
        }

        if(!_romanResult && secondNumber > 10){
            return "Второе число больше 10";
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

        if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
            return 10;
        }
        return -1;
    }


    public static void main(String[] args)
    {
        new Main();
    }
}
