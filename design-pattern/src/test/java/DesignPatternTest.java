import command.*;
import command.command.*;
import command.receiver.FanReceiver;
import command.receiver.LightReceiver;
import composite.Menu;
import composite.MenuComponent;
import composite.MenuItem;
import decorator.Beverage;
import decorator.component.Espresso;
import decorator.component.HouseBlend;
import decorator.decorator.Mocha;
import decorator.decorator.Whip;
import observer.CurrentConditionsDisplay;
import observer.WeatherData;
import org.junit.Test;
import state.GumballMachine;
import strategy.Duck;
import strategy.FlyWithWing;
import strategy.TiancaiDuck;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Lankidd on 2017/3/23.
 */
public class DesignPatternTest {
    @Test
    public void testStrategy() throws Exception {
        Duck duck = new TiancaiDuck();
        duck.performFly();
        duck.setFlyBehavior(new FlyWithWing());
        duck.performFly();
    }

    @Test
    public void testObserver() throws Exception {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(20.6f, 13.0f, 21.5f);
    }

    @Test
    public void testDecorator() throws Exception {
        Beverage espresso = new Espresso();
        System.out.println("espresso = " + espresso);
        System.out.println("espresso.cost() = " + espresso.cost());

        Beverage houseBlend = new HouseBlend();
        houseBlend = new Whip(houseBlend);
        houseBlend = new Mocha(houseBlend);
        System.out.println("houseBlend = " + houseBlend);
        System.out.println("houseBlend.cost() = " + houseBlend.cost());
    }

    @Test
    public void testCommand() throws Exception {
        WaitressInvoker waitressInvoker = new WaitressInvoker(3);

        LightReceiver light = new LightReceiver("light");
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);
        waitressInvoker.setOnCommands(0, lightOnCommand, lightOffCommand);

        FanReceiver fan = new FanReceiver("fan");
        FanHighCommand fanHighCommand = new FanHighCommand(fan);
        FanOffCommand fanOffCommand = new FanOffCommand(fan);
        waitressInvoker.setOnCommands(1, fanHighCommand, fanOffCommand);

        MacroCommand macroOnCommand = new MacroCommand(lightOnCommand, fanHighCommand);
        MacroCommand macroOffCommand = new MacroCommand(lightOffCommand, fanOffCommand);
        waitressInvoker.setOnCommands(2, macroOnCommand, macroOffCommand);

        waitressInvoker.onCommandPushed(2);
        waitressInvoker.offCommandPushed(2);
        waitressInvoker.onCommandPushed(0);
        waitressInvoker.undoCommandPushed();
    }

    @Test
    public void testComposite() throws Exception {
        MenuItem item1 = new MenuItem("tomato");
        MenuItem item2 = new MenuItem("potato");
        MenuItem item3 = new MenuItem("milk");
        MenuItem item4 = new MenuItem("bean");
        Menu menu = new Menu("food");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        List<MenuComponent> menuComponentList = Arrays.asList(item4, menu);
        menuComponentList.forEach(MenuComponent::print);
    }

    @Test
    public void testState() throws Exception {
        GumballMachine gumballMachine = new GumballMachine(2);
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.dispense();

        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.dispense();

        gumballMachine.insertQuarter();

        gumballMachine.refillBall(1);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.dispense();

        gumballMachine.insertQuarter();
    }
}
