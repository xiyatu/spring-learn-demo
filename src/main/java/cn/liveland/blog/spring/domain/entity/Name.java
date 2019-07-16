package cn.liveland.blog.spring.domain.entity;

import com.baomidou.mybatisplus.annotations.TableId;

/**
 * @author xiyatu
 * @date 2019/7/16 15:01
 * @description
 */
public class Name {

    @TableId
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
