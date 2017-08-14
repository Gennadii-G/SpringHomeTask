package ru.epam.spring.hometask.utils.adapter;

import ru.epam.spring.hometask.abstract_layout.service.BookingService;
import ru.epam.spring.hometask.abstract_layout.service.EventService;
import ru.epam.spring.hometask.abstract_layout.service.UserService;
import ru.epam.spring.hometask.domain.Event;
import ru.epam.spring.hometask.domain.Ticket;
import ru.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class BookingConsoleAdapter {

    private BookingService bookingService;
    private EventService eventService;
    private UserService userService;

    public BookingConsoleAdapter(BookingService bookingService, EventService eventService, UserService userService) {
        this.bookingService = bookingService;
        this.eventService = eventService;
        this.userService = userService;
    }

    public String getTicketPrice(String event, String airDate, String userEmail, String seatsStr[]) {
        double summPrice = 0;
        try {
            int eventID = Integer.parseInt(event);
            Event currentEvent = eventService.getById(eventID);

            if (currentEvent == null) return "Event not found";

            LocalDateTime airDateTime = LocalDateTime.parse(airDate, Event.FORMATTER);
            User user = userService.getUserByEmail(userEmail);

            if (user == null) return "User not found";

            Set<Long> seats = convertStringToSetLong(seatsStr);

            if(currentEvent.getAuditoriums().get(airDateTime)==null)return "Event in choose air date not found";

            if(!currentEvent.getAuditoriums().get(airDateTime).getAllSeats().containsAll(seats))return "Seats not found";

            String result = String.valueOf(bookingService.getTicketsPrice(currentEvent, airDateTime, user, seats));
            return result;

        } catch (NumberFormatException e) {
            return "argument 'eventID' is wrong";
        } catch (DateTimeParseException e) {
            return "argument 'air date' is wrong";
        } catch (NullPointerException e) {
            return "argument is wrong";
        }

    }

    private Set<Long> convertStringToSetLong(String[] seatsStr) {
        Set<Long> set = new HashSet<>();
        for (String seatStr : seatsStr) {
            Long seat = Long.parseLong(seatStr);
            set.add(seat);
        }
        return set;
    }

    public String bookTicket(String event, String airDate, String userEmail, String seatsStr[]) {
        try {
            int eventId = Integer.parseInt(event);
            Event currentEvent = eventService.getById(eventId);

            if (currentEvent == null) return "Event not found";

            LocalDateTime airDateTime = LocalDateTime.parse(airDate, Event.FORMATTER);
            User user = userService.getUserByEmail(userEmail);

            if (user == null) return "User not found";

            Set<Long> seats = convertStringToSetLong(seatsStr);

            if(currentEvent.getAuditoriums().get(airDateTime)==null)return "Event in choose air date not found";

            if(!currentEvent.getAuditoriums().get(airDateTime).getAllSeats().containsAll(seats))return "Seats not found";
            Set<Ticket> tickets = new HashSet<>();
            for (Long seat : seats){
                Ticket ticket = new Ticket(user,currentEvent,airDateTime,seat);
                tickets.add(ticket);
            }
            bookingService.bookTickets(tickets);
            return "seats: " + seats.toString() + " is booked";

        } catch (NumberFormatException e) {
            return "argument 'eventID' is wrong";
        } catch (DateTimeParseException e) {
            return "argument 'air date' is wrong";
        } catch (NullPointerException e) {
            return "argument is wrong";
        }
    }

    public String getPurchasedTicketsForEvent(String event, String airDate) {
        try {
            int eventID = Integer.parseInt(event);
            Event currentEvent = eventService.getById(eventID);

            if (currentEvent == null) return "Event not found";

            LocalDateTime airDateTime = LocalDateTime.parse(airDate, Event.FORMATTER);

            if(currentEvent.getAuditoriums().get(airDateTime)==null)return "Event in choose air date not found";

            Set<Ticket> tickets = bookingService.getPurchasedTicketsForEvent(currentEvent,airDateTime);

            return "Tickets, sell to event " + currentEvent.getName() + " on " + airDate + ", is " + tickets.size();

        } catch (NumberFormatException e) {
            return "argument 'eventID' is wrong";
        } catch (DateTimeParseException e) {
            return "argument 'air date' is wrong";
        } catch (NullPointerException e) {
            return "argument is wrong";
        }
    }
}
