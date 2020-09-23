#!/usr/bin/env groovy
import org.apache.commons.lang.StringUtils
def call(Map stageParams)
{
pipeline
{
agent any
stages
{
  stage('deploying')
  {
    steps
    {
     deploy adapters: [tomcat8(credentialsId: '5866d9c5-7497-4a2f-9477-39037e355cb6', path: '', url:'http://localhost:8083/')],
       contextPath: stageParams.context, 
       war: stageParams.warr  
    }
  }
  
}
}

}
