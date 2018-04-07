/// <reference path="../../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';
import {TimeManagementView} from './TimeManagementView';
import {Iso8601Service} from "../services/Iso8601Service";

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<TimeManagementView/>, div);
});

function today(): Date {
    const now = new Date();
    now.setHours(0, 0, 0, 0);
    return now;
}

function todayAsIsoString(): String {
    const todayDate = today();
    return todayDate.getFullYear() + "-" + todayDate.getMonth() + "-" + todayDate.getDay();
}

describe(`<${TimeManagementView.name} />`, () => {
    let tree: ShallowWrapper;

    let iso8601ServiceMock = jest.mock(Iso8601Service.name);
    iso8601ServiceMock.mock(Iso8601Service.today.name, () => {
        return "1985-01-02"
    });
    // TODO: MarMer 07.04.2018 Get out how to mock the function "today" correctly!

    beforeAll(() => {
        tree = shallow(<TimeManagementView/>);
    });

    it('should display a view to book working hours', () => {
        expect(tree.find('BookingDayView')).toExist();
    });

    it('should display the booking view for the current date by default', () => {
        expect(tree.find('BookingDayView')
            .prop('date'))
            .toEqual(todayAsIsoString());
    });

});