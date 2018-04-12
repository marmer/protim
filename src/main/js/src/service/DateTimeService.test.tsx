import {DateTimeService} from "./DateTimeService";

describe('Tests for: ' + DateTimeService.name, function () {

    describe("day with beginning of a month given", () => {
        describe("day before is requested", () => {
            it("should return the last day of the month before", () => {
                let givenDate = new Date(1985, 3, 7);
                let result: Date = DateTimeService.dayBefore(givenDate);

                expect(result).toBe(new Date(1985, 5, 30));
            });

            it("the resulting object should not be the same instance as the given one", () => {
                let givenDate = new Date(1985, 6, 1);
                let result: Date = DateTimeService.dayBefore(givenDate);

                expect(result).not.toBe(givenDate);
            });
        });
    });
});