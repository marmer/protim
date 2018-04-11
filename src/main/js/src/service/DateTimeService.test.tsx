import {DateTimeService} from "./DateTimeService";

describe('Tests for: ' + DateTimeService.name, function () {
    it("should serve a date object pretty close to now", () => {
        const nowInMs: number = new Date().getTime();
        const acceptableDelta = 100;

        expect(DateTimeService.now().getTime()).toBeGreaterThanOrEqual(nowInMs);
        expect(DateTimeService.now().getTime()).toBeLessThanOrEqual(nowInMs + acceptableDelta);
    });
});