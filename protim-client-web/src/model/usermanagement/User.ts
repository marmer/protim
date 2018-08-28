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

    clone(): User {
        // TODO: marmer 28.08.2018 don't forget to clone the array
        return new User(this.username, this.password, this.enabled, this.roles);
    }

    isAdmin(): boolean {
        return this.hasRole("ADMIN");
    }

    isUser(): boolean {
        return this.hasRole("USER");
    }

    private hasRole(roleName: string) {
        return !(!this.roles || this.roles.findIndex(value => value === roleName) !== -1);
    }
}
