package org.poo.cb;

import java.util.Map;

/**
 * Interfata MapFromFile este folosita pentru a citi informatii dintr-un fisier si a le salva intr-un map.
 * @param <T> - tipul cheii map-ului
 */
public interface MapFromFile<T> {
    /*
    Metoda parseFile este folosita pentru a citi din fisier informatii si a le salva intr-un map.
     */
    Map<T, ?> parseFile(String filePath);
}
