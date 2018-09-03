import * as React from 'react';
import * as ReactDOM from "react-dom";
import BookingEntryView from "./BookingEntryView";

describe("Tests for:  BookingEntryView", () => {
    it('renders without crashing', () => {
        const tbody = document.createElement('tbody');
        ReactDOM.render(<BookingEntryView/>, tbody);
        ReactDOM.unmountComponentAtNode(tbody);
    });
});
