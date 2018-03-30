import * as React from 'react';
import './App.css';
import {BookingDayView} from './BookingDayView';

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
                    <div className="alert alert-primary" role="alert">
                        This should be pretty green if bootstrap loads. Otherwhise something went wrong ^^
                    </div>
                </p>
                <BookingDayView/>
            </div>
        );
    }
}

export default App;
