loadedData = LOAD '/user/hduser/cars/part-m-00000' USING PigStorage(',') AS (car:chararray,mpg:double,cyl:int,dis:double,horsePower:double,weight:double,acc:double,model:int,origin:chararray);
filteredData = FILTER loadedData BY (model == 75) AND ((origin == 'US') OR (origin == 'Europe') OR (origin == 'Japan'));
groupByOrigin = GROUP filteredData BY origin;
outputData = FOREACH groupByOrigin GENERATE group,COUNT(filteredData);
STORE outputData INTO 'hdfs://localhost:9000/project3_pig_usecase2_output' USING PigStorage(',');
