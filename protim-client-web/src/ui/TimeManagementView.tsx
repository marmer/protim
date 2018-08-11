import * as moment from 'moment';
import * as React from 'react';
import {DateTimeService} from '../service/DateTimeService';
import {SystemTimeService} from '../service/SystemTimeService';
import {BookingDayView} from './BookingDayView';

export interface TimeManagement {
    bookingDay: Date;

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
            <div className="card">
                <div className="card-header text-center">
                    <button title="last" type="button" className="btn btn-link button-last-day">
                        <i className="fas fa-arrow-left" onClick={() => this.switchToLastDay()}/>
                    </button>
                    <input
                        type="date"
                        name="bookingDay"
                        value={moment(this.state.bookingDay).format('YYYY-MM-DD')}
                        onChange={(event) => {
                            this.switchToDay(event.target.valueAsDate);
                        }}
                    />
                    <button title="last" type="button" className="btn btn-link button-next-day">
                        <i className="fas fa-arrow-right" onClick={() => this.switchToNextDay()}/>
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
