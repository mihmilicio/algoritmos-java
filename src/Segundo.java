import java.util.Scanner;

public class Segundo {

    public static void main (String[] args) {
        Scanner leitor = new Scanner(System.in);

        System.out.print("Informe a matrícula:  ");
        int matricula = leitor.nextInt();

        leitor.nextLine();

        System.out.print("Informe o nome:  ");
        String nome = leitor.nextLine();

        System.out.print("Está presente?  ");
        boolean presente = leitor.nextBoolean();


        System.out.println();
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.print("Presente: ");
        if (presente) {
            System.out.println("(X) Sim    ( ) Não");
        } else {
            System.out.println("( ) Sim    (X) Não");
        }

    }

}
