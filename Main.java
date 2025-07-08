import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Banco banco = new Banco();

    public static void main(String[] args) {
        System.out.println("=== BEM-VINDO AO ALPHABANK ===");

        int opcao;
        do{
            exibirMenu();
            opcao = lerOpcao();
            try{
                switch (opcao){
                    case 1 -> criarConta();
                    case 2 -> depositar();
                    case 3 -> sacar();
                    case 4 -> transferir();
                    case 5 -> consultarSaldo();
                    case 6 -> exibirExtrato();
                    case 7 -> listarContas();
                    case 0 -> System.out.println("Obrigado por nosso sistema");
                    default -> System.out.println("Opção inválida!!! Tente novamente");
                }
            } catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu(){
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Criar Conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Transferir");
        System.out.println("5 - Consultar Saldo");
        System.out.println("6 - Extrato");
        System.out.println("7 - Listar Contas");
        System.out.println("0 - Sair");
        System.out.println("Escolha uma opção: ");
    }

    private static int lerOpcao(){
        try{
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (InputMismatchException e){
            scanner.nextLine();
            return -1;
        }
    }

    private static void criarConta(){
        System.out.println("Nome do cliente: ");
        String nome = scanner.nextLine();

        System.out.println("CPF: ");
        String cpf = scanner.nextLine();

        System.out.println("Tipo (corrente/poupança): ");
        String tipo = scanner.nextLine();

        try{
            Conta novaConta = banco.criarConta(tipo, nome, cpf);
            System.out.println("✓ Conta criada com sucesso!");
            System.out.println("Número da conta: " + novaConta.getNumero());
            System.out.println("Tipo: " + novaConta.getTipo());
        } catch (IllegalArgumentException e){
            System.out.println("✗ Erro ao criar conta: " + e.getMessage());
        }
    }

    private static void depositar(){
        System.out.println("Número da conta: ");
        int numero = scanner.nextInt();

        Conta conta = banco.buscarConta(numero);
        if (conta == null){
            System.out.println("✗ Conta não encontrada!");
            return;
        }

        System.out.println("Valor do depósito: R$ ");
        BigDecimal valor = scanner.nextBigDecimal();

        try{
            conta.depositar(valor);
            System.out.println("✓ Depósito realizado com sucesso!");
            System.out.println("Novo saldo: R$ " + conta.getSaldo().setScale(2, RoundingMode.HALF_UP));
        } catch (IllegalArgumentException e){
            System.out.println("✗ Erro: " + e.getMessage());
        }
    }

        private static void sacar() {
        System.out.print("Número da conta: ");
        int numero = scanner.nextInt();
        
        Conta conta = banco.buscarConta(numero);
        if (conta == null) {
            System.out.println("✗ Conta não encontrada!");
            return;
        }
        
        System.out.print("Valor do saque: R$ ");
        BigDecimal valor = scanner.nextBigDecimal();
        
        try {
            if (conta.sacar(valor)) {
                System.out.println("✓ Saque realizado com sucesso!");
                System.out.println("Novo saldo: R$ " + conta.getSaldo().setScale(2, RoundingMode.HALF_UP));
            } else {
                System.out.println("✗ Saldo insuficiente!");
                if (conta instanceof ContaCorrente) {
                    ContaCorrente cc = (ContaCorrente) conta;
                    System.out.println("Saldo disponível (com limite): R$ " + 
                                     cc.getSaldoDisponivel().setScale(2, RoundingMode.HALF_UP));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Erro: " + e.getMessage());
        }
    }

    private static void transferir(){
        System.out.println("Conta origem: ");
        int numeroOrigem = scanner.nextInt();

        System.out.println("Conta destino: ");
        int numeroDestino = scanner.nextInt();

        Conta contaOrigem = banco.buscarConta(numeroOrigem);
        Conta contaDestino = banco.buscarConta(numeroDestino);

        if (contaOrigem == null || contaDestino == null){
            System.out.println("✗ Uma das contas não foi encontrada!");
            return;
        }

        System.out.println("Valor da transferência: R$ ");
        BigDecimal valor = scanner.nextBigDecimal();

        try{
            if (contaOrigem.transferir(contaDestino, valor)){
                System.out.println("✓ Transferência realizada com sucesso!");
                System.out.println("Novo saldo da conta origem: R$ " + contaOrigem.getSaldo().setScale(2, RoundingMode.HALF_UP));
            } else{
                System.out.println("✗ Saldo insuficiente!");
            }
        } catch (IllegalArgumentException e){
            System.out.println("✗ Erro: " + e.getMessage());
        }
    }
}
