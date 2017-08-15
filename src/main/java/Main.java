
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.Bootstrap;
import ru.epam.spring.hometask.CLI.Commands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("start");

        LogManager.getLogManager().reset(); // off spring-msg
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Commands.setContext(context);
        Bootstrap.main(args);
        context.close();
    }


}
