package by.itacademy.javaenterprise.seledtsova.dao;

import by.itacademy.javaenterprise.seledtsova.entity.Item;

import java.util.List;

public interface ItemDao {

    Item saveItem(Item item);

    List<Item> getAll();

    Item findItemByItemId(Long id);

    Item deleteItemById(Long id);

}
