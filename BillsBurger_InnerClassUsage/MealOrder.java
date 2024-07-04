package BillsBurger_InnerClassUsage;

import java.util.ArrayList;
import java.util.List;

public class MealOrder {

    private double price = 5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public MealOrder() {
        this(1);
    }

    public MealOrder(double conversionRate) {
        burger = new Burger("regular");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
        this.conversionRate = conversionRate;
    }

    public double getTotal() {
        double total = drink.price + burger.getPrice() + side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26s$%.2f"
                .formatted(burger, drink, side, "Total : ", getTotal());
    }

    public void addToppings(String... toppings) {
        burger.addToppings(toppings);
        /*ArrayList<Item> extras = new ArrayList<>();
        for (var t : toppings) {
            extras.add(new Item(t.name(), "topping",0));
        }
        burger.toppings = extras;*/

    }

    private class Item {

        private String type;
        private String name;
        private double price;

        public Item(String name, String type, double price) {
            this.type = type;
            this.name = name;
            this.price = price;
        }

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? MealOrder.this.price : 0);
        }
        // BillsBurger_InnerClassUsage.MealOrder.this.price е price от BillsBurger_InnerClassUsage.MealOrder


        public static double getPrice(double price, double rate) {
            return price * rate;
        }


        @Override
        public String toString() {
            return "%10s%15s $%.2f".formatted(type, name, getPrice(price, conversionRate));
        }
    }


    private class Burger extends Item {

        private enum  Extra {AVOCADO, BACON,CHEESE,KETCHUP,MAYO,MUSTARD,PICKLES;

            public  double getPrice() {
                return switch (this){
                    case AVOCADO -> 1.0;
                    case BACON, CHEESE -> 1.5;
                    default -> 0;
                };
            }
        }
        private List<Item> toppings;


        public Burger(String name) {
            super(name, "burger", 5.0);
            toppings = new ArrayList<>();
        }

        public double getPrice(){
            double total = super.price; //в противен случай price ще идва от BillsBurger_InnerClassUsage.MealOrder
            for(Item topping : toppings){
                total += topping.price;
            }
            return total;
        }

        public void toppingPrice() {
            for (var t : toppings) {
                switch (t.name){
                    case "CHEESE"-> t.price += 0.5;
                    case "BACON" -> t.price += 1;
                }
            }
        }

        public void addToppings(String... extraToppings){
            for( var t : extraToppings) {
                try {
                    Extra topping = Extra.valueOf(t.toUpperCase());
                    toppings.add(new Item(topping.name(), "topping", topping.getPrice()));
                }
                catch (IllegalArgumentException e){
                    System.out.println("No topping found for " +  t);
                }
            }
        }
        @Override
        public String toString() {
            StringBuilder itemized = new StringBuilder(super.toString());
            for (Item topping : toppings) {
                itemized.append("\n");
                itemized.append(topping);
            }
            return itemized.toString();

        }
    }
}
