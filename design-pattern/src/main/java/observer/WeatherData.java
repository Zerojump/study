package observer;

import java.util.Observable;

/**
 * Created by Lankidd on 2017/3/24.
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChange() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChange();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
}
