import * as React from 'react';
import {BookingDay} from '../model/BookingDay';

export interface BookingDayViewProps {
    day: Date
}

export class BookingDayView extends React.Component<BookingDayViewProps, BookingDay> {
    constructor(props: BookingDayViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        // TODO this component BookingDayView needs some sense
        let date = this.props.day;
        const dateString = date.getDay() + "." + date.getMonth() + "." + date.getFullYear();
        return (
            <div>
                <p>Date: {dateString}</p>
            </div>
        );
    }
}