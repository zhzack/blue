package xyz.blue.service.impl;


import org.springframework.stereotype.Service;
import xyz.blue.mapper.UserMapper;
import xyz.blue.pojo.User;
import xyz.blue.service.UserService;

//@Service
@Service("UserService")//别名

public class UserServiceImpl implements UserService {

    final
    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public int deleteByPrimaryKey(Integer user_id) {
        return userMapper.deleteByPrimaryKey(user_id);
    }

    @Override
    public int insert(User record) {

        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer user_id) {
        return userMapper.selectByPrimaryKey(user_id);
    }

    @Override
    public User selectByName(String user_name) {
        return userMapper.selectByName(user_name);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}

