package page;

public class LoginPageXPath {
    public static final String TAB_LOGIN_BY_EMAIL_XPATH = "//button[text()='почте или логину']";
    public static final String INPUT_EMAIL_XPATH = "//input[@id='authLogin']";
    public static final String INPUT_PASSWORD_XPATH = "//input[@id='loginPassword']";
    public static final String BUTTON_ENTER_XPATH = "//button[@type='submit']";
    public static final String EMAIL_ERROR_MESSAGE_XPATH = "//input[@id='authLogin']/following-sibling::div[@class='error-message']";
    public static final String PASSWORD_ERROR_MESSAGE_XPATH = "//input[@id='loginPassword']/following-sibling::div[@class='error-message']";
    public static final String ERROR_MESSAGE_XPATH = "//div[contains(@class,'error-message')]";
}
