package worker.karyawan;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import model.Karyawan;
import service.KaryawanService;
import view.KaryawanFrame;

public class LoadKaryawanWorker extends SwingWorker<List<Karyawan>, Void> {
    private final KaryawanFrame frame;
    private final KaryawanService karyawanService;

    public LoadKaryawanWorker(KaryawanFrame frame, KaryawanService karyawanService) {
        this.frame = frame;
        this.karyawanService = karyawanService;
        frame.getProgressBar().setIndeterminate(true);
        frame.getProgressBar().setString("Loading employee data...");
    }

    @Override
    protected List<Karyawan> doInBackground() throws Exception {
        return karyawanService.getAllKaryawan();
    }

    @Override
    protected void done() {
        frame.getProgressBar().setIndeterminate(false);
        try {
            List<Karyawan> result = get();
            frame.getProgressBar().setString(result.size() + " records loaded");
        } catch (Exception e) {
            frame.getProgressBar().setString("Failed to load data");
            JOptionPane.showMessageDialog(frame,
                    "Error loading data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}