import * as React from 'react';
import {Route, Switch} from 'react-router';
import HeaderView from "./ui/HeaderView";
import TimetrackingView from './ui/TimetrackingView';
import UsermanagementView from "./ui/UsermanagementView";

class ProtimApp extends React.Component {
    render() {
        return (
            <div className="App">
                <HeaderView/>
                <Switch>
                    <Route path="/user(/:user)?" component={UsermanagementView}/>
                    <Route path="/timetracking(/:day)?" component={TimetrackingView}/>
                    // TODO: marmer 04.09.2018 Errorhandling and default path
                </Switch>
            </div>
        );
    }
}

export default ProtimApp;
