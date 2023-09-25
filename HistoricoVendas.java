import java.util.ArrayList;
public class HistoricoVendas {
    private ArrayList<Venda> vendas;

    public HistoricoVendas() {
        vendas = new ArrayList<>();
    }

    public boolean cadastraVenda(Venda v) {
        vendas.add(v);
        return true;
    }

    public Venda getVenda(int numero) {
        for (Venda v : vendas) {
            if (v.getNumero() == numero) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Venda> getUltimasVendas(int n) {
        ArrayList<Venda> ultimas = new ArrayList<>();

        for (int i = vendas.size() - 1; i >= 0 && ultimas.size() < n; i--) {
            Venda v = vendas.get(i);
            ultimas.add(v);
        }
        return ultimas;
    }
}
