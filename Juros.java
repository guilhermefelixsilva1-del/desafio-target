import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

// Exercicio 3 - Calcula os juros de uma conta atrasada (2,5% ao dia)
public class Juros {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Valor da conta: ");
        double valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));

        System.out.print("Data de vencimento (dd/mm/aaaa): ");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate vencimento = LocalDate.parse(scanner.nextLine().trim(), formato);

        LocalDate hoje = LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(vencimento, hoje);

        double juros = 0;
        if (diasAtraso > 0) {
            juros = valor * 0.025 * diasAtraso;
        } else {
            diasAtraso = 0;
        }

        System.out.println("Dias de atraso: " + diasAtraso);
        System.out.printf("Juros: R$ %.2f%n", juros);
        System.out.printf("Valor total: R$ %.2f%n", valor + juros);

        scanner.close();
    }
}
