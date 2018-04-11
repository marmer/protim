import * as React from 'react';
import {TimeManagement} from '../model/TimeManagement';
import {BookingDayView} from './BookingDayView';
import {SystemTimeService} from "../service/SystemTimeService";

export interface TimeManagementViewProps {
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <div>
                <div>
                    <button className="button button-last-day">{"<"}</button>
                    <button className="button button-next-day">{">"}</button>
                </div>
                <BookingDayView day={SystemTimeService.now()}/>
            </div>
        );
    }
}
