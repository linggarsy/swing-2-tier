package service;

import java.util.List;
import model.Karyawan;

public interface KaryawanService {
    void createKaryawan(Karyawan karyawan);
    void updateKaryawan(Karyawan karyawan);
    void deleteKaryawan(Karyawan karyawan);
    List<Karyawan> getAllKaryawan();
}