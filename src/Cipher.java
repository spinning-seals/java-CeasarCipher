import java.io.*;

public class Cipher
{
    private static int cipherKey = 3;

    public static int getCipherKey()
    {
        return cipherKey;
    }

    public static void setCipherKey(int cipherKey)
    {
        Cipher.cipherKey = cipherKey;
    }

    static char[] alphabetCharArray = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String encrypt(String myInput, int cipherKey)
    {
        char[] encryptedCharArray = myInput.toCharArray();
        for (int i = 0; i < encryptedCharArray.length; i++)
        {

            char currentChar = encryptedCharArray[i];
            for(int j = 0; j < alphabetCharArray.length; j++) {
                if (currentChar == alphabetCharArray[j]) {
                    int shiftedIndex = (j + cipherKey) % alphabetCharArray.length;
                    currentChar = alphabetCharArray[shiftedIndex];
                    encryptedCharArray[i]= currentChar;
                    break;
                }
            }
        }
        return new String(encryptedCharArray);
    }

    public static void encryptFile() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"));

        String line;
        while ((line = reader.readLine()) != null)
        {
            String encryptedLine = encrypt(line.toUpperCase(), getCipherKey());
            writer.write(encryptedLine);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    public static String decrypt(String encryptedInput, int cipherKey)
    {
        char[] toBeDecryptedCharArray = encryptedInput.toCharArray();

        for (int i = 0; i < toBeDecryptedCharArray.length; i++)
        {

            char currentChar = toBeDecryptedCharArray[i];
            for(int j = 0; j < alphabetCharArray.length; j++) {
                if (currentChar == alphabetCharArray[j]) {
                    int shiftedIndexCalculation = (j - cipherKey + alphabetCharArray.length);
                    int shiftedIndex = shiftedIndexCalculation % alphabetCharArray.length;
                    currentChar = alphabetCharArray[shiftedIndex];
                    toBeDecryptedCharArray[i]= currentChar;
                    break;
                }
            }
        }
        return new String(toBeDecryptedCharArray);
    }

    public static void decryptFile() throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader("encrypted.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted.txt"));

        String line;
        while ((line = reader.readLine()) != null)
        {
            String decryptedLine = decrypt(line.toUpperCase(), getCipherKey());
            writer.write(decryptedLine);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}
