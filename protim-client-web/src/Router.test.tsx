import {mount} from "enzyme";
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {MemoryRouter} from "react-router";
import Router from './Router';
import HeaderView from "./ui/HeaderView";
import TimetrackingView from "./ui/TimetrackingView";

describe("Router", () => {

    it('renders without crashing', () => {
        const div = document.createElement('div');
        ReactDOM.render(<Router/>, div);
        ReactDOM.unmountComponentAtNode(div);
    });

    describe("Userpath called", () => {
        it('should render the user view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={['/protim/user']}>
                    <Router/>
                </MemoryRouter>);

            // TODO: marmer 03.09.2018 GO on here. This tutorial may help:
            // TODO: https://medium.com/@antonybudianto/react-router-testing-with-jest-and-enzyme-17294fefd303

            const timetrackingWrapper = wrapper.find(TimetrackingView);
            expect(timetrackingWrapper.exists()).toBe(true)
        });

        it('should render the header view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={['/user']}>
                    <Router/>
                </MemoryRouter>);

            expect(wrapper.find(HeaderView).exists()).toBe(true)
        })
    });
});
