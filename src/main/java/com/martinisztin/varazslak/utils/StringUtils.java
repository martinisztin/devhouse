package com.martinisztin.varazslak.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    private StringUtils() {

    }

    private static void convertRemainingAccentCharacters(final StringBuilder decomposed) {
        for (int i = 0; i < decomposed.length(); i++) {
            final char charAt = decomposed.charAt(i);
            switch (charAt) {
                case '\u0141':
                    decomposed.setCharAt(i, 'L');
                    break;
                case '\u0142':
                    decomposed.setCharAt(i, 'l');
                    break;
                // D with stroke
                case '\u0110':
                    // LATIN CAPITAL LETTER D WITH STROKE
                    decomposed.setCharAt(i, 'D');
                    break;
                case '\u0111':
                    // LATIN SMALL LETTER D WITH STROKE
                    decomposed.setCharAt(i, 'd');
                    break;
                // I with bar
                case '\u0197':
                    decomposed.setCharAt(i, 'I');
                    break;
                case '\u0268':
                    decomposed.setCharAt(i, 'i');
                    break;
                case '\u1D7B':
                    decomposed.setCharAt(i, 'I');
                    break;
                case '\u1DA4':
                    decomposed.setCharAt(i, 'i');
                    break;
                case '\u1DA7':
                    decomposed.setCharAt(i, 'I');
                    break;
                // U with bar
                case '\u0244':
                    // LATIN CAPITAL LETTER U BAR
                    decomposed.setCharAt(i, 'U');
                    break;
                case '\u0289':
                    // LATIN SMALL LETTER U BAR
                    decomposed.setCharAt(i, 'u');
                    break;
                case '\u1D7E':
                    // LATIN SMALL CAPITAL LETTER U WITH STROKE
                    decomposed.setCharAt(i, 'U');
                    break;
                case '\u1DB6':
                    // MODIFIER LETTER SMALL U BAR
                    decomposed.setCharAt(i, 'u');
                    break;
                // T with stroke
                case '\u0166':
                    // LATIN CAPITAL LETTER T WITH STROKE
                    decomposed.setCharAt(i, 'T');
                    break;
                case '\u0167':
                    // LATIN SMALL LETTER T WITH STROKE
                    decomposed.setCharAt(i, 't');
                    break;
                default:
                    break;
            }
        }
    }

    private static final Pattern STRIP_ACCENTS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); //$NON-NLS-1$
    public static final String EMPTY = "";

    public static String stripAccents(final String input) {
        if(input == null)
            return input;

        final StringBuilder decomposed = new StringBuilder(Normalizer.normalize(input, Normalizer.Form.NFKD));

        convertRemainingAccentCharacters(decomposed);

        return STRIP_ACCENTS_PATTERN.matcher(decomposed).replaceAll(EMPTY);
    }

    public static String addKnownAccent(String input) {
        String res = input.substring(0, 1).toUpperCase() + input.substring(1);

        return res.replace('a', 'á').replace('o', 'ő');
    }
}
