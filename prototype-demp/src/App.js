import React, {Component} from 'react';
import './App.css';
import BlockchainLogin from './Components/BlockchainLogin'
import Reward from '../src/Components/Reward'
import Redeem from '../src/Components/Redeem'
import InvalidReward from '../src/Components/InvalidReward'
import ThankYou from '../src/Components/ThankYou'
import RedeemNormal from '../src/Components/RedeemNormal'
import {
    BrowserRouter as Router,
        Routes,
        Route,
        Link
} from 'react-router-dom';
import NormalLogin from "./Components/NormalLogin";
import logo from '../src/helpers/logo.svg';

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

//add description for Email (db and NFT metadata)
//refactor
//styling all components
//deploy
//test on phone for scaling
//test everything (populate dbs and pinata)

class App extends Component {
    render() {
        return (
            <Router>
                <div className="App">
                    <img
                        src={logo} className="app-logo" alt="logo" />
                    {/*<h1>
                        logo
                    </h1>*/}
                    <ul>
                    </ul>
                    <Routes>
                        <Route exact path='/' element={< BlockchainLogin />}></Route>
                        <Route exact path='/blockchainReward/partnerAlfred' element={<BlockchainLogin/>}></Route>
                        <Route exact path='/blockchainReward/partnerHonig' element={<BlockchainLogin/>}></Route>
                        <Route exact path='/blockchainReward/partner3' element={<BlockchainLogin/>}></Route>
                        <Route exact path='/normalReward' element={< NormalLogin />}></Route>
                        <Route exact path='/redeemNFT/1' element={< Redeem />}></Route>
                        <Route exact path='/redeemNFT/2' element={< Redeem />}></Route>
                        <Route exact path='/redeemNormal/11' element={< RedeemNormal />}></Route>
                        <Route exact path='/redeemNormal/12' element={< RedeemNormal />}></Route>
                        <Route exact path='/notvalid' element={< InvalidReward />}></Route>
                        <Route exact path='/thankyou' element={< ThankYou />}></Route>
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



