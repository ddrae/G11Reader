package com.g11.g11reader.fileinput;

import com.g11.g11reader.backend.Book;
import com.g11.g11reader.backend.Effect;
import com.g11.g11reader.backend.Element;
import com.g11.g11reader.backend.Page;
import com.g11.g11reader.backend.effects.GoToPageEffect;
import com.g11.g11reader.backend.effects.PlaySoundEffect;
import com.g11.g11reader.backend.elements.AnimationElement;
import com.g11.g11reader.backend.elements.ButtonElement;
import com.g11.g11reader.backend.elements.ImageElement;
import com.g11.g11reader.backend.elements.SoundElement;
import com.g11.g11reader.backend.elements.TextElement;
import com.g11.g11reader.backend.elements.TimerElement;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by H on 2016-12-28.
 */

public class ContentLoader {
    private ContentLoader() {}

    public static List<Page> loadBook(BufferedReader br) {
        List<Page> result = new ArrayList<>();
        List<Element> currentPage = new ArrayList<>();

        try{
            for(String line; (line = br.readLine()) != null; ) {
                //String[] elements = line.split("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
                //String[] elements = line.split("\\s+");
                String[] elements = line.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");
                if(line.startsWith("{")) {
                    currentPage = new ArrayList<>();
                } else if(line.startsWith("}")) {
                    result.add(new Page(currentPage));
                } else if(elements.length > 0) {
                    for(String s : elements) {
                        System.out.println(s);
                    }
                    if(elements[0].equals("a")) {
                        currentPage.add(loadAnimationElement(elements));
                    } else if(elements[0].equals("b")) {
                        currentPage.add(loadButtonElement(elements));
                    } else if(elements[0].equals("i")) {
                        currentPage.add(loadImageElement(elements));
                        System.out.println(currentPage.size());
                    } else if(elements[0].equals("s")) {
                        currentPage.add(loadSoundElement(elements));
                    } else if(elements[0].equals("tx")) {
                        currentPage.add(loadTextElement(elements));
                    } else if(elements[0].equals("ti")) {
                        currentPage.add(loadTimerElement(elements));
                    }
                }
            }
        } catch (IOException e) {
            //TODO catch this exception
        }

        return result;
    }

    private static Element loadAnimationElement(String[] elements) {
        try {
            Integer index = Integer.parseInt(elements[1]);
            Integer x = Integer.parseInt(elements[2]);
            Integer y = Integer.parseInt(elements[3]);
            return new AnimationElement(index, x, y);
        } catch (Exception e) {
            return null;
        }
    }

    private static Element loadButtonElement(String[] elements) {
        try {
            Integer x = Integer.parseInt(elements[1]);
            Integer y = Integer.parseInt(elements[2]);
            Integer w = Integer.parseInt(elements[3]);
            Integer h = Integer.parseInt(elements[4]);
            Effect eff = loadEffect(elements, 5);
            return new ButtonElement(x, y, w, h, eff);
        } catch (Exception e) {
            return null;
        }
    }

    private static Element loadImageElement(String[] elements) {
        try {
            Integer index = Integer.parseInt(elements[1]);
            Integer x = Integer.parseInt(elements[2]);
            Integer y = Integer.parseInt(elements[3]);
            return new ImageElement(index, x, y);
        } catch (Exception e) {
            return null;
        }
    }

    private static Element loadSoundElement(String[] elements) {
        try {
            Integer index = Integer.parseInt(elements[1]);
            return new SoundElement(index);
        } catch (Exception e) {
            return null;
        }
    }

    private static Element loadTextElement(String[] elements) {
        try {
            Integer x = Integer.parseInt(elements[1]);
            Integer y = Integer.parseInt(elements[2]);
            String text = elements[3].substring(1).substring(0,elements[3].length()-2);
            return new TextElement(x, y, text);
        } catch (Exception e) {
            return null;
        }
    }

    private static Element loadTimerElement(String[] elements) {
        Integer t = Integer.parseInt(elements[1]);
        Effect eff = loadEffect(elements, 2);
        return new TimerElement(t, eff);
    }

    private static Effect loadEffect(String[] elements, int offset) throws NullPointerException,
            NumberFormatException {
        if(elements[offset].equals("p")) {
            Integer index = Integer.parseInt(elements[offset+1]);
            return new GoToPageEffect(index);
        } else if(elements[offset].equals("s")) {
            Integer index = Integer.parseInt(elements[offset+1]);
            return new PlaySoundEffect(index);
        }
        throw new NullPointerException();
    }
}
