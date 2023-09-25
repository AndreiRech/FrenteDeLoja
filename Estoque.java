import java.util.ArrayList;
public class Estoque {
    private ArrayList <ItemEstoque> itens;

    public Estoque() {
        itens = new ArrayList<>();
    }

    public Produto getProduto(int codigo) {
        for (ItemEstoque i: itens) {
            if (i.getCodigo() == codigo) {
                return i.getProduto();
            }
        } 
        return null;
    }

    public int getQuantidadeDisponivel(int codigo) {
        for (ItemEstoque i: itens) {
            if (i.getCodigo() == codigo) {
                return i.getQuantidade();
            }
        } 
        return 0;
    }

    public boolean baixaEstoque (int codigo, int quantidade) {
        for (ItemEstoque i: itens) {
            if(i.getCodigo() == codigo && i.getQuantidade() >= quantidade) {
                i.baixaEstoque(quantidade);
                return true;
            }
        }
        return false;
    }

    public boolean reposicaoEstoque (int codigo, int quantidade) {
        for (ItemEstoque i: itens) {
            if (i.getCodigo() == codigo) {
                i.reposicaoEstoque(quantidade);
                return true;
            }
        }
        return false;
    }

    public boolean cadastraProduto (Produto p, int quantidade) {
        ItemEstoque item = new ItemEstoque(p, quantidade);
        itens.add(item);
        return true;
    }
}
