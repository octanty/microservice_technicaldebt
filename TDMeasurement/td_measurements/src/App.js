import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import ListDirectory from './ListDirectory';
import MIFileResult from './MIFileResult';
import MIDirectoryResult from './MIDirectoryResult';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
            <Route path='/' exact={true} component={Home} />
            <Route path='/listDirectory' exact={true} component={ListDirectory} />
            <Route path='/miDirectoryResult' exact={true} component={MIDirectoryResult} />
            <Route path='/miFileResult' component={MIFileResult}/>
        </Switch>
       </Router>
    );
  }
}

export default App;