loadedData = LOAD '/user/hduser/cars/part-m-00000' USING PigStorage(',') AS (car:chararray,mpg:double,cyl:int,dis:double,horsePower:double,weight:double,acc:double,model:int,origin:chararray);
filteredData = FILTER loadedData BY (model == 75) AND ((origin == 'US') OR (origin == 'Europe') OR (origin == 'Japan'));
groupByOrigin = GROUP filteredData BY origin;
outputData = FOREACH groupByOrigin GENERATE group,COUNT(filteredData);
grpByAll = GROUP outputData ALL;
rs = FOREACH grpByAll GENERATE MAX(outputData.$1);
result = FILTER outputData BY $1==rs.$0;
STORE result INTO 'hdfs://localhost:9000/project3_pig_usecase2_2_output' USING PigStorage(',');
