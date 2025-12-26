package worker.karyawan;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Karyawan;
import service.KaryawanService;
import view.KaryawanFrame;

public class DeleteKaryawanWorker extends SwingWorker<Void, Void> {
    private final KaryawanFrame frame;
    private final KaryawanService karyawanService;
    private final Karyawan karyawan;

    public DeleteKaryawanWorker(KaryawanFrame frame, KaryawanService karyawanService, Karyawan karyawan) {
        this.frame = frame;
        this.karyawanService = karyawanService;
        this.karyawan = karyawan;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Deleting employee record...");
    }

    @Override
    protected Void doInBackground() throws Exception {
        karyawanService.deleteKaryawan(karyawan);
        return null;
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            get();
            frame.getProgressBar().setString("Employee deleted successfully");
            JOptionPane.showMessageDialog(frame,
                    "Employee record has been deleted.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to delete employee");
            JOptionPane.showMessageDialog(frame,
                    "Error deleting data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}