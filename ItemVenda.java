public class ItemVenda {
    private Produto produto;
    private double precoUnitarioCobrado;
    private int quantidade;

    public ItemVenda (Produto produto, double precoUnitarioCobrado, int quantidade) {
        this.produto = produto;

        if (precoUnitarioCobrado > 0) {
            this.precoUnitarioCobrado = precoUnitarioCobrado;
        }
        else {
            precoUnitarioCobrado = 1;
        }

        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
        else {
            quantidade = 0;
        }
    }

    public Produto getProduto() {
        return produto;
    }

    public double getPrecoUnitarioCobrado() {
        return precoUnitarioCobrado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorItem() {
        return precoUnitarioCobrado * quantidade;
    }
}