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
package com.gerkenip.trending;

import java.util.ArrayList;

public class TrendingWorkflow {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		com.gerkenip.trending.Count.CountJob.main(args);
				

		com.gerkenip.trending.Sort.SortJob.main(args);
				
	}

}
