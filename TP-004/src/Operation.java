import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {

    private final LocalDateTime dateTime;
    private final String type;
    private final float amount;

    public Operation(String type, float amount) {
        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public float getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-18s %10.2f", dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                type, amount);
    }
}
