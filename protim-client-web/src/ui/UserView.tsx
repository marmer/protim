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
                    {this.listElement("Username", this.state.user.username)}
                    {this.listElement("Username", this.getCommaSeparatedRoles(this.state.user.roles))}
                    {this.listElement("Enabled", "" + this.state.user.enabled)}
                </ul>
            </div>
        </div>
    }

    private listElement(label: string = "Username", value?: string | null) {
        return <li className="list-group-item">
            <label title={"Username"}>
                <small className="text-muted">{label}</small>
                <h6 className="my-0">{value}</h6>
            </label>
        </li>;
    }

    private getCommaSeparatedRoles(list?: string[]): string | null {
        return !list
            ? null
            : list.join(", ");
    }
}