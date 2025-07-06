public class ContaCorrente extends Conta {
    public ContaCorrente(Cliente cliente, int numero){
        super(cliente, numero);
    }

    public String getTipo(){
        return "Conta Corrente";
    }
}
