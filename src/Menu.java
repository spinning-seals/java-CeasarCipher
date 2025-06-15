import java.io.*;
import java.util.*;

public class Menu extends Cipher {
    static String scannedInput = "";
    static String encryptedInput = "";
    static String decryptedInput = "";
    static boolean checkMainMenuInput = false;
    static boolean checkSubMenuInput = false;

    public static void start() throws IOException {
        System.out.println("~*~ WELCOME TO THE CEASAR CIPHER! ~*~");
        Scanner scanInput = new Scanner(System.in);
        checkMainMenuInput = true;
        FileManager fileManager = new FileManager();
        while (checkMainMenuInput) {
            System.out.println("<-~ Current console cipher key: " + getCipherKey() + " ~->");
            System.out.println(">-| Choose an option! |-<:");
            System.out.println("1: Encrypt console input");
            System.out.println("2: Decrypt console input");
            System.out.println("3: Encrypt input file");
            System.out.println("4: Decrypt encrypted file");
            System.out.println("5. Brute force: Decrypt encrypted file");
            System.out.println("6: Statistical analysis: Decrypt encrypted file");
            System.out.println("0: Exit");

            System.out.print("YOUR CHOICE: ");
            String menuInput = scanInput.nextLine();

            if (!menuInput.matches("\\d")) {
                System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER BETWEEN 0 AND 6.");
                continue;
            }

            int menuChoice = Integer.parseInt(menuInput);

            if (menuChoice < 0 || menuChoice > 6) {
                System.out.println("PLEASE CHOOSE A NUMBER BETWEEN 0 AND 6.");
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
                        } else if (!scannedInput.matches("[a-zA-Z .,!?\\-:'\"\"]+")) {
                            System.out.println("INVALID INPUT! ONLY LETTERS, SPACES AND SOME SYMBOLS (\"'.', ',', '!', '?', '-', ':', '\\'', '\\\"', ' '\") ALLOWED.");
                        } else {
                            checkSubMenuInput = false;
                        }
                    }
                    System.out.println("YOUR INPUT IS: " + scannedInput);
                    checkSubMenuInput = true;
                    promptCipherKey(scanInput);
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
                    decryptedInput = decrypt(encryptedInput.toUpperCase(), getCipherKey());
                    System.out.println("IT'S DECRYPTIN' TIME: " + decryptedInput);
                    break;
                }
                case 3: {
                    fileManager.createFileIfMissing("input.txt");
                    String inputText = fileManager.readFile("input.txt");
                    if(inputText.isEmpty())
                    {
                        System.out.println("ERROR: input.txt IS EMPTY! PLEASE ADD SOMETHING TO input.txt!");
                        break;
                    }
                    promptCipherKey(scanInput);
                    String encryptedText = encrypt(inputText, getCipherKey());
                    fileManager.writeToFile("encrypted.txt", encryptedText);
                    System.out.println("FIND THE ENCRYPTED OUTPUT IN encrypted.txt");
                    break;
                }
                case 4: {
                    if (fileManager.fileExists("encrypted.txt"))
                    {
                        String inputText = fileManager.readFile("encrypted.txt");
                        if (!inputText.isEmpty()) {
                            String decryptedText = decrypt(inputText, getCipherKey());
                            fileManager.writeToFile("decrypted.txt", decryptedText);
                            System.out.println("FIND THE DECRYPTED OUTPUT IN decrypted.txt");
                        } else
                        {
                            System.out.println("ERROR: encrypted.txt IS EMPTY. ENCRYPT SOMETHING FIRST!");
                        }
                    } else
                    {
                        System.out.println("ERROR: encrypted.txt FILE IS MISSING. ENCRYPT SOMETHING FIRST!");
                    }
                    break;
                }
                case 5:
                {
                    BruteForce.decryptBruteForce();
                    break;
                }
                case 6:
                    try {
                        StatAnalysis.decryptWithStatAnalysis();
                    } catch (IOException e) {
                        System.out.println("ERROR DURING STAT ANALYSIS: " + e.getMessage());
                    }
                    break;
                case 0: {
                    System.out.println("GOODBYE!");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("WRONG INPUT, TRY AGAIN!");
                }
            }
        }
    }

    private static void promptCipherKey(Scanner scanInput) {
        boolean validKey = false;
        while (!validKey) {
            System.out.println("ENTER CIPHER KEY (WHOLE NUMBERS ONLY)");
            if (!scanInput.hasNextInt()) {
                System.out.println("INVALID INPUT! ONLY WHOLE NUMBERS ALLOWED.");
                scanInput.nextLine();
            } else {
                int scannedInt = scanInput.nextInt();
                scanInput.nextLine();
                if (scannedInt < 0 || scannedInt > 25) {
                    System.out.println("INVALID INPUT! PLEASE ENTER A NUMBER BETWEEN 0 AND 25.");
                } else {
                    setCipherKey(scannedInt);
                    System.out.println("CIPHER KEY: " + getCipherKey());
                    validKey = true;
                }
            }
        }
    }
}