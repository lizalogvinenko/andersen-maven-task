package util;

import org.openqa.selenium.By;

public class AndersenBy {

    public static By testId(String id) {
        final String xpath = String.format("//*[@data-test-id='%s']", id);
        return By.xpath(xpath);
    }
}
