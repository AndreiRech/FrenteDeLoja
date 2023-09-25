import java.util.ArrayList;
public class Venda {
    private ArrayList <ItemVenda> itens;
    private Estoque estoque;
    private static int proxNum = 1;
    private int numero;
    private double subtotal, desconto, total, imposto;
    private boolean concluida = false;
    private final double IMPOSTO = 0.25, DESCONTO = 0.1;

    public Venda(Estoque estoque) {
        this.estoque = estoque;
        itens = new ArrayList<>();
        this.numero = proxNum++;
    }

    public boolean insereItem(Produto produto, double precoUnitarioCobrado, int quantidade) {
        int codigoProduto = produto.getCodigo();
        int quantidadeDisponivel = estoque.getQuantidadeDisponivel(codigoProduto);

        if (!concluida && quantidade > 0 && quantidadeDisponivel >= quantidade && precoUnitarioCobrado >= 0) {
            if (estoque.baixaEstoque(codigoProduto, quantidade)) {
                ItemVenda item = new ItemVenda(produto, precoUnitarioCobrado, quantidade);
                itens.add(item);
                return true;
            }
        }
        return false;
    }

    public boolean removeItem(int codigo) {
        if (!concluida) {
            ArrayList<ItemVenda> itensParaRemover = new ArrayList<>();
            for (ItemVenda item : itens) {
                if (item.getProduto().getCodigo() == codigo) {
                    itensParaRemover.add(item);
                }
            }
            itens.removeAll(itensParaRemover);
            return !itensParaRemover.isEmpty();
        }
        return false;
    }

    public boolean conclui() {
        if (!concluida && !itens.isEmpty()) {
            subtotal = 0;

            concluida = true;
            return true;
        }
        return false;
    }

    public void imprimeRecibo() {
        System.out.println("Número da Venda: " + getNumero());
        System.out.println("Itens da Venda:");

        for (ItemVenda item : itens) {
            Produto produto = item.getProduto();
            System.out.println("- " + produto.getDescricao() + " x" + item.getQuantidade() + " Preço Unitário: R$" + item.getPrecoUnitarioCobrado() + " Valor do Item: R$" + item.getValorItem());
        }

        System.out.println("Subtotal: R$" + getSubtotal());
        System.out.println("Desconto: R$" + getDesconto());
        System.out.println("Imposto: R$" + getImposto());
        System.out.println("Total: R$" + getTotal() +"\n");
    }

    public double getDesconto() {
        double subTotal = getSubtotal();

        if (subTotal > 250) {
            desconto = subTotal * DESCONTO;
        }
        else {
            desconto = 0;
        }

        return desconto;
    }

    public double getImposto() {
        double subTotal = getSubtotal();
        imposto = IMPOSTO * subTotal;
        return imposto;
    }

    public double getSubtotal() {
        subtotal = 0;
        for (ItemVenda i : itens) {
            subtotal += i.getValorItem();
        }
        return subtotal;
    }

    public double getTotal() {
        total = getSubtotal() - getDesconto() + getImposto();
        return total;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        imprimeRecibo();
        return "";
    }
}