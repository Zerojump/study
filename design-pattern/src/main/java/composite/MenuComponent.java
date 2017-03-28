package composite;

import java.util.Optional;

/**
 * Created by Lankidd on 2017/3/26.
 */
public interface MenuComponent {
    void add(MenuComponent menuComponent);

    void remove(MenuComponent menuComponent);

    Optional<MenuComponent> getChild(int index);

    String getName();

    void print();
}
