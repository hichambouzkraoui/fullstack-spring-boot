import { render, screen, fireEvent } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import React from 'react';
import CreateEmployeeComponent from './CreateEmployeeComponent.jsx';

const MockComponent = () => {
    const props = { params:{id:1} }
    return (
        <BrowserRouter>
        <CreateEmployeeComponent match = {props }/>
        </BrowserRouter>
    )

}

describe('CreateEmployeeComponent tests', () => {
    it('should contain First Name input', () => {
        const props = { params:{id:1} }
    render(<CreateEmployeeComponent match = {props}/>);
        const input = screen.getByPlaceholderText(/First Name/);
        expect(input).toHaveTextContent("")
    });

    it('should be able to input First Name', () => {
        const props = { params:{id:1} }
    render(<CreateEmployeeComponent match = {props}/>);
        const input = screen.getByPlaceholderText(/First Name/);
        fireEvent.change(input, {target: {value: "Rayan"}})
        expect(input.value).toBe("Rayan")
    });

    it('input should have keep after save', () => {
        const props = { params:{id:1} }
    render(<CreateEmployeeComponent match = {props}/>);
        const input = screen.getByPlaceholderText(/First Name/);
        const saveButton = screen.getByRole("button", {name: "Save"})
        fireEvent.change(input, {target: {value: "Rayan"}})
        fireEvent.click(saveButton)
        expect(input.value).toBe("Rayan")
    });
});

// describe('CreateEmployeeComponent integration tests', () => {

//     it('input should have keep after save', () => {
//     render(<MockComponent />);
//         const input = screen.getByPlaceholderText(/First Name/);
//         const saveButton = screen.getByRole("button", {name: "Save"})
//         fireEvent.change(input, {target: {value: "Rayan"}})
//         fireEvent.click(saveButton)
//         expect(screen.getByText("Rayan")).toBeInDocument()
//     });
// });