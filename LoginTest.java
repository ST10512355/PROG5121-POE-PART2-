import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // ===== Username Tests =====

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }

    // ===== Password Tests =====

    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordFailsComplexity() {
        Login login = new Login("kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }

    // ===== Cell Phone Tests =====

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }

    // ===== Login Tests =====

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    // ===== Return Login Status Tests =====

    @Test
    public void testReturnLoginStatusSuccess() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertTrue(result.contains("it is great to see you again"));
    }

    @Test
    public void testReturnLoginStatusFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String result = login.returnLoginStatus("kyl_1", "wrongpassword");
        assertEquals("Username or password incorrect, please try again.", result);
    }
}