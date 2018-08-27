import * as React from 'react';
import {User} from "../model/usermanagement/User";
import {RestClient} from "../service/rest/RestClient";
import {StringUtils} from "../service/StringUtils";

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
        RestClient.getJson("https://localhost/api/v1/usermanagement/users/" + props.username)
            .then(value => this.setState({user: value}));
    }

    render() {
        return <div className="card">
            <div className="card-body">
                <h5 className="card-title">
                    <span className="text-muted">User</span>
                </h5>
                <ul className="list-group">
                    <li className="list-group-item">
                        {this.username()}
                    </li>
                    {this.listElement("Username", this.state.user.username)}
                    {this.listElement("Username", StringUtils.getCommaAndSpaceSeparated(this.state.user.roles))}
                    {this.listElement("Enabled", "" + this.state.user.enabled)}
                </ul>
            </div>
        </div>
    }

    private listElement(label: string, value?: string | null) {
        return <li className="list-group-item">
            <label title={"Username"}>
                <small className="text-muted">{label}</small>
                {/*<h6 className="my-0">{value}</h6>*/}
                <input
                    type="text"
                    className="form-control"
                    placeholder="Input group example"
                    aria-label="Input group example"
                    aria-describedby="btnGroupAddon"
                    value={value == null ? "" : value}
                />
            </label>
        </li>;
    }

    private username() {
        return <div className="input-group">
            <div className="input-group-prepend">
                <div className="input-group-text" id="btnGroupAddon">Username:</div>
            </div>
            <input
                type="text"
                className="form-control"
                placeholder="Input group example"
                aria-label="Input group example"
                aria-describedby="btnGroupAddon"
            />
        </div>
    }
}