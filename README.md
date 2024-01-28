# webstaurantstoreselenium
# Selenium WebDriver Maven Project for WebstaurantStore

This is a sample Selenium WebDriver project using Java, Maven, and JUnit 5. 
The project automates tests on the [WebstaurantStore](https://www.webstaurantstore.com/) website. 
The single test performs searching for 'Stainless work Table', navigating through search results, adding last item to the cart, and emptying the cart.

## Environment Setup

- **Operating System:** Windows
- **Java Version:** JDK 11
- **Test Framework:** Selenium/JUnit4
- **Browser:** Chrome (WebDriver)
  
## Project Structure

The project follows a Maven directory structure with the following key folders:

- 'src/main/java': Contains Java source code.
- 'src/main/resources': Contains resources like configuration files.
- 'pom.xml': Maven project configuration.

## Running Instructions

### Importing Project into Eclipse

1. Clone the repository or download the ZIP.
2. Open Eclipse IDE.
3. Select 'File' -> 'Import'.
4. Choose 'Existing Maven Projects'.
5. Navigate to the project root directory and select it.
6. Click 'Finish'.

### Running the Test

From Command Line

1. mvn test


From Eclipse
1. In Eclipse, open 'SearchTextboxTest.java' located in 'src/test/java'.
2. Right-click on the file SearchTextboxTest.java and select 'Run As' -> 'JUnit Test'.
3. Wait for the test to complete.


### Viewing the Test Report

1. After the test execution, Project root folder will have extend report generated.
2. Open the generated HTML report ('extent-report-1706398310954.html') in a web browser.
3. The report includes detailed information about the test execution.

## Test Scenario

The test scenario automates the following steps on [WebstaurantStore](https://www.webstaurantstore.com/):

1. Navigate to the website.
2. Search for 'stainless work table'.
3. Navigate through all search result pages, checking for the presence of the 'Table' keyword ( Test not designed to fail when Table not found )
4. Add the last item in the search result to the cart.
5. Navigate to the cart page.
6. Empty the cart.
7. Quit the browser.

**Note:** 
1. The junit test is not designed to fail if any item in the search result does not contain the 'Table' keyword.
However, the failed item will be present in the generated report under Fail section.
2. There are implicit/explicit wait in the application so that operations occuring could be viewed in browser. 