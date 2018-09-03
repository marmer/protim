import System from "./System";

/**
 * Maybe some kind of Ticket system.
 */
export default class Assignment {
    systemId: string;
    system: System;

    constructor(systemId: string, system: System) {
        this.systemId = systemId;
        this.system = system;
    }
}