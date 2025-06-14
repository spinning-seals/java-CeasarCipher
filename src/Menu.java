import java.io.*;
import java.util.*;

public class Menu extends Main {
    static String scannedInput = "";
    static String encryptedInput = "";
    static String decryptedInput = "";
    static boolean checkMainMenuInput = false;
    static boolean checkSubMenuInput = false;

    public static void start() throws IOException {
        System.out.println("~*~ WELCOME TO THE CEASAR CIPHER! ~*~");
        Scanner scanInput = new Scanner(System.in);
        checkMainMenuInput = true;
        while (checkMainMenuInput) {
            System.out.println("<-~ Current cipher key: "+ getCipherKey()+" ~->");
            System.out.println(">-| Choose an option! |-<:");
            System.out.println("1: Encrypt console input");
            System.out.println("2: Decrypt console input");
            System.out.println("3: Encrypt file");
            System.out.println("4: Decrypt file");
            System.out.println("0: Exit");

            try {
                int menuChoice = Integer.parseInt(scanInput.nextLine());
                if (menuChoice < 0 || menuChoice > 5) {
                    System.out.println("PLEASE CHOOSE A NUMBER BETWEEN 1 AND 5.");
                    continue;
                }

                switch (menuChoice) {
                    case 1: {
                        checkSubMenuInput = true;
                        while (checkSubMenuInput) {
                            System.out.println("PLEASE ENTER YOUR INPUT (LETTERS AND SPACES ONLY), THAT WILL BE ENCRYPTED:");
                            scannedInput = scanInput.nextLine();

                            if (scannedInput.isEmpty()) {
                                System.out.println("EMPTY INPUT, PLEASE TRY AGAIN.");
                            } else if (!scannedInput.matches("[a-zA-Z ]+")) {
                                System.out.println("INVALID INPUT! ONLY LETTERS AND SPACES ALLOWED.");
                            } else {
                                checkSubMenuInput = false;
                            }
                        }
                        System.out.println("YOUR INPUT IS: " + scannedInput);
                        checkSubMenuInput = true;

                        while (checkSubMenuInput) {
                            System.out.println("ENTER CIPHER KEY (WHOLE NUMBERS ONLY)");
                            if (!scanInput.hasNextInt()) {
                                System.out.println("INVALID INPUT! ONLY WHOLE NUMBERS ALLOWED.");
                                scanInput.nextLine();
                            } else {
                                int scannedInt = scanInput.nextInt();
                                scanInput.nextLine();
                                setCipherKey(scannedInt);
                                System.out.println("CIPHER KEY: "+getCipherKey());
                                checkSubMenuInput = false;
                            }
                        }

                        encryptedInput = encrypt(scannedInput.toUpperCase(), getCipherKey());
                        System.out.println("IT'S ENCRYPTIN' TIME: " + encryptedInput);
                        break;
                    }

                    case 2: {
                        checkSubMenuInput = true;
                        while (checkSubMenuInput) {
                            if (encryptedInput.isEmpty()) {
                                System.out.println("ENCRYPT INPUT FIRST!");
                                checkSubMenuInput = false;
                                break;
                            } else {
                                checkSubMenuInput = false;
                            }
                        }
                        System.out.println("YOUR ENCRYPTED INPUT IS: " + encryptedInput);
                        decryptedInput = decrypt(encryptedInput.toUpperCase(),getCipherKey());
                        System.out.println("IT'S DECRYPTIN' TIME: " + decryptedInput);
                        break;
                    }
                    case 3: {
                        while (checkSubMenuInput) {
                            System.out.println("ENTER CIPHER KEY (WHOLE NUMBERS ONLY)");
                            if (!scanInput.hasNextInt()) {
                                System.out.println("INVALID INPUT! ONLY WHOLE NUMBERS ALLOWED.");
                                scanInput.nextLine();
                            } else {
                                int scannedInt = scanInput.nextInt();
                                scanInput.nextLine();
                                setCipherKey(scannedInt);
                                System.out.println("CIPHER KEY: "+getCipherKey());
                                checkSubMenuInput = false;
                            }
                        }
                        encryptFile();
                        System.out.println("FIND THE ENCRYPTED OUTPUT IN encrypted.txt");
                        break;
                    }
                    case 4: {
                        System.out.println("CIPHER KEY: "+getCipherKey());
                        decryptFile();
                        System.out.println("FIND THE DECRYPTED OUTPUT IN decrypted.txt");
                        break;
                    }
                    case 0: {
                        System.out.println("GOODBYE!");
                        System.exit(0);
                        break;
                    }
                    default: {
                        System.out.println("WRONG INPUT, TRY AGAIN!");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER BETWEEN 1 AND 5.");
            }
        }
    }
}