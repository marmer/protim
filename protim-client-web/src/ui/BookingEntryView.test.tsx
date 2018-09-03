import * as React from 'react';
import * as ReactDOM from "react-dom";
import BookingEntryView from "./BookingEntryView";

describe("Tests for:  BookingEntryView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<BookingEntryView/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
