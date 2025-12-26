package worker.karyawan;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Karyawan;
import service.KaryawanService;
import view.KaryawanFrame;

public class UpdateKaryawanWorker extends SwingWorker<Void, Void> {
    private final KaryawanFrame frame;
    private final KaryawanService karyawanService;
    private final Karyawan karyawan;

    public UpdateKaryawanWorker(KaryawanFrame frame, KaryawanService karyawanService, Karyawan karyawan) {
        this.frame = frame;
        this.karyawanService = karyawanService;
        this.karyawan = karyawan;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Updating employee data...");
    }

    @Override
    protected Void doInBackground() throws Exception {
        karyawanService.updateKaryawan(karyawan);
        return null;
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            get();
            frame.getProgressBar().setString("Employee updated successfully");
            JOptionPane.showMessageDialog(frame,
                    "Employee record has been updated.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to update employee");
            JOptionPane.showMessageDialog(frame,
                    "Error updating data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}