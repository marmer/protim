import * as React from 'react';
import {BookingDayView} from './BookingDayView';
import {SystemTimeService} from '../service/SystemTimeService';
import {DateTimeService} from '../service/DateTimeService';
import * as moment from 'moment';

export class TimeManagement {
    public bookingDay: Date;

}
export interface TimeManagementViewProps {
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
        this.state = {
            bookingDay: SystemTimeService.today()
        };
    }

    render(): React.ReactNode {

        return (
            <div className="container">
                <div className="row">
                    <button
                        className="button button-last-day col-sm"
                        onClick={() => this.switchToLastDay()}
                    >
                        {'<'}
                    </button>
                    <input
                        type="date"
                        name="bookingDay"
                        value={moment(this.state.bookingDay).format('YYYY-MM-DD')}
                        onChange={(event) => {
                            this.switchToDay(event.target.valueAsDate);
                        }}
                    />
                    <button
                        className="button button-next-day col-sm"
                        onClick={() => this.switchToNextDay()}
                    >
                        {'>'}
                    </button>
                </div>
                <BookingDayView day={this.state.bookingDay}/>
            </div>
        );
    }

    private switchToNextDay(): void {
        this.setState({
            bookingDay: DateTimeService.dayAfter(this.state.bookingDay)
        });
    }

    private switchToLastDay(): void {
        this.setState({
            bookingDay: DateTimeService.dayBefore(this.state.bookingDay)
        });
    }

    private switchToDay(date: Date) {
        this.setState({
            bookingDay: DateTimeService.dayBefore(date)
        });
    }

}
