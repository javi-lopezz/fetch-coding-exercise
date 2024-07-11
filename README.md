# Fetch Coding Exercise

## Overview

This project is a solution to the Fetch Coding Exercise. The goal is to find the fake gold bar among 9 bars using a balance scale. The fake bar is lighter than the others. The solution involves an automated testing script using Selenium WebDriver to interact with a web-based simulation.

## Prerequisites

Before running this project, ensure you have the following installed:

- **Java Development Kit (JDK)**
  - Download and install from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
  - Ensure `java` and `javac` are in your system's PATH.

- **Maven**
  - Download and install from [Maven](https://maven.apache.org/download.cgi).
  - Ensure `mvn` is in your system's PATH.

- **Google Chrome Browser**
  - Download and install from [Google Chrome](https://www.google.com/chrome/).

- **ChromeDriver**
  - Download the version compatible with your Chrome browser from [ChromeDriver](https://sites.google.com/a/chromium.org/chromedriver/downloads).
  - Ensure `chromedriver` is in your system's PATH or specify its location in your test code if needed.

## Setup

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/your-username/fetch-coding-exercise.git
    cd fetch-coding-exercise
    ```

2. **Navigate to the Project Directory**:

    ```bash
    cd fetch-coding-exercise
    ```

3. **Install Dependencies**:

    ```bash
    mvn install
    ```

## Running the Project

To run the automated script, execute the following command from the project root directory:

```bash
mvn exec:java -Dexec.mainClass="com.example.FetchCodingExercise2"
