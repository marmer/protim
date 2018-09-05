import * as React from "react";
import {Route, Switch} from 'react-router';
import PermissionService from "./service/Permissions/PermissionService";
import ServiceFactory from "./service/ServiceFactory";
import HeaderView from "./ui/HeaderView";
import TimetrackingView from './ui/TimetrackingView';
import UsermanagementView from "./ui/UsermanagementView";

export default class ProtimApp extends React.Component {
    private permissionService: PermissionService;

    constructor(props: any, context: any) {
        super(props, context);

        this.permissionService = ServiceFactory.getPermissionService();
    }

    render() {
        return (
            <div className="App">
                <HeaderView/>
                <Switch>
                    <Route path="/timetracking(/:day)?" component={TimetrackingView}/>
                    <Route path="/user(/:user)?" component={this.showIfAdmin(UsermanagementView)}/>
                    <Route path="/" component={TimetrackingView}/>
                    // TODO: marmer 04.09.2018 Errorhandling
                </Switch>
            </div>
        );
    }

    private showIfAdmin(component: any) {
        return this.permissionService.isAdmin() ? component : TimetrackingView;
    }
}
