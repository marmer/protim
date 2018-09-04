import {StringUtils} from "./StringUtils";

describe('Tests for StringUtils', () => {

    describe("List of Strings given", () => {
        it("Should conat the individual parts with a comma", () => {
            const result = StringUtils.getCommaAndSpaceSeparated(["first", "second"]);
            expect(result).toEqual("first, second");
        });
    });
    describe("call without a parameter given", () => {
        it('should return an empty string', () => {
            const result = StringUtils.getCommaAndSpaceSeparated();
            expect(result).toBe("");
        });
    })
});
