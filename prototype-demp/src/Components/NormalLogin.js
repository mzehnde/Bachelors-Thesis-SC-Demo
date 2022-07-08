import React from 'react';
import styled from 'styled-components';
import SendIcon from '@mui/icons-material/Send';
import { api, handleError } from '../helpers/api';
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import EmailIcon from '@mui/icons-material/Email';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
//import {withRouter} from  'react-router-dom';

//TODO:
//LOCALLY:
//1. fetch metadata from pinata --> backend
//2. pass it to mintNFT
//3. check if it works
//4. add identifier to metadata --> if url =/blockchainReward/1: only take metadata with id 2 (other partner)

//DEPLOY
//1. Deploy on heroku --> consider: @origin, proxy, db...
//2. generate QR Codes and populate DB
//3. Test it
//4. Populate with real rewards
//5. Test again

//OTHER THINGS
//1. add reward descriptions for Mail!!!
//2. debug db warning
//3. new gmail account with "APP name"
//4. scale for phone



class NormalLogin extends React.Component{
    constructor() {
        super();
        this.state = {
            email:null
        };
    }



    handleInputChange(key, value) {
        // Example: if the key is username, this statement is the equivalent to the following one:
        // this.setState({'username': value});
        this.setState({ [key]: value });
    }

    componentDidMount() {
    }

    emailValidation(){
        const regex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
        if(!this.state.email || regex.test(this.state.email) === false){
            this.setState({
                error: "Email is not valid"
            });
            return false;
        }
        return true;
    }

    onSubmit(){
        if(this.emailValidation()){
            this.sendReward();
        }
    }

    async sendReward() {

        try{
            const requestBody = JSON.stringify({
                emailAddress: this.state.email,
            });

            const response = await api.post('/test/sendNormalEmail', requestBody);

            console.log(response.data);
            window.location.href="/reward"

        } catch (error){
            alert(`Unfortunately there are no more rewards left!`);
        }
    }

    render(){
        const paperStyle = {padding: 10, height:'35vh', width:230, margin: '20px auto' }
        const mailStyle = {position: "center"}
        const buttonStyle = {position:"center"}

        return (
            <Grid alignItems="center">
                <Paper elevation={10} style={paperStyle}>
                    <EmailIcon fontSize="large" style={mailStyle}>

                    </EmailIcon>
                    <h2>
                        Register to receive a Reward
                    </h2>
                    <TextField label={"E-mail"}
                               placeholder={"Enter E-Mail here..."}
                               fullWidth required
                               onChange={e => {
                                   this.handleInputChange('email', e.target.value)}}
                    >
                    </TextField>
                    <span className="text-danger" >{this.state.error}</span>
                    <Button
                        onClick={
                            ()=>this.onSubmit()}
                        style={buttonStyle}
                        type = 'submit' color = 'pink' variant = 'contained' endIcon={<SendIcon />}>
                        Claim Reward
                    </Button>
                </Paper>
            </Grid>
        );
    }
}

export default NormalLogin