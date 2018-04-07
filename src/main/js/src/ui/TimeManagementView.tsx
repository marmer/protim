import * as React from 'react';
import {TimeManagement} from './TimeManagement';

export interface TimeManagementViewProps {
    date: String;
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
        this.state = new TimeManagement(props.date);
    }

    render(): React.ReactNode {
        return (
            <div>DayView with an entry: {this.state.bookingDayEntry}</div>
        );
    }
}