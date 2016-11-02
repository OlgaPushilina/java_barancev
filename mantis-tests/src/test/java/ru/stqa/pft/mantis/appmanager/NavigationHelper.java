package ru.stqa.pft.mantis.appmanager;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;

/**
 * Created by Olga on 10/19/16.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"),username);
    type(By.name("password"),password);
    click(By.cssSelector("input[value='Login']"));
  }

  public boolean isLoggedInAs(String username) {
    String user = wd.findElement(By.id("logged-in-user")).getText();
    return (user.equals(username));
  }

  public void manageUsers() {
    click(By.cssSelector("a.manage-menu-link"));
    click(By.linkText("Manage Users"));
    Assert.assertTrue(isElementPresent(By.cssSelector("input[value='Manage User']")));
  }

  public void chooseUser(Users user) {
    click(By.linkText(user.getName()));
  }

  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));
  }
}
