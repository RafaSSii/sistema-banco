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
}