import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class CurrencyConverter {
    private static final String API_KEY = "584238861b12151d4c59f2f2";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    private final Gson gson = new Gson();

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        String url = BASE_URL + fromCurrency + "/" + toCurrency + (amount > 0 ? "/" + amount : "");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CurrencyResponse currencyResponse = gson.fromJson(response.body(), CurrencyResponse.class);

            if ("success".equals(currencyResponse.result())) {
                if (amount > 0) {
                    // Si se especificó un monto, devolver el resultado de la conversión
                    return currencyResponse.conversion_result() != null ? currencyResponse.conversion_result() : 0;
                } else {
                    // Si no se especificó monto, devolver solo la tasa de cambio
                    return currencyResponse.conversion_rate();
                }
            } else {
                // Manejar errores según el tipo
                System.err.println("Error en la API: " + currencyResponse.result());
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
            return 0;
        }
    }
}
