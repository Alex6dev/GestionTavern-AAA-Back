package aaa.tavern.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer{


    public Customer() {}
    
    //constructeur pour le service newCustomer in customerManagementService
    public Customer(Integer purseOfGold, 
                    Float happiness, 
                    Float hunger, 
                    Float thirst, 
                    Float nauseaLevel,
                    Float alcoholLevel, 
                    Float toilet, 
                    Time timeInTavern, 
                    Float nauseaTolerance, 
                    Float alcoholTolerance,
                    Boolean gender, 
                    Integer expGiven, 
                    TableRest tableRest, 
                    HashSet<RecipeCustomer> commandList,
                    Timestamp consommationStart
                    ) {
        this.purseOfGold = purseOfGold;
        this.happiness = happiness;
        this.hunger = hunger;
        this.thirst = thirst;
        this.nauseaLevel = nauseaLevel;
        this.alcoholLevel = alcoholLevel;
        this.toilet = toilet;
        this.timeInTavern = timeInTavern;
        this.nauseaTolerance = nauseaTolerance;
        this.alcoholTolerance = alcoholTolerance;
        this.gender = gender;
        this.expGiven = expGiven;
        this.tableRest = tableRest;
        this.commandList = commandList;
        this.consommationStart=consommationStart;
    }

    public Customer(
        Integer purseOfGold, 
        Float happiness, 
        Float hunger, 
        Float thirst, 
        Float nausealevel, 
        Float alcoholLevel, 
        Float toilet, 
        Float nauseaTolerance,
        Float alcoholTolerance,
        Boolean gender,
        Integer expGiven

        )
        {
        this.purseOfGold = purseOfGold;
        this.happiness = happiness;
        this.hunger = hunger;
        this.thirst = thirst;
        this.nauseaLevel = nausealevel;
        this.alcoholLevel = alcoholLevel;
        this.toilet = toilet;
        this.nauseaTolerance = nauseaTolerance;
        this.alcoholTolerance = alcoholTolerance;
        this.gender = gender;
        this.expGiven = expGiven;
        this.commandList = new HashSet<RecipeCustomer>();
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer idCustomer;

    @Column(name = "purse_of_gold")
    private Integer purseOfGold;

    private Float happiness;

    private Float hunger;

    private Float thirst;

    @Column(name="nausea_level")
    private Float nauseaLevel;

    @Column(name = "alcohol_level")
    private Float alcoholLevel;
    
    private Float toilet;

    @Column(name = "time_in_tavern",nullable = true)
    private Time timeInTavern;

    @Column(name = "nausea_tolerance")
    private Float nauseaTolerance;

    @Column(name = "alcohol_tolerance")
    private Float alcoholTolerance;
    
    private Boolean gender;

    @Column(name = "exp_given")
    private Integer expGiven;

    @ManyToOne
    @JoinColumn(name = "table_rest_id",nullable = true)
    private TableRest tableRest;

    @OneToMany(mappedBy = "customer")
    private Set<RecipeCustomer> commandList=new HashSet<RecipeCustomer>();
    
    @Column(name="consommation_start")
    private Timestamp consommationStart;
    
    /**
	 * Deux Customer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Customer that = (Customer) o;
        return Objects.equals(idCustomer, that.idCustomer);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return idCustomer.hashCode();
    }
    // #region Get/Set
    public Integer getIdCustomer() {
        return idCustomer;
    }
    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getPurseOfGold() {
        return purseOfGold;
    }

    public void setPurseOfGold(Integer purseOfGold) {
        this.purseOfGold = purseOfGold;
    }


    public Float getHappiness() {
        return happiness;
    }


    public void setHappiness(Float happiness) {
        this.happiness = happiness;
    }


    public Float getHunger() {
        return hunger;
    }


    public void setHunger(Float hunger) {
        this.hunger = hunger;
    }


    public Float getThirst() {
        return thirst;
    }


    public void setThirst(Float thirst) {
        this.thirst = thirst;
    }


    public Float getNauseaLevel() {
        return nauseaLevel;
    }


    public void setNauseaLevel(Float nauseaLevel) {
        this.nauseaLevel = nauseaLevel;
    }


    public Float getAlcoholLevel() {
        return alcoholLevel;
    }


    public void setAlcoholLevel(Float alcoholLevel) {
        this.alcoholLevel = alcoholLevel;
    }


    public Float getToilet() {
        return toilet;
    }


    public void setToilet(Float toilet) {
        this.toilet = toilet;
    }


    public Time getTimeInTavern() {
        return timeInTavern;
    }


    public void setTimeInTavern(Time timeInTavern) {
        this.timeInTavern = timeInTavern;
    }


    public Float getNauseaTolerance() {
        return nauseaTolerance;
    }


    public void setNauseaTolerance(Float nauseaTolerance) {
        this.nauseaTolerance = nauseaTolerance;
    }


    public Float getAlcoholTolerance() {
        return alcoholTolerance;
    }


    public void setAlcoholTolerance(Float alcoholTolerance) {
        this.alcoholTolerance = alcoholTolerance;
    }


    public Boolean getGender() {
        return gender;
    }


    public void setGender(Boolean gender) {
        this.gender = gender;
    }


    public Integer getExpGiven() {
        return expGiven;
    }


    public void setExpGiven(Integer expGiven) {
        this.expGiven = expGiven;
    }


    public TableRest getTableRest() {
        return tableRest;
    }


    public void setTableRest(TableRest tableRest) {
        this.tableRest = tableRest;
    }


    public Set<RecipeCustomer> getCommandList() {
        return commandList;
    }


    public void setCommandList(Set<RecipeCustomer> commandList) {
        this.commandList = commandList;
    }

    public Timestamp getConsommationStart() {
        return consommationStart;
    }

    public void setConsommationStart(Timestamp consommationStart) {
        this.consommationStart = consommationStart;
    }

    // #endregion
}
