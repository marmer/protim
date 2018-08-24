import {Booking} from "./Booking";

export class BookingDay {
    day: string;
    bookings: Booking[];

    constructor(day: string, bookings: Booking[]) {
        this.day = day;
        this.bookings = bookings;
    }
}