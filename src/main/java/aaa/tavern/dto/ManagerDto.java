package aaa.tavern.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;

public class ManagerDto {

    protected ManagerDto() {

    }

    public ManagerDto(Manager manager) {
        this.id = manager.getIdManager();
        this.name = manager.getName();
        this.reputation = manager.getReputation();
        this.chest = manager.getChest();
        this.level = manager.getLevel();
        this.experience = manager.getExperience();
        this.idPlayer = manager.getPlayer().getIdPlayer();
        this.maxExp = manager.getMaxExp();
     
        for (InventoryIngredient inventoryIngredient : manager.getInventoryIngredient()) {
            if(inventoryIngredient.getQuantity() > 0){
                Ingredient ingredient = inventoryIngredient.getIngredient();
                Integer quantity = inventoryIngredient.getQuantity();
                this.ingredientQuantity.add(new InventoryManagerIngredientDto(ingredient,quantity));
            }
        }
    }

    private Integer id;

    private String name;

    private Integer reputation;

    private Integer chest;

    private Integer level;

    private Integer experience;

    private Integer idPlayer;

    private Integer maxExp;

    private List<InventoryManagerIngredientDto> ingredientQuantity = new ArrayList<InventoryManagerIngredientDto>();

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
        ManagerDto other = (ManagerDto) obj;
        return Objects.equals(id, other.id);
    }

    // #region get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public Integer getReputation() {
        return reputation;
    }

    public Integer getChest() {
        return chest;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getExperience() {
        return experience;
    }

    public List<InventoryManagerIngredientDto> getIngredientQuantity() {
        return ingredientQuantity;
    }
  

    public Integer getMaxExp() {
        return maxExp;
    }
    // #endregion

}
