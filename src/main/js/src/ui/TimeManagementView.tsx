import * as React from 'react';
import {TimeManagement} from '../model/TimeManagement';
import {BookingDayView} from './BookingDayView';
import {DateTimeService} from "../service/DateTimeService";

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
                    <button id="lastDay">{"<"}</button>
                    <button id="nextDay">{">"}</button>
                </div>
                <BookingDayView day={DateTimeService.now()}/>
            </div>
        );
    }
}
