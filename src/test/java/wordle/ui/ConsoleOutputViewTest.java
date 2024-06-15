package wordle.ui;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wordle.domain.Record;
import wordle.domain.Tile;
import wordle.fixture.RecordFixture;
import wordle.fixture.ResultFixture;

class ConsoleOutputViewTest {

    private PrintStream standardOut;
    private OutputStream captor;

    private ConsoleOutputView consoleOutputView;

    @BeforeEach
    protected final void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        consoleOutputView = new ConsoleOutputView();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    protected final void printOutput() {
        System.setOut(standardOut);
    }

    @Test
    void Record를_출력할_수_있다() {
        Record record = createRecordFixture();

        consoleOutputView.showRecord(record);

        assertThat(captor.toString()).isEqualTo("""
                ⬜🟩⬜🟨⬜
                ⬜⬜⬜🟨⬜
                ⬜🟩🟨🟨⬜
                ⬜🟩⬜🟨⬜
                ⬜⬜⬜⬜⬜
                🟩🟩🟩🟩🟩
                """);
    }

    @Test
    void 게임종료를_출력할_수_있다() {
        Record record = createRecordFixture();

        consoleOutputView.end(record);

        assertThat(captor.toString()).isEqualTo("""
                6/6
                                
                ⬜🟩⬜🟨⬜
                ⬜⬜⬜🟨⬜
                ⬜🟩🟨🟨⬜
                ⬜🟩⬜🟨⬜
                ⬜⬜⬜⬜⬜
                🟩🟩🟩🟩🟩
                """);
    }

    private static Record createRecordFixture() {
        return RecordFixture.create(
                ResultFixture.createResults(Tile.GRAY, Tile.GREEN, Tile.GRAY, Tile.YELLOW,
                        Tile.GRAY),
                ResultFixture.createResults(Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.YELLOW,
                        Tile.GRAY),
                ResultFixture.createResults(Tile.GRAY, Tile.GREEN, Tile.YELLOW, Tile.YELLOW,
                        Tile.GRAY),
                ResultFixture.createResults(Tile.GRAY, Tile.GREEN, Tile.GRAY, Tile.YELLOW,
                        Tile.GRAY),
                ResultFixture.createResults(Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY),
                ResultFixture.createResults(Tile.GREEN, Tile.GREEN, Tile.GREEN, Tile.GREEN,
                        Tile.GREEN)
        );
    }
}