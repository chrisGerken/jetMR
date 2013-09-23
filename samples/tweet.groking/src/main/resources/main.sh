
function setup() {

	export JOB_JAR=/home/chrisgerken/workspaces/Helios/hadoop/cassandra.example/target/tweet.groking-1.0.0-SNAPSHOT-jar-with-dependencies.jar;

}

function run_workflow () {

	/home/chrisgerken/Downloads/hadoop-0.20.2/bin/hadoop jar $JOB_JAR com.gerkenip.main.Workflow;

}

function run_entire_flow () {

	setup;

	run_workflow;

}
