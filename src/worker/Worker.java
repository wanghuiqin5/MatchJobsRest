package worker;

import java.util.List;

public class Worker {
    
    int rating;
    boolean isActive;
    List<String> certificates;
    List<String> skills;
    JobSearchAddress jobSearchAddress;
    String transportation;
    boolean hasDriversLicense;
    //
    String phone;
    String email;
    //
    int userId;
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public List<String> getCertificates() {
        return certificates;
    }
    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    public JobSearchAddress getJobSearchAddress() {
        return jobSearchAddress;
    }
    public void setJobSearchAddress(JobSearchAddress jobSearchAddress) {
        this.jobSearchAddress = jobSearchAddress;
    }
    public String getTransportation() {
        return transportation;
    }
    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }
    public boolean isHasDriversLicense() {
        return hasDriversLicense;
    }
    public void setHasDriversLicense(boolean hasDriversLicense) {
        this.hasDriversLicense = hasDriversLicense;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
    

}
