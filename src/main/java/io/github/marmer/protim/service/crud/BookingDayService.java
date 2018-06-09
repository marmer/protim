package io.github.marmer.protim.service.crud;

import io.github.marmer.protim.service.model.BookingDay;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service related to Bookingdays.
 */
public interface BookingDayService {
    /**
     * Provides the {@link BookingDay} for a specific date.
     *
     * @param date Date of the booking day.
     * @return The related {@link BookingDay}
     */
    Optional<BookingDay> getBookingDay(LocalDate date);

    /**
     * Lists the IDs of the bookings for the given day.
     *
     * @param day The day of interest.
     * @return ID List.
     */
    List<Long> getEnttyIDsForDay(LocalDate day);
}
