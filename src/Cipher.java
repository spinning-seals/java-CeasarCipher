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
            'U', 'V', 'W', 'X', 'Y', 'Z',
            '.', ',', '!', '?', '-', ':', '\'', '\"', ' '
    };


    public static String encrypt(String myInput, int cipherKey)
    {
        char[] encryptedCharArray = myInput.toCharArray();
        for (int i = 0; i < encryptedCharArray.length; i++)
        {

            char currentChar = encryptedCharArray[i];
            boolean found = false;
            for(int j = 0; j < alphabetCharArray.length; j++) {
                if (currentChar == alphabetCharArray[j]) {
                    int shiftedIndex = (j + cipherKey) % alphabetCharArray.length;
                    currentChar = alphabetCharArray[shiftedIndex];
                    encryptedCharArray[i]= currentChar;
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                encryptedCharArray[i] = currentChar;
            }
        }
        return new String(encryptedCharArray);
    }

    public static String decrypt(String encryptedInput, int cipherKey)
    {
        char[] toBeDecryptedCharArray = encryptedInput.toCharArray();

        for (int i = 0; i < toBeDecryptedCharArray.length; i++)
        {

            char currentChar = toBeDecryptedCharArray[i];
            boolean found = false;
            for(int j = 0; j < alphabetCharArray.length; j++) {
                if (currentChar == alphabetCharArray[j]) {
                    int shiftedIndexCalculation = (j - cipherKey + alphabetCharArray.length);
                    int shiftedIndex = shiftedIndexCalculation % alphabetCharArray.length;
                    currentChar = alphabetCharArray[shiftedIndex];
                    toBeDecryptedCharArray[i]= currentChar;
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                toBeDecryptedCharArray[i] = currentChar;
            }
        }
        return new String(toBeDecryptedCharArray);
    }
}