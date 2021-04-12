import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';

class ListDirectory extends Component {
  state = {
    isLoading: true,
    directories: []
  };

  async componentDidMount() {
    const response = await fetch('/directories/listDirectory');
    const body = await response.json();
    this.setState({ directories: body, isLoading: false });
  }

    async calculate() {
      await fetch('measurements/calculate', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      });
    }

  render() {
    const {directories, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <div className="App-intro">
            <h2>Directory List</h2>
            {directories.map(directory =>
              <div>
                {directory.path}
              </div>
            )}
             <Button color="success" onClick={() => this.calculate()}>calculate</Button>
             <Button color="link"><Link to="/miDirectoryResult">See Result In Directory List</Link></Button>
          </div>
        </header>
      </div>
    );
  }
}

export default ListDirectory;