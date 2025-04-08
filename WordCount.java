import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        Map<String, Integer> freq = new HashMap<>();
        Scanner doc = new Scanner(System.in).useDelimiter("[^a-zA-Z]+");
        while (doc.hasNext()) {
            String word = doc.next().toLowerCase();
            Integer count = freq.get(word);
            if (count == null) {
                count = 0;
            }
            freq.put(word, count + 1);
        }

        int maxCount = 0;
        String maxWord = "noword";
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxWord = entry.getKey();
            }
        }

        System.out.println("The most frequent word is : " + maxWord + " with frequency : " + maxCount);
    }
}
