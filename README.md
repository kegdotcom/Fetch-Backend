# Fetch-Backend
This is my submission for the Backend Software Engineer Internship Take Home Test

This program takes in a number of points to spend, as well as a .csv file containing transactions and their payers, points, and timestamps, and spends the points in each transaction, from oldest to newest, up to the number of points specified in the run parameters.

How to execute:
1. Make sure you have java installed. If not, you can install it here: https://www.java.com/en/download/manual.jsp
2. download the code in this public repository into its own directory on your local machine
3. make sure the .csv file to load the data from is in the same directory
6. open a terminal in that directory and run the following commands:

javac -cp ./ PointSpender.java

java PointSpender <Points to spend> <.csv file name>
