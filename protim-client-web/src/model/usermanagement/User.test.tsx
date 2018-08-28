import {User} from "./User";

describe("Tests for: User", () => {
    describe("clone", () => {
        it("should return clone", () => {
            const user = new User("un", "pw", true, ["r1", "r2"]);
            expect(user.clone()).toStrictEqual(new User("un", "pw", true, ["r1", "r2"]));
        });
    });
});
