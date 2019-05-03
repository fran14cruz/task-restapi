package me.restapi.dao.mapper;

import me.restapi.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TaskMapper implements RowMapper {
    @Override
    public Task mapRow(ResultSet rs, int i) throws SQLException {
        Task task = new Task();

        task.setUuid(rs.getObject("id", UUID.class));
        task.setStatus(rs.getObject("status", String.class));
        task.setTimestamp(rs.getObject("date", String.class));

        return task;
    }
}
