import { render, screen } from '@testing-library/react';
import React from 'react';
import App from './App.js';

describe('App tests', () => {
    it('should contain the header', () => {
    render(<App />);
        const heading = screen.getByText(/Employee Management App/i);
        expect(heading).toBeVisible()
    });
});