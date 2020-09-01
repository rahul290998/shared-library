#!/usr/bin/env groovy

import org.apache.commons.lang.StringUtils

def call(String filter_string, int occurrence) {
    pipeline{
      agent any
        stages{

              stage('maven build'){
                  steps{
                      script{
		    	                bat "mvn clean compile"
                      	  }
               	     }  
                 }	
        }
}

    
    
    
    def logs = currentBuild.rawBuild.getLog(10000).join('\n')
    int count = StringUtils.countMatches(logs, filter_string);
    if (count > occurrence -1) {
        currentBuild.result='UNSTABLE'
    }

}
