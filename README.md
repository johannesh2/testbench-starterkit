# TestBench Starter Kit

This a simple starter kit for running automated UI tests in a Vaadin 8 project using TestBench 5.
The old TestBench starterkit for Vaadin 7 and TestBench 4 is at https://github.com/johannesh2/testbench4-starterkit

## Profiles 
The project has two few profiles related to tests: `dev` and `ci`. Dev is meant for development environment and ci is meant for CI build. Select profile with `-P` switch e.g. `mvn package -P ci`. Each profile's configuration file is in `/profiles/$ {build.profile.id}/config.properties`. You can set browser driver with `-Dtest.browser` Possible values are `jbrowser`, `chrome`, or `phantomjs`. Default value is `jbrowser`.

## UI integration tests with Vaadin TestBench
Tests are run using Maven Failsafe plugin. Execute `mvn verify`. Verify phase will run multiple goals e.g. starting Jetty in a port defined in profile's `config.properties` file.

Failsafe's default test inclusion patterns are used.
* `"**/IT*.java"` - includes all of its subdirectories and all Java filenames that start with "IT".
* `"**/*IT.java"` - includes all of its subdirectories and all Java filenames that end with "IT".
* `"**/*ITCase.java"` - includes all of its subdirectories and all Java filenames that end with "ITCase".

## Unit tests
For unit tests the Maven Surefire plugin is used. Surefire's default test inclusion patterns are used.
* `"**/Test*.java"` - includes all of its subdirectories and all Java filenames that start with "Test".
* `"**/*Test.java"` - includes all of its subdirectories and all Java filenames that end with "Test".
* `"**/*TestCase.java"` - includes all of its subdirectories and all Java filenames that end with "TestCase".
