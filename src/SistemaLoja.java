import java.util.ArrayList;
import java.util.Scanner;

public class SistemaLoja {

    static class Produto {
        int id;
        String nome;
        double preco;
        int estoque;

        Produto(int id, String nome, double preco, int estoque) {
            this.id = id;
            this.nome = nome;
            this.preco = preco;
            this.estoque = estoque;
        }
    }

    static ArrayList<Produto> listaProdutos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int contadorId = 1;
    static double totalVendas = 0;

    public void iniciar() {
        System.out.println("Sistema da loja iniciado!");
    }

 public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE LOJA =====");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Realizar Venda");
            System.out.println("4 - Relatório de Vendas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    realizarVenda();
                    break;
                case 4:
                    relatorio();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    static void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade em estoque: ");
        int estoque = scanner.nextInt();

        Produto produto = new Produto(contadorId++, nome, preco, estoque);
        listaProdutos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    static void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto p : listaProdutos) {
            System.out.println("ID: " + p.id +
                    " | Nome: " + p.nome +
                    " | Preço: R$" + p.preco +
                    " | Estoque: " + p.estoque);
        }
    }

    static void realizarVenda() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();

        Produto produtoEncontrado = null;

        for (Produto p : listaProdutos) {
            if (p.id == id) {
                produtoEncontrado = p;
                break;
            }
        }

        if (produtoEncontrado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        if (quantidade > produtoEncontrado.estoque) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        double valorVenda = quantidade * produtoEncontrado.preco;
        produtoEncontrado.estoque -= quantidade;
        totalVendas += valorVenda;

        System.out.println("Venda realizada! Total: R$" + valorVenda);
    }

    static void relatorio() {
        System.out.println("===== RELATÓRIO =====");
        System.out.println("Total vendido: R$" + totalVendas);

        for (Produto p : listaProdutos) {
            if (p.estoque < 5) {
                System.out.println("⚠ Estoque baixo: " + p.nome);
            }
        }
    }
}
