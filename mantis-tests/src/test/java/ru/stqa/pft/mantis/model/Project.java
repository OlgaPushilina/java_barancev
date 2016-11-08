package ru.stqa.pft.mantis.model;

/**
 * Created by Olga on 11/8/16.
 */
public class Project {

  private int id;

  public int getId() {
    return id;
  }

  public Project withId(int id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }

  private String name;
}
