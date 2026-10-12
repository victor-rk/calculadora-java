import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class Main {

    public static double calculate(double x1, double x2, String operator) {
        double result = 0;

        switch (operator) {
            case "+":
                result = x1 + x2;
                break;
            case "-":
                result = x1 - x2;
                break;
            case "*":
                result = x1 * x2;
                break;
            case "/":
                if (x2 != 0) {
                    result = x1 / x2;
                }
                else {
                    throw new ArithmeticException("Erro: divisão por zero\n");
                }
                break;
        }

        return result;
    }

    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e) {
            System.out.println("Erro na pausa.");
        }
    }



    public static void main (String[] args) {

        List<String> history = new ArrayList<>();
        double equals;
        boolean continueCalc = true, valid_n1 ,valid_n2, correctOp;
        int calcId = 1;
        String operationLog;

        Scanner scan = new Scanner(System.in);
        DecimalFormat formatter = new DecimalFormat("0.##");

        System.out.println("\n=================================");
        System.out.println("           Calculadora           ");
        System.out.println("=================================");

        while (continueCalc) {
            double n1 = 0, n2 = 0;
            valid_n1 = false;
            valid_n2 = false;

            while (!valid_n1) {
                try {
                    System.out.print("\nDigite o primeiro numero: ");
                    n1 = Double.parseDouble(scan.next().replace(",", "."));
                    valid_n1 = true;
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Entrada inválida, digite apenas números.");
                }
            }

            System.out.print("\nDigite o operador (+ - * /): ");
            String op = scan.next();

            while (!op.equals("+") && !op.equals("-") && !op.equals("*") && !op.equals("/")) {
                System.out.println("\nOperador inválido, tente novamente\n");
                System.out.print("Digite o operador (+ - * /): ");
                op = scan.next();
            }

            while (!valid_n2) {
                try {
                    System.out.print("\nDigite o segundo numero: ");
                    n2 = Double.parseDouble(scan.next().replace(",", "."));
                    valid_n2 = true;
                } catch (NumberFormatException e) {
                    System.out.println("\nErro: Entrada inválida, digite apenas números.");
                }
            }

            try {
                equals = calculate(n1, n2, op);

                System.out.println("\n" + formatter.format(n1) + " " + op + " " + formatter.format(n2) + " = " + formatter.format(equals));
                System.out.println("Resultado: " + formatter.format(equals));

                operationLog = calcId + ") " + formatter.format(n1) + " " + op + " " + formatter.format(n2) + " = " + formatter.format(equals);
                history.add(operationLog);

            } catch (ArithmeticException e) {
                System.out.println("\n" + e.getMessage());
            }

            wait(2000);

            System.out.println("\n===== Opções =====");
            System.out.println("1) Fazer outra conta");
            System.out.println("2) Mostrar histórico");
            System.out.println("3) Sair da calculadora");

            correctOp = false;

            while (!correctOp) {
                System.out.print("\nColoque o numero da opção: ");
                String option = scan.next();

                switch (option) {
                    case "1":
                        correctOp = true;
                        break;
                    case "2":
                        System.out.println("\n==== Historico ====");
                        for (int i = 0; i < history.size(); i++) {
                            System.out.println("Calculo " + history.get(i));
                        }

                        wait(1500);

                        System.out.print("\nContinuar?\n1) Sim\n2) Nao\n\nColoque o numero da opção: ");
                        String subOption = scan.next();

                        if (subOption.equals("2")) {
                            continueCalc = false;
                            System.out.println("Programa finalizado.");
                        } else if (!subOption.equals("1")) {
                            System.out.println("\nOpção inválida, voltando à calculadora...");
                            wait(1000);
                        }

                        correctOp = true;
                        break;
                    case "3":
                        System.out.println("Programa finalizado.");
                        continueCalc = false;
                        correctOp = true;
                        break;

                    default:
                        System.out.println("Insira uma opção válida.");
                        break;
                }
            }
            calcId++;

        }

        scan.close();
    }
}
