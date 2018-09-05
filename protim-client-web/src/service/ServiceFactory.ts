import PermissionService from "./Permissions/PermissionService";
import RestClientPermissionService from "./Permissions/RestClientPermissionService";

export default class ServiceFactory {
    static getPermissionService(): PermissionService {
        return new RestClientPermissionService();
    }
}