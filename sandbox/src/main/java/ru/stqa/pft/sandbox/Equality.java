package ru.stqa.pft.sandbox;

/**
 * Created by t_aleksandr on 8/25/16.
 */
public class Equality {

  public static void main(String[] args) {
    String s1 = "firefox 2.0";
    String s2 = "firefox " + Math.sqrt(4.0);

    //String s1 = "firefox";
    //String s2 = "fire" + "fox";
    //String s2 = s1;
    //String s2 = new String(s1);

    System.out.println(s1==s2);
    System.out.println(s1.equals(s2));
  }
}
