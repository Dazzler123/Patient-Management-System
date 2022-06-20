package entity;

public class Patient {
    private String nic; //primary key
    private String name;
    private String mobile;
    private String address;
    private int age;
    private String gender;
    private String weight;
    private String history;
    private String other_issues;

    public Patient(String nic, String name, String mobile, String address, int age, String gender, String weight, String history, String other_issues) {
        this.nic = nic;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.history = history;
        this.other_issues = other_issues;
    }

    public Patient() {
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getOther_issues() {
        return other_issues;
    }

    public void setOther_issues(String other_issues) {
        this.other_issues = other_issues;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", weight='" + weight + '\'' +
                ", history='" + history + '\'' +
                ", other_issues='" + other_issues + '\'' +
                '}';
    }
}
