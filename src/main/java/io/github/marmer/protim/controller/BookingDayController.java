package io.github.marmer.protim.controller;

import io.github.marmer.protim.model.BookingDay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("booking")
public class BookingDayController {
    @RequestMapping("day")
    public String listSomeBookingDays(final Model model) {
        final BookingDay bookingDay = new BookingDay().setId(42L);
        model.addAttribute("day", bookingDay);
        model.addAttribute("someString", "someStringValue");
        return "hello";
    }
}
