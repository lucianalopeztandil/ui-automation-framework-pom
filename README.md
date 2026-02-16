# UI Automation Framework – E-commerce Application

This project is a scalable end-to-end UI automation framework developed using Java, Selenium WebDriver and TestNG.

The framework follows the Page Object Model (POM) design pattern and implements reusable base components to ensure maintainability, readability and scalability.

## Key Features

- Page Object Model with Page Factory implementation
- AbstractComponent for reusable wait and navigation logic
- BaseTest with multi-browser support (Chrome / Firefox)
- Positive and negative test scenarios
- Explicit synchronization strategy for dynamic UI elements
- Maven-based execution
- Suite execution via TestNG
- Designed to be CI-ready (Jenkins integration planned)

## Test Coverage

- Successful login and end-to-end purchase flow
- Login validation with incorrect credentials
- Product validation negative scenario

## Test Account Requirement

To execute the tests, you must create your own account on the e-commerce application.

For security reasons, credentials are not stored in the repository. Please configure your own email and password before running the test suite.

