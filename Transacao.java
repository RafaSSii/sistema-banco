import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Transacao {
    private String tipo;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String descricao;

    public Transacao(String tipo, BigDecimal valor, String descricao){
        this.tipo = tipo;
        this.valor = valor;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("%s | %s | R$ %.2f | %s", dataHora.format(formatter), tipo, valor, descricao);
    }
}


