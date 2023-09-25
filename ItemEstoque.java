public class ItemEstoque {
    private Produto produto;
    private int quantidade;

    public ItemEstoque (Produto produto, int quantidade) {
        this.produto = produto;

        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
        else {
            this.quantidade = 0;
        }
    }

    public int getCodigo() {
        return produto.getCodigo();
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public boolean baixaEstoque (int quant) {
        if (quant <= quantidade && quant > 0) {
            quantidade -= quant;
            return true;
        }
        return false;
    }

    public boolean reposicaoEstoque (int quant) {
        if (quant > 0) {
            quantidade += quant;
            return true;
        }
        return false;
    }
}