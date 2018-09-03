import User from "./User";

describe("Tests for: User", () => {
    let user: User;
    beforeEach(() => {
        user = new User("un", "pw", true, ["r1", "r2"]);
    });
    describe("clone", () => {
        it("should return clone", () => {
            expect(user.clone()).toEqual(new User("un", "pw", true, ["r1", "r2"]))
        });
    });
    describe("isUser", () => {
        describe("user with user role", () => {

            it("should return true", () => {
                user.roles = ["USER"];
                expect(user.isUser()).toBe(true)
            });
        });
        describe("user with other role", () => {
            it("should return false", () => {
                user.roles = ["ADMIN"];
                expect(user.isUser()).toBe(false)
            });
        });
        describe("user with lots of rules admin", () => {
            it("should return false", () => {
                user.roles = ["USER", "ADMIN"];
                expect(user.isUser()).toBe(true)
            });
        });
    });
    describe("isAdmin", () => {
        describe("user with admin role", () => {

            it("should return true", () => {
                user.roles = ["ADMIN"];
                expect(user.isAdmin()).toBe(true)
            });
            describe("user with other role", () => {
                it("should return false", () => {
                    user.roles = ["USER"];
                    expect(user.isAdmin()).toBe(false)
                });
            });
            describe("user with lots of rules admin", () => {
                it("should return false", () => {
                    user.roles = ["USER", "ADMIN"];
                    expect(user.isAdmin()).toBe(true)
                });
            });

        });
    });

});
