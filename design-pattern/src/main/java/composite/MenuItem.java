package composite;

import java.util.Optional;

/**
 * Created by Lankidd on 2017/3/26.
 */
public class MenuItem implements MenuComponent {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MenuComponent> getChild(int index) {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("-----------");
        System.out.println(name);
    }
}
