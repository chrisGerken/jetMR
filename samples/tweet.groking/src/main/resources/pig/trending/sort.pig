-- sort.pig
--
-- execute with:  java -cp pig.jar org.apache.pig.Main -x local sort.pig
--
REGISTER /home/chrisgerken/workspaces/indigo/stage01/tweet.groking/target/tweet.groking-1.0.0-SNAPSHOT-jar-with-dependencies.jar   ;

--
-- Load from sequence files in hdfs://localhost:9000/tmp/SocialLinks/Links
--
-- TermCounts = LOAD 'hdfs://localhost:9000/tmp/SocialLinks/Links' 



