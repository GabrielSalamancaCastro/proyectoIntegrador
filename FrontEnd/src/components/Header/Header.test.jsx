import React from "react";
import { Header } from "./Header";
import '@testing-library/jest-dom/extend-expect';
import { render, screen, cleanup } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { createMemoryHistory } from 'history';
import { Router } from "react-router";
import { shallow, configure } from "enzyme";
import Enzyme from 'enzyme';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';

Enzyme.configure({ adapter: new Adapter() });

test("Deberia renderizarse sin error", () => {
  const wrapper = shallow(<Header/>)
  expect(wrapper).toHaveLength(1)
})

test("full app rendering/navigating", () => {
  const history = createMemoryHistory()
  render (
    <Router history={history}>
      <Header/>
    </Router>
  )

  expect(screen.getByText(/Los mejores carros a tu disposición/i)).toBeInTheDocument()
})