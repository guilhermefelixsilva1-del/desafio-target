import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Exercicio 1 - Calcula a comissao de cada vendedor
public class Comissao {

    public static double calcularComissao(double valor) {
        if (valor < 100) {
            return 0;
        } else if (valor < 500) {
            return valor * 0.01;
        } else {
            return valor * 0.05;
        }
    }

    public static void main(String[] args) throws Exception {
        String json = Files.readString(Path.of("vendas.json"));

        // pega o vendedor e o valor de cada venda do json
        Pattern padrao = Pattern.compile("\"vendedor\":\\s*\"(.*?)\",\\s*\"valor\":\\s*([\\d.]+)");
        Matcher matcher = padrao.matcher(json);

        // LinkedHashMap para manter a ordem dos vendedores
        Map<String, Double> comissoes = new LinkedHashMap<>();

        while (matcher.find()) {
            String vendedor = matcher.group(1);
            double valor = Double.parseDouble(matcher.group(2));

            double comissao = calcularComissao(valor);
            comissoes.put(vendedor, comissoes.getOrDefault(vendedor, 0.0) + comissao);
        }

        System.out.println("Comissao por vendedor:");
        System.out.println("----------------------");
        for (String vendedor : comissoes.keySet()) {
            System.out.printf("%s: R$ %.2f%n", vendedor, comissoes.get(vendedor));
        }
    }
}
