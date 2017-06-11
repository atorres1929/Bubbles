package info.datahelix.bubbles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopContent {

    public static final List<ShopItem> ITEMS = new ArrayList<ShopItem>();

    public static final Map<String, ShopItem> ITEM_MAP = new HashMap<String, ShopItem>();

    static {
        // Add some sample items.
        addItem(createShopItem(100,         "Clicker - 1 Bubble"));
        addItem(createShopItem(1000,        "Factory - 10 Bubbles"));
        addItem(createShopItem(5000,        "Clicker Factory - 50 Bubbles"));
        addItem(createShopItem(10000,       "Factory that makes Clickers - 100 Bubbles"));
        addItem(createShopItem(50000,       "Factory that makes Factories - 500 Bubbles"));
        addItem(createShopItem(100000,      "Battery Killer - 1,000 Bubbles"));
        addItem(createShopItem(500000,      "You Should Stop Playing - 5,000 Bubbles"));
        addItem(createShopItem(1000000,     "Seriously. Stop. - 10,0000 Bubbles"));
        addItem(createShopItem(5000000,     "You Have No LIfe - 50,0000 Bubbles"));
        addItem(createShopItem(10000000,    "I Hope This Kills Your Phone - 100,000 Bubbles"));
    }

    private static void addItem(ShopItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static ShopItem createShopItem(int price, String name) {
        return new ShopItem(String.valueOf(price), name);
    }

    public static class ShopItem {
        public final String id;
        public final String content;

        public ShopItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
