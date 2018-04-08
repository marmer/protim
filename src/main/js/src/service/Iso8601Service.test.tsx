import {Iso8601Service} from "./Iso8601Service";

describe('Tests for: ' + Iso8601Service.name, function () {
    // TODO: MarMer 07.04.2018 Better use something mockable.
    let now = new Date();

    it("should return an iso date of the now", () => {
        expect(Iso8601Service.today()).toEqual(now.getFullYear() + "-" + now.getMonth() + "-" + now.getDay());
    });
});