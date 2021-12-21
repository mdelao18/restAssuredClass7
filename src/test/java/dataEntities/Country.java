package dataEntities;

public class Country {

    private String postCode;
    private String country;
    private String countryAbbrev;
    private String place[];

    public Country(String postCode, String country, String countryAbbrev, String[] place) {
        this.postCode = postCode;
        this.country = country;
        this.countryAbbrev = countryAbbrev;
        this.place = place;
    }


    public String[] getPlace() {
        return place;
    }

    public void setPlace(String[] place) {
        this.place = place;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryAbbrev() {
        return countryAbbrev;
    }

    public void setCountryAbbrev(String countryAbbrev) {
        this.countryAbbrev = countryAbbrev;
    }
}


