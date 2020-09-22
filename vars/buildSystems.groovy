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
  
  stage('artifactory storing'){
            steps{
              
                    script {
                        def server = Artifactory.server(stageParams.servername)
               
                        def rtMaven = Artifactory.newMavenBuild()
                   
                        rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                        rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                        rtMaven.tool = stageParams.tool
                        def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install'
                        server.publishBuildInfo buildInfo
                    }
            
            }
        }
}
}

}
