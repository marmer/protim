/**
 * Service handle dates easily.
 *
 * @author marmer
 * @since  11.04.2018
 */
export class DateTimeService {
    public static dayAfter(date: Date): Date {
        // TODO: MarMer 11.04.2018 implement me!

        let date1 = new Date(date);
        date1.setMonth(date1.getMonth() - 1); // total nonsense of course
        return date1;
    }
}