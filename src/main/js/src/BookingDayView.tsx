import * as React from 'react';
import {BookingDay} from './BookingDay';

export class DayViewProps {
}

export class BookingDayView extends React.Component<DayViewProps, BookingDay> {

    constructor(props: DayViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <div>DayView</div>
        );
    }
}