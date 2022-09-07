import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public void addRestaurantDetails(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
    public boolean isRestaurantOpen()
    {
        LocalTime timeNow = getCurrentTime();
        return timeNow.isBefore(this.closingTime) && timeNow.isAfter(this.openingTime);
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    //TDD approach, writing this method to make the failed test case pass
    public int calulatePrice(List<String> selectedItems)
    {
        int totalPrice = 0;
        for(String dishName: selectedItems)
        {
            for(Item item: menu)
            {
                if(dishName.equals(item.getName()))
                    totalPrice += item.getPrice();
            }
        }

        return totalPrice;
    }

    public String getName() {
        return name;
    }

    public LocalTime getOpeningTime()
    {
        return openingTime;
    }

    public String getLocation()
    {
        return location;
    }

    public LocalTime getClosingTime()
    {
        return closingTime;
    }

}
