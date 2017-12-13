package com.gi.ctdn.dao;

import com.gi.ctdn.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Admin selectByLogin(@Param("login") String login);
}
