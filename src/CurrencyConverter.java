import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter {
    private static final String API_KEY = "584238861b12151d4c59f2f2";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";
    private final Gson gson = new Gson();
    private final List<ConversionRecord> history = new ArrayList<>();

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
                double result = amount > 0 ? currencyResponse.conversion_result() : currencyResponse.conversion_rate();
                // Add to history
                history.add(new ConversionRecord(fromCurrency, toCurrency, amount, result));
                return result;
            } else {
                System.err.println("Error in API: " + currencyResponse.result());
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            return 0;
        }
    }

    public void printHistory() {
        history.forEach(System.out::println);
    }
}
