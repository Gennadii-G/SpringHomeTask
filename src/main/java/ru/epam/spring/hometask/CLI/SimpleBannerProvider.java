package ru.epam.spring.hometask.CLI;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleBannerProvider extends DefaultBannerProvider {

    public String getBanner() {
        StringBuilder strB = new StringBuilder();
        strB.append("\\\\\\\\\\HELLO COMRAD/////")
                .append(OsUtils.LINE_SEPARATOR);
        return strB.toString();
    }

    public String getVersion() {
        return "1.0";
    }

    public String getWelcomeMessage() {
        return "Welcome to application for managing a movie theater";
    }

    public String getProviderName() {
        return "Dmitriy Top";
    }
}
