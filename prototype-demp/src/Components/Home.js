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
//1. create nice html Email
//2. add info button with explanation
//3. after button: route /reward --> Email succesfull sent to...
//4. Backend: create /redeem with QRCodereward URL --> find reward by QRCode (consistent url to db) --> mark as redeemed DONE
//5. add check if redeemed
//6. frontend: route reward redemption DONE
//7. frontend: render form to insert sales DONE
//8. Backend: add sales generated to correct partner & to correct reward DONE
//9. Create two tables for reward --> crosspromot and normal --> check with qrcodeurl which repo to search
//9.1 --> when redeeming --> search through both repos to find --> never same qr url for reward!
//7. Test it locally by populating db with test entries

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


class Home extends React.Component{
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
        console.log(window.location.pathname.substring(1))
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
            console.log(this.state);
            this.sendEmail();
            window.location.href="/reward"
            //this.props.history.push('/test/reward')

            //history.push("/display");
        }
    }

    async sendEmail() {
        try{
            const requestBody = JSON.stringify({
                emailAddress: this.state.email,
                partner_QR_Code: window.location.pathname.substring(1)
            });

            const response = await api.post('/test/sendMail', requestBody);

            //this.props.history.push(`/reward`);
            console.log(response);

        } catch (error){
            alert(`Something went wrong during the login: \n${handleError(error)}`);
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
                        /*onClick={() => {
                            this.sendEmail();
                        }}*/
                        onClick={
                            ()=>this.onSubmit()}
                        //disabled={!this.state.email}
                        style={buttonStyle}
                        type = 'submit' color = 'pink' variant = 'contained' endIcon={<SendIcon />}>
                        Claim Reward
                    </Button>
                </Paper>
            </Grid>
        );
    }
}

export default Home