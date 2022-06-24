import React from 'react';
import styled from 'styled-components';
import { BaseContainer } from '../helpers/layout';
import {Button} from '../helpers/button';


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

    render(){
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
                                    window.location.href='/reward';
                                }}
                            >
                            Login
                            </Button>
                        </ButtonContainer>
                    </Form>
                </FormContainer>
            </BaseContainer>
        );
    }
}

export default Home