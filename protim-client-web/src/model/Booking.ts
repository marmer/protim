import {Assignment} from "./Assignment";
import {BookingTime} from "./BookingTime";

export class Booking {
    id: number;
    time: BookingTime;
    description: string;
    comments: string;
    assignment: Assignment;
    booked: boolean;

    constructor(id: number, time: BookingTime, description: string, comments: string, assignment: Assignment, booked: boolean) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.comments = comments;
        this.assignment = assignment;
        this.booked = booked;
    }
}