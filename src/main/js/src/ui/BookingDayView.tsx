import * as React from 'react';
import {BookingDay} from '../model/BookingDay';

export interface BookingDayViewState {
    bookingDay: BookingDay;
}

export interface BookingDayViewProps {
    day: Date;
}

export class BookingDayView extends React.Component<BookingDayViewProps, BookingDayViewState> {
    constructor(props: BookingDayViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        // TODO this component BookingDayView needs some sense
        return (
            <div className="container">
                <p>Date: {this.props.day.toISOString()}</p>
                <div className="row">
                    <div className="col">
                        1 of 2
                    </div>
                    <div className="col">
                        2 of 2
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        1 of 3
                    </div>
                    <div className="col">
                        2 of 3
                    </div>
                    <div className="col">
                        3 of 3
                    </div>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleFormControlSelect1">Example select</label>
                    <select className="form-control" id="exampleFormControlSelect1">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>
                </div>
            </div>
        );
    }
}