package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface UserDataService {
    ArrayList<UserPO> getAll();

    ArrayList<UserPO> get(String username);

    ResultMessage insert(UserPO po);

    ResultMessage delete(UserPO po);

    ResultMessage update(UserPO po);
}
