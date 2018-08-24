export class User {
    username?: string;
    password?: string;
    enabled?: boolean;
    roles?: string[];

    constructor(username?: string, password?: string, enabled?: boolean, roles?: string[]) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

}
