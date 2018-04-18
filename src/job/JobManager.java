package job;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import util.RestfulRequest;

public class JobManager {

    public static final String JOBSURL = "http://test.swipejobs.com/api/jobs";

    // get all jobs
    public static List<JobInfo> getJobList() {
        List<JobInfo> jobList = new ArrayList<JobInfo>();
        Gson gson = new Gson();
        RestfulRequest rest = new RestfulRequest(JOBSURL);
        rest.sendRequest();
        if (rest.isSuccess()) {
            String ret = rest.getReturnData();
            Type type = new TypeToken<List<JobInfo>>() {}.getType();
            jobList = gson.fromJson(ret, type);
            System.out.println("Jobs number returned:" + jobList.size());
            System.out.println("Jobs:" + gson.toJson(jobList));
            return jobList;
        } else {
            System.out.println("Jobs Rest request failed:" + rest.toString());
            return null;
        }
    }

    public static void main(String[] args) {
        getJobList();
    }

}
