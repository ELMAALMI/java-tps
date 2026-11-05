import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        String[] options = {
                "List All Account",
                "Search for Account",
                "Create Account",
                "Delete Account",
                "Deposit Amount",
                "Withdraw amount",
                "Transfert",
                "Receipt",
                "Exit"
        };
        Menu menu = new Menu("Account Management", options, scanner);

        int choice;
        do {
            choice = menu.choice();
            try {
                switch (choice) {
                    case 1:
                        listAll();
                        break;
                    case 2:
                        System.out.println(bank.search(readInt("Numero du compte : ")));
                        break;
                    case 3:
                        createAccount();
                        break;
                    case 4:
                        bank.delete(bank.search(readInt("Numero du compte a supprimer : ")));
                        System.out.println("Compte supprime.");
                        break;
                    case 5:
                        deposit();
                        break;
                    case 6:
                        withdraw();
                        break;
                    case 7:
                        transfert();
                        break;
                    case 8:
                        bank.receipt(bank.search(readInt("Numero du compte : ")));
                        break;
                    case 9:
                        System.out.println("Au revoir.");
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (choice != 9);
    }

    private static void listAll() {
        List<BankAccount> accounts = bank.list();
        if (accounts.isEmpty()) {
            System.out.println("Aucun compte.");
            return;
        }
        System.out.println("----------------- Liste des comptes ----------------");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    private static void createAccount() {
        int type = readInt("Type de compte (1 = Saving, 2 = Checking) : ");
        float initial = readFloat("Montant initial : ");
        BankAccount account;
        if (type == 1) {
            float rate = readFloat("Taux d'interet (ex: 0.03) : ");
            account = new SavingAccount(initial, rate);
        } else if (type == 2) {
            account = new CheckingAccount(initial);
        } else {
            System.out.println("Type invalide.");
            return;
        }
        bank.create(account);
        System.out.println("Compte cree : " + account);
    }

    private static void deposit() {
        BankAccount a = bank.search(readInt("Numero du compte : "));
        bank.deposite(a, readFloat("Montant : "));
        System.out.println("Nouveau solde : " + a.getBalance());
    }

    private static void withdraw() {
        BankAccount a = bank.search(readInt("Numero du compte : "));
        bank.withdraw(a, readFloat("Montant : "));
        System.out.println("Nouveau solde : " + a.getBalance());
    }

    private static void transfert() {
        BankAccount from = bank.search(readInt("Compte source : "));
        BankAccount to = bank.search(readInt("Compte destinataire : "));
        bank.transfert(from, to, readFloat("Montant : "));
        System.out.println("Virement effectue.");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrez un entier valide.");
            }
        }
    }

    private static float readFloat(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Float.parseFloat(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Entrez un nombre valide.");
            }
        }
    }
}
