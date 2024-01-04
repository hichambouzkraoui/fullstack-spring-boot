import { render, screen } from '@testing-library/react';
import React from 'react';
import HeaderComponent from './HeaderComponent.js';

describe('HeaderComponent tests', () => {
    it('should contain the header', () => {
    render(<HeaderComponent  title = "example1"/>);
        const link = screen.getByRole("link");
        expect(link).toHaveTextContent("example1")
    });

    it('should contain the header', () => {
        render(<HeaderComponent  title = "example2"/>);
            const link = screen.getByRole("link");
            expect(link).toHaveTextContent("example2")
        });
});