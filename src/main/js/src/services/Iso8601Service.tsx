export class Iso8601Service {
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