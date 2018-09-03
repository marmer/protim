/**
 * Service handle dates easily.
 *
 */
export default class DateTimeService {
    public static dayAfter(date: Date): Date {
        const newDate: Date = new Date(date);
        newDate.setDate(date.getDate() + 1);
        return newDate;
    }

    public static dayBefore(date: Date): Date {
        const newDate = new Date(date);
        newDate.setDate(date.getDate() - 1);

        return newDate;
    }
}