import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Output {
    public static void main(String[] args) {
        // Зчитуємо слова з обох файлів
        List<String> GerWordsList = readWordsFromFile("Gwords.txt");
        List<String> EngWordsList = readWordsFromFile("Ewords.txt");

        // Перевіряємо, чи файли зчиталися коректно і мають однакову кількість слів
        if (GerWordsList.isEmpty() || EngWordsList.isEmpty() || GerWordsList.size() != EngWordsList.size()) {
            System.out.println("Перевірка файлів не пройдена.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Integer lastWordIndex = null;

        System.out.println("Натисніть Enter, щоб вивести випадкове слово...");

        while (true) {
            scanner.nextLine(); // Чекаємо, поки користувач натисне Enter

            if (lastWordIndex == null) {
                // Вибираємо випадковий індекс зі списку для німецького слова
                lastWordIndex = random.nextInt(GerWordsList.size());
                String gerWord = GerWordsList.get(lastWordIndex);

                // Виводимо німецьке слово
                System.out.println("Німецьке слово: " + gerWord);
            } else {
                // Виводимо переклад англійською для попереднього слова
                String engWord = EngWordsList.get(lastWordIndex);
                System.out.println("Переклад: " + engWord);

                // Після виведення перекладу, видаляємо використані слова
                GerWordsList.remove((int) lastWordIndex);
                EngWordsList.remove((int) lastWordIndex);

                // Якщо всі слова вичерпано, завершуємо програму
                if (GerWordsList.isEmpty()) {
                    System.out.println("Усі слова виведені!");
                    break;
                }

                // Скидаємо індекс для нового випадкового слова
                lastWordIndex = null;
            }
        }

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
