package interfaces;

public interface ShopInterface {
    public int getItemIndexByDescription(String description);
    public void showMenu();
    public void showShop(boolean isTraining) ;
    public void shop() ;
}
