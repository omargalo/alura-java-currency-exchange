import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {
    private static final String API_KEY = "584238861b12151d4c59f2f2";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        String url = BASE_URL + fromCurrency + "/" + toCurrency;
        if (amount > 0) {
            url += "/" + amount; // Añadir el monto a la URL si es mayor que 0
        }
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            if (jsonResponse.get("result").getAsString().equals("success")) {
                if (amount > 0) {
                    // Si se especificó un monto, devolver el resultado de la conversión
                    return jsonResponse.get("conversion_result").getAsDouble();
                } else {
                    // Si no se especificó monto, devolver solo la tasa de cambio
                    return jsonResponse.get("conversion_rate").getAsDouble();
                }
            } else {
                // Manejar errores según el tipo
                String errorType = jsonResponse.get("error-type").getAsString();
                System.err.println("Error en la API: " + errorType);
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al procesar la solicitud: " + e.getMessage());
            return 0;
        }
    }
}
