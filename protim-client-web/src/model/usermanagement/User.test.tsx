import {User} from "./User";

describe("Tests for:  User", () => {
    let user: User;
    beforeEach(() => user = new User(
        "username",
        "password",
        true,
        ["USER, ADMIN"]));
    describe("withEnabledCalled", () => {
        it("should return a clone with enabled set to the new value", () => {
            const clonedUser = user.withEnabled(false);
            expect(clonedUser).toStrictEqual(new User(
                user.username,
                user.password,
                false,
                user.roles))
            // TODO: marmer 28.08.2018 Not done here
        });
    });
});