import React, {Component} from 'react';
import './App.css';
import Home from '../src/Components/Home'
import Reward from '../src/Components/Reward'
import Redeem from '../src/Components/Redeem'
import InvalidReward from '../src/Components/InvalidReward'
import {
    BrowserRouter as Router,
        Routes,
        Route,
        Link
} from 'react-router-dom';

/* DOC:
LINK: creates a Link
    to: refers to where link should navigate to
ROUTE: establish the link between component’s UI and the URL
    exact: renders only exact /normalReward page (not for example /normalReward/10)
    path: pathname assigned to component
    element: refers the component that's rendered when matching its path
ROUTES: to render a single component -> iterates over the routes and renders first one that matches
*
* */



class App extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <h1>
                        Collect Your Reward
                    </h1>
                    <ul>
                    </ul>
                    <Routes>
                        <Route exact path='/' element={< Home />}></Route>
                        <Route exact path='/normalReward' element={< Home />}></Route>
                        <Route exact path='/blockchainReward' element={<Home/>}></Route>
                        <Route exact path='/1' element={< Home />}></Route>
                        <Route exact path='/redeem/1' element={< Redeem />}></Route>
                        <Route exact path='/notvalid' element={< InvalidReward />}></Route>
                    </Routes>
                </div>
            </Router>
        );
    }
}
export default App;
//Fetching from Backend:
 /*   state = {};

    componentDidMount() {
        this.dadJokes()
    }

    dadJokes = () => {
        fetch('/api/dadjokes')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h3 className="App-title">{this.state.message}</h3>
                </header>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
            </div>
        );
    }
}

export default App;*/



