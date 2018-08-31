import * as React from 'react';
import {Route, Switch} from 'react-router';
import {BrowserRouter} from "react-router-dom";
import {TimetrackingView} from './ui/TimetrackingView';
import {UsermanagementView} from "./ui/UsermanagementView";

class Router extends React.Component {
    render() {
        return (
            <div className="App">
                <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                    {/*todo User React Link for navigation*/}
                    <a className="navbar-brand" href="#">Protim</a>
                    {/*todo Make Toggler somehow*/}
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-toggle="collapse"
                        data-target="#navbarText"
                        aria-controls="navbarText"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"/>
                    </button>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                {/*todo User React Link for navigation*/}
                                {/*Make active when in /timetracking*/}
                                {/*Show only when the current user is an admin*/}
                                <a className="nav-link" href="#">Pricing</a>
                            </li>
                            <li className="nav-item">
                                {/*todo User React Link for navigation*/}
                                {/*Make active when in /user*/}
                                {/*Show only when the current user is a user*/}
                                <a className="nav-link" href="#">Usermanagement</a>
                            </li>
                        </ul>
                        <span className="navbar-text">
      Navbar text with an inline element
                        </span>
                    </div>
                </nav>
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
