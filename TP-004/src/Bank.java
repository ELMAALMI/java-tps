import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public BankAccount search(int num) {
        for (BankAccount account : accounts) {
            if (account.getAccountId() == num) {
                return account;
            }
        }
        throw new IllegalArgumentException("Erreur : le compte n" + num + " n'existe pas.");
    }

    public void create(BankAccount account) {
        for (BankAccount a : accounts) {
            if (a.getAccountId() == account.getAccountId()) {
                throw new IllegalArgumentException("Erreur : le compte n" + account.getAccountId() + " existe deja.");
            }
        }
        accounts.add(account);
    }

    public List<BankAccount> list() {
        List<BankAccount> sorted = new ArrayList<>(accounts);
        sorted.sort(Comparator.comparingDouble(BankAccount::getBalance));
        return sorted;
    }

    public void deposite(BankAccount account, float amount) {
        account.deposit(amount);
    }

    public void withdraw(BankAccount account, float amount) {
        account.withdraw(amount);
    }

    public void transfert(BankAccount accountfrom, BankAccount accountto, float amount) {
        accountfrom.transfer(accountto, amount);
    }

    public void delete(BankAccount account) {
        if (!accounts.remove(account)) {
            throw new IllegalArgumentException("Erreur : le compte n" + account.getAccountId() + " n'existe pas.");
        }
    }

    public void receipt(BankAccount account) {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Account : " + account.getAccountId() + "        date : " + today);
        System.out.printf("%-20s %-18s %10s%n", "date/time", "operation", "amount");
        System.out.println("-------------------- ------------------ ----------");
        for (Operation op : account.getOperations()) {
            System.out.println(op);
        }
        System.out.printf("%nBalance : %.2f%n", account.getBalance());
    }
}
