package wordle.fixture;

import java.util.stream.IntStream;
import wordle.domain.Result;
import wordle.domain.Tile;

public class ResultFixture {

    public static Result[] createGreenResults(int count) {
        return IntStream.range(0, count)
                .mapToObj(ResultFixture::createGreenResult)
                .toArray(Result[]::new);
    }

    public static Result createGreenResult(int position) {
        return new Result(Tile.GREEN, position);
    }

    public static Result createYellowResult(int position) {
        return new Result(Tile.YELLOW, position);
    }

    public static Result createGrayResult(int position) {
        return new Result(Tile.GRAY, position);
    }
}