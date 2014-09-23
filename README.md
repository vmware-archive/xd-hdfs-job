xd-hdfs-job
===================

Simple [hdfs] Job module for [Spring-XD] using Spring Data Hadoop. We provide 2 modules:

* hdfs-archive - move or copy files between HDFS folders
* hdfs-script - runs an groovy Spring Data Hadoop script

Additional modules can be easily created using the framework of the modules above. There is no Java code in them, only module definition and properties.

## Installation

Set the environment variable `XD_HOME` to the Spring-XD installation directory

    export XD_HOME=<root-install-dir>/spring-xd/xd

Copy the `hdfs-*.xml` and `hdfs-*.properties` module definition into `${XD_HOME}/modules/job/`   
    
    cp src/main/resources/hdfs-* `${XD_HOME}/modules/job`

Copy the scripts `src/main/resources/*.groovy` into `${XD_HOME}/modules/processor/scripts`  
    
    cp src/main/resources/*.groovy `${XD_HOME}/modules/processor/scripts`

## Usage

#### HDFS Archive

    xd>job create hdfs-a1 --definition "hdfs-archive 
    --sourceDir=/xd/daily 
    --targetDir=/xd/archive" --deploy 

OR

    xd>job create hdfs-a2 --definition "hdfs-archive 
    --sourceDir=/xd/daily 
    --targetDir=/xd/archive
    --appendDate=true" --deploy 
    
OR

    xd>job create hdfs-a3 --definition "hdfs-archive 
    --sourceDir=/xd/daily 
    --targetDir=/xd/archive
    --deleteFiles=true
    --dateFormat='yyyy-MM-dd''T''HH.mm.ss'" --deploy 

##### Embedded SQL command      

    xd>job create hdfs-s1 --definition "hdfs-script --location=sample1.groovy" --deploy 
  
  
 
