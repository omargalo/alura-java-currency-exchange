import java.time.LocalDateTime;

public class ConversionRecord {
    private final String fromCurrency;
    private final String toCurrency;
    private final double originalAmount;
    private final double convertedAmount;
    private final LocalDateTime timestamp;

    public ConversionRecord(String fromCurrency, String toCurrency, double originalAmount, double convertedAmount) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.originalAmount = originalAmount;
        this.convertedAmount = convertedAmount;
        this.timestamp = LocalDateTime.now();
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Conversion from " + fromCurrency + " to " + toCurrency +
                ": " + originalAmount + " converted to " + convertedAmount +
                " on " + timestamp;
    }
}
