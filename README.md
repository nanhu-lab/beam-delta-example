# beam-delta-example
An example to show how to make Apache Beam write data to Delta Lake,  and read data from Delta Lake.  
## Requirements
* The [beam-datalake](https://github.com/nanhu-lab/beam-datalake) project needs to be compiled and installed first.
## Quickstart
Test cases are given in the BeamDeltaLakeTest document.
1. First, create a delta table using initDeltaTable(), which contains four fields: id, user_name, user_age, user_remark.
2. Then, use testDeltaWrite() to write the data to Delta Lake. In testDeltaWrite(), the simulated data is created, then the simulated data is converted by Apache Beam (converting the user_name to uppercase), and finally written to Delta Lake.
3. At last, use testDeltaRead() to read the data out of Delta Lake, and then filter according to the user_age, and write the data that meets the criteria to text.  
## Configuration
A few important dependencies are shown below, and others are seen in the pom.xml  
```xml
<properties>  
    <spark.version>3.2.0</spark.version>
    <beam.version>2.41.0</beam.version>
    <delta.version>2.0.2</delta.version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.nanhulab</groupId>
        <artifactId>beam-datalake</artifactId>
        <version>1.0.0</version>
    </dependency>
    <!-- delta -->
    <dependency>
        <groupId>io.delta</groupId>
        <artifactId>delta-core_2.12</artifactId>
        <version>${delta.version}</version>
    </dependency>
    <!-- beam -->
    <dependency>
        <groupId>org.apache.beam</groupId>
        <artifactId>beam-runners-core-java</artifactId>
        <version>${beam.version}</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.beam</groupId>
        <artifactId>beam-runners-direct-java</artifactId>
        <version>${beam.version}</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.beam</groupId>
        <artifactId>beam-sdks-java-core</artifactId>
        <version>${beam.version}</version>
        <scope>provided</scope>
    </dependency>
    <!-- spark -->
    <dependency>
        <groupId>org.apache.spark</groupId>
        <artifactId>spark-sql_2.12</artifactId>
        <version>${spark.version}</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.spark</groupId>
        <artifactId>spark-core_2.12</artifactId>
        <version>${spark.version}</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.spark</groupId>
        <artifactId>spark-streaming_2.12</artifactId>
        <version>${spark.version}</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
