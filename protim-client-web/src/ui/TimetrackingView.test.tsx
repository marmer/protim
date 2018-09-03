import * as React from 'react';
import * as ReactDOM from "react-dom";
import TimetrackingView from "./TimetrackingView";

describe("Tests for:  TimetrackingView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<TimetrackingView/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
