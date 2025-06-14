import java.io.IOException;

public class BruteForce extends Cipher
{
    public static void decryptBruteForce() throws IOException {
        FileManager fileManager = new FileManager();
        for (int i = 0; i < alphabetCharArray.length-1; i++) {
            String inputText = fileManager.readFile("encrypted.txt");
            System.out.println("BRUTE FORCE! Cipher key: " + i + " result: " + decrypt(inputText, i));
        }
    }
}