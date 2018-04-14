import * as React from 'react';
import {BookingDay} from '../model/BookingDay';
import * as moment from 'moment';

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
        return (
            <div className="container">
                <input
                    type="date"
                    name="bookingDay"
                    value={moment(this.props.day).format('YYYY-MM-DD')}
                    disabled={true}
                />
                <div className="row form-group align-items-center">
                    {/*start time*/}
                    <input
                        type="time"
                        className="col-1 form-control starttime"
                        placeholder="1d 2h 25m"
                    />
                    {/*duration*/}
                    <input
                        type="time"
                        className="col-1 form-control"
                        placeholder="1d 2h 25m"
                    />
                    {/*description*/}
                    <input type="text" className="col form-control" placeholder="What has been done"/>
                    {/*notes*/}
                    <input type="text" className="col form-control" placeholder="personal notes"/>
                    {/*assignment ticket system*/}
                    <select className="col-2 form-control">
                        <option>None</option>
                        <option>Jira</option>
                    </select>
                    {/*is booked button*/}
                    <button type="col-1 button" className="btn">X</button>
                </div>
            </div>
        );
        // TODO this component BookingDayView needs some sense
    }
}