package aaa.tavern.dto;

import aaa.tavern.entity.Place;

public class PlaceDto {

    private Integer id;

    private String name;

    private Integer type;

    private Integer level;
    
    protected PlaceDto(){

    }
    public PlaceDto(Place place) {
        this.id = place.getPlaceId();
        this.name = place.getName();
        this.type = place.getType();
        this.level = place.getLevel();
    }

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
        PlaceDto other = (PlaceDto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    // #region Get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }
    // #endregion
}
