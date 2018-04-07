import * as React from 'react';
import {TimeManagement} from "../model/TimeManagement";
import {BookingDayView} from "./BookingDayView";

export interface TimeManagementViewProps {
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <BookingDayView date={this.todayAsIsoString()}/>
        );
    }

    today(): Date {
        const now = new Date();
        now.setHours(0, 0, 0, 0);
        return now;
    }

    todayAsIsoString(): String {
        const todayDate = this.today();
        return todayDate.getFullYear() + "-" + todayDate.getMonth() + "-" + todayDate.getDay();
    }
}
