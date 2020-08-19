import java.util.Scanner;

public class Calculadora {
    public static void main (String[] args) {
        boolean keepCalc = false;

        do {
            Scanner input = new Scanner(System.in);
            String[] nomeTermo1 = {"a primeira parcela", "o aditivo", "o dividendo", "o primeiro fator"};
            String[] nomeTermo2 = {"a segunda parcela", "o subtrativo", "o divisor", "o segundo fator"};

            System.out.println("--- Calculadora aritmética ---");
            System.out.println();
            System.out.println("+ = adição");
            System.out.println("- = subtração");
            System.out.println("/ = divisão");
            System.out.println("* = multiplicação");

            String op;
            int opCode = 0;

            do {
                System.out.print("Qual operação deseja realizar? ");
                op = input.nextLine();
                switch (op) {
                    case "+" -> {
                        System.out.println("Adição selecionada");
                        opCode = 0;
                    }
                    case "-" -> {
                        System.out.println("Subtração selecionada");
                        opCode = 1;
                    }
                    case "/" -> {
                        System.out.println("Divisão selecionada");
                        opCode = 2;
                    }
                    case "*" -> {
                        System.out.println("Multiplicação selecionada");
                        opCode = 3;
                    }
                    default -> System.out.println("ATENÇÃO: operação inválida");
                }
                System.out.println();
            } while (!op.equals("+") && !op.equals("-") && !op.equals("/") && !op.equals("*"));


            int termo1;
            int termo2;
            double resultado;

            System.out.print("Insira " + nomeTermo1[opCode] + ": ");
            termo1 = input.nextInt();

            do {
                System.out.print("Insira " + nomeTermo2[opCode] + ": ");
                termo2 = input.nextInt();

                if (opCode == 2 && termo2 == 0) {
                    System.out.println("Não é permitido dividir por 0, tente novamente.");
                    System.out.println();
                }
            } while (opCode == 2 && termo2 == 0);


            switch (op) {
                case "+" -> {
                    resultado = termo1 + termo2;
                }
                case "-" -> {
                    resultado = termo1 - termo2;
                }
                case "/" -> {
                    resultado = (double) termo1 / termo2;
                }
                case "*" -> {
                    resultado = termo1 * termo2;
                }
                default -> {
                    resultado = 0;
                }
            }

            System.out.println();
            System.out.println(termo1 + " " + op + " " + termo2 + " = " + resultado);

            System.out.println();
            System.out.print("Deseja continuar calculando? ");
            keepCalc = input.nextBoolean();

            System.out.println("\n");
        } while (keepCalc);
    }
}
