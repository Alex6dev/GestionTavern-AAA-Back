package aaa.tavern.dto;

import aaa.tavern.entity.TableRest;

public class TableRestDto {

    private Integer id;

    private Integer numberPlace;

    private Float hygiene;

    private Float posX;

    private Float posY;

    private Integer idPlace;
    
    protected TableRestDto(){

    }
    public TableRestDto(TableRest tableRest) {

        this.id = tableRest.getIdTable();
        this.numberPlace = tableRest.getNumberPlace();
        this.hygiene = tableRest.getHygiene();
        this.posX = tableRest.getPosX();
        this.posY = tableRest.getPosY();
        this.idPlace = tableRest.getPlace().getPlaceId();
    }

    // #region Get

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TableRestDto other = (TableRestDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer getNumberPlace() {
        return numberPlace;
    }

    public Float getHygiene() {
        return hygiene;
    }

    public Float getPosX() {
        return posX;
    }

    public Float getPosY() {
        return posY;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdPlace() {
        return idPlace;
    }
    // #endregion

}
