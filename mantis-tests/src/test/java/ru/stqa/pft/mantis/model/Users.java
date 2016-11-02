package ru.stqa.pft.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Olga on 11/1/16.
 */
@Entity
@Table(name = "mantis_user_table")
public class Users {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "username")
  private String name;
  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getEmail() {
    return email;
  }
}
