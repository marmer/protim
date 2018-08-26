import React from 'react';
import {List} from "../model/UserList";
import {UserListEntry} from "../model/UserListEntry";
import {User} from "../model/usermanagement/User";
import {RestClient} from "../service/rest/RestClient";
import {UserView} from "./UserView";

export interface UsermanagementViewProps {
}

export interface UsermanagementViewState {
    userlist: List<UserListEntry>
    currentUser?: User;
}

export class UsermanagementView extends React.Component<UsermanagementViewProps, UsermanagementViewState> {
    constructor(props: UsermanagementViewProps) {
        super(props);
        this.state = {
            userlist: new List([])
        };
        RestClient.getJson("https://localhost/api/v1/usermanagement")
            .then(value => this.setState({
                userlist: value
            }));
    }

    render(): React.ReactNode {
        return (
            <div>
                <h1>Users</h1>
                <div className="list-group">
                    {this.usernames()}
                </div>
                <UserView username="admin"/>
            </div>
        );
    }

    private usernames() {
        return this.state.userlist.entries.map(value => {
            return <button
                type="button"
                className="list-group-item list-group-item-action"
                key={value.username}
            >
                value.username
            </button>
        })
    }
}