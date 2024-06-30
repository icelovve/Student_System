package out;

import java.util.Scanner;

public class cal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String choice;

        do {
            System.out.print("\nEnter number 1 \n>> ");
            double number_1 = input.nextDouble();

            System.out.print("Enter operator (+, -, *, /) \n>> ");
            String operator = input.next();

            System.out.print("Enter number 2 \n>> ");
            double number_2 = input.nextDouble();

            System.out.println();
            switch (operator) {
                case "+":
                    System.out.print(number_1 + " " + operator + " " + number_2 + " >> ");
                    System.out.println(add(number_1, number_2));
                    break;
                case "-":
                    System.out.print(number_1 + " " + operator + " " + number_2 + " >> ");
                    System.out.println(subtract(number_1, number_2));
                    break;
                case "*":
                    System.out.print(number_1 + " " + operator + " " + number_2 + " >> ");
                    System.out.println(multiply(number_1, number_2));
                    break;
                case "/":
                    if (number_2 != 0) {
                        System.out.print(number_1 + " " + operator + " " + number_2 + " >> ");
                        System.out.println(divide(number_1, number_2));
                    } else {
                        System.out.println("Error: Division by zero.");
                    }
                    break;
                default:
                    System.out.println("Invalid operator");
                    break;
            }

            System.out.print("Do you want to continue[y/n]: ");
            choice = input.next();
        } while (!choice.equals("n"));

        input.close();
    }

    public static double add(double x, double y) {
        return x + y;
    }

    public static double subtract(double x, double y) {
        return x - y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        return x / y;
    }
}
