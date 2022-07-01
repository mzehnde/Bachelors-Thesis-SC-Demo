import React from 'react';
import styled from 'styled-components';
import { BaseContainer } from '../helpers/layout';
import SendIcon from '@mui/icons-material/Send';
//import {Button} from '../helpers/button';
import { api, handleError } from '../helpers/api';
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import {Avatar} from "@material-ui/core";
import EmailIcon from '@mui/icons-material/Email';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { useFormik } from 'formik';

export const FormContainer = styled.div`
  margin-top: 2em;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 300px;
  justify-content: center;
`;

export const Form = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 60%;
  height: 375px;
  font-size: 16px;
  font-weight: 300;
  padding-left: 37px;
  padding-right: 37px;
  border-radius: 5px;
  background: linear-gradient(rgb(27, 124, 186), rgb(2, 46, 101));
  transition: opacity 0.5s ease, transform 0.5s ease;
`;

export const Label = styled.label`
  color: white;
  margin-bottom: 10px;
  text-transform: uppercase;
`;

export const InputField = styled.input`
  &::placeholder {
    color: rgba(255, 255, 255, 1.0);
  }
  height: 35px;
  padding-left: 15px;
  margin-left: -4px;
  border: none;
  border-radius: 20px;
  margin-bottom: 20px;
  background: rgba(255, 255, 255, 0.2);
  color: white;
`;

export const ButtonContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 20px;
`;



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


    componentDidMount() {}

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
        }
    }

    //To add: pass url path --> id of partner to find correct reward
    async sendEmail() {
        console.log(this.state.email)


        try{
            const requestBody = JSON.stringify({
                emailAddress: this.state.email,
                partner: 1
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
                        onClick={()=>this.onSubmit()}
                        //disabled={!this.state.email}
                        style={buttonStyle}
                        type = 'submit' color = 'pink' variant = 'contained' endIcon={<SendIcon />}>
                        Claim Reward
                    </Button>
                </Paper>
            </Grid>
        );

    }


    /*render(){
        return (
            <BaseContainer>
                <FormContainer>
                    <Form>
                        <Label>Email</Label>
                        <InputField
                            placeholder="Enter Email here.."
                            onChange={e => {
                                this.handleInputChange('email', e.target.value);
                            }}
                        />
                        <ButtonContainer>
                            <Button
                                disabled={!this.state.email}
                                width="50%"
                                onClick={() => {
                                    this.sendEmail();
                                }}
                            >
                            Login
                            </Button>
                        </ButtonContainer>
                    </Form>
                </FormContainer>
            </BaseContainer>
        );
    }*/
}

export default Home