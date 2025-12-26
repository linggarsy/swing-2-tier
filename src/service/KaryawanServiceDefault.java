package service;

import java.util.List;
import dao.KaryawanDao;
import dao.mysql.KaryawanDaoMySql;
import model.Karyawan;

public class KaryawanServiceDefault implements KaryawanService {
    private final KaryawanDao karyawanDao = new KaryawanDaoMySql();

    @Override
    public void createKaryawan(Karyawan karyawan) {
        karyawanDao.insert(karyawan);
    }

    @Override
    public void updateKaryawan(Karyawan karyawan) {
        karyawanDao.update(karyawan);
    }

    @Override
    public void deleteKaryawan(Karyawan karyawan) {
        karyawanDao.delete(karyawan.getId());
    }

    @Override
    public List<Karyawan> getAllKaryawan() {
        return karyawanDao.findAll();
    }
}