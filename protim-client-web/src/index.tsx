import 'bootstrap/dist/css/bootstrap.min.css'
import * as React from 'react';
import * as ReactDOM from 'react-dom';
import {BrowserRouter} from "react-router-dom";
import './index.css';
import ProtimApp from './ProtimApp';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
    <BrowserRouter>
        <ProtimApp/>
    </BrowserRouter>,
    document.getElementById('root') as HTMLElement
);
registerServiceWorker();
