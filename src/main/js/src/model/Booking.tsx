import {Assignment} from "./Assignment";
import {BookingTime} from "./BookingTime";

export class Booking {
    id: Number;
    time: BookingTime;
    description: String;
    comments: String;
    assignment: Assignment;
}