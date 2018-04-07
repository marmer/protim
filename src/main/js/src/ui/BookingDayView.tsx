import * as React from 'react';
import {BookingDay} from '../model/BookingDay';

export interface BookingDayViewProps {
    date: String
}

export class BookingDayView extends React.Component<BookingDayViewProps, BookingDay> {
    constructor(props: BookingDayViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        // TODO this component BookingDayView needs some sense
        return (
            <div/>
        );
    }
}