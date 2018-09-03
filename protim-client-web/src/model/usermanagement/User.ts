export default class User {
    username: string;
    password: string;
    enabled: boolean;
    roles: string[];

    constructor(username: string = "", password: string = "", enabled: boolean = true, roles: string[] = []) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    clone(): User {
        return new User(this.username, this.password, this.enabled, Object.assign([], this.roles));
    }

    isAdmin(): boolean {
        return this.hasRole("ADMIN");
    }

    isUser(): boolean {
        return this.hasRole("USER");
    }

    private hasRole(roleName: string) {
        return (!this.roles || this.roles.findIndex(value => value === roleName) !== -1);
    }
}
