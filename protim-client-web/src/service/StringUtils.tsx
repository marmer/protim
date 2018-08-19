export class StringUtils {
    public static getCommaSeparatedRoles(list?: string[]): string | null {
        return !list
            ? null
            : list.join(", ");
    }
}