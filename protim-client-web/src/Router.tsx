import * as React from 'react';
import {Route, Switch} from 'react-router';
import {BrowserRouter} from "react-router-dom";
import {HeaderView} from "./HeaderView";
import {TimetrackingView} from './ui/TimetrackingView';
import {UsermanagementView} from "./ui/UsermanagementView";

class Router extends React.Component {
    render() {
        return (
            <div className="App">
                <HeaderView/>
                <BrowserRouter>
                    <Switch>
                        <Route path="/user(/:user)?" component={UsermanagementView}/>
                        <Route path="/timetracking(/:day)?" component={TimetrackingView}/>
                    </Switch>
                </BrowserRouter>
            </div>
        );
    }
}

export default Router;
