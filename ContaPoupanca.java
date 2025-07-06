public class ContaPoupanca extends Conta {
    public ContaPoupanca(Cliente cliente, int numero){
        super(cliente, numero);
    }

    public String getTipo(){
        return "Conta Poupança";
    }
}
