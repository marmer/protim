/// <reference path="../../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';
import {TimeManagementView} from './TimeManagementView';
import {SystemTimeService} from '../service/SystemTimeService';
import fn = jest.fn;

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<TimeManagementView/>, div);
});

describe(`<${TimeManagementView.name} />`, () => {
    let tree: ShallowWrapper;
    let today: Date;
    let nowMock: jest.Mock;

    beforeAll(() => {
        today = new Date();
        today.setFullYear(1985, 1, 2);
        nowMock = fn(() => {
            return today;
        });
        SystemTimeService.now = nowMock;
        tree = shallow(<TimeManagementView/>);
    });

    it('should display a view to book working hours', () => {
        expect(tree.find('BookingDayView')).toExist();
    });

    it('should display the booking view for the current date by default', () => {
        expect(tree.find('BookingDayView')
            .prop('day'))
            .toEqual(today);
    });

    it("schould contain a button to go the day after", () => {

        // const mock = jest.fn((args1, args2) => args1 === "a" ? "a" : "b")
        expect(tree.find("button.button-next-day")).toHaveText(">");
    });

    it("schould contain a button to go the day before", () => {
        expect(tree.find("button.button-last-day")).toHaveText("<");
    });

});