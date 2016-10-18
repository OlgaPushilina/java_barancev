package ru.stqa.pft.mantis.model;

/**
 * Created by Olga on 10/18/16.
 */
public class MailMessage {
  public String to;
  public String text;

  public MailMessage(String to, String text) {
    this.to = to;
    this.text = text;
  }
}
