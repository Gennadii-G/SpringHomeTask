package ru.epam.spring.hometask.CLI;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ru.epam.spring.hometask.utils.Session;
import ru.epam.spring.hometask.utils.adapter.AuditoriumConsoleAdapter;
import ru.epam.spring.hometask.utils.adapter.BookingConsoleAdapter;
import ru.epam.spring.hometask.utils.adapter.EventConsoleAdapter;
import ru.epam.spring.hometask.utils.adapter.UserConsoleAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Commands implements CommandMarker {

    private static ConfigurableApplicationContext context;
    private static UserConsoleAdapter userConsoleAdapter;
    private static EventConsoleAdapter eventConsoleAdapter;
    private static AuditoriumConsoleAdapter auditorimConsoleAdapter;
    private static BookingConsoleAdapter bookingConsoleAdapter;
    private static Session session;
    private static final String COMMANDS_FOR_LOGIN_USER = "getTickets, ";
    private static final List<String> COMMANDS_FOR_LOGIN_USER_TEST = Arrays.asList("1", "2", "3");
    private static Map<String, String> parameters;

    @CliAvailabilityIndicator({"login", "registration"}) //base
    public boolean isHasUser() {
        return !session.isAuth();
    }

    @CliAvailabilityIndicator({COMMANDS_FOR_LOGIN_USER})
    public boolean isAuthUser(){
        return session.isAuth();
    }

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

        parameters = new HashMap<String, String>(){{
            put("firstName", firstName);
            put("lastName", lastName);
            put("email", email);
//            put("birthDate", birthDate);
        }};
        return userConsoleAdapter.userRegistration(parameters);
    }

    @CliCommand(value = {"user-delete"})
    public String userDelete(
            @CliOption(key = "id", mandatory = true) String id) {
        return userConsoleAdapter.delUser(Integer.parseInt(id));
    }

    @CliCommand(value = {"get-user"})
    public String getUserById(
            @CliOption(key = "id", mandatory = true) String id) {
        return userConsoleAdapter.getUserById(Integer.parseInt(id));
    }

    @CliCommand(value = {"get-user-by-email"})
    public String getUserByEmail(
            @CliOption(key = "email", mandatory = true) String email) {
        return userConsoleAdapter.getUserByEmail(email);
    }

    @CliCommand(value = {"getAllUser"})
    public String getAllUser() {
        return userConsoleAdapter.getAll();
    }

    @CliCommand(value= {"show-event"})
    public String getByName(@CliOption(key = "eventName", mandatory = true)String eventName){
        return eventConsoleAdapter.getEvent(eventName);
    }

    @CliCommand(value={"add-event"})
    public String saveEvent(
            @CliOption(key = "eventName", mandatory = true) String eventName,
            @CliOption(key = "airDates", mandatory = true) String airDates,
            @CliOption(key = "basePrice", mandatory = true) String basePrice,
            @CliOption(key = "rating", mandatory = true) String rating,
            @CliOption(key = "auditorium", mandatory = true) String auditorium) {

        parameters = new HashMap<String, String>(){{
            put("eventName", eventName);
            put("basePrice", basePrice);
            put("rating", rating);
            put("auditorium", auditorium);
            put("airDates", airDates);
        }};
        return eventConsoleAdapter.save(parameters);
//        return eventConsoleAdapter.save(eventName, airDatesArray, basePrice, rating, auditoriums);
    }

    @CliCommand(value = {"show-all-event"})
    public String getAllEvent(){
        return eventConsoleAdapter.getAllEvents();
    }

    @CliCommand(value = {"delete-event"})
    public String removeEvent( @CliOption(key = "id", mandatory = true) String id){
        return eventConsoleAdapter.remove(id);
    }

    @CliCommand(value = {"show-all-auditoriums"})
    public String getAllAuditoriums(){
        return auditorimConsoleAdapter.getAll();
    }

    @CliCommand(value = {"show-auditorium"})
    public String getAudByName(@CliOption(key = "auditoriumName", mandatory = true) String audName){
        return auditorimConsoleAdapter.getByName(audName);
    }

    @CliCommand(value = {"get-price"})
    public String getTicketsPrice(
            @CliOption(key = "eventID", mandatory = true) String event,
            @CliOption(key = "airDate", mandatory = true) String airDate,
            @CliOption(key = "userEmail", mandatory = true) String userEmail,
            @CliOption(key = "seats", mandatory = true) String seats) {

        return bookingConsoleAdapter.getTicketPrice(event,airDate,userEmail,seats.split(","));
    }

    @CliCommand(value = {"book-ticket"})
    public String bookTicket(
            @CliOption(key = "eventID", mandatory = true) String event,
            @CliOption(key = "airDate", mandatory = true) String airDate,
            @CliOption(key = "userEmail", mandatory = true) String userEmail,
            @CliOption(key = "seats", mandatory = true) String seats) {

        return bookingConsoleAdapter.bookTicket(event,airDate,userEmail,seats.split(","));
    }

    @CliCommand(value = {"get-purchased-tickets"})
    public String getPurchasedTicketsForEvent(
            @CliOption(key = "eventId", mandatory = true) String event,
            @CliOption(key = "airDate", mandatory = true) String airDate) {

        return bookingConsoleAdapter.getPurchasedTicketsForEvent(event,airDate);
    }

    public static void setContext(ConfigurableApplicationContext context){
        Commands.context = context;
        userConsoleAdapter = context.getBean(UserConsoleAdapter.class);
        eventConsoleAdapter = context.getBean(EventConsoleAdapter.class);
        auditorimConsoleAdapter = context.getBean(AuditoriumConsoleAdapter.class);
        bookingConsoleAdapter = context.getBean(BookingConsoleAdapter.class);
        session = context.getBean(Session.class);
    }
}
