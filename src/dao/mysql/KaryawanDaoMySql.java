package dao.mysql;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import dao.KaryawanDao;
import model.Karyawan;

public class KaryawanDaoMySql implements KaryawanDao {

    @Override
    public void insert(Karyawan karyawan) {
        String sql = "INSERT INTO employees (employee_id, name, department, position, salary, hire_date, email, phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, karyawan.getEmployeeId());
            statement.setString(2, karyawan.getName());
            statement.setString(3, karyawan.getDepartment());
            statement.setString(4, karyawan.getPosition());
            statement.setDouble(5, karyawan.getSalary());
            statement.setDate(6, Date.valueOf(karyawan.getHireDate()));
            statement.setString(7, karyawan.getEmail());
            statement.setString(8, karyawan.getPhone());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Karyawan karyawan) {
        String sql = "UPDATE employees SET employee_id = ?, name = ?, department = ?, position = ?, " +
                    "salary = ?, hire_date = ?, email = ?, phone = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, karyawan.getEmployeeId());
            statement.setString(2, karyawan.getName());
            statement.setString(3, karyawan.getDepartment());
            statement.setString(4, karyawan.getPosition());
            statement.setDouble(5, karyawan.getSalary());
            statement.setDate(6, Date.valueOf(karyawan.getHireDate()));
            statement.setString(7, karyawan.getEmail());
            statement.setString(8, karyawan.getPhone());
            statement.setInt(9, karyawan.getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Karyawan> findAll() {
        List<Karyawan> karyawanList = new ArrayList<>();
        String sql = "SELECT * FROM employees ORDER BY id";
        
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                Karyawan karyawan = new Karyawan();
                karyawan.setId(resultSet.getInt("id"));
                karyawan.setEmployeeId(resultSet.getString("employee_id"));
                karyawan.setName(resultSet.getString("name"));
                karyawan.setDepartment(resultSet.getString("department"));
                karyawan.setPosition(resultSet.getString("position"));
                karyawan.setSalary(resultSet.getDouble("salary"));
                
                Date hireDate = resultSet.getDate("hire_date");
                if (hireDate != null) {
                    karyawan.setHireDate(hireDate.toLocalDate());
                }
                
                karyawan.setEmail(resultSet.getString("email"));
                karyawan.setPhone(resultSet.getString("phone"));
                
                karyawanList.add(karyawan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return karyawanList;
    }
}