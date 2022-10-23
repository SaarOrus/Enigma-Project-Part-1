package userInterface.menu;

public class Menu {
    public static void showMenu() {
        StringBuilder printString = new StringBuilder();

        printString.append("\nPlease choose one of the options:\n");
        printString.append("1. Reading the system information file\n");
        printString.append("2. Displaying the machine specifications\n");
        printString.append("3. Selecting an initial code configuration (manually)\n");
        printString.append("4. Selection of initial code configuration (automatically)\n");
        printString.append("5. Input processing\n");
        printString.append("6. Reset machine's code\n");
        printString.append("7. History and statistics\n");
        printString.append("8. Exiting the system\n");

        System.out.println(printString);
    }
}
