public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) throws IllegalArgumentException {
        validarNome(nome);
        validarCpf(cpf);
        this.nome = nome;
        this.cpf = cpf;
    }
    public void validarNome(String nome){
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("Coloque um nome");
        }
        if (nome.trim().length() < 2){
            throw new IllegalArgumentException("Nome deve ter pelo menos 2 caracteres");
        }
    }
    private void validarCpf(String cpf){
        if (cpf == null || cpf.replaceAll("[^0-9]","").length() != 11){
            throw new IllegalArgumentException("CPF dewve ter 11 dígitos");
        }
    }
    public String getNome(){
        return nome;
    }
    public String getCpf(){
        return cpf;
    }

    @Override
    public String toString(){
        return String.format("Cliente: %s | CPF: %s", nome, cpf);
    }

}

