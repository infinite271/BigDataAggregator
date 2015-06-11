big-data-aggregator
===================

The project is written in Java 8 and attempts to follow DDD best practices to structure the project. With Java 8 the application (minus tests) has moved away from the need to use any traditional iterative constructs such as for or while loops.
 
Instead it makes extensive use of the foreach and lambda expressions for processing elements in collections, as well as method references.

===================
Install
===================

To install the application all you need to do is build the project and produce the single JAR file. In IntelliJ you can do this by opening the project then going to:

`Project Structure -> Artifacts -> Add -> JAR -> From Module with Dependencies -> Select All Modules -> Select Main class`

After doing this you can go to `Build -> Build Artifacts` this will then produce a single JAR that you can run using the command line.

===================
Run
===================

Running the application can be done by using the traditional java -jar command followed by any of the two different formats:
 
{transaction file name, exchangerate file name, currency}
{transaction file name, exchangerate file name, partner, currency}

`java -jar transactions.csv exchangerates.csv "Unlimited ltd." "GBP"`
 
Note* The application looks for the two csv files in the current working directory, you should place these files alongside the JAR.

===================
Implementation
===================

The application layer of the solution is encapsulated within the `AggregatorImpl`. From here it orchestrates the `TransactionManager` and `ExchangeManager` who are responsible for any actions concerned with the Transaction and Exchange domains respectively.

Both managers extend the abstract class `AggregateManager` and inherit the load method which is a generic method used to read lines from a text file.

There are two assemblers used to assemble raw lines read from a given csv into `Transaction` and `CurrencyPair` models that are then used in the domain layer.

In the infrastructure layer there is a `FileWriter` class used solely to write/append data a new/existing file.

Lastly, there is `BenchmarkService` which is used to help time how long actions take within the application.

===================
Testing
===================

There are two test classes available which are:
 
`AggregatorEndToEndTest`
 
This class will setup two dummy test files that hold pre-determined transactions and exchange rates. The test then runs and it confirms that we receive the expected results.
The tests will clean up after themselves by removing both of the dummy files once it is done.
 
`AggregatorPerformanceTest`
 
This class is only meant to be run on-demand to test the performance of the application. It contains a generateRandomTransactions method that can be used to generate any number of
Transactions for testing purposes.
 
The actual tests don’t confirm that the results are correct but instead will enable you to trace the performance of the application when it runs using large datasets.

===================
Performance
===================
 
100,000,000 Transactions grouped by GBP = 38 seconds


100,000,000 Transactions grouped by Unlimited ltd. and GBP = 8 seconds

===================
Using Parallel Streams
===================

By changing the line in the AggregateManager `lines.forEach(this::process)` to `lines.parallel().forEach(this::process)` I am able to half the processing time required to group all transactions by GBP.

100,000,000 Transactions grouped by GBP = 16 seconds


100,000,000 Transactions grouped by Unlimited ltd. and GBP = 6 seconds
