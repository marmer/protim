import * as React from 'react';
import * as ReactDOM from "react-dom";
import UsermanagementView from "./UsermanagementView";

describe("Tests for:  UsermanagementView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<UsermanagementView/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
