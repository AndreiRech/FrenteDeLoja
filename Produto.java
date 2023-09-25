public class Produto {
    private int codigo;
    private String descricao;
    private double precoUnitario;

    public Produto (int codigo, String descricao, double precoUnitario) {
       this.codigo = codigo;

        if (!descricao.isEmpty()) {
            this.descricao = descricao;
        }
        else {
            this.descricao = "Descrição não informada";
        }

        if (precoUnitario > 0) {
            this.precoUnitario = precoUnitario;
        }
        else {
            this.precoUnitario = 1;
        }
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString() {
        return "\n[Codigo = " + codigo + " | Descricao = " + descricao + " | Preco Unitario = R$" + precoUnitario + "]\n";
    }

    
}