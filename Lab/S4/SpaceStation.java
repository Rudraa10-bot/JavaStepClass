import java.util.*;

// Enum for Crew Ranks (not public, so it can stay in this file)
enum CrewRank {
    CADET(1), OFFICER(2), COMMANDER(3), CAPTAIN(4), ADMIRAL(5);

    private final int level;

    CrewRank(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

// Base class for crew members
class SpaceCrew {
    // Final attributes (cannot change after initialization)
    public final String crewId;
    public final String homeplanet;
    public final CrewRank initialRank;

    // Mutable attributes
    protected CrewRank currentRank;
    protected int skillLevel;
    protected int missionCount;
    protected int spaceHours;

    // Static fields
    public static final String STATION_NAME = "Stellar Odyssey";
    public static final int MAX_CREW_CAPACITY = 50;

    // Emergency recruitment (minimal info - random planet)
    public SpaceCrew(String crewId) {
        this(crewId, randomPlanet(), CrewRank.CADET, CrewRank.CADET, 0, 0, 0);
    }

    // Standard recruitment
    public SpaceCrew(String crewId, String homeplanet, CrewRank initialRank) {
        this(crewId, homeplanet, initialRank, initialRank, 0, 0, 0);
    }

    // Experienced transfer
    public SpaceCrew(String crewId, String homeplanet, CrewRank initialRank,
                     int missionCount, int skillLevel) {
        this(crewId, homeplanet, initialRank, initialRank, missionCount, skillLevel, 0);
    }

    // Full detailed profile
    public SpaceCrew(String crewId, String homeplanet, CrewRank initialRank,
                     CrewRank currentRank, int missionCount, int skillLevel, int spaceHours) {
        this.crewId = crewId;
        this.homeplanet = homeplanet;
        this.initialRank = initialRank;
        this.currentRank = currentRank;
        this.missionCount = missionCount;
        this.skillLevel = skillLevel;
        this.spaceHours = spaceHours;
    }

    // Final methods
    public final String getCrewIdentification() {
        return "Crew ID: " + crewId + ", Homeplanet: " + homeplanet + ", Initial Rank: " + initialRank;
    }

    public final boolean canBePromoted() {
        return currentRank.getLevel() < CrewRank.ADMIRAL.getLevel();
    }

    public final int calculateSpaceExperience() {
        return (missionCount * 10) + (skillLevel * 5) + (spaceHours / 100);
    }

    private static String randomPlanet() {
        String[] planets = {"Mars", "Venus", "Europa", "Titan", "Ganymede"};
        Random rand = new Random();
        return planets[rand.nextInt(planets.length)];
    }

    @Override
    public String toString() {
        return crewId + " (" + currentRank + ") from " + homeplanet +
                " | Missions: " + missionCount + ", Skills: " + skillLevel +
                ", Hours: " + spaceHours;
    }
}

// PilotCrew (final certifications)
class PilotCrew extends SpaceCrew {
    private final String flightCertification;

    public PilotCrew(String crewId, String homeplanet, CrewRank rank, String flightCertification) {
        super(crewId, homeplanet, rank);
        this.flightCertification = flightCertification;
    }

    public String getFlightCertification() {
        return flightCertification;
    }
}

// ScienceCrew (final specialization)
class ScienceCrew extends SpaceCrew {
    private final String researchSpecialization;

    public ScienceCrew(String crewId, String homeplanet, CrewRank rank, String researchSpecialization) {
        super(crewId, homeplanet, rank);
        this.researchSpecialization = researchSpecialization;
    }

    public String getResearchSpecialization() {
        return researchSpecialization;
    }
}

// EngineerCrew (final certification)
class EngineerCrew extends SpaceCrew {
    private final String engineeringType;

    public EngineerCrew(String crewId, String homeplanet, CrewRank rank, String engineeringType) {
        super(crewId, homeplanet, rank);
        this.engineeringType = engineeringType;
    }

    public String getEngineeringType() {
        return engineeringType;
    }
}

// Final class SpaceStationRegistry
final class SpaceStationRegistry {
    private static List<SpaceCrew> crewList = new ArrayList<>();

    private SpaceStationRegistry() {} // Prevent instantiation

    public static void assignCrew(SpaceCrew crew) {
        if (crewList.size() < SpaceCrew.MAX_CREW_CAPACITY) {
            crewList.add(crew);
            System.out.println("Crew assigned: " + crew.crewId);
        } else {
            System.out.println("Station capacity full!");
        }
    }

    public static void showAllCrew() {
        System.out.println("=== Crew of " + SpaceCrew.STATION_NAME + " ===");
        for (SpaceCrew c : crewList) {
            System.out.println(c);
        }
    }

    public static void handleEmergency() {
        boolean hasPilot = false, hasEngineer = false, hasScientist = false;
        for (SpaceCrew c : crewList) {
            if (c instanceof PilotCrew) hasPilot = true;
            if (c instanceof EngineerCrew) hasEngineer = true;
            if (c instanceof ScienceCrew) hasScientist = true;
        }
        if (hasPilot && hasEngineer && hasScientist) {
            System.out.println("Emergency handled! Crew combination successful.");
        } else {
            System.out.println("Emergency failed! Critical crew missing.");
        }
    }
}

// Main class
public class SpaceStation {
    public static void main(String[] args) {
        // Emergency recruitment
        SpaceCrew cadet = new SpaceCrew("C101");

        // Standard recruitment
        SpaceCrew officer = new SpaceCrew("C102", "Earth", CrewRank.OFFICER);

        // Experienced transfer
        SpaceCrew commander = new SpaceCrew("C103", "Mars", CrewRank.COMMANDER, 15, 80);

        // Specialized crews
        PilotCrew pilot = new PilotCrew("P201", "Venus", CrewRank.CAPTAIN, "Interstellar Flight Cert");
        ScienceCrew scientist = new ScienceCrew("S301", "Europa", CrewRank.OFFICER, "Astrobiology");
        EngineerCrew engineer = new EngineerCrew("E401", "Titan", CrewRank.COMMANDER, "Quantum Systems");

        // Register crew
        SpaceStationRegistry.assignCrew(cadet);
        SpaceStationRegistry.assignCrew(officer);
        SpaceStationRegistry.assignCrew(commander);
        SpaceStationRegistry.assignCrew(pilot);
        SpaceStationRegistry.assignCrew(scientist);
        SpaceStationRegistry.assignCrew(engineer);

        // Show all crew
        SpaceStationRegistry.showAllCrew();

        // Emergency scenario
        SpaceStationRegistry.handleEmergency();

        // Test final methods
        System.out.println(pilot.getCrewIdentification());
        System.out.println("Can be promoted? " + pilot.canBePromoted());
        System.out.println("Experience: " + pilot.calculateSpaceExperience());
    }
}
