
package cn.edu.uoh.cs.weatherforecast;

import java.util.HashMap;
import java.util.Map;

public class Yesterday {

    private String fl;
    private String fx;
    private String high;
    private String type;
    private String low;
    private String date;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The fl
     */
    public String getFl() {
        return fl;
    }

    /**
     * 
     * @param fl
     *     The fl
     */
    public void setFl(String fl) {
        this.fl = fl;
    }

    /**
     * 
     * @return
     *     The fx
     */
    public String getFx() {
        return fx;
    }

    /**
     * 
     * @param fx
     *     The fx
     */
    public void setFx(String fx) {
        this.fx = fx;
    }

    /**
     * 
     * @return
     *     The high
     */
    public String getHigh() {
        return high;
    }

    /**
     * 
     * @param high
     *     The high
     */
    public void setHigh(String high) {
        this.high = high;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The low
     */
    public String getLow() {
        return low;
    }

    /**
     * 
     * @param low
     *     The low
     */
    public void setLow(String low) {
        this.low = low;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
