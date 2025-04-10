import md.com.jaru.andrusca.solidrancing.file.FileProcessor;
import md.com.jaru.andrusca.solidrancing.lap.BestLapCalculator;
import md.com.jaru.andrusca.solidrancing.lap.LapDataParser;
import md.com.jaru.andrusca.solidrancing.lap.RatingTableFormatter;

import java.nio.file.Path;

public class RacingLapsProcessorImpl implements RacingLapsProcessor{

    public static final String EXPECTED_FILE_EXTENSION = ".result";
    public static final String RESULT_FILE_EXTENSION = ".calculated";


    protected final FileProcessor fileProcessor;
    protected final LapDataParser lapDataParser;
    protected final BestLapCalculator bestLapCalculator;
    protected final RatingTableFormatter ratingTableFormatter;

    public RacingLapsProcessorImpl(FileProcessor fileProcessor, LapDataParser lapDataParser, BestLapCalculator bestLapCalculator, RatingTableFormatter ratingTableFormatter) {
        this.fileProcessor = fileProcessor;
        this.lapDataParser = lapDataParser;
        this.bestLapCalculator = bestLapCalculator;
        this.ratingTableFormatter = ratingTableFormatter;
    }


    @Override
    public int processDirectory(Path directoryPath) {
        return 0;
    }
}
