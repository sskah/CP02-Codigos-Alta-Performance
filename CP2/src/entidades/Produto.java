package entidades;

public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private String localizacao;

    public Produto(String codigo, String descricao, double preco, String localizacao){
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.localizacao = localizacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }
}
