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

    withEnabled(enabled: boolean) {
        // TODO: marmer 28.08.2018 Not done here. The coles should be cloned as well.
        return new User(this.username, this.password, enabled, this.roles);
    }
}
