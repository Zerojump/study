package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Lankidd on 2017/3/24.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentConditionsDisplay{" +
                    "temperature=" + temperature +
                    ", humidity=" + humidity +
                    '}');
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            temperature = weatherData.getTemperature();
            humidity = weatherData.getHumidity();
            display();
        }
    }
}
