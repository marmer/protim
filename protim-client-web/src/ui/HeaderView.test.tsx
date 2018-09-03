import * as React from 'react';
import * as ReactDOM from "react-dom";
import HeaderView from "./HeaderView"

describe("Tests for:  HeaderView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<HeaderView/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
