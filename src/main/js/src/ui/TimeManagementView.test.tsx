/// <reference path="../../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {shallow, ShallowWrapper} from 'enzyme';
import {TimeManagementView} from './TimeManagementView';
import {Iso8601Service} from '../service/Iso8601Service';
import mock = jest.mock;
import fn = jest.fn;

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<TimeManagementView/>, div);
});

describe(`<${TimeManagementView.name} />`, () => { 
    let tree: ShallowWrapper;
    const todayAsIsoDate = '1985-01-02';

    mock('../service/' + Iso8601Service.name);
    Iso8601Service.today = fn(() => {
        return todayAsIsoDate;
    });

    beforeAll(() => {
        tree = shallow(<TimeManagementView/>);
    });

    it('should display a view to book working hours', () => {
        expect(tree.find('BookingDayView')).toExist();
    });

    it('should display the booking view for the current date by default', () => {
        expect(tree.find('BookingDayView')
            .prop('date'))
            .toEqual(todayAsIsoDate);
    });

});