-- load.pig
--
-- execute with:  java -cp pig.jar org.apache.pig.Main -x local load.pig
--
REGISTER /home/chrisgerken/workspaces/indigo/stage01/tweet.groking/target/tweet.groking-1.0.0-SNAPSHOT-jar-with-dependencies.jar   ;

--
-- Load from text files in file:///home/
--
InterestingTokens = LOAD 'file:///home/' ;





