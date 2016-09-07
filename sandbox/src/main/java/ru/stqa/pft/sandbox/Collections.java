package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by t_aleksandr on 9/6/16.
 */
public class Collections {
  public static void main(String[] args) {
    //String[] langs = new String[4];
    //langs[0] = "Java";
    //langs[1] = "C#";
    //langs[2] = "Python";
    //langs[3] = "PHP";

    String[] langs = {"Java", "C#", "Python", "PHP" };

    //for (int i = 0; i < langs.length; i++) {
      //System.out.println("Я хочу выучить " + langs [i]);
    //}

    //for (String l : langs) {
      //System.out.println("Я хочу выучить " + l);
    //}

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");

    //for (String l : languages) {
      //System.out.println("Я хочу выучить " + l);
    //}

    List<String> prLang = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : prLang) {
      System.out.println("Я хочу выучить " + l);
    }

    //for (int i = 0; i < prLang.size(); i++) {
      //System.out.println("Я хочу выучить " + prLang.get(i));
    //}

    List mixed = Arrays.asList("Java", "C#", "Python", "PHP");

    for (Object l : mixed) {
      System.out.println("Я хочу выучить " + l);
    }

  }
}
