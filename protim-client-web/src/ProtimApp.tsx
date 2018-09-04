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
                    <Route path="/timetracking(/:day)?" component={TimetrackingView}/>
                    <Route path="/user(/:user)?" component={UsermanagementView}/>
                    <Route path="/" component={UsermanagementView}/>
                    // TODO: marmer 04.09.2018 Errorhandling
                </Switch>
            </div>
        );
    }
}

export default ProtimApp;
