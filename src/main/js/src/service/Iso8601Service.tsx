/**
 * Service to handle ISO 8601 formats.
 */
export class Iso8601Service {
    /**
     * Provides the current date as ISO 8601 String.
     * @returns {String} Today
     */
    static today(): String {
        const todayDate = Iso8601Service.todayDate();
        return Iso8601Service.toIsoDate(todayDate);
    }

    private static toIsoDate(todayDate: Date): String {
        return todayDate.getFullYear() + "-" + todayDate.getMonth() + "-" + todayDate.getDay();
    }

    private static todayDate(): Date {
        const now = new Date();
        now.setHours(0, 0, 0, 0);
        return now;
    }
}