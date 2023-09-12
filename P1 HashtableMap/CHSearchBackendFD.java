import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CHSearchBackendFD implements CHSearchBackendInterface {

    @Override
    public void loadData(String filename) throws FileNotFoundException {
        System.out.println("data");
    }

    @Override
    public List<String> findPostsByTitleWords(String words) {
        List<String> text = new ArrayList<String>();
        text.add("Title");
        text.add("Title1");
        text.add("Title2");
        return text;
    }

    @Override
    public List<String> findPostsByBodyWords(String words) {
        List<String> text = new ArrayList<String>();
        text.add("Body");
        text.add("Body1");
        text.add("Body2");
        return text;
    }

    @Override
    public List<String> findPostsByTitleOrBodyWords(String words) {
        List<String> text = new ArrayList<String>();
        text.add("Post");
        text.add("Post1");
        text.add("Post2");
        return text;
    }

    @Override
    public String getStatisticsString() {
        String statsPlaceholder = "Placeholder";
        return statsPlaceholder;
    }

}
