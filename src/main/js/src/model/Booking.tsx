import {Assignment} from "./Assignment";
import {BookingTime} from "./BookingTime";

export class Booking {
    id: number;
    time: BookingTime;
    description: string;
    comments: string;
    assignment: Assignment;
    booked: boolean;
}