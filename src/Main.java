import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        String input = "Допустим, что здесь написан какой-то текст...";

        Stream<Character> stream1 = StreamSupport.stream(new CharacterSpliterator(input), false);
        stream1.filter(Character::isLetter) //оставляем только буквы
                .forEach(System.out::print);

        System.out.println();

        Stream<Character> stream2 = StreamSupport.stream(new CharacterSpliterator(input), false);
        stream2.map(Character::toUpperCase) //приводим к верхнему регистру
                .forEach(System.out::print);

        System.out.println();

        Stream<Character> stream3 = StreamSupport.stream(new CharacterSpliterator(input), false);
        stream3.map(Character::toUpperCase)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting())) //группируем в map и считаем каждый символ
                .forEach((character, count) -> System.out.println(character + ": " + count));
    }
}