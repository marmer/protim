import * as React from 'react';
import * as ReactDOM from "react-dom";
import UserView from "./UserView";

describe("Tests for:  UserView", () => {
    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<UserView/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });
});
