package course.examples.networking.url;

public class Country {
    public String name;
    public String code;
    public String urlMapImage;

    public Country(String iName, String iCode) {
        name = iName;
        code = iCode;
        urlMapImage = String.format("https://img.geonames.org/img/country/250/%s.png", code);
    }
}
