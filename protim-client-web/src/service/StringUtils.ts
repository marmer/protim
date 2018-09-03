export class StringUtils {
    public static getCommaAndSpaceSeparated(list?: string[]): string | null {
        return !list
            ? null
            : list.join(", ");
    }
}