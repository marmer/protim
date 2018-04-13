import {BookingDayView} from "./BookingDayView";
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<BookingDayView day={new Date(1985, 0, 2)}/>, div);
});

describe(`<${BookingDayView.name} />`, () => {

    let day: Date = new Date(1985, 0, 2);

    let tree: ShallowWrapper;

    beforeEach(() => {
        tree = shallow(<BookingDayView day={day}/>);
    });

    describe("no entry exists for the given day", () => {
        describe("an empty entry is shown", () => {
            it('should exist', () => {
                fail("some child " + tree);
            });
        });
    });
});
