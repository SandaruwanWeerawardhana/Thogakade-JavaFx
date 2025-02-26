package Service.Custom.Impl;

import Model.Item;
import Service.Custom.ItemService;
import Util.DaoType;
import javafx.collections.ObservableList;
import repository.DaoFactory;
import repository.custom.ItemDao;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private static ItemServiceImpl instance;

    private ItemServiceImpl(){}

    public static ItemServiceImpl getInstance() {
        return instance==null? instance = new ItemServiceImpl():instance;
    }

    ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
    @Override
    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        List<Item> itemList = itemDao.getAll();
        return itemList;
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return itemDao.save(item);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(String Id) throws SQLException, ClassNotFoundException {
        return itemDao.delete(Id);
    }

    @Override
    public Item search(String Id) throws SQLException, ClassNotFoundException {
        return itemDao.search(Id);
    }

    @Override
    public ObservableList<String> getId() throws SQLException, ClassNotFoundException {
        return itemDao.getId();
    }
}
