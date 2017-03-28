package composite;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class Menu implements MenuComponent {
    private String name;
    private List<MenuComponent> componentList = new LinkedList<>();

    public Menu(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        componentList.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        componentList.remove(menuComponent);
    }

    @Override
    public Optional<MenuComponent> getChild(int index) {
        return index < 0 || index > componentList.size() ? Optional.empty() : Optional.of(componentList.get(index));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("==========================");
        System.out.println(name);
        componentList.forEach(MenuComponent::print);
    }
}
