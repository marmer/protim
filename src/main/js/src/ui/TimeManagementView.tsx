import * as React from 'react';
import {BookingDayView} from './BookingDayView';
import {SystemTimeService} from "../service/SystemTimeService";
import {DateTimeService} from "../service/DateTimeService";

export class TimeManagement {
    public bookingDay: Date;

}
export interface TimeManagementViewProps {
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
        this.state = {
            bookingDay: SystemTimeService.now()
        };
    }

    render(): React.ReactNode {
        return (
            <div>
                <div>
                    <button className="button button-last-day" onClick={() => this.switchToLastDay()}>{"<"}</button>
                    <button className="button button-next-day" onClick={() => this.switchToNextDay()}>{">"}</button>
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
}
