/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CountryManagement;

/**
 *
 * @author hoangson
 */
public class EastAsiaCountries extends Country implements Comparable<EastAsiaCountries> {

    private String countryTerrain;

    public EastAsiaCountries() {
    }

    public EastAsiaCountries(String countryTerrain) {
        this.countryTerrain = countryTerrain;
    }

    public EastAsiaCountries(String countryCode, String countryName, float totalArea, String countryTerrain) {
        super(countryCode, countryName, totalArea);
        this.countryTerrain = countryTerrain;
    }

    public String getCountryTerrain() {
        return countryTerrain;
    }

    public void setCountryTerrain(String countryTerrain) {
        this.countryTerrain = countryTerrain;
    }

    @Override
    public void display() {
        System.out.format("%-20s%-20s%-20s%-20s\n", this.countryCode, this.countryName, this.totalArea, this.countryTerrain);
        System.out.println();
    }

    @Override
    public int compareTo(EastAsiaCountries t) {
        return this.getCountryName().compareToIgnoreCase(t.getCountryName());
    }
}
