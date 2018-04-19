package job;

import java.util.List;

public class JobInfo {
    int jobId;
    boolean driverLicenseRequired;
    List<String> requiredCertificates;
    Location location;
    int workersRequired;
    String jobTitle;
    String company;
    String about;
    String startDate;
    String billRate;
    public int getJobId() {
        return jobId;
    }
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public boolean isDriverLicenseRequired() {
        return driverLicenseRequired;
    }
    public void setDriverLicenseRequired(boolean driverLicenseRequired) {
        this.driverLicenseRequired = driverLicenseRequired;
    }
    public List<String> getRequiredCertificates() {
        return requiredCertificates;
    }
    public void setRequiredCertificates(List<String> requiredCertificates) {
        this.requiredCertificates = requiredCertificates;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public int getWorkersRequired() {
        return workersRequired;
    }
    public void setWorkersRequired(int workersRequired) {
        this.workersRequired = workersRequired;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getBillRate() {
        return billRate;
    }
    public void setBillRate(String billRate) {
        this.billRate = billRate;
    }
    
    
}
