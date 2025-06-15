import java.io.IOException;

public class BruteForce extends Cipher
{
    static String[] commonWords =
            {
                    " the ", " and ", " is ", " to ", " of ", " in ", " that ", " it ",
                    " on ", " for ", " you ", " with ", " was ", " are ", " this ",
                    " be ", " have ", " from ", " by ", " not ", " or ", " at ", " as ",
                    " but ", " they ", " we ", " his ", " her ", " my ", " me ", " your ",
                    " so ", " if ", " do ", " can ", " will ", " just ", " about ", " like ",
                    " up ", " out ", " what ", " when ", " all ", " would ", " there ", " their ",
                    "i ", "a ", "an ", "he ", "she ", "we ", "us ", "our "
            };

    public static void decryptBruteForce() throws IOException
    {
        int bestScore = -1;
        String bestResult = "";
        int bestKey = -1;
        FileManager fileManager = new FileManager();
        String inputText = fileManager.readFile("encrypted.txt");
        for (int i = 0; i < alphabetCharArray.length-1; i++)
        {
            String decryptedWord = decrypt(inputText, i).toLowerCase();
            int score = 0;
            String[] themWords = decryptedWord.split("\\W+");  // 🔍 tekst opdelen
            for (String word : themWords) {
                for (String common : commonWords) {
                    if (word.equalsIgnoreCase(common.trim())) {
                        score++;
                    }
                }
            }
            if (score > bestScore) {
                bestScore = score;
                bestResult = decryptedWord;
                bestKey = i;
            }
        }
        System.out.println("BEST MATCH FOUND WITH KEY: " + bestKey);
        System.out.println("BEST RESULT: " + bestResult);
        fileManager.writeToFile("brute_output.txt", bestResult);
    }
}