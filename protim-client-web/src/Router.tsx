import * as React from 'react';
import {Route, Switch} from 'react-router';
import {BrowserRouter} from "react-router-dom";
import HeaderView from "./ui/HeaderView";
import TimetrackingView from './ui/TimetrackingView';
import UsermanagementView from "./ui/UsermanagementView";

class Router extends React.Component {
    render() {
        return (
            <BrowserRouter basename="/protim">
                <div className="App">
                    <HeaderView/>
                    <Switch>
                        <Route path="/user(/:user)?" component={UsermanagementView}/>
                        <Route path="/timetracking(/:day)?" component={TimetrackingView}/>
                    </Switch>
                </div>
            </BrowserRouter>
        );
    }
}

export default Router;
