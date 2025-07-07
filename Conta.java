import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta {
    protected Cliente cliente;
    protected BigDecimal saldo;
    protected int numero;
    protected List<Transacao> historico;

    public Conta(Cliente cliente, int numero){
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = BigDecimal.ZERO;
        this.historico = new ArrayList<>();
        this.historico.add(new Transacao("ABERTURA", BigDecimal.ZERO, "Conta criada"));
    }

    public void depositar(BigDecimal valor) throws IllegalArgumentException{
        validarValor(valor);
        saldo = saldo.add(valor);
        historico.add(new Transacao("DEPÓSITO", valor, "Depósito realizado"));
    }

    public boolean sacar(BigDecimal valor) throws IllegalArgumentException{
       validarValor(valor);
       if (podeRetirar(valor)){
        saldo = saldo.subtract(valor);
        historico.add(new Transacao("SAQUE", valor, "Saque realizado"));
        return true;
       }
       return false; 
    }

    public boolean transferir(Conta destino, BigDecimal valor) throws IllegalArgumentException{
        validarValor(valor);
        if (podeRetirar(valor)){
            saldo = saldo.subtract(valor);
            destino.saldo = destino.saldo.add(valor);

            historico.add(new Transacao("TRANFERÊNCIA", valor, "Transferêmcia para conta" + destino.numero));
            destino.historico.add(new Transacao("TRANSFERÊNCIA", valor, "Transferência recebida da conta" + numero));
            return true;
        }
        return false;
    }

    protected void validarValor(BigDecimal valor){
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor deve ser positivo!!!");
        }
    }

    protected boolean podeRetirar(BigDecimal valor){
        return saldo.compareTo(valor) >= 0;
    }

    public void exibirExtrato(){
        System.out.println("\n=== EXTRATO DA CONTA " + numero + " ===");
        System.out.println(cliente.toString());
        System.out.println("Tipo: " + getTipo());
        System.out.println("\nMovimentações:");

        for (Transacao t : historico){
            System.out.println("- " + t.toString());
        }

        System.out.println("\nSaldo atual: R$ " + saldo.setScale(2, RoundingMode.HALF_UP));
        System.out.println("=====================================\n");
    }

    public BigDecimal getSaldo(){
        return saldo;
    }

    public int getNumero(){
        return numero;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public abstract String getTipo();

    @Override
    public String toString(){
        return String.format("Conta %d - %s - %s - Saldo: R$ %.2f", numero, getTipo(), cliente.getNome(), saldo);
    }
}