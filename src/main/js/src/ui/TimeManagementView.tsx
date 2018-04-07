import * as React from 'react';
import {TimeManagement} from "../model/TimeManagement";
import {BookingDayView} from "./BookingDayView";
import {Iso8601Service} from "../services/Iso8601Service";

export interface TimeManagementViewProps {
}

export class TimeManagementView extends React.Component<TimeManagementViewProps, TimeManagement> {
    constructor(props: TimeManagementViewProps) {
        super(props);
    }

    render(): React.ReactNode {
        return (
            <BookingDayView date={Iso8601Service.today()}/>
        );
    }
}
