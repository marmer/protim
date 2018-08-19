import * as React from 'react';
import {User} from "../model/usermanagement/User";
import {RestClient} from "../service/rest/RestClient";

export interface UsermanagementViewState {
    user: User;
}

export interface UsermanagementViewProps {
    username: string
}

export class UserView extends React.Component<UsermanagementViewProps, UsermanagementViewState> {
    constructor(props: UsermanagementViewProps) {
        super(props);
        this.state = {
            user: new User()
        };
        RestClient.getJson("https://localhost/api/v1/usermanagement/users/admin")
            .then(value => this.setState({user: value}));
    }

    sleep(time: number) {
        // TODO: marmer 15.08.2018 remove me
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    render() {
        return <div className="card">
            <div className="card-body">
                <h5 className="card-title">
                    <span className="text-muted">User</span>
                </h5>
                <ul className="list-group">
                    <li className="list-group-item">
                        <label title="Username">
                            <h6 className="my-0">{this.state.user.username}</h6>
                            <small className="text-muted">Username</small>
                        </label>
                    </li>
                    <li className="list-group-item">
                        <label title="Roles">
                            <h6 className="my-0">{this.getCommaSeparatedRoles(this.state.user.roles)}</h6>
                            <small className="text-muted">Roles</small>
                        </label>
                    </li>
                    <li className="list-group-item">
                        <label title="Enabled">
                            <h6 className="my-0">{"" + this.state.user.enabled}</h6>
                            <small className="text-muted">Enabled</small>
                        </label>
                    </li>
                </ul>
                </div>
            </div>
    }

    private getCommaSeparatedRoles(list?: string[]): string | null {
        return !list
            ? null
            : list.join(", ");
    }
}