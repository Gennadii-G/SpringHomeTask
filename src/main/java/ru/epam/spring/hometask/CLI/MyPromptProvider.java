package ru.epam.spring.hometask.CLI;

import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.PromptProvider;
import org.springframework.stereotype.Component;

/** */

@Component
@Order(1147483647)
public class MyPromptProvider implements PromptProvider {
    public MyPromptProvider() {
    }

    public String getPrompt() {
        return "->";
    }

    public String getProviderName() {
        return "default prompt provider";
    }
}
