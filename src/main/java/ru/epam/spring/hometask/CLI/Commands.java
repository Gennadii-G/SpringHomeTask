package ru.epam.spring.hometask.CLI;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ru.epam.spring.hometask.domain.User;
import ru.epam.spring.hometask.util.Session;
import ru.epam.spring.hometask.adapter.AuditoriumConsoleAdapter;
import ru.epam.spring.hometask.adapter.BookingConsoleAdapter;
import ru.epam.spring.hometask.adapter.EventConsoleAdapter;
import ru.epam.spring.hometask.adapter.UserConsoleAdapter;

import java.util.HashMap;
import java.util.Map;

@Component
public class Commands implements CommandMarker {

    private static ConfigurableApplicationContext context;
    private static UserConsoleAdapter userConsoleAdapter;
    private static EventConsoleAdapter eventConsoleAdapter;
    private static AuditoriumConsoleAdapter auditorimConsoleAdapter;
    private static BookingConsoleAdapter bookingConsoleAdapter;
    private static Session session;
    private static Map<String, String> parameters;

    @CliAvailabilityIndicator({"login", "registration"}) //base
    public boolean isHasUser() {
        return !session.isAuth();
    }

    @CliAvailabilityIndicator({"show-event", "show-all-events", "show-all-auditoriums", "book-ticket", "get-price", "show-auditorium"})
    public boolean isAuthUser(){ return session.isGuest() || session.isAuth();}

    @CliAvailabilityIndicator({"user-delete", "get-user", "get-user-by-email", "show-all-users", "add-event", "delete-event", "get-purchased-tickets"})
    public boolean isAdmin() { return (session.getCurrentUser().isPower());}

    @CliCommand(value = "test", help = "something will help you")
    public String doTest() {
        return "[ TEST - OK ]";
    }

    @CliCommand(value = "current-user", help = "show current user")
    public String getActiveUser(){
        User user = session.getCurrentUser();
        if(user == null) return "нет активного пользователя";
        else return user.getId() + ". " + user.getFirstName();
    }

    @CliCommand(value = "login", help = "email")
    public String login(@CliOption(key = "email", mandatory = true) String email){
        User user = userConsoleAdapter.loginByEmail(email);
        if(user == null)return "пользователь с таким ящиком не найден";
        else{
            session.setCurrentUser(user);
            return "пользователь " + user.getFirstName() + " вошел";
        }
    }

    @CliCommand(value = "logout", help = "---")
    public String logout(){
        String usName = session.getCurrentUser().getFirstName();
        session.logout();
        return "пользователь " + usName + " вышел.";
    }

    @CliCommand(value = "registration", help = "firstName, lastName, birthDate(2000-10-10), email")
    public String userRegistration(
            @CliOption(key = "firstName", mandatory = true) String firstName,
            @CliOption(key = "lastName", mandatory = true) String lastName,
            @CliOption(key = "birthDate", mandatory = true) String birthDate,
            @CliOption(key = "email", mandatory = true) String email){

        parameters = new HashMap<String, String>(){{
            put("firstName", firstName);
            put("lastName", lastName);
            put("email", email);
            put("birthDate", birthDate);
        }};
        return userConsoleAdapter.userRegistration(parameters);
    }

    @CliCommand(value = "user-delete", help = "id")
    public String userDelete(
            @CliOption(key = "id", mandatory = true) String id) {
        return userConsoleAdapter.delUser(Integer.parseInt(id));
    }

    @CliCommand(value = "get-user", help = "id")
    public String getUserById(
            @CliOption(key = "id", mandatory = true) String id) {
        return userConsoleAdapter.getUserById(Integer.parseInt(id));
    }

    @CliCommand(value = "get-user-by-email", help = "email")
    public String getUserByEmail(
            @CliOption(key = "email", mandatory = true) String email) {
        return userConsoleAdapter.getUserByEmail(email);
    }

    @CliCommand(value = {"show-all-users"}, help = "eventName")
    public String getAllUser() {
        return userConsoleAdapter.getAll();
    }

    @CliCommand(value= {"show-event"})
    public String getByName(@CliOption(key = "eventName", mandatory = true)String eventName){
        return eventConsoleAdapter.getEvent(eventName);
    }

    @CliCommand(value={"add-event"}, help = "eventName, airDates(2000-10-10 12:00,2000-10-10 12:30), basePrice, rating, auditorium")
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
    }

    @CliCommand(value = {"show-all-events"}, help = "---")
    public String getAllEvent(){
        return eventConsoleAdapter.getAllEvents();
    }

    @CliCommand(value = {"delete-event"}, help = "id")
    public String removeEvent( @CliOption(key = "id", mandatory = true) String id){
        return eventConsoleAdapter.remove(id);
    }

    @CliCommand(value = {"show-all-auditoriums"}, help = "---")
    public String getAllAuditoriums(){
        return auditorimConsoleAdapter.getAll();
    }

    @CliCommand(value = {"show-auditorium"}, help = "auditoriumName")
    public String getAudByName(@CliOption(key = "auditoriumName", mandatory = true) String audName){
        return auditorimConsoleAdapter.getByName(audName);
    }

    @CliCommand(value = {"get-price"}, help = "eventId, airDate, seats")
    public String getTicketsPrice(
            @CliOption(key = "eventId", mandatory = true) String event,
            @CliOption(key = "airDate", mandatory = true) String airDate,
            @CliOption(key = "seats", mandatory = true) String seats) {

        parameters = new HashMap<String, String>(){{
            put("eventId", event);
            put("airDate", airDate);
            put("userEmail", session.getCurrentUser().getEmail());
            put("seats", seats);
        }};
        return bookingConsoleAdapter.getTicketPrice(parameters);
    }

    @CliCommand(value = {"book-ticket"}, help = "eventID, airDate, seats")
    public String bookTicket(
            @CliOption(key = "eventID", mandatory = true) String event,
            @CliOption(key = "airDate", mandatory = true) String airDate,
            @CliOption(key = "seats", mandatory = true) String seats) {

        return bookingConsoleAdapter.bookTicket(event,airDate,session.getCurrentUser().getEmail(),seats.split(","));
    }

    @CliCommand(value = {"get-purchased-tickets"}, help = "eventId, airDate")
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
