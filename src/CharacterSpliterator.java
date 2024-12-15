import java.util.Spliterator;
import java.util.function.Consumer;

public class CharacterSpliterator implements Spliterator<Character> {
    private final String line;
    private int currentIndex;

    public CharacterSpliterator(String line) {
        this.line = line;
        this.currentIndex = 0;
    }

    @Override
    //передаёт символ строки в Consumer<? super Character> action и продвигается на один символ вперёд
    public boolean tryAdvance(Consumer<? super Character> action) {
        if (currentIndex < line.length()) {
            action.accept(line.charAt(currentIndex));
            currentIndex++;
            return true;
        }
        return false;
    }

    @Override
    //разделение строки на подстроки для параллельной обработки
    public Spliterator<Character> trySplit() {
        int numberOfRemainingCharacters = line.length() - currentIndex; //количество оставшихся символов
        if (numberOfRemainingCharacters < 2) {
            return null;
        }

        int splitIndex = currentIndex + numberOfRemainingCharacters / 2;
        String splitString = line.substring(currentIndex, splitIndex); //делим строку на две
        currentIndex = splitIndex;

        return new CharacterSpliterator(splitString);
    }


    @Override
    //количество оставшихся символов
    public long estimateSize() {
        return line.length() - currentIndex;
    }

    @Override
    //характеристики, соответствующие Spliterator
    public int characteristics() {
        return ORDERED | SIZED | SUBSIZED | NONNULL | IMMUTABLE;
    }
}