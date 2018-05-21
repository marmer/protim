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
        return (
            <div>
                <div className="table-responsive-md">
                    <table className="table table-sm">
                        <thead>
                        <tr>
                            <th scope="col">StartTime</th>
                            <th scope="col">Duration</th>
                            <th scope="col">Description</th>
                            <th scope="col">Notes</th>
                            <th scope="col">Ticket</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input type="time" placeholder="e.g. 09:25"/>
                            </td>
                            <td>
                                <input type="time" placeholder="e.g. 01:15"/>
                            </td>
                            <td>
                                <textarea rows={1} placeholder="What has been done"/>
                            </td>
                            <td>
                                <textarea rows={1} placeholder="personal notes"/>
                            </td>
                            <td>
                                <textarea rows={1} placeholder="TICKET-123"/>
                            </td>
                            <td>
                                <div className="btn-group">
                                    <button type="button" className="btn btn-primary" title="synced">
                                        <i className="fas fa-lock"/>
                                    </button>

                                    <button type="button" className="btn btn-primary" title="synced">
                                        <i className="fas fa-lock-open"/>
                                    </button>
                                    <button type="button" className="btn btn-primary" title="sync">
                                        <i className="fas fa-sync-alt"/>
                                    </button>
                                    <button type="button" className="btn btn-danger" title="remove">
                                        <i className="fas fa-trash-alt"/>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
            </div>
        );
    }
}