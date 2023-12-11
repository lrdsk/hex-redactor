import forms.MainForm;
import utils.CustomFileReader;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        CustomFileReader customFileReader = new CustomFileReader(Path.of("D:\\Java_Web_Projects\\Guide from youtube\\tinkoff\\text.txt"));
        MainForm mainForm = new MainForm(customFileReader);
        mainForm.createForm();

    }
}