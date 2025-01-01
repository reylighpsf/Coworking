package utilities;
import java.io.IOException;

public class ClearScreen {

    // Metode untuk membersihkan layar
    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                // Perintah untuk membersihkan layar di Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Escape sequence untuk terminal berbasis Unix/Linux
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error clearing screen: " + e.getMessage());
            }
        }
    }

