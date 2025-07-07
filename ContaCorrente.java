import java.math.BigDecimal;
import java.math.RoundingMode;

public class ContaCorrente extends Conta{
    private BigDecimal limite;

    public ContaCorrente(Cliente cliente, int numero){
        super(cliente, numero);
        this.limite = new BigDecimal("500.00");
    }

    @Override
    protected boolean podeRetirar(BigDecimal valor){
        BigDecimal saldoDisponivel = saldo.add(limite);
        return saldoDisponivel.compareTo(valor) >= 0;
    }

    @Override
    public String getTipo(){
        return "Conta Corrente";
    }

    public BigDecimal getLimite(){
        return limite;
    }

    public BigDecimal getSaldoDisponivel(){
        return saldo.add(limite);
    }

    @Override
    public void exibirExtrato(){
        System.out.println("\n=== EXTRATO DA CONTA " + numero + " ===");
        System.out.println(cliente.toString());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Limite: R$ " + limite.setScale(2, RoundingMode.HALF_UP));
        System.out.println("\nMovimentações:");

        for (Transacao t : historico){
            System.out.println("- " + t.toString());
        }

        System.out.println("\nSaldo atual: R$ " + saldo.setScale(2, RoundingMode.HALF_UP));
        System.out.println("=====================================\n");
    }
}