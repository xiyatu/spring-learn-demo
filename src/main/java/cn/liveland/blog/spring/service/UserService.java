package cn.liveland.blog.spring.service;

import cn.liveland.blog.spring.annotation.SimpleTransactional;
import cn.liveland.blog.spring.domain.mapper.NameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiyatu
 * @date 2019/7/16 14:42
 *
 */
@Service
public class UserService {


    @Autowired
    private NameMapper nameMapper;


    @SimpleTransactional
    public void saveName(String name) {
        nameMapper.saveName(name);
        nameMapper.saveName(name);
        throw new RuntimeException("系统错误");
    }

}
