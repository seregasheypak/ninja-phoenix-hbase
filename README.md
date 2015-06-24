# ninja-phoenix-jooq

## aim
* avoid HBase low-level api
* expose SQL interface for other services
* avoid concatenated SQL queries and use SQL builder from Jooq
* reduce code footprint
* get smth like play, but in java, since there is no reason to learn scala just because of play
* have a chance to run legacy servlets

## current state
* client can POST json
* json transparently mapped to java bean (no coding here) 
* can upsert bean
* can find bean
* itest (web + hbase) provided 

### various links related to project
https://github.com/sequenceiq/sequenceiq-samples/blob/master/phoenix-jooq/src/main/java/com/sequenceiq/samples/phoenix/Query.java
http://www.ninjaframework.org/documentation/logging.html
http://phoenix.apache.org/language/datatypes.html#bigint_type

https://groups.google.com/forum/#!topic/phoenix-hbase-user/R6AkfnX9a3U

https://groups.google.com/forum/#!topic/ninja-framework/hGNK7OrEd70

https://groups.google.com/forum/#!topic/ninja-framework/FoDKG20lqwg

####extend to support phoenix
http://flywaydb.org/contribute/code/contributeNewDatabase.html