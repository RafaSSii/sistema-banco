import java.util.ArrayList;
import java.util.List;

public class Banco{
    private List<Conta> contas = new ArrayList<>();
    private int contadorContas = 1001;

    public Conta criarConta(String tipo, String nome, String cpf) throws IllegalArgumentException{
        Cliente cliente = new Cliente(nome, cpf);
        Conta conta;

        switch (tipo.toLowerCase()){
            case "corrente":
                conta = new ContaCorrente(cliente, contadorContas);
                break;
            case "poupanca":
            case "poupança":
                conta = new ContaPoupanca(cliente, contadorContas);
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido! Use 'corrente' ou 'poupanca'");
        }

        contas.add(conta);
        contadorContas++;
        return conta;
    }

    public Conta buscarConta(int numero){
        for (Conta c : contas){
            if (c.getNumero() == numero){
                return c;
            }
        }
        return null;
    }

    public List<Conta> listarContas(){
        return new ArrayList<>(contas);
    }

    public boolean existeConta(int numero){
        return buscarConta(numero) != null;
    }
    
}
