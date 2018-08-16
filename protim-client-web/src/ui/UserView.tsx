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
        return <div>
            <div>
                <div>
                    <label title="Username">
                        <div>{this.state.user.username}</div>
                    </label>
                </div>
                <div>
                    <label title="Roles">
                        <div>{this.state.user.roles}</div>
                    </label>
                </div>
                <div>
                    <label title="Enabled">
                        <div>{this.state.user.enabled}</div>
                    </label>
                </div>
            </div>
        </div>
    }
}