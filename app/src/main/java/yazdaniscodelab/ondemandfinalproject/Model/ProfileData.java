package yazdaniscodelab.ondemandfinalproject.Model;

/**
 * Created by Yazdani on 10/18/2018.
 */

public class ProfileData {

    private String name;
    private String phone;
    private String district;
    private String address;
    private String id;
    private String date;

    public ProfileData(){

    }

    public ProfileData(String name, String phone, String district, String address, String id, String date) {
        this.name = name;
        this.phone = phone;
        this.district = district;
        this.address = address;
        this.id = id;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
