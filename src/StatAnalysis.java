import java.io.IOException;
import java.util.*;

public class StatAnalysis
{
    private static final Map<Character, Double> ENGLISH_FREQ = Map.ofEntries(
            Map.entry('a', 8.167), Map.entry('b', 1.492), Map.entry('c', 2.782),
            Map.entry('d', 4.253), Map.entry('e', 12.702), Map.entry('f', 2.228),
            Map.entry('g', 2.015), Map.entry('h', 6.094), Map.entry('i', 6.966),
            Map.entry('j', 0.153), Map.entry('k', 0.772), Map.entry('l', 4.025),
            Map.entry('m', 2.406), Map.entry('n', 6.749), Map.entry('o', 7.507),
            Map.entry('p', 1.929), Map.entry('q', 0.095), Map.entry('r', 5.987),
            Map.entry('s', 6.327), Map.entry('t', 9.056), Map.entry('u', 2.758),
            Map.entry('v', 0.978), Map.entry('w', 2.360), Map.entry('x', 0.150),
            Map.entry('y', 1.974), Map.entry('z', 0.074)
    );

    public static Map<Character, Integer> countLetters(String text)
    {
        Map<Character, Integer> countLetters = new HashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                countLetters.put(c, countLetters.getOrDefault(c, 0) + 1);
            }
        }

        return countLetters;
    }

    public static Map<Character, Integer> frequencyShiftingLetters(Map<Character, Integer> frequencyMap, int shift)
    {
        Map<Character, Integer> shifted = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            char shiftedChar = (char) ('a' + (c - 'a' + shift) % 26);
            shifted.put(c, frequencyMap.getOrDefault(shiftedChar, 0));
        }
        return shifted;
    }

    public static int findBestShift(Map<Character, Integer> realFrequency, int totalLetters) {
        int bestShift = 0;
        double lowestScore = Double.MAX_VALUE;

        for (int shift = 0; shift < 26; shift++) {
            Map<Character, Integer> shiftedFreq = frequencyShiftingLetters(realFrequency, shift);
            double score = 0;

            for (char c = 'a'; c <= 'z'; c++) {
                double actualPercentage = (shiftedFreq.getOrDefault(c, 0) * 100.0) / totalLetters;
                double englishPercentage = ENGLISH_FREQ.getOrDefault(c, 0.0);
                score += Math.pow(actualPercentage - englishPercentage, 2);
            }

            if (score < lowestScore) {
                lowestScore = score;
                bestShift = shift;
            }
        }

        return bestShift;
    }

    public static void main(String[] args) throws IOException
    {
        decryptWithStatAnalysis();
    }

    public static void decryptWithStatAnalysis() throws IOException {
        FileManager fileManager = new FileManager();
        String encryptedText = fileManager.readFile("encrypted.txt");

        Map<Character, Integer> frequency = countLetters(encryptedText);
        int totalLetters = encryptedText.replaceAll("[^a-zA-Z]", "").length();

        int bestShift = findBestShift(frequency, totalLetters);
        System.out.println("MOST LIKELY CEASAR KEY, ACCORDING TO STATISTICAL ANALYSIS: " + bestShift);

        String decrypted = Cipher.decrypt(encryptedText, bestShift);
        fileManager.writeToFile("stat_output.txt", decrypted);
        System.out.println("DECRYPTED OUTPUT WRITTEN TO stat_output.txt");
    }
}