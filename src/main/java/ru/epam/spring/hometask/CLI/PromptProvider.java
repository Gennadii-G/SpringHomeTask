package ru.epam.spring.hometask.CLI;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class PromptProvider extends DefaultPromptProvider {

    @Override
    public final String getPrompt() {
        return "$";
    }

    @Override
    public String getProviderName() {
        return "default prompt provider";
    }

}
