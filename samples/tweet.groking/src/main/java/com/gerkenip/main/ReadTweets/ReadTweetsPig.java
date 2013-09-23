/*
 * 
 * This source code and information are provided "AS-IS" without 
 * warranty of any kind, either expressed or implied, including
 * but not limited to the implied warranties of merchantability
 * and/or fitness for a particular purpose.
 * 
 * This source code was generated using an evaluation copy 
 * of the Cassandra/Hadoop Accelerator and may not be used for
 * production purposes.
 *
 * For more information, contact Chris Gerken at
 * chris.gerken@gerkenip.com
 * 
 */
package com.gerkenip.main.ReadTweets;

import org.apache.pig.PigServer;

public class ReadTweetsPig {

	public static void main(String[] args) {

		try {
			
			PigServer pigServer = new PigServer("local");
			
			pigServer.registerScript("/home/chrisgerken/workspaces/indigo/stage01/tweet.groking/src/main/resources/pig/main/readtweets.pig");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

}
