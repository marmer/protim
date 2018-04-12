/// <reference path="../../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';
import {TimeManagementView} from './TimeManagementView';
import {SystemTimeService} from '../service/SystemTimeService';
import {BookingDayView} from "./BookingDayView";
import {DateTimeService} from "../service/DateTimeService";
import fn = jest.fn;

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<TimeManagementView/>, div);
});

describe(`<${TimeManagementView.name} />`, () => {
    let tree: ShallowWrapper;
    let today: Date = new Date();
    let nextDay: Date = new Date();
    let lastDay: Date = new Date();
    let nowMock: jest.Mock;

    beforeEach(() => {
        today.setFullYear(1985, 1, 2);
        nextDay.setFullYear(1985, 1, 1);
        lastDay.setFullYear(1985, 1, 3);

        nowMock = fn(() => {
            return today;
        });
        SystemTimeService.now = nowMock;
        tree = shallow(<TimeManagementView/>);
    });

    describe(BookingDayView.name, () => {
        let bookingDayView: ShallowWrapper;
        beforeEach(() => {
            bookingDayView = findBookingDayView();
        });

        it('should exist', () => {
            expect(bookingDayView).toExist();
        });

        it('should be for today', () => {
            expect(bookingDayView.prop('day')).toEqual(today);
        });

    });

    function findBookingDayView() {
        return tree.find('BookingDayView');
    }

    describe("button to move a day forward in time", () => {
        let dayForwardButton: ShallowWrapper;
        beforeEach(() => {
            dayForwardButton = tree.find("button.button-next-day");
        });

        it("should exist", () => {
            expect(dayForwardButton).toExist();
        });

        it("should show an appropriate text", () => {
            expect(dayForwardButton).toHaveText(">");
        });

        it("should go to the next day on click", () => {
            // preparation
            DateTimeService.dayAfter = fn((day) => {
                return day === today ? nextDay : null;
            });

            // execution
            dayForwardButton.simulate("click");

            // assertion
            let bookingDayView = findBookingDayView();
            expect(bookingDayView.prop("day")).toEqual(nextDay);
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

        it("should show an appropriate text", () => {
            expect(dayBackButton).toHaveText("<");
        });

        it("should go to the last day on click", () => {
            // preparation
            DateTimeService.dayBefore = fn((day) => {
                return day === today ? lastDay : null;
            });

            // execution
            dayBackButton.simulate("click");

            // assertion
            let bookingDayView = findBookingDayView();
            console.debug(bookingDayView.prop("day"))
            expect(bookingDayView.prop("day")).toEqual(lastDay);
        });
    });
});
