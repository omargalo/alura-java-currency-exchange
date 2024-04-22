import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. USD a MXN (Peso mexicano)");
            System.out.println("2. USD a ARS (Peso argentino)");
            System.out.println("3. USD a BRL (Real brasileño)");
            System.out.println("4. USD a CLP (Peso chileno)");
            System.out.println("5. USD a COP (Peso colombiano)");
            System.out.println("6. Otras monedas");
            System.out.println("7. Salir");
            int option = scanner.nextInt();

            String from = "USD";
            String to = "";
            double amount;

            switch (option) {
                case 1:
                    to = "MXN";
                    break;
                case 2:
                    to = "ARS";
                    break;
                case 3:
                    to = "BRL";
                    break;
                case 4:
                    to = "CLP";
                    break;
                case 5:
                    to = "COP";
                    break;
                case 6:
                    System.out.println("Introduce el código de la moneda original (ej. USD):");
                    from = scanner.next().toUpperCase();
                    System.out.println("Introduce el código de la moneda deseada (ej. MXN):");
                    to = scanner.next().toUpperCase();
                    break;
                case 7:
                    exit = true;
                    continue;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    continue;
            }

            if (!exit) {
                System.out.println("Introduce la cantidad a convertir (0 para solo tasa de cambio):");
                amount = scanner.nextDouble();

                double result = converter.convertCurrency(from, to, amount);
                if (amount > 0) {
                    System.out.println(amount + " " + from + " = " + result + " " + to);
                } else {
                    System.out.println("1 " + from + " = " + result + " " + to);
                }
            }
        }
        scanner.close();
    }
}
