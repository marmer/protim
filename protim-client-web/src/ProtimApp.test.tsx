import {mount} from "enzyme";
import React from 'react';
import {MemoryRouter} from "react-router";
import ProtimApp from './ProtimApp';

// The prefix "mock" as well as storing the mock within a variable is important. Without jest won't mock anything
const mockTimeTrackingView = <div id="TimetrackingView"/>;
jest.mock("./ui/TimetrackingView", () => () => mockTimeTrackingView);

// The prefix "mock" as well as storing the mock within a variable is important. Without jest won't mock anything
const mockUsermanagementView = <div id="UsermanagementView"/>;
jest.mock("./ui/UsermanagementView", () => () => mockUsermanagementView);

// The prefix "mock" as well as storing the mock within a variable is important. Without jest won't mock anything
const mockHeaderView = <div id="HeaderView"/>;
jest.mock("./ui/HeaderView", () => () => mockHeaderView);

// TODO: marmer 04.09.2018 add tests for paths with parameters (like usernames or days)

describe("ProtimApp", () => {
    describe("path for timetracking called", () => {
        const path = '/timetracking';
        it('should render the timetracking view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#TimetrackingView").exists()).toBe(true)
        });

        it('should render the header view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#HeaderView").exists()).toBe(true)
        });

        it('should not render the usermanagement view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);
            expect(wrapper.find("#UsermanagementView").exists()).toBe(false)
        })
    });

    describe("path for user called", () => {
        const path = '/user';
        it('should not render the timetracking view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#TimetrackingView").exists()).toBe(false)
        });

        it('should render the header view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#HeaderView").exists()).toBe(true)
        });

        it('should render the usermanavement view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#UsermanagementView").exists()).toBe(true)
        })
    });
    describe("basepath called", () => {
        const path = '/';
        it('should not render the timetracking view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#TimetrackingView").exists()).toBe(false)
        });

        it('should render the header view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#HeaderView").exists()).toBe(true)
        });

        it('should render the usermanavement view', () => {
            const wrapper = mount(
                <MemoryRouter initialEntries={[path]}>
                    <ProtimApp/>
                </MemoryRouter>);

            expect(wrapper.find("#UsermanagementView").exists()).toBe(true)
        })
    });

});
