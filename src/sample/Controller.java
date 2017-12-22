package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {
    public TextArea area2;
    private File a;
    public TextArea area1;
    private Stage stage;
    public void doClick(ActionEvent actionEvent) throws IOException{
        FileChooser f = new FileChooser();
        a = f.showOpenDialog(stage);
        FileWriter s = new FileWriter(a+"");
        s.write(area1.getText());
        s.close();
    }

    public void runButton(ActionEvent actionEvent) throws IOException{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessBuilder pb = new ProcessBuilder("src/sample/hello.sh");
                    Process p = pb.start();
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    BufferedReader stdError = new BufferedReader(new
                            InputStreamReader(p.getErrorStream()));

                    String s = "";
                    while ((s = stdInput.readLine()) != null) {
                        area2.appendText(s);
                    }
                    while ((s = stdError.readLine()) != null) {
                        area2.appendText(s);
                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    public void getStage(Stage stage) {
        this.stage = stage;
    }

    public void closeOp(ActionEvent actionEvent) {
    }
}
