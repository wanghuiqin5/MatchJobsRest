package rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;

import job.JobInfo;
import job.JobManager;
import job.Location;
import util.LocationUtil;
import worker.JobSearchAddress;
import worker.Worker;
import worker.WorkerManager;

@Path("/jobs")
public class JobMatchResource {
    
    /**
     * Get up to 3 matched job for a given worker id. URL: /jobs/{workerId}
     * @param workerId
     * @return
     */
    @Path("{workerId}")
    @GET
    @Produces("application/json")
    public String getMatchJobs(@PathParam(value="workerId") int workerId){
        System.out.println("Matched job for worker "+workerId);
        List<JobInfo> jobList = JobManager.getJobList();
        Worker worker = WorkerManager.getWorker(workerId);
        List<JobInfo> matchedJobList = new ArrayList<JobInfo>();

        if(worker !=null && jobList !=null && jobList.size()>0){
            //filter with required properties
            matchedJobList = jobList.stream().filter(job->isDriverLicenseMatched(job,worker)
                    && isRequiredCertsMatched(job,worker)
                    && isSkillsMatched(job,worker)
                    && isLocationMatched(job,worker)
                    ).limit(3).collect(Collectors.toList());
            System.out.println("Matched job for worker "+workerId+": "+matchedJobList.size());
            System.out.println("Matched job for worker "+workerId+": "+new Gson().toJson(matchedJobList));
        }else
            matchedJobList = jobList;
        return new Gson().toJson(matchedJobList);
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
    
    public static boolean isLocationMatched(JobInfo job, Worker worker){
        Location location = job.getLocation();
        JobSearchAddress jobSearchAddress = worker.getJobSearchAddress();
        String unit = jobSearchAddress.getUnit();
        
        double distance = LocationUtil.getDistance(location.getLatitude(), location.getLongitude(),
                jobSearchAddress.getLatitude(), jobSearchAddress.getLongitude(),unit);
        
        int ret = new Double(jobSearchAddress.getMaxJobDistance()).compareTo(new Double(distance));
        if(ret>=0)
            return true;
        else
            return false;
    }
    
    
    public static void main(String[] args){
        int workerID = -1;
        List<JobInfo> jobList = JobManager.getJobList();
        List<Worker> workerList = WorkerManager.getWorkerList();
        while(workerID++<50){
            
            Worker worker = WorkerManager.getWorker(workerList,workerID);
            List<JobInfo> matchedJobList = new ArrayList<JobInfo>();

            if(worker !=null && jobList !=null && jobList.size()>3){
                //filter with required properties
                matchedJobList = jobList.stream().filter(job->isDriverLicenseMatched(job,worker)
                        && isRequiredCertsMatched(job,worker)
                        && isSkillsMatched(job,worker)
                        && isLocationMatched(job,worker)
                        ).limit(3).collect(Collectors.toList());
                System.out.println("Matched job for worker "+workerID+": "+matchedJobList.size());
                System.out.println("Matched job for worker "+workerID+": "+new Gson().toJson(matchedJobList));

            }else
                matchedJobList = jobList;
        }
    }

}
