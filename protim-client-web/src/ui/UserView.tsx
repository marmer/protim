import * as React from 'react';
import {User} from "../model/usermanagement/User";
import {RestClient} from "../service/rest/RestClient";
import {StringUtils} from "../service/StringUtils";

export interface UsermanagementViewState {
    user: User;
}

export interface UsermanagementViewProps {
    username?: string
}

export class UserView extends React.Component<UsermanagementViewProps, UsermanagementViewState> {
    constructor(props: UsermanagementViewProps) {
        super(props);

        this.state = {
            user: new User()
        };
    }

    componentWillUpdate(nextProps: Readonly<UsermanagementViewProps>) {
        if (nextProps.username !== this.props.username) {
            this.loadUserDetails(nextProps.username)
        }
    }

    render() {
        return this.props.username == null
            ? null
            : <div className="card">
                <div className="card-body">
                    <h5 className="card-title">
                        <span className="text-muted">User</span>
                    </h5>
                    <ul className="list-group">
                        {this.listElement("Username", this.state.user.username!)}

                        {this.listElement("Username", StringUtils.getCommaAndSpaceSeparated(this.state.user.roles))}

                        {<li className="list-group-item">
                            <label title={"Username"}>
                                <small className="text-muted">{"Enabled"}</small>
                                {/*<h6 className="my-0">{value}</h6>*/}
                                <input
                                    type="checkbox"
                                    className="form-control"
                                    placeholder="label"
                                    aria-label="label"
                                    aria-describedby="btnGroupAddon"
                                    checked={this.state.user.enabled}
                                    onClick={() => this.switchEnabled()}
                                />
                            </label>
                        </li>}
                    </ul>
                </div>
            </div>
    }

    private switchEnabled() {
        const {username, enabled, password, roles} = this.state.user;

        const newUser = new User(username, password, !enabled, roles);
        this.setState({user: newUser})
    }

    private loadUserDetails(name: string | undefined) {
        if (name) { // udnefined | "" | null | 0 | false
            RestClient.getJson("https://localhost/api/v1/usermanagement/users/" + name)
                .then(value => this.setState({user: value}));
        }
    }

    private listElement(label: string, value: string | null) {
        return <li className="list-group-item">
            <label title={"Username"}>
                <small className="text-muted">{label}</small>
                {/*<h6 className="my-0">{value}</h6>*/}
                <input
                    type="text"
                    className="form-control"
                    placeholder="label"
                    aria-label="label"
                    aria-describedby="btnGroupAddon"
                    value={value == null ? "" : value}
                />
            </label>
        </li>;
    }
}