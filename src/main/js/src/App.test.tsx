/// <reference path="../node_modules/jest-enzyme/lib/index.d.ts" />

import * as React from 'react';
import * as ReactDOM from 'react-dom';
import App from './App';
import {shallow, ShallowWrapper} from 'enzyme';

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App/>, div);
});

describe(`<${App.name} />`, () => {
    let tree: ShallowWrapper;
    beforeAll(() => {
        tree = shallow(<App/>);
    });

    it('View with current day is displayed', () => {
        expect(tree.find('BookingDayView')).toExist();
    });

    it('An important day was set for mankind', () => {
        expect(tree.find('BookingDayView').prop('date')).toEqual('1979-10-12');
    });

    it('snapshot', () => {
        expect(tree).toMatchSnapshot();
    });
});