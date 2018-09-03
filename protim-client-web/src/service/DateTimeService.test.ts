import DateTimeService from "./DateTimeService";

describe('Tests for: DateTimeService', () => {
    describe("day before is requested", () => {
        describe("day with beginning of a month given", () => {
            it("should return the last day of the month before", () => {
                const givenDate = new Date(1985, 4, 1);

                const result: Date = DateTimeService.dayBefore(givenDate);

                expect(result).toEqual(new Date(1985, 3, 30));
            });

        });
        it("the resulting object should not be the same instance as the given one", () => {
            const givenDate = new Date(1985, 5, 1);
            const result: Date = DateTimeService.dayBefore(givenDate);

            expect(result).not.toBe(givenDate);
        });
    });

    describe("day after is requested", () => {
        describe("day with ending of a month given", () => {
            it("should return the last day of the month before", () => {
                const givenDate = new Date(1985, 2, 31);
                const result: Date = DateTimeService.dayAfter(givenDate);

                expect(result).toEqual(new Date(1985, 3, 1));
            });

        });
        it("the resulting object should not be the same instance as the given one", () => {
            const givenDate = new Date(1985, 4, 1);
            const result: Date = DateTimeService.dayAfter(givenDate);

            expect(result).not.toBe(givenDate);
        });
    });

});