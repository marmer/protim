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
        this.loadUserDetails();
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
                        {this.listElement("Username", this.state.user.username)}
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
                                    onClick={this.switchEnabled}
                                />
                            </label>
                        </li>}
                    </ul>
                </div>
            </div>
    }

    componentDidUpdate(prevProps: Readonly<UsermanagementViewProps>, prevState: Readonly<UsermanagementViewState>, snapshot?: any): void {
        if (prevProps.username !== this.props.username) {
            this.loadUserDetails()
        }
    }

    private switchEnabled() {

        // TODO: marmer 28.08.2018 Not done here
        this.setState({user: this.state.user.withEnabled(!this.state.user.enabled)});
    }

    private loadUserDetails() {
        if (this.props.username != null) {
            RestClient.getJson("https://localhost/api/v1/usermanagement/users/" + this.props.username)
                .then(value => this.setState({user: value}));
        }
    }

    private listElement(label: string, value?: string | null) {
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