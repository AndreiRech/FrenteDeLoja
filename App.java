import java.util.Scanner;

public class App {
    private Scanner in = new Scanner (System.in);
    private CatalogoProdutos catalogo = new CatalogoProdutos();
    private HistoricoVendas historico = new HistoricoVendas();
    private Estoque estoque = new Estoque();

    public void menuSelecaoUsuario() {
        String mensagem = """
                \n---------- [ SELEÇÃO DE USUÁRIO ] ----------

                1. [ Gerente ]
                2. [ Cliente ]
                3. [ Sair ]
                """;
        System.out.println(mensagem);
    }

    public void menuGerente() {
        String mensagem = """
                \n---------- [ MENU DE OPÇÕES - GERENTE ] ----------

                1. [Imprimir catálogo]
                2. [Consultar item no catálogo]
                3. [Adicionar produto]
                4. [Dar baixa em item no estoque]
                5. [Repor item no estoque]
                6. [Ver quantidade de produtos em estoque]
                7. [Ver últimas vendas]
                8. [Ver venda específica]
                9. [Voltar]
                """;
        System.out.println(mensagem);
    }

    public void menuCliente() {
        String mensagem = """
                \n---------- [ MENU DE OPÇÕES - CLIENTE ] ----------

                1. [Imprimir catálogo]
                2. [Inserir item]
                3. [Remover item]
                4. [Imprimir recibo]
                5. [Concluir venda]
                6. [Voltar]
                """;
        System.out.println(mensagem);
    }

    public void selecaoUsuario() {

        int opc = 0;

        do {
            menuSelecaoUsuario();
            opc = in.nextInt();

            switch (opc) {
                case 1:
                    opcoesGerente();
                    break;
                case 2:
                    opcoesCliente();
                    break;
                case 3:
                    System.out.println("\n... Saindo ...");
                    break;
                default:
                    System.out.println("\nInforme uma opção válida!");
                    break;
            }
        }
        while (opc != 3);
    }

    public void opcoesCliente() {
        int opc=0, codigo=0, quantidade=0;
        boolean continuar = true;
        String escolha="";
        Venda venda = new Venda(estoque);

        do {
            menuCliente();
            opc = in.nextInt();

            switch(opc) {
                case 1:
                    imprimeCatalogo();
                    break;
                case 2:
                    insereItem(venda, codigo, quantidade, continuar, escolha);
                    break;
                case 3:
                    removeItem(venda, codigo);
                    break;
                case 4:
                    imprimeRecibo(venda);
                    break;
                case 5:
                    concluiVenda(venda);
                    break;
                case 6:
                    System.out.println("\nSaindo do menu do cliente!");
                    break;
                default:
                    System.out.println("\nInforme uma opção válida!");
                    break;
            }
        }
        while(opc != 6);
    }

    public void opcoesGerente() {
        double precoUnitario=0;
        String descricao="", escolha="";
        int codigo=0, quantidade=0, opc = 0;
        boolean continuar = true;

        do {
            menuGerente();
            opc = in.nextInt();

            switch(opc) {
                case 1:
                    imprimeCatalogo();
                    break;
                case 2:
                    consultaCatalogo(codigo);
                    break;
                case 3:
                    adicionarProduto(continuar, codigo, descricao, precoUnitario, escolha, quantidade);
                    break;
                case 4:
                    baixaEstoque(continuar, codigo, quantidade, escolha);
                    break;
                case 5:
                    reporEstoque(continuar, codigo, quantidade, escolha);
                    break;
                case 6:
                    quantidadeEstoque(codigo);
                    break;
                case 7:
                    ultimasVendas(quantidade);
                    break;
                case 8:
                    vendaEspecifica(codigo);
                    break;
                case 9:
                    System.out.println("\nSaindo do menu do gerente!");
                    break;
                default:
                    System.out.println("\nInforme uma opção válida!");
                    break;
            }
        }
        while (opc != 9);
    }

    public void imprimeCatalogo() {
        System.out.println("\n---------- [ CATÁLOGO ] ----------\n");
        catalogo.imprime();
    }

    public void insereItem(Venda venda, int codigo, int quantidade, boolean continuar, String escolha) {
        System.out.println("\n---------- [ INSERINDO ITEM ] ----------");

        while(continuar) {
            System.out.print("\nInforme o código do produto a ser adicionado: ");
            codigo = in.nextInt();
            Produto produtoAdd = catalogo.consulta(codigo);

            if(produtoAdd != null) {
                System.out.print("\nDigite a quantidade de produtos a serem adicionados: ");
                quantidade = in.nextInt();

                if(venda.insereItem(produtoAdd, produtoAdd.getPrecoUnitario() , quantidade)) {
                    System.out.println("Item adicionado com sucesso!");
                }
                else {
                    System.out.println("Houve um problema ao inserir o item.");                    }
            }
            else {
                System.out.println("Produto não encontrado no catálogo!");
            }

            System.out.print("\nDeseja adicionar outro produto? [S / N] : ");
            escolha = in.next();

            if (escolha.equals("N")) {
                continuar = false;
            }
        }
    }

    public void removeItem(Venda venda, int codigo) {
        System.out.println("\n---------- [ REMOVENDO ITEM ] ----------");

        System.out.print("\nInforme o código do produto a ser removido: ");
        codigo = in.nextInt();
        Produto produtoRmv = catalogo.consulta(codigo);

        if(produtoRmv != null) {
            if (venda.removeItem(codigo)) {
                System.out.println("Item removido com sucesso!");
            }
            else {
            System.out.println("Houve um problema ao remover o item.");
            }
        }
        else {
            System.out.println("Produto não encontrado no catálogo!");
        }
    }

