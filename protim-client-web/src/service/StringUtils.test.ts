import {StringUtils} from "./StringUtils";

describe("List of Strings given", () => {
    it("Should conat the individual parts with a comma", () => {
        const result = StringUtils.getCommaAndSpaceSeparated(["first", "second"]);
        expect(result).toEqual("first, second");
    });
});