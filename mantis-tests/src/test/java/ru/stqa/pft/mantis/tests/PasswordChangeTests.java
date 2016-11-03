package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;
import static ru.stqa.pft.mantis.tests.RegistrationTests.findConfirmationLink;

/**
 * Created by Olga on 10/18/16.
 */
public class PasswordChangeTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testPasswordChange() throws IOException {
    String username = app.getProperty("web.adminLogin");
    String password = app.getProperty("web.adminPassword");
    app.navigate().login(username, password);
    assertTrue(app.navigate().isLoggedInAs(username));
    app.navigate().manageUsers();
    List<Users> allUsers = app.db().users();
    Set<Users> usersWhoAreNotAdmin = allUsers.stream()
            .filter(u -> !u.getName().equals("administrator"))
            .collect(Collectors.toSet());
    Users user = usersWhoAreNotAdmin.iterator().next();
    app.navigate().chooseUser(user);
    app.navigate().resetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
    long now = System.currentTimeMillis();
    String newPassword = String.format("pass%s" , now);
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user.getName(), newPassword));
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
