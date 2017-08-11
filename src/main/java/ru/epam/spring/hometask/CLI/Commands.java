package ru.epam.spring.hometask.CLI;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ru.epam.spring.hometask.utils.console_handlers.UserAdapter;

import java.util.HashMap;
import java.util.Map;

@Component
public class Commands implements CommandMarker {

    private static ConfigurableApplicationContext context;
    private static UserAdapter userAdapter;

    @CliCommand(value = {"test"})
    public String doTest() {

        return "[ TEST - OK ]";
    }

    @CliCommand(value= {"login"})
    public String login(@CliOption(key = "name", mandatory = true) String name){
        return "You are logged in as " + name;
    }

    @CliCommand(value = {"registration"})
    public String userRegistration(
            @CliOption(key = "firstName", mandatory = true) String firstName,
            @CliOption(key = "lastName", mandatory = true) String lastName,
//            @CliOption(key = "birthDate", mandatory = true) String birthDate,
        @CliOption(key = "email", mandatory = true) String email){

        Map<String, String> userInfo = new HashMap<String, String>(){{
            put("firstName", firstName);
            put("lastName", lastName);
            put("email", email);
//            put("birthDate", birthDate);
        }};
        return userAdapter.userRegistration(userInfo);
    }

    public static void setContext(ConfigurableApplicationContext context){
        Commands.context = context;
        userAdapter = context.getBean(UserAdapter.class);
//        smth = context.getBean(*.class);


    }
}
