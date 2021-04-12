import React, { Component } from 'react';
import './App.css';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import ApiService from './ApiService';


class Home extends Component {

    onFileChangeHandler = (e) => {
            const formData = new FormData();
            for(let i = 0; i< e.target.files.length; i++) {
                formData.append('file', e.target.files[i])
            }
            ApiService.upload(formData)
                .then(res => {
                        console.log(res.data);
                        alert("File uploaded successfully.")
                })
        };
  render() {
    return (
     <div className="App-intro">
        Upload Directory
        <Container fluid>
            <input type="file" className="form-control" name="file" directory="" webkitdirectory="" multiple/>
            <Button color="success" onClick={() => this.onFileChangeHandler()}>Upload</Button>
            <Button color="link"><Link to="/listDirectory">List Directory</Link></Button>
        </Container>
      </div>
    );
  }
}



export default Home;