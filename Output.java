import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Output {
    public static void main(String[] args) {
        List<String> wordsList = readWordsFromFile("words.txt");

        if (wordsList.isEmpty()) {
            System.out.println("Файл порожній або не вдалося його прочитати.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Натисніть Enter, щоб вивести випадкове слово...");

        // Поки список не порожній, виводимо рандомні слова
        while (!wordsList.isEmpty()) {
            scanner.nextLine(); // Чекаємо, поки користувач натисне Enter

            // Вибираємо випадковий індекс зі списку
            int randomIndex = random.nextInt(wordsList.size());

            // Отримуємо слово за випадковим індексом
            String randomWord = wordsList.get(randomIndex);

            // Виводимо слово
            System.out.println("Випадкове слово: " + randomWord);

            // Видаляємо використане слово зі списку, щоб воно не повторювалось
            wordsList.remove(randomIndex);
        }

        System.out.println("Усі слова виведені!");
        scanner.close();
    }

    // Метод для читання слів із файлу
    public static List<String> readWordsFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            // Читаємо файл пострічно і додаємо слова до списку
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine().trim();
                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + fileName);
        }
        return words;
    }
}
