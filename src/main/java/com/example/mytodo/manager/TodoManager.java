package com.example.mytodo.manager;

import com.example.mytodo.db.DBConnectionProvider;
import com.example.mytodo.model.ToDo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoManager {

    Connection connection = DBConnectionProvider.getInstance().getConnection();

    public List<ToDo> getAllTodos() {
        String sql = "SELECT * FROM todos";
        List<ToDo> todos = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                todos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(Date.valueOf(resultSet.getDate("created_date").toLocalDate()))
                        .finishDate(resultSet.getDate("finish_date") != null ?
                                Date.valueOf(resultSet.getDate("finish_date").toLocalDate()) : null)
                        .userId(resultSet.getInt("user_id"))
                        .status(resultSet.getString("status"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public ToDo getToDoById(int id) {
        String sql = "SELECT * FROM todos WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .userId(resultSet.getInt("user_id"))
                        .status(resultSet.getString("status"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void add(ToDo toDo) {
        String sql = "INSERT INTO todos(title, created_date, finish_date, user_id, status) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, toDo.getTitle());
            ps.setDate(2, new Date(toDo.getCreatedDate().getTime()));
            if (toDo.getFinishDate() != null) {
                ps.setDate(3, new Date(toDo.getFinishDate().getTime()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setInt(4, toDo.getUserId());
            ps.setString(5, toDo.getStatus());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    toDo.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ToDo todo) {
        String sql = "UPDATE todos SET title = ?, created_date = ?, finish_date = ?, user_id = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, todo.getTitle());

            if (todo.getCreatedDate() != null) {
                preparedStatement.setDate(2, new java.sql.Date(todo.getCreatedDate().getTime()));
            } else {
                preparedStatement.setNull(2, java.sql.Types.DATE);
            }

            preparedStatement.setInt(4, todo.getUserId());
            preparedStatement.setString(5, todo.getStatus());
            preparedStatement.setInt(6, todo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean todoTitleExists(String toDoTitle) {
        String sql = "SELECT COUNT(*) FROM todos WHERE title = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, toDoTitle);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteTodo(int id) {
        String sql = "DELETE FROM todos WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
