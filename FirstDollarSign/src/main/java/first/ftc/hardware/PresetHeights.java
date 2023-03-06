package first.ftc.hardware;

import androidx.annotation.*;

import java.util.Optional;

/**
 * Preset Height levels for competition.<br>
 */
@SuppressWarnings("unused")
public enum PresetHeights {
    /**
     * Default Height to be at (or when grabbing from human player portal).
     */
    GROUND0(0),
    /**
     * Ground Junction. Scores 2 points.
     */
    GROUND_JUNCTION(1),
    /**
     * Lowest Pole Junction. Scores 3 points.
     */
    LOW_JUNCTION(2),
    /**
     * Medium Pole Junction. Scores 4 points.
     */
    MID_JUNCTION(3),
    /**
     * Highest Pole Junction. Scores 5 points.
     */
    HIGH_JUNCTION(4),
    /**
     * Lowest Cone of the cone stack.
     */
    CONE_STACK_1(11),
    /**
     * 2nd Cone of the cone stack.
     */
    CONE_STACK_2(12),
    /**
     * 3rd Cone of the cone stack.
     */
    CONE_STACK_3(13),
    /**
     * 4th Cone of the cone stack.
     */
    CONE_STACK_4(14),
    /**
     * Top Most Cone of the cone stack.
     */
    CONE_STACK_5(15);
    /**
     * Internal private ID.
     */
    private final int id;

    /**
     * Creates a new preset
     * @param id internal id for retrieving the identifier
     */
    PresetHeights(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    @NonNull
    @SuppressWarnings("NewApi")
    public Optional<Integer> getPoints() {
        switch(this){
            case GROUND_JUNCTION: return Optional.of(2);
            case LOW_JUNCTION: return Optional.of(3);
            case MID_JUNCTION: return Optional.of(4);
            case HIGH_JUNCTION: return Optional.of(5);
            default: return Optional.empty();
        }
    }
    public static int getIdOf(@NonNull PresetHeights height) {
        return height.getId();
    }
    @NonNull
    public static Optional<Integer> getPointsOf(@NonNull PresetHeights height) {
        return height.getPoints();
    }
    /**
     * Retrieves the preset by id
     * @param id {@link Integer int} id of the Preset
     * @return the Preset height, otherwise {@link PresetHeights#GROUND0} if it fails
     */
    @NonNull
    public static PresetHeights fromId(int id) {
        for(PresetHeights height : PresetHeights.values()) {
            if(height.getId() == id) return height;
        }
        return PresetHeights.GROUND0;
    }
}
