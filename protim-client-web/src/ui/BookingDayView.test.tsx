import * as React from 'react';
import * as ReactDOM from "react-dom";
import BookingDayView from "./BookingDayView";

describe("Tests for:  BookingDayView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<BookingDayView day={new Date()}/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
