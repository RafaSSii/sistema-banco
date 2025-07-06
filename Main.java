import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        int opcao;

        do{
            System.out.println("\n=== BANCO SIMPLES ===");
            System.out.println("1 - Criar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Listar Contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao){
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Tipo (corrente/poupança): ");
                    String tipo = scanner.nextLine();

                    Conta novaConta = banco.criarConta(tipo, nome, cpf);
                    System.out.println("Conta criada!!! Número: " + novaConta.getNumero());
                }
            }
        }
    }
}
