package dao;

import java.util.List;
import model.Karyawan;

public interface KaryawanDao {
    void insert(Karyawan karyawan);
    void update(Karyawan karyawan);
    void delete(int id);
    List<Karyawan> findAll();
}