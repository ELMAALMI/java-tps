import java.util.Scanner;

public class Menu {
    private final String title;
    private final String[] options;
    private final Scanner scanner;

    public Menu(String title, String[] options, Scanner scanner) {
        this.title = title;
        this.options = options;
        this.scanner = scanner;
    }

    public void display() {
        System.out.println();
        System.out.println("-------------------------- " + title + " --------------------------");
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + "- " + options[i]);
        }
        System.out.print("Tapez votre choix : ");
    }

    public int choice() {
        display();
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                int c = Integer.parseInt(line);
                if (c >= 1 && c <= options.length) {
                    return c;
                }
            } catch (NumberFormatException ignored) {
                // on redemande
            }
            System.out.print("Choix invalide, tapez un nombre entre 1 et " + options.length + " : ");
        }
    }
}
