package com.teamwork.kejizhai.dao;

import com.teamwork.kejizhai.bean.shoppingcart;
import java.sql.SQLException;
import java.util.List;

public interface shoppingcartDao {

    boolean addshoppingcart(shoppingcart shoppingcart) throws SQLException;
    List<shoppingcart> getshoppingcart(int Iuid) throws SQLException;
    boolean deleteShoppingcart(int scid) throws SQLException;
}