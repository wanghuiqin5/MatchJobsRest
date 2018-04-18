package worker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import util.RestfulRequest;

public class WorkerManager {
    
    public static final String WORKERURL = "http://test.swipejobs.com/api/workers";
    
    //get worker information with work id
    public static Worker getWorker(int id){
        List<Worker> workerList = new ArrayList<Worker>();
        Gson gson = new Gson();
        RestfulRequest rest = new RestfulRequest(WORKERURL);
        rest.sendRequest();
        if(rest.isSuccess()){
            String data = rest.getReturnData();
            Type type = new TypeToken<List<Worker>>() {}.getType();
            if(data==null){
                System.out.println("No any worker information returned from Rest request");
                return null;
            }
            workerList = gson.fromJson(data, type);
            System.out.println("Worker number returned:" + workerList.size());
            List<Worker> list = workerList.stream().filter(worker -> worker.userId==id).collect(Collectors.toList());
            if(list!=null && list.size()>0){
                Worker worker =  list.get(0);
                System.out.println("The worker information for id "+id +":"+gson.toJson(worker));
            }else{
                System.out.println("No worker information with id "+id+" returned from Rest");
                return null;
            }
        }else{
            System.out.println("Workers Rest request failed:"+rest.toString());
            return null;
        }
        return null;
    }
    
    
    public static void main(String[] args){
        getWorker(5);
        getWorker(-1);
    }

}
