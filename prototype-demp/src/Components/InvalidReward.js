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


export default class InvalidReward extends React.Component{
    render() {
        return(
            <h2>
                The provided reward has already been redeemed and is invalid
            </h2>
        )
    }
}

