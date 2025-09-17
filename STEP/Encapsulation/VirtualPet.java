
package Encapsulation;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

// Immutable PetSpecies
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isEmpty() || evolutionStages == null || evolutionStages.length == 0
                || maxLifespan <= 0 || habitat == null || habitat.isEmpty()) {
            throw new IllegalArgumentException("Invalid PetSpecies data");
        }
        this.speciesName = speciesName;
        this.evolutionStages = evolutionStages.clone();
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return evolutionStages.clone(); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", evolutionStages=" + Arrays.toString(evolutionStages) +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}

// Main VirtualPet
public class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age, happiness, health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg", "Child", "Adult", "Elder"};
    static final int MAX_HAPPINESS = 100, MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    // Constructor chaining
    public VirtualPet() {
        this("Defaulty", new PetSpecies("DefaultSpecies", DEFAULT_EVOLUTION_STAGES, 100, "Forest"), 0, 50, 50);
    }

    public VirtualPet(String name) {
        this(name, new PetSpecies("DefaultSpecies", DEFAULT_EVOLUTION_STAGES, 100, "Forest"), 0, 50, 50);
    }

    public VirtualPet(String name, PetSpecies species) {
        this(name, species, 0, 50, 50);
    }

    public VirtualPet(String name, PetSpecies species, int age, int happiness, int health) {
        this.petId = generatePetId();
        this.species = species;
        this.birthTimestamp = System.currentTimeMillis();
        setPetName(name);
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    // Getters/Setters
    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = validateStat(age, 0, species.getMaxLifespan()); }

    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness, 0, MAX_HAPPINESS); }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health, 0, MAX_HEALTH); }

    // Public interface
    public void feedPet(String foodType) {
        int bonus = calculateFoodBonus(foodType);
        modifyHealth(bonus);
    }

    public void playWithPet(String gameType) {
        int effect = calculateGameEffect(gameType);
        modifyHappiness(effect);
    }

    // Protected internal calculations
    protected int calculateFoodBonus(String foodType) {
        return foodType.equalsIgnoreCase("fruit") ? 10 : 5;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.equalsIgnoreCase("fetch") ? 15 : 7;
    }

    // Private helpers
    private int validateStat(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    private String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private void modifyHappiness(int delta) {
        this.happiness = validateStat(this.happiness + delta, 0, MAX_HAPPINESS);
        checkEvolution();
    }

    private void modifyHealth(int delta) {
        this.health = validateStat(this.health + delta, 0, MAX_HEALTH);
        checkEvolution();
    }

    private void checkEvolution() {
        // Simple placeholder
    }

    // Package-private
    String getInternalState() {
        return "Pet: " + petName + " | Happiness: " + happiness + " | Health: " + health;
    }

    @Override
    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", species=" + species +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet that = (VirtualPet) o;
        return Objects.equals(petId, that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }

    // Demo main
    public static void main(String[] args) {
        PetSpecies dragonSpecies = new PetSpecies("Dragon", new String[]{"Egg", "Hatchling", "Fire Drake"}, 200, "Mountain");
        VirtualPet pet = new VirtualPet("Flamey", dragonSpecies, 5, 70, 80);
        System.out.println(pet);
        pet.feedPet("fruit");
        pet.playWithPet("fetch");
        System.out.println(pet.getInternalState());
    }
}

