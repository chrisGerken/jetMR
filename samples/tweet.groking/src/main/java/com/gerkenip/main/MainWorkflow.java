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
package com.gerkenip.main;

import java.util.ArrayList;

public class MainWorkflow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		com.gerkenip.main.ReadTweets.ReadTweetsJob.main(args);
				

		com.gerkenip.main.FilterByTerm.FilterByTermJob.main(args);
				

		com.gerkenip.main.FilterByLink.FilterByLinkJob.main(args);
				

		com.gerkenip.main.FilterByHashtag.FilterByHashtagJob.main(args);
				

		com.gerkenip.main.Join.JoinJob.main(args);
				
	}

}
