loadedData = LOAD '/user/hduser/cars/part-m-00000' USING PigStorage(',') AS (car:chararray,mpg:double,cyl:int,dis:double,horsePower:double,weight:double,acc:double,model:int,origin:chararray);
outputData = FILTER loadedData BY (horsePower > 200.0);
STORE outputData INTO 'hdfs://localhost:9000/project3_pig_usecase1_output' USING PigStorage(',');
DUMP outputData;
