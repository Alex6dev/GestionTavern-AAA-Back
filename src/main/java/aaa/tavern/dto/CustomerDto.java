package aaa.tavern.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;

public class CustomerDto {

    private Integer id;

    private Integer purseOfGold;

    private Float happiness;

    private Float hunger;

    private Float thirst;

    private Float nauseaLevel;

    private Float alcoholLevel;

    private Float toilet;

    private Time timeInTavern;

    private Float nauseaTolerance;

    private Float alcoholTolerance;

    private Boolean gender;

    private Integer expGiven;

    private Integer idTableRest;

    private Long consommationStart;

    private List<RecipeCustomerDto> commandList = new ArrayList<RecipeCustomerDto>();

    protected CustomerDto(){
        
    }
    public CustomerDto(Customer customer) {
        this.id = customer.getIdCustomer();
        this.purseOfGold = customer.getPurseOfGold();
        this.happiness = customer.getHappiness();
        this.hunger = customer.getHunger();
        this.thirst = customer.getThirst();
        this.nauseaLevel = customer.getNauseaLevel();
        this.alcoholLevel = customer.getAlcoholLevel();
        this.toilet = customer.getToilet();
        this.timeInTavern = customer.getTimeInTavern();
        this.nauseaTolerance = customer.getNauseaTolerance();
        this.alcoholTolerance = customer.getAlcoholTolerance();
        this.gender = customer.getGender();
        this.expGiven = 0;

        if(customer.getTableRest()!=null)
            this.idTableRest=customer.getTableRest().getIdTable();
        else
            this.idTableRest=null;

        if(customer.getConsommationStart()!=null)
            this.consommationStart = customer.getConsommationStart().getTime();
        else
            this.consommationStart=null;  

        for (RecipeCustomer recipeCustomer : customer.getCommandList()) {
            Long dateTime = null ;
            if(recipeCustomer.getRecipeStart() != null){
                dateTime = recipeCustomer.getRecipeStart().getTime() ;
            }

            RecipeCustomerDto recipeCustomerDto = new RecipeCustomerDto(
                recipeCustomer.getCustomer().getIdCustomer(), 
                recipeCustomer.getRecipe().getName(), 
                recipeCustomer.getRecipe().getId(),
                dateTime);
                
            this.commandList.add(recipeCustomerDto);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerDto other = (CustomerDto) obj;
        return Objects.equals(id, other.id);
    }

    // #region
    public Integer getPurseOfGold() {
        return purseOfGold;
    }

    public Float getHappiness() {
        return happiness;
    }

    public Float getHunger() {
        return hunger;
    }

    public Float getThirst() {
        return thirst;
    }

    public Float getNauseaLevel() {
        return nauseaLevel;
    }

    public Float getAlcoholLevel() {
        return alcoholLevel;
    }

    public Float getToilet() {
        return toilet;
    }

    public Time getTimeInTavern() {
        return timeInTavern;
    }

    public Float getNauseaTolerance() {
        return nauseaTolerance;
    }

    public Float getAlcoholTolerance() {
        return alcoholTolerance;
    }

    public Boolean getGender() {
        return gender;
    }

    public Integer getExpGiven() {
        return expGiven;
    }

    public Integer getIdTableRest() {
        return idTableRest;
    }

    public Long getConsommationStart() {
        return consommationStart;
    }

    public Integer getId() {
        return id;
    }
    public List<RecipeCustomerDto> getCommandList() {
        return commandList;
    }

    // #endregion
}
