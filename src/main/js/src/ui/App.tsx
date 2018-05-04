import * as React from 'react';
import {TimeManagementView} from './TimeManagementView';

class App extends React.Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src="./logo.svg" className="App-logo" alt="logo"/>
                    <h1 className="App-title">Welcome to Protim</h1>
                </header>
                <TimeManagementView/>
            </div>
        );
    }
}

export default App;
