package com.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import job.JobInfo;
import job.JobManager;
import worker.Worker;
import worker.WorkerManager;

@Path("/jobs")
public class JobMatchResource {
    
    @Path("{workerId}")
    @GET
    @Produces("application/json")
    public void getMatchJobs(@PathParam(value="workerId") int workerId){
        
    }
    
    
    public static boolean isDriverLicenseMatched(JobInfo job, Worker worker){
        return job.isDriverLicenseRequired()==worker.isHasDriversLicense();
    }
   
    public static boolean isRequiredCertsMatched(JobInfo job, Worker worker){
        List<String> requiredCerts = job.getRequiredCertificates();
        List<String> certs = worker.getCertificates();
        
        if(requiredCerts==null || requiredCerts.isEmpty())
            return true;
        
        if(certs==null || certs.isEmpty())
            return false;
        
        for(String cert: requiredCerts){
            if(!certs.contains(cert))
                return false;
        }
        return true;
    }
    
    public static boolean isSkillsMatched(JobInfo job, Worker worker){
        List<String> skills = worker.getSkills();
        String jobTitle =  job.getJobTitle();
        
        if(skills==null || skills.isEmpty())
            return false;
        
        return skills.contains(jobTitle);
    }
    
    public static void main(String[] args){
        int workerID = 4;
        List<JobInfo> jobList = JobManager.getJobList();
        Worker worker = WorkerManager.getWorker(workerID);
        List<JobInfo> matchedJobList = new ArrayList<JobInfo>();
        
        if(worker !=null && jobList !=null && jobList.size()>3){
            //filter with required properties
            matchedJobList = jobList.stream().filter(job->isDriverLicenseMatched(job,worker)
                    && isRequiredCertsMatched(job,worker)
                    && isSkillsMatched(job,worker)
                    ).collect(Collectors.toList());
            System.out.println(matchedJobList.size());
            
        }else
            matchedJobList = jobList;
        
    }

}
