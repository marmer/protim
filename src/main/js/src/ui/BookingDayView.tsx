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
        return (
            <div>
                <p>Date: {this.props.day.toISOString()}</p>
            </div>
        );
    }
}