package Hotel;

import java.util.List;
import java.util.Map;

public interface IEditFoodItem extends IAddFoodItem, IRemoveFoodItem, IGetMenu {

    public void addFoodItems (FoodItem foodItem);

    public void removeFoodItem (String foodName);


}
