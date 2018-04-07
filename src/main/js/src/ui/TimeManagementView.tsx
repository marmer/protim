import * as React from 'react';
import {BookingDay} from './BookingDay';

export interface DayViewProps {
    date: String;
}

export class TimeManagementView extends React.Component<DayViewProps, BookingDay> {
    constructor(props: DayViewProps) {
        super(props);
        this.state = new BookingDay(props.date);
    }

    render(): React.ReactNode {
        return (
            <div>DayView with an entry: {this.state.bookingDayEntry}</div>
        );
    }
}