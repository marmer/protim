import * as React from 'react';
import {User} from "../model/usermanagement/User";

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
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    render() {
        return <div>{this.state.user.username}</div>
    }
}