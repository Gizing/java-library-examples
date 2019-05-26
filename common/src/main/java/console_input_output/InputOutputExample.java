package console_input_output;

import java.util.Scanner;

public class InputOutputExample
{

    public static void main(String[] args)
    {
        System.out.println("输入");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        System.out.print(count + "个输入： ");
        for (int i = 0; i < count; ++i)
        {
            int value = scanner.nextInt();
            System.out.print(value + " ");
        }
        System.out.println();
        scanner.close();

        System.out.println("输出");
        System.out.printf("%s-%d-%.2f-%2.1f", "hello", 1, 1.1111, 6666.666666);
    }

}
