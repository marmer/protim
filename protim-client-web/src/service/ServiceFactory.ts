import RestClientPermissionService from "./Permissions/RestClientPermissionService";

export default class ServiceFactory {
    static getPermissionService = () => new RestClientPermissionService();
}