    public void imprimeRecibo(Venda venda) {
        System.out.println("\n---------- [ IMPRIMINDO RECIBO ] ----------");
        venda.imprimeRecibo();
    }

    public void concluiVenda(Venda venda) {
        System.out.println("\n---------- [ CONCLUINDO VENDA ] ----------");

        if (venda.conclui()) {
            System.out.println("A venda foi concluída com sucesso!");
            historico.cadastraVenda(venda);
            System.out.println("Caso deseje adicionar outra venda, retorne a seleção de usuário antes de adicionar um novo item!");
        }
        else {
            System.out.println("Houve um problema ao conlcuir a venda.");
        }
    }

    public void consultaCatalogo(int codigo) {
        System.out.println("\n---------- [ CONSULTAR NO CATÁLOGO ] ----------");

        System.out.print("\nInforme o código do produto a ser encontrado: ");
        codigo = in.nextInt();

        System.out.println(catalogo.consulta(codigo));
    }

    public void adicionarProduto(boolean continuar, int codigo, String descricao, double precoUnitario, String escolha, int quantidade) {
        continuar = true;

        while(continuar) {
            System.out.println("\n---------- [ ADICIONAR PRODUTO ] ----------");
                    
            System.out.print("\nInforme o código do item: ");
            codigo = in.nextInt();

            System.out.print("\nInforme a descrição do item: ");
            descricao = in.next();

            System.out.print("\nInforme o preço unitário do item: ");
            precoUnitario = in.nextDouble();

            Produto produto = new Produto(codigo, descricao, precoUnitario);

            if (catalogo.cadastra(produto)) {
                System.out.println("\nProduto cadastrado com sucesso no catálogo!");
            } else {
                System.out.println("\nHouve um problema no cadastro no catálogo.");
                break;
            }

            System.out.print("\nDeseja cadastrar item no estoque? [S / N] : ");
            escolha = in.next().toUpperCase();

            if (escolha.equals("S")) {
                System.out.print("\nInforme a quantidade de itens a serem adicionados: ");
                quantidade = in.nextInt();

                if(estoque.cadastraProduto(produto, quantidade)) {
                    System.out.println("\nProduto cadastrado com sucesso no estoque!");
                }
                else {
                    System.out.println("\nHouve um problema no cadastro no estoque.");
                }
            }
            else {
                estoque.cadastraProduto(produto, 0);
            }

            System.out.print("\nDeseja cadastrar outro produto? [S / N] : ");
            escolha = in.next().toUpperCase();

            if (escolha.equals("N")) {
                continuar = false;
            }
        }
    }

    public void baixaEstoque(boolean continuar, int codigo, int quantidade, String escolha) {
        continuar = true;

        while (continuar) {
            System.out.println("\n---------- [ BAIXA ITEM EM ESTOQUE ] ----------");

            System.out.print("\nInforme o código do item: ");
            codigo = in.nextInt();

            System.out.print("\nInforme a quantidade de itens a serem retirados: ");
            quantidade = in.nextInt();

            if(estoque.baixaEstoque(codigo, quantidade)) {
                System.out.println("\nBaixa no estoque realizada com sucesso!");
            }
            else {
                System.out.println("\nHouve um erro ao realizar a baixa no estoque.");
            }

            System.out.print("\nDeseja dar baixa em outro produto? [S / N] : ");
            escolha = in.next().toUpperCase();

            if (escolha.equals("N")) {
                continuar = false;
            }
        }
    }

    public void reporEstoque(boolean continuar, int codigo, int quantidade, String escolha) {
        continuar = true;

        while (continuar) {
            System.out.println("\n---------- [ REPOR ITEM EM ESTOQUE ] ----------");

            System.out.print("\nInforme o código do item: ");
            codigo = in.nextInt();

            System.out.print("\nInforme a quantidade de itens a serem repostos: ");
            quantidade = in.nextInt();

            if(estoque.reposicaoEstoque(codigo, quantidade)) {
                System.out.println("\nReposição no estoque realizada com sucesso!");
            }
            else {
                System.out.println("\nHouve um erro ao realizar a reposição no estoque.");
            }

            System.out.print("\nDeseja repor outro produto? [S / N] : ");
            escolha = in.next().toUpperCase();

            if (escolha.equals("N")) {
                continuar = false;
            }
        }
    }

    public void quantidadeEstoque(int codigo) {
        System.out.println("\n---------- [ QUANTIDADE EM ESTOQUE ] ----------");

        System.out.print("\nInforme o código do item: ");
        codigo = in.nextInt();

        System.out.println("\nQuantidade em estoque disponível: " + estoque.getQuantidadeDisponivel(codigo));          
    }

    public void ultimasVendas(int quantidade) {
        System.out.println("\n---------- [ ÚLTIMAS VENDAS ] ----------");

        System.out.print("\nInforme a quantidade de vendas que deseja ver: ");
        quantidade = in.nextInt();

        System.out.println(historico.getUltimasVendas(quantidade));
    }

    public void vendaEspecifica(int codigo) {
        System.out.println("\n---------- [ VENDA ESPECÍFICA ] ----------");
                    
        System.out.print("\nInforme o número da venda que deseja ver: ");
        codigo = in.nextInt();

        System.out.print(historico.getVenda(codigo));
    }
}