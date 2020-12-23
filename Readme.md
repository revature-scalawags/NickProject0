# Overwatch Stats Retriever
A simple project that retrieves statistics on Overwatch Profiles

## Compile
> sbt compile

## Test
> sbt test

## Run
> sbt run

# Requirements
Scala
Java
# Features
Can retrieve any piece of information from any Overwatch profile if provided the username and platform.
# Usage
1. Navigate to OWSTATS folder and run the command:
>sbt run
2. Input information into the terminal as directed by the program.
# Known Issues
If user types their input too quickly after being prompted, not all keystrokes will register. This causes it to seem like a valid input
was invalid from the user's perspective. Trying the same input again works without problem.