import java.util.*;

public class Banco {
    private List<Conta> contas = new ArrayList<>();
    private int contadorContas = 1;

    public Conta criarConta(String tipo, String nome, String cpf){
        Cliente cliente = new Cliente(nome, cpf);
        Conta conta = tipo.equals("corrente") ? new ContaCorrente(cliente, contadorContas) : new ContaPoupanca(cliente, contadorContas);

        contas.add(conta);
        contadorContas++;
        return conta;
    }

    public Conta buscarConta(int numero){
        for (Conta c : contas){
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    public List<Conta> listarContas(){
        return contas;
    }
}
