package worker.karyawan;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Karyawan;
import service.KaryawanService;
import view.KaryawanFrame;

public class SaveKaryawanWorker extends SwingWorker<Void, Void> {
    private final KaryawanFrame frame;
    private final KaryawanService karyawanService;
    private final Karyawan karyawan;

    public SaveKaryawanWorker(KaryawanFrame frame, KaryawanService karyawanService, Karyawan karyawan) {
        this.frame = frame;
        this.karyawanService = karyawanService;
        this.karyawan = karyawan;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Saving new employee...");
    }

    @Override
    protected Void doInBackground() throws Exception {
        karyawanService.createKaryawan(karyawan);
        return null;
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            get(); // To catch any exception
            frame.getProgressBar().setString("Employee saved successfully");
            JOptionPane.showMessageDialog(frame,
                    "New employee record has been saved.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to save employee");
            JOptionPane.showMessageDialog(frame,
                    "Error saving data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}