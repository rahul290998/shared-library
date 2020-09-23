#!/usr/bin/env groovy
import org.apache.commons.lang.StringUtils
def call(Map stageParams)
{
pipeline
{
agent any
stages
{
stage('first step')
{
steps{
bat"mvn clean install"
}
  
}
  stage('deploying')
  {
    steps{
     deploy adapters: stageParams.adapter,
       contextPath: stageParams.context, 
       war: stageParams.warr
}
  }
  
}
}

}
