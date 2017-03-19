loadedData = LOAD '/user/hduser/cars/part-m-00000' USING PigStorage(',') AS (car:chararray,mpg:double,cyl:int,dis:double,horsePower:double,weight:double,acc:double,model:int,origin:chararray);
filteredData = FILTER loadedData BY (weight < 2500) AND ((cyl == 6) OR (cyl == 4) OR (cyl == 3));
groupByCyl = GROUP filteredData BY cyl;
result = FOREACH groupByCyl GENERATE group,COUNT_STAR(filteredData);
STORE result INTO 'hdfs://localhost:9000/project3_pig_usecase3_output' USING PigStorage(',');
