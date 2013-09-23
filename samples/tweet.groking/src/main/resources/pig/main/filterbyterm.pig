-- filterbyterm.pig
--
-- execute with:  java -cp pig.jar org.apache.pig.Main -x local filterbyterm.pig
--
REGISTER /home/chrisgerken/workspaces/indigo/stage01/tweet.groking/target/tweet.groking-1.0.0-SNAPSHOT-jar-with-dependencies.jar   ;

--
-- Load from sequence files in file:///home/
--
-- InterestingTerms = LOAD 'file:///home/' 

--
-- Load from sequence files in file:///home/
--
-- DatedTerms = LOAD 'file:///home/' 



