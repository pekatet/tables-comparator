import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TablesComparator {
    HashMap<String, String> prevTable;
    HashMap<String, String> newTable;

    TablesComparator(){
        prevTable = new HashMap<>();
        newTable = new HashMap<>();
        prevTable.put("url3", "html3");
        prevTable.put("url2", "html2");
        prevTable.put("url1", "html1");
        newTable.put("url2", "html22");
        newTable.put("url4", "html4");
        newTable.put("url5", "html5");
    }

    private Set<String> intersectionKeys(){
        Set<String> newKeys = new HashSet<>(newTable.keySet());
        Set<String> intersectionKeys = new HashSet<>(prevTable.keySet());
        intersectionKeys.retainAll(newKeys);
        return intersectionKeys;
    }

    public Set<String> keysWithChangedValues(){
        Set<String> keysWithChangedValues = new HashSet<>();
        Set<String> intersectionKeys = intersectionKeys();
        for (String key:intersectionKeys) {
            if (!prevTable.get(key).equals(newTable.get(key))){
                keysWithChangedValues.add(key);
            }
        }
        return keysWithChangedValues;
    }

    public Set<String> keysInPrevOnly(){
        Set<String> prevKeys = new HashSet<>(prevTable.keySet());
        prevKeys.removeAll(intersectionKeys());
        return prevKeys;
    }

    public Set<String> keysInNewOnly(){
        Set<String> newKeys = new HashSet<>(newTable.keySet());
        newKeys.removeAll(intersectionKeys());
        return newKeys;
    }

    //Алгоритм находящий все необходимые наборы ключей за один проход
    public void allKeys(Set<String> changed, Set<String> inPrevOnly, Set<String> inNewOnly){
        TreeMap<String, String> sortedPrev = new TreeMap<>(prevTable);
        TreeMap<String, String> sortedNew = new TreeMap<>(newTable);
        Iterator<Map.Entry<String,String>> iteratorPrev = sortedPrev.entrySet().iterator();
        Iterator<Map.Entry<String,String>> iteratorNew = sortedNew.entrySet().iterator();
        Map.Entry<String,String> prevEntry = iteratorPrev.next();
        Map.Entry<String,String> newEntry = iteratorNew.next();
        while (true){
            if (prevEntry.getKey().compareTo(newEntry.getKey()) < 0){
                inPrevOnly.add(prevEntry.getKey());
                if (iteratorPrev.hasNext()) {
                    prevEntry = iteratorPrev.next();
                }
                else {
                    inNewOnly.add(newEntry.getKey());
                    break;
                }
            }
            else if (prevEntry.getKey().compareTo(newEntry.getKey()) > 0){
                inNewOnly.add(newEntry.getKey());
                if (iteratorNew.hasNext()){
                    newEntry = iteratorNew.next();
                }
                else {
                    inPrevOnly.add(prevEntry.getKey());
                    break;
                }
            }
            else {
                if (! prevEntry.getValue().equals(newEntry.getValue())) {
                    changed.add(prevEntry.getKey());
                }
                if (iteratorPrev.hasNext() && iteratorNew.hasNext()) {
                    prevEntry = iteratorPrev.next();
                    newEntry = iteratorNew.next();
                }
                else break;
            }
        }
        iteratorNew.forEachRemaining((ent) -> inNewOnly.add(ent.getKey()));
        iteratorPrev.forEachRemaining((ent) -> inPrevOnly.add(ent.getKey()));
    }



}

