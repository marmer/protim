import PermissionService from "./Permissions/PermissionService";
import ServiceFactory from "./ServiceFactory";

describe("Tests for: ServiceFactory", () => {
    describe("call for PermissionService", () => {
        it('should return an instance of permission service', () => {
            const result: PermissionService = ServiceFactory.getPermissionService();
            expect(result).toBeDefined();
        });
    });
});