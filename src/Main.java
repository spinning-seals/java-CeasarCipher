import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("WELCOME TO THE CEASAR CIPHER!");
        Scanner scanInput = new Scanner(System.in);
        String scannedInput = "";
        boolean checkInput = true;

        while (checkInput) {
            System.out.println("PLEASE ENTER YOUR INPUT (LETTERS AND SPACES ONLY):");
            scannedInput = scanInput.nextLine();

            if (scannedInput.isEmpty()) {
                System.out.println("EMPTY INPUT, PLEASE TRY AGAIN.");
            } else if (!scannedInput.matches("[a-zA-Z ]+")) {
                System.out.println("INVALID INPUT! ONLY LETTERS AND SPACES ALLOWED.");
            } else {
                checkInput = false;
            }
        }

        System.out.println("YOUR INPUT IS: " + scannedInput);
        String encryptedInput = encrypt(scannedInput.toUpperCase());
        System.out.println("IT'S ENCRYPTIN' TIME: " + encryptedInput);
        String decryptedInput = decrypt(encryptedInput);
        System.out.println("IT'S DECRYPTIN' TIME: " + decryptedInput);

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        String line;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();

    }

    public static String encrypt(String myInput)
    {
        char[] encryptedCharArray = myInput.toCharArray();
        char[] alphabetCharArray = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        for (int i = 0; i < encryptedCharArray.length; i++)
        {

                char currentChar = encryptedCharArray[i];
                for(int j = 0; j < alphabetCharArray.length; j++) {
                    if (currentChar == alphabetCharArray[j]) {
                        int shiftedIndex = (j + 3) % 26;
                        currentChar = alphabetCharArray[shiftedIndex];
                        encryptedCharArray[i]= currentChar;
                        break;
                    }
                }
            }
        return new String(encryptedCharArray);
        }

    public static String decrypt(String encryptedInput)
    {
        char[] toBeDecryptedCharArray = encryptedInput.toCharArray();
        char[] alphabetCharArray = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        for (int i = 0; i < toBeDecryptedCharArray.length; i++)
        {

            char currentChar = toBeDecryptedCharArray[i];
            for(int j = 0; j < alphabetCharArray.length; j++) {
                if (currentChar == alphabetCharArray[j]) {
                    int shiftedIndexCalculation = (j - 3 + 26);
                    int shiftedIndex = shiftedIndexCalculation % 26;
                    currentChar = alphabetCharArray[shiftedIndex];
                    toBeDecryptedCharArray[i]= currentChar;
                    break;
                }
            }
        }
        return new String(toBeDecryptedCharArray);
    }
}