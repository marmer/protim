import {SystemTimeService} from "./SystemTimeService";

describe("Tests for: DateTimeService", () => {
    describe("#now", () => {
        it('should serve a date object pretty close to now', () => {
            const nowInMs: number = new Date().getTime();
            const acceptableDelta = 100;

            expect(SystemTimeService.now().getTime()).toBeGreaterThanOrEqual(nowInMs);
            expect(SystemTimeService.now().getTime()).toBeLessThanOrEqual(nowInMs + acceptableDelta);
        });
    });

    describe('#today', () => {
        it('should serve a date object pretty close to now', () => {
            const now = new Date();
            expect(SystemTimeService.today()).toEqual(new Date(now.getFullYear(), now.getMonth(), now.getDate()));
        });
    });
});