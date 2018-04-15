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
            <div>
                <input
                    type="date"
                    name="bookingDay"
                    value={moment(this.props.day).format('YYYY-MM-DD')}
                    disabled={true}
                />
                <h1>Option 1 - Grid Layout</h1>
                <div className="container">
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
                        <textarea className="col form-control" placeholder="What has been done"/>
                        {/*notes*/}
                        <textarea className="col form-control" placeholder="personal notes"/>
                        {/*assignment ticket system*/}
                        <select className="col-2 form-control">
                            <option>None</option>
                            <option>Jira</option>
                        </select>
                        {/*is booked button*/}
                        <button type="col-1 button" className="btn">X</button>
                    </div>
                </div>
                <h1>Option 2 - Option Input Group</h1>
                <div className="input-group">
                    {/*start time*/}
                    <input
                        type="time"
                        className="input-group-prepend starttime"
                        placeholder="1d 2h 25m"
                    />
                    {/*duration*/}
                    <input
                        type="time"
                        className=""
                    />

                    {/*description*/}
                    <textarea className="" placeholder="What has been done"/>
                    {/*notes*/}
                    <textarea className="" placeholder="personal notes"/>
                    {/*assignment ticket system*/}
                    <select className="">
                        <option>None</option>
                        <option>Jira</option>
                    </select>
                    {/*is booked button*/}
                    <button type="button" className="btn input-group-append">X</button>
                </div>

                <h1>Option 3 - form with labels in card group</h1>
                <div className="card-group">
                    <div className="card w-50">
                        <form className="card-body">
                    <div className="form-group row">
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            StartTime
                        </label>
                        <input
                            type="time"
                            className="col-sm"
                            placeholder="1d 2h 25m"
                        />
                    </div>
                    <div className="form-group row">
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            Duration
                        </label>
                        <input
                            type="time"
                            className="col"
                        />
                    </div>
                    <div className="form-group row">
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            Description
                        </label>
                        <textarea className="col" placeholder="What has been done"/>
                    </div>
                    <div className="form-group row">
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            Notes
                        </label>
                        <textarea className="col-sm" placeholder="personal notes"/>
                    </div>
                    <div className="form-group row">
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            Assignment/Ticket system
                        </label>
                        <select className="col-sm">
                            <option>None</option>
                            <option>Jira</option>
                        </select>
                    </div>
                    <div className="form-group row"> {/*is booked button*/}
                        <label
                            className="col-sm-4
                             col-form-label"
                        >
                            Booked
                        </label>
                        <button type="button" className="btn col-sm">X</button>
                    </div>
                </form>
                    </div>
                    <div className="card w-50">
                        <form className="card-body">
                            <div className="form-group row">
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    StartTime
                                </label>
                                <input
                                    type="time"
                                    className="col-sm"
                                    placeholder="1d 2h 25m"
                                />
                            </div>
                            <div className="form-group row">
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    Duration
                                </label>
                                <input
                                    type="time"
                                    className="col"
                                />
                            </div>
                            <div className="form-group row">
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    Description
                                </label>
                                <textarea className="col" placeholder="What has been done"/>
                            </div>
                            <div className="form-group row">
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    Notes
                                </label>
                                <textarea className="col-sm" placeholder="personal notes"/>
                            </div>
                            <div className="form-group row">
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    Assignment/Ticket system
                                </label>
                                <select className="col-sm">
                                    <option>None</option>
                                    <option>Jira</option>
                                </select>
                            </div>
                            <div className="form-group row"> {/*is booked button*/}
                                <label
                                    className="col-sm-4
                             col-form-label"
                                >
                                    Booked
                                </label>
                                <button type="button" className="btn col-sm">X</button>
                            </div>
                        </form>
                    </div>
                </div>
                <h1>Option 4 - Responsive Table</h1>
            </div>
        );
        // TODO this component BookingDayView needs some sense
    }
}