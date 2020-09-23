#!/usr/bin/env groovy
import org.apache.commons.lang.StringUtils
def call(Map parameters)
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
     deploy adapters: parameters.adapter,
       contextPath: parameters.context, 
       war: parameters.warr  
    }
  }
  
}
}

}
