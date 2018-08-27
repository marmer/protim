import React from 'react';
import {List} from "../model/UserList";
import {UserListEntry} from "../model/UserListEntry";
import {RestClient} from "../service/rest/RestClient";
import {UserView} from "./UserView";

export interface UsermanagementViewProps {
}

export interface UsermanagementViewState {
    userlist: List<UserListEntry>
    selectedUser?: string;
}

export class UsermanagementView extends React.Component<UsermanagementViewProps, UsermanagementViewState> {
    constructor(props: UsermanagementViewProps) {
        super(props);
        this.state = {
            userlist: new List([])
        };
        RestClient.getJson("https://localhost/api/v1/usermanagement/users")
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
                {this.userView()}
            </div>
        );
    }

    private userView() {
        const selectedUsername = this.state.selectedUser;
        return selectedUsername == null ? null : <UserView username={selectedUsername}/>;
    }

    private usernames() {
        return this.state.userlist.entries.map(value => {
            return <button
                type="button"
                className="list-group-item list-group-item-action"
                key={value.username}
                onClick={() => this.setUserDetails(value.username)}
            >
                {value.username}
            </button>
        })
    }

    private setUserDetails(username: string) {
        return this.setState({
            selectedUser: username
        });
    }
}