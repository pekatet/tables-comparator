import java.util.Set;

public class Main {

    public static void main(String[] args) {
        TablesComparator tablesComparator = new TablesComparator();
        LetterFormer letterFormer = new LetterFormer();

        //Вариант решения 1
        //С использованием отдельных простых методов для большей понятности и гибкости
        Set<String> keysChanged = tablesComparator.keysWithChangedValues();
        Set<String> keysPrev = tablesComparator.keysInPrevOnly();
        Set<String> keysNew = tablesComparator.keysInNewOnly();
        letterFormer.formLetter1(keysChanged, keysPrev, keysNew);

        //Вариант решения 2
        //С использованием алгоритма находящего все необходимые наборы ключей за один проход
        keysChanged.clear();
        keysPrev.clear();
        keysNew.clear();
        tablesComparator.allKeys(keysPrev, keysNew, keysChanged);
        letterFormer.formLetter2(keysPrev, keysNew, keysChanged);
    }
}
