/**
 * Service to get the current Date Time. This can be used to simulate System Time Changes which may especially be
 * helpful for tests.
 *
 * @author marmer
 * @since 11.04.2018
 */
export class SystemTimeService {
    public static now(): Date {
        return new Date();
    }
}