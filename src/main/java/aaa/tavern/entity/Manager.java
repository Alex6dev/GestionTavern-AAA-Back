package aaa.tavern.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idManager;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "reputation")
    private Integer reputation;

    @Column(name = "chest")
    private Integer chest;

    @Column(name = "level")
    private Integer level;

    @Column(name = "experience")
    private Integer experience;

    @ManyToOne()
    @JoinColumn(name = "player_id")
    private Player player;

    @Transient
    private Integer maxExp;

    // inventaire BDD
    @OneToMany
    @JoinColumn(name = "manager_id", nullable = true)
    @NotFound(
    action = NotFoundAction.IGNORE)
    private List<InventoryIngredient> inventoryIngredient = new ArrayList<InventoryIngredient>();

    // inventaire jeu
    @Transient
    private Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();

    public Manager() {

    }

    public Manager(
            String name,
            Integer reputation,
            Integer chest,
            Integer level,
            Integer experience,
            Player player) {
        this.name = name;
        this.reputation = reputation;
        this.chest = chest;
        this.level = level;
        this.experience = experience;
        this.player = player;
    }

    @PostLoad
    private void transformForIngredientQuantity() {
        getCalcMaxExp();

        Map<Ingredient, Integer> tab = new HashMap<Ingredient, Integer>();
        for (InventoryIngredient inventoryIngredient : this.inventoryIngredient) {
            Ingredient ingredient = inventoryIngredient.getIngredient();
            Integer quantity = inventoryIngredient.getQuantity();
            tab.put(ingredient, quantity);
        }
        this.ingredientQuantity = tab;
    }

    @PrePersist
    @PreUpdate
    private void transformForIngredientIngredient() {
        if(this.inventoryIngredient != null){
            for(InventoryIngredient ivig : this.inventoryIngredient){
                ivig.setQuantity(this.ingredientQuantity.get(ivig.getIngredient()));
            }
        }
        /*
        this.inventoryIngredient.clear();
        for (Ingredient ingredient : this.ingredientQuantity.keySet()) {
            Integer quantity = this.ingredientQuantity.get(ingredient);
            
            InventoryIngredient inventoryIngredient = new InventoryIngredient(this, ingredient, quantity);
            this.inventoryIngredient.add(inventoryIngredient);
        }*/
    }

    private void getCalcMaxExp() {
        this.maxExp = this.level * 10;
    }

    /**
     * Deux Managers sont les mêmes si ils ont le même identifiant.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Manager that = (Manager) o;
        return Objects.equals(idManager, that.idManager);
    }

    /**
     * L'identifiant définit le hash.
     */
    @Override
    public int hashCode() {
        return idManager.hashCode();
    }

    // #region get/set
    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getChest() {
        return chest;
    }

    public void setChest(Integer chest) {
        this.chest = chest;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
        getCalcMaxExp();
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        if(this.maxExp<=experience){
            this.level+=1;
            this.experience=experience-maxExp;
        }else{
            this.experience=experience;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }

    public void addIngredientQuantity(Ingredient ingredient) {
        Integer quantity = ingredientQuantity.get(ingredient);
        if (quantity != null) {
            quantity++;
            ingredientQuantity.replace(ingredient, quantity);
        } else {
            ingredientQuantity.put(ingredient, 1);
        }
    }

    public Map<Ingredient, Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(Map<Ingredient, Integer> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public List<InventoryIngredient> getInventoryIngredient() {
        return inventoryIngredient;
    }

    public void setInventoryIngredient(List<InventoryIngredient> inventoryIngredient) {
        this.inventoryIngredient = inventoryIngredient;
    }

    // #endregion

}