import React from 'react';
import styled from 'styled-components';
import SendIcon from '@mui/icons-material/Send';
import { api, handleError } from '../helpers/api';
import Grid from "@material-ui/core/Grid";
import Paper from "@material-ui/core/Paper";
import EmailIcon from '@mui/icons-material/Email';
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import PaidIcon from '@mui/icons-material/Paid';
import FormGroup from "@material-ui/core/FormGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Checkbox from "@material-ui/core/Checkbox";
import red from "@material-ui/core/colors/red";
//import {withRouter} from  'react-router-dom';


class Redeem extends React.Component{
    constructor() {
        super();
        this.state = {
            sales:null
        };
    }

    handleInputChange(key, value) {
        // Example: if the key is username, this statement is the equivalent to the following one:
        // this.setState({'username': value});
        this.setState({ [key]: value });
        console.log(this.state.didSpend)
    }

    //To add: check if reward already redeemed and if yes push to component with corresponding message, if no do nothing
    async componentDidMount() {
        const path = window.location.pathname
        const rewardqr = path.substring(path.length -1)
        console.log(rewardqr)

       /* try{
            const requestBody = JSON.stringify({
                qrcodereward : rewardqr
            });

            const response = await api.post('/test/isRedeemed', requestBody);


            //this.props.history.push(`/reward`);
            console.log(response);

        } catch (error){
            alert(`Something went wrong during the login: \n${handleError(error)}`);
        }*/
    }





    //add sales and mark as redeemed
    async onSubmit(){
        try{
            const requestBody = JSON.stringify({
                sales:this.state.sales,
                qrcodereward:window.location.pathname
            });

            const response = await api.put('/test/addSales', requestBody);

            //this.props.history.push(`/reward`);
            console.log(response);

        } catch (error){
            alert(`Something went wrong during the login: \n${handleError(error)}`);
        }

    }

    render(){
        const paperStyle = {padding: 10, height:'60vh', width:230, margin: '20px auto' }
        const mailStyle = {position: "center"}
        const buttonStyle = {position:"center"}
        const H4 = styled.h4({
            background:red
        });

        return (
            <Grid alignItems="center">
                <Paper elevation={10} style={paperStyle}>
                    <PaidIcon fontSize="large" style={mailStyle}>
                    </PaidIcon>
                    <h2>
                        Please fill out this form
                    </h2>
                    <H4>
                        Did the customer spend any money additionally to his reward?
                    </H4>

                    <TextField label={"Money spent"}
                               placeholder={"Enter amount spent here..."}
                               fullWidth required
                               onChange={e => {
                                   this.handleInputChange('sales', e.target.value)}}
                    >
                    </TextField>
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

export default Redeem