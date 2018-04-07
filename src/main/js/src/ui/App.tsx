import * as React from 'react';
import {TimeManagementView} from './TimeManagementView';

class App extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src="./logo.svg" className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to React</h1>
                </header>
                <p className="App-intro">
                    To get started, edit <code>src/App.tsx</code> and save to reload.
                </p>
                <div className="alert alert-primary" role="alert">
                    This should be pretty colored if bootstrap loads. Otherwhise something went wrong ^^
                </div>
                <TimeManagementView/>
            </div>
        );
    }
}

export default App;
