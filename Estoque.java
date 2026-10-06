import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Exercicio 2 - Movimentacao de estoque (entrada e saida)
public class Estoque {

    static class Produto {
        int codigo;
        String descricao;
        int estoque;

        Produto(int codigo, String descricao, int estoque) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.estoque = estoque;
        }
    }

    static class Movimentacao {
        int id;
        int codigoProduto;
        String tipo;
        int quantidade;
        String descricao;

        Movimentacao(int id, int codigoProduto, String tipo, int quantidade, String descricao) {
            this.id = id;
            this.codigoProduto = codigoProduto;
            this.tipo = tipo;
            this.quantidade = quantidade;
            this.descricao = descricao;
        }
    }

    static List<Produto> produtos = new ArrayList<>();
    static List<Movimentacao> movimentacoes = new ArrayList<>();
    static int proximoId = 1;

    public static void main(String[] args) throws Exception {
        carregarProdutos();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            mostrarEstoque();
            System.out.println("\n1 - Entrada");
            System.out.println("2 - Saida");
            System.out.println("3 - Ver movimentacoes");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String opcao = scanner.nextLine().trim();

            if (opcao.equals("0")) {
                break;
            }

            if (opcao.equals("3")) {
                for (Movimentacao m : movimentacoes) {
                    System.out.println("ID " + m.id + " | Produto " + m.codigoProduto + " | " + m.tipo
                            + " de " + m.quantidade + " | " + m.descricao);
                }
                continue;
            }

            if (!opcao.equals("1") && !opcao.equals("2")) {
                System.out.println("Opcao invalida!");
                continue;
            }

            System.out.print("Codigo do produto: ");
            int codigo = Integer.parseInt(scanner.nextLine().trim());
            Produto produto = buscarProduto(codigo);
            if (produto == null) {
                System.out.println("Produto nao encontrado!");
                continue;
            }

            System.out.print("Quantidade: ");
            int quantidade = Integer.parseInt(scanner.nextLine().trim());
            if (quantidade <= 0) {
                System.out.println("A quantidade precisa ser maior que zero!");
                continue;
            }

            System.out.print("Descricao da movimentacao: ");
            String descricao = scanner.nextLine().trim();

            String tipo;
            if (opcao.equals("1")) {
                tipo = "Entrada";
                produto.estoque += quantidade;
            } else {
                tipo = "Saida";
                if (quantidade > produto.estoque) {
                    System.out.println("Estoque insuficiente!");
                    continue;
                }
                produto.estoque -= quantidade;
            }

            movimentacoes.add(new Movimentacao(proximoId, codigo, tipo, quantidade, descricao));
            System.out.println("\nMovimentacao " + proximoId + " registrada!");
            System.out.println("Estoque final de " + produto.descricao + ": " + produto.estoque);
            proximoId++;
        }

        scanner.close();
    }

    static void carregarProdutos() throws Exception {
        String json = Files.readString(Path.of("estoque.json"));

        // pega codigo, descricao e estoque de cada produto do json
        Pattern padrao = Pattern.compile(
                "\"codigoProduto\":\\s*(\\d+),\\s*\"descricaoProduto\":\\s*\"(.*?)\",\\s*\"estoque\":\\s*(\\d+)");
        Matcher matcher = padrao.matcher(json);

        while (matcher.find()) {
            int codigo = Integer.parseInt(matcher.group(1));
            String descricao = matcher.group(2);
            int estoque = Integer.parseInt(matcher.group(3));
            produtos.add(new Produto(codigo, descricao, estoque));
        }
    }

    static Produto buscarProduto(int codigo) {
        for (Produto p : produtos) {
            if (p.codigo == codigo) {
                return p;
            }
        }
        return null;
    }

    static void mostrarEstoque() {
        System.out.println("\n--- Estoque atual ---");
        for (Produto p : produtos) {
            System.out.println(p.codigo + " - " + p.descricao + ": " + p.estoque);
        }
    }
}
