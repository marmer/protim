import PermissionService from "./PermissionService";

export default class RestClientPermissionService implements PermissionService {
    isAdmin(): boolean {
        return false;
    }
}