import {SystemTimeService} from "./SystemTimeService";

describe('Tests for: ' + SystemTimeService.name, function () {
    it("should serve a date object pretty close to now", () => {
        const nowInMs: number = new Date().getTime();
        const acceptableDelta = 100;

        expect(SystemTimeService.now().getTime()).toBeGreaterThanOrEqual(nowInMs);
        expect(SystemTimeService.now().getTime()).toBeLessThanOrEqual(nowInMs + acceptableDelta);
    });
});