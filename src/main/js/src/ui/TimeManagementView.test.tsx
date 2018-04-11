/// <reference path="../../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';
import {TimeManagementView} from './TimeManagementView';
import {SystemTimeService} from '../service/SystemTimeService';
import {BookingDayView} from "./BookingDayView";
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

    describe(BookingDayView.name, () => {
        let bookingDayView: ShallowWrapper;
        beforeEach(() => {
            bookingDayView = tree.find('BookingDayView');
        });

        it('should exist', () => {
            expect(bookingDayView).toExist();
        });

        it('should be for today', () => {
            expect(bookingDayView.prop('day')).toEqual(today);
        });

    });

    describe("button to move a day forward in time", () => {
        let dayForwardButton: ShallowWrapper;
        beforeEach(() => {
            dayForwardButton = tree.find("button.button-next-day");
        });

        it("should exist", () => {
            expect(dayForwardButton).toExist();
        });

        it("should contain a button to go the day after", () => {
            // const mock = jest.fn((args1, args2) => args1 === "a" ? "a" : "b")

            expect(dayForwardButton).toHaveText(">");
        });

    });

    describe("button to move a day back in time", () => {
        let dayBackButton: ShallowWrapper;
        beforeEach(() => {
            dayBackButton = tree.find("button.button-last-day");
        });

        it("should exist", () => {
            expect(dayBackButton).toExist();
        });

        it("should contain a button to go the day before", () => {

            expect(dayBackButton).toHaveText("<");
        });
    });
});
