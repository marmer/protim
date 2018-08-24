import React, {Component} from 'react';
import {BookingDay} from '../model/BookingDay';
import {BookingEntryView} from './BookingEntryView';

export interface BookingDayViewState {
    bookingDay: BookingDay;
}

export interface BookingDayViewProps {
    day: Date;
}

export class BookingDayView extends Component<BookingDayViewProps, BookingDayViewState> {
    constructor(props: BookingDayViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <div>
                <div className="table-responsive-md">
                    <table className="table table-sm">
                        <thead>
                        <tr>
                            {/*// TODO: marmer 21.05.2018 Lets get the heters from the entry view.*/}
                            <th scope="col">StartTime</th>
                            <th scope="col">Duration</th>
                            <th scope="col">Description</th>
                            <th scope="col">Notes</th>
                            <th scope="col">Ticket</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <BookingEntryView/>
                        </tbody>
                    </table>
                    </div>
            </div>
        );
    }
}