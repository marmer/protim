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

        this.sleep(1500).then(() => {
            // TODO: marmer 15.08.2018 load user from props
            const u = new User();
            u.username = "hundekuchen";
            this.setState({
                user: u
            });
        });
    }

    sleep(time: number) {
        // TODO: marmer 15.08.2018 remove me
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    render() {
        return <div>{this.state.user.username}</div>
    }
}