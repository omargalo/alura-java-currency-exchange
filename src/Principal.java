import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        // Implementar menú de interacción aquí
        boolean exit = false;
        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Salir");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Introduce el código de la moneda original (ej. USD):");
                    String from = scanner.next().toUpperCase();
                    System.out.println("Introduce el código de la moneda deseada (ej. MXN):");
                    String to = scanner.next().toUpperCase();
                    System.out.println("Introduce la cantidad a convertir (0 para solo tasa de cambio):");
                    double amount = scanner.nextDouble();

                    double result = converter.convertCurrency(from, to, amount);
                    if (amount > 0) {
                        System.out.println(amount + " " + from + " = " + result + " " + to);
                    } else {
                        System.out.println("1 " + from + " = " + result + " " + to);
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close(); // Importante cerrar el scanner para liberar recursos
    }
}
