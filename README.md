# Data Translator

Project purpose is to achieve data after filtering and translating from input data. Solution is developed by Spring boot 
which provides self-contained. Application server and Database embedded itself. Therefore, it does not require external 
resources.

## Getting Started

This project have requirements:

● A data vendor delivery data in flat files.These flat files are in matrix format. The first row contains the column 
labels, the further rows are data rows. In the first column holds a data vendor specific identifier. Column separator 
is a tab.

Example:
```HTML
COL0        COL1        COL2        COL3
ID1        VAL11        VAL12        VAL12
ID2        VAL21        VAL22        VAL23
```

● There is a configuration file that lists the columns that we want to extract.

Example (skip column COL2):
```HTML
COL0        OURID
COL1        OURCOL1
COL3        OURCOL3
```

● There is an another configuration file that lists the data vendor specific identifiers, so the rows that we want to 
extract. Similar to point 2: these are translated to the values in column 2

Example (skip ID1):
```HTML
ID2        OURIDXXX
```

● The task is to build a 'translator' that reads in these three files and produces output in the same structure: first 
row with 'our' column labels, further rows with the data we wanted to extract. The output file records don't have to be 
in the same order as the input.

Example based on examples above:
```HTML
OURID        OURCOL1        OURCOL3
OURIDXXX     VAL21          VAL23
``` 

● Optional for Junior Developers: The solution must be able to process very big data files with big number of rows and 
columns by utilizing all available resources (CPU cores and memory). 

Data is stored in embedded MongoDB. Other in memory embedded db technologies have been tested. When comes to reading 
speed, MongoDB is the best choice. The core scheduler is OS based, and the JVM relies on the OS scheduler it is running 
on.

### Prerequisites

You can run the application from the command line with Maven. Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources, and run that. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

Also, WAR file can be generated to be deployed inside the containers. spring-boot-maven-plugin supports as well. But 
additional configuration may required for embedded container dependencies.

In this application, when Spring boot wake up, application begins to load initial data to mongo db. Therefore, it is required to wait loading data to mongo db.

### Expected deliveries

The service can run both on our SaaS platform and on premise installations. Delivered service can be easily run on
premise even in case where docker is not available.

### Installing

You can run the application from the command line with Maven. In the project directory which contains pom.xml file, You 
can run the application using 

```HTML
mvn spring-boot:run
```

Or you can build the JAR file with 

```HTML
mvn clean package 
```

Then you can run the JAR file:

```HTML
java -jar target/data_translator-0.0.1-SNAPSHOT.jar
```

If you want to use as WAR file, you need to change packaging tag as war in the pom.xml;

```HTML
<packaging>war</packaging>
```

and, again run the application using

```HTML
mvn clean package
```

war file can be seen inside the target directory.

Zipped File uploading web page can be accessed as;

```HTML
localhost:8080/
```

In this page, after submitting the zipped file, application extracts zipped file and distributes three sub files to
related directory (initialData directory). Three files are used for filtering and translating for main data. After designing
main data, it is loaded to mongo db. 

Result data table can seen below link;

```HTML
localhost:8080/data
```

## Running the tests

Test classes can found under src/test/java/com.onurtokat/ directory.

```HTML
/config/MongoDbSpringIntegrationTest.class
/controllers/DataControllerTest.class
/controllers/DataControllerMockTest.class
/init/DataInitTest.class
/services/DataServiceImplTest.class
/storage/FileSystemStorageServiceTest.class
/utility/HeaderConverterTest.class
/utility/UnzipFileTest.class
/utility/VendorConverterTest.class
/SmokeTest.class
/SpringBootMongoDbApplicationTest.class
```
### Break down into end to end tests

<li>MongoDB configuration correctness have been checked</li>
<li>GET and POST mapping method returns have been checked</li>
<li>Data loading steps and exception throwns have been checked</li>
<li>Repository data correctness have been checked</li>
<li>File operations and exceptions have been checked</li>
<li>Header data conversion has been checked</li>
<li>Vendor data conversion has been checked</li>
<li>Unziping data has been checked</li>
<li>Simulated controller</li>
<li>SpringBoot application checked with mvc</li>  

## Deployment

 This project can be ran as JAR in the;
 
 <li>Locally for testing</li>
 <li>On a server</li>
 <li>On a server hosted by cloud provider</li>
 <li>In a container</li>
 <li>In a container hosted by cloud provider</li>

## Built With

* [Spring Boot 2.0.0.M7](http://spring.io/guides) - The Spring Boot framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Embedded MongoDB 1.50.5](https://www.mongodb.com/) - Used to generate Embedded Database
* [Thymleaf](https://www.thymeleaf.org/) - Used to web framework
 

## Authors

Onur Tokat

## Limitations

* Three files (data, header, vendor) should use tab delimiter,  
* Three files should be named as data.txt, header.txt, and vendor.txt and should be putted in zip extentioned file.