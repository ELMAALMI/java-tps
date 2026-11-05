import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccount {
    private static int nextId = 1; // auto_increment

    private final int accountId;
    protected float balance;
    protected final List<Operation> operations = new ArrayList<>();

    public BankAccount() {
        this(0.0f);
    }

    public BankAccount(float initialAmount) {
        if (initialAmount < 0) {
            throw new IllegalArgumentException("Le solde initial ne peut pas etre negatif.");
        }
        this.accountId = nextId++;
        this.balance = initialAmount;
        if (initialAmount > 0) {
            addOperation(new Operation("OPENING", initialAmount));
        }
    }

    public int getAccountId() {
        return accountId;
    }

    public float getBalance() {
        return balance;
    }

    public List<Operation> getOperations() {
        return Collections.unmodifiableList(operations);
    }

    protected void addOperation(Operation op) {
        operations.add(op);
    }

    public void deposit(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du depot doit etre positif.");
        }
        balance += amount;
        addOperation(new Operation("DEPOSIT", amount));
    }

    public void withdraw(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit etre positif.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Solde insuffisant (solde = " + balance + ").");
        }
        balance -= amount;
        addOperation(new Operation("WITHDRAW", -amount));
    }

    public void transfer(BankAccount other, float amount) {
        if (other == null) {
            throw new IllegalArgumentException("Compte destinataire invalide.");
        }
        if (other == this) {
            throw new IllegalArgumentException("Le compte source et le compte destinataire sont identiques.");
        }
        withdraw(amount);
        other.deposit(amount);
    }

    @Override
    public String toString() {
        return String.format("%s [id=%d, balance=%.2f]", getClass().getSimpleName(), accountId, balance);
    }
}
