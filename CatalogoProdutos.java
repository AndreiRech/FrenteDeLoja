import java.util.ArrayList;
public class CatalogoProdutos {
    private ArrayList<Produto> produtos;

    public CatalogoProdutos () {
        produtos = new ArrayList<>();
    }

    public boolean cadastra(Produto p) {
        for (Produto produtoExistente : produtos) {
            if (produtoExistente.getCodigo() == p.getCodigo()) {
                return false;
            }
        }
        return produtos.add(p);
    }

    public Produto consulta(int codigo) {
        for (Produto p : produtos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void imprime() {
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}