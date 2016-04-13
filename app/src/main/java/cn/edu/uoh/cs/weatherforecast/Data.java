
package cn.edu.uoh.cs.weatherforecast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    private String wendu;
    private String ganmao;
    private List<Forecast> forecast = new ArrayList<Forecast>();
    private Yesterday yesterday;
    private String city;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The wendu
     */
    public String getWendu() {
        return wendu;
    }

    /**
     * 
     * @param wendu
     *     The wendu
     */
    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    /**
     * 
     * @return
     *     The ganmao
     */
    public String getGanmao() {
        return ganmao;
    }

    /**
     * 
     * @param ganmao
     *     The ganmao
     */
    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    /**
     * 
     * @return
     *     The forecast
     */
    public List<Forecast> getForecast() {
        return forecast;
    }

    /**
     * 
     * @param forecast
     *     The forecast
     */
    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    /**
     * 
     * @return
     *     The yesterday
     */
    public Yesterday getYesterday() {
        return yesterday;
    }

    /**
     * 
     * @param yesterday
     *     The yesterday
     */
    public void setYesterday(Yesterday yesterday) {
        this.yesterday = yesterday;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
