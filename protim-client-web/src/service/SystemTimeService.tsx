/**
 * Service to get the current Date Time. This can be used to simulate System Time Changes which may especially be
 * helpful for tests.
 */
export class SystemTimeService {
    public static now(): Date {
        return new Date();
    }

    public static today() {
        const now = SystemTimeService.now();
        return new Date(now.getFullYear(), now.getMonth(), now.getDate());
    }
}