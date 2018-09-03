export default class BookingTime {
    startTime: Date;
    lengthInMs: number;

    constructor(startTime: Date, lengthInMs: number) {
        this.startTime = startTime;
        this.lengthInMs = lengthInMs;
    }
}