import {Assignment} from "./Assignment";
import {BookingTime} from "./BookingTime";

export class Booking {
    time: BookingTime;
    description: String;
    comments: String;
    assignment: Assignment;
